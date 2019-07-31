package com.sandalen.jwts.scala

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD
import org.apache.spark.storage.StorageLevel

import scala.util.Random
import org.apache.hadoop.fs._

sealed trait ITree

case class ITreeBranch(left: ITree, right: ITree, split_column: Int, split_value: Double) extends ITree

case class ITreeLeaf(size: Long) extends ITree

/** @param trees      trained trees
  * @param maxSamples The number of samples to train each base tree
  */
case class iForest(trees: Array[ITree], maxSamples: Int) {

  def predict(x: Array[Double]) = {
    if (trees.forall(_ == null)) {
      throw new Exception("Please train before predict!!")
    } else {
      val predictions = trees.map(s => pathLength(x, s, 0)).toList
      math.pow(2, -(predictions.sum / predictions.size) / cost(maxSamples))
//      if(score>0.6){
//        List(score,1)
//      }else{
//        List(score,0)
//      }
    }
  }

  @scala.annotation.tailrec
  final def pathLength(x: Array[Double], tree: ITree, path_length: Int): Double = {
    tree match {
      case ITreeLeaf(size) =>
        path_length + cost(size)

      case ITreeBranch(left, right, split_column, split_value) =>
        val sample_value = x(split_column)
        if (sample_value < split_value)
          pathLength(x, left, path_length + 1)
        else
          pathLength(x, right, path_length + 1)
    }
  }

  private def cost(num_items: Long): Double =
    if (num_items <= 1) 1.0 else 2.0 * (math.log(num_items - 1.0) + 0.577215664901532860606512090082402431) - (2.0 * (num_items - 1.0) / num_items)
}

object iForest {

  /**
    * @param numTrees    The number of base tree in the ensemble
    * @param maxSamples  The number of samples to train each base tree ,should be small!! should be small!! should be small!!
    *                    should be small!! should be small!! should be small!!
    * @param maxFeatures The fraction of features to train each base tree value in (0.0,1.0]
    *                    //    * @param withReplacement whether sampling is done with replacement, do something in future
    * @param nJobs       The number of jobs to run in parallel for fit ,do something in future
    */
  def buildForest(data: RDD[Array[Double]], numTrees: Int = 100, maxSamples: Int = 256, maxFeatures: Double = 1.0, nJobs: Int = 10) = {
    val sc = data.sparkContext
    val cacheData = if (sc.getRDDStorageInfo.filter(_.id == data.id).nonEmpty) data else data.persist(StorageLevel.MEMORY_AND_DISK)

    val dataCnt = data.count()
    println("count is " + dataCnt)
    println("cacheData is "+cacheData.take(1)(0).size + "===============================")
    //[[1,0,2,0,0,0]]
    val numFeatures = cacheData.take(1)(0).size
    checkData(cacheData, numFeatures)
    //256
    val sampleNumSamples = Math.min(maxSamples, dataCnt).toInt
    val sampleNumFeatures = (maxFeatures * numFeatures).toInt
    //loge256/loge2 => log 2 256
    val maxDepth = Math.ceil((math.log(math.max(sampleNumSamples, 2)) / math.log(2))).toInt

    //抽样比率需要小于1
    val sampleRatio = Math.min(sampleNumSamples * 1.0 / dataCnt * 2, 1.0)
    //Iterator[RDD[ITree]] --> Iterator[Array[ITree]] --> Array[ITree]
    val trees =
      (0 until numTrees).sliding(nJobs, nJobs).map {
        arr =>

          sc.union(
            arr.map {
              i =>
                //抽样,然后zipWithIndex 格式(x1,0),(x2,1) .... (x,maxSample) 再过滤出来子sample数量的数据
                cacheData.sample(false, sampleRatio, System.currentTimeMillis()).zipWithIndex().filter(_._2 <= sampleNumSamples)
                  .map(_._1).repartition(1).mapPartitions {
                  iter =>
                    //double[257]
                    val delta = iter.toArray
                    val sampleFeatures = if (sampleNumFeatures < numFeatures) Random.shuffle((0 until numFeatures).toList).take(sampleNumFeatures) else (0 until numFeatures).toList

                    Iterator(growTree(delta, maxDepth, sampleFeatures, 0))
                }
            }
          ).collect()

          //Array[ITree]
      }.reduce(_ ++ _)

    new iForest(trees, maxSamples)
  }

  def saveModel(sc: SparkContext, iforest: iForest, path: String) = {
//    val hdfs = FileSystem.get(sc.hadoopConfiguration)
//    hdfs.delete(new Path(path), true)
    sc.parallelize(Seq(iforest), 1).saveAsObjectFile(path)
  }

  def loadModel(sc: SparkContext, path: String) = {
    sc.objectFile[iForest](path).collect()(0)
  }

  private def growTree(data: Array[Array[Double]], maxDepth: Int, sampleFeatures: Seq[Int], currentDepth: Int): ITree = {
    val numSamples = data.length
    if (currentDepth >= maxDepth || numSamples <= 1 || data.distinct.length == 1) {
      new ITreeLeaf(numSamples)
    } else {
      val splitColumn = sampleFeatures(Random.nextInt(sampleFeatures.length))
      val columnValue = data.map(_.apply(splitColumn))
      val colMin = columnValue.min
      val colMax = columnValue.max
      val splitValue = colMin + Random.nextDouble() * (colMax - colMin)
      val dataLeft = data.filter(_ (splitColumn) < splitValue)
      val dataRight = data.filter(_ (splitColumn) >= splitValue)
      new ITreeBranch(growTree(dataLeft, maxDepth, sampleFeatures, currentDepth + 1),
        growTree(dataRight, maxDepth, sampleFeatures, currentDepth + 1),
        splitColumn, splitValue)
    }
  }

  private def checkData(data: RDD[Array[Double]], numFeatures: Int) = {
    //如果存在某行数据属性列数不等于特征数，则报错
    //condition为false，报错
    assert(data.filter(arr => !(arr.length == numFeatures)).isEmpty(), "data must in equal column size")
  }

  def main(args: Array[String]): Unit = {
    //加载数据
    val conf = new SparkConf().setAppName("isoForest").setMaster("local[4]")
    val sc = new SparkContext(conf)
    val lines = sc.textFile("C:/Users/dell/Desktop/spark_if_train.csv")
    val data = lines.map(line => line.split(",")).map(s => s.slice(1,s.length))
    val header = data.first()
    //[[1,2,3,4],[5,6,7,8]]
    //[1,2,3,4]
    val rows = data.filter(line => line(0) != header(0)).map(s => s.map(_.toDouble))
    //训练模型
    val forest = buildForest(rows, numTrees=10)
    //保存模型
    saveModel(sc,forest,"C:/Users/dell/Desktop/hadoop/isoForest/model/forest")
    //加载模型
    val saveforest = loadModel(sc,"C:/Users/dell/Desktop/hadoop/isoForest/model/forest")
    //使用训练后的模型直接检测
    val result_rdd = rows.map(row => row ++ Array(forest.predict(row))).cache()
    //使用保存的模型来检测
    val result_rdd_save = rows.map(row => row ++ Array(saveforest.predict(row))).cache()
    //保存模型
    result_rdd.map(lines => lines.mkString(",")).repartition(1).saveAsTextFile("C:/Users/dell/Desktop/hadoop/isoForest/test_predict_labels")
    result_rdd_save.map(lines => lines.mkString(",")).repartition(1).saveAsTextFile("C:/Users/dell/Desktop/hadoop/isoForest/test_predict_labels_save")
  }

}