package com.sandalen.jwts.scala

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.classification.{LogisticRegression, RandomForestClassifier}
import org.apache.spark.ml.feature.{IndexToString, StringIndexer, VectorIndexer}
import org.apache.spark.sql.SparkSession

object LrForMultiClassifier {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName(s"${this.getClass.getSimpleName}").setMaster("local[4]")
    val sc = new SparkContext(conf)
    val spark = SparkSession.builder().getOrCreate()
    val dataPath = "C:\\Users\\dell\\Desktop\\spark-master\\data\\mllib\\sample_multiclass_classification_data.txt"

    randomForest(sc,spark,dataPath)

  }

  def lr(spark:SparkSession,input_path:String) = {
    val data = spark.read.format("libsvm").load(input_path)
    val lr = new LogisticRegression().setMaxIter(10).setRegParam(0.3).setElasticNetParam(0.8)

    val model = lr.fit(data)
  }

  def randomForest(sc:SparkContext,spark:SparkSession,input_path:String) = {
    val data = spark.read.format("libsvm").load(input_path)

    val labelIndexer = new StringIndexer()
      .setInputCol("label")
      .setOutputCol("indexedLabel")
      .fit(data)

    val featureIndexer = new VectorIndexer()
      .setInputCol("feature")
      .setOutputCol("indexedFeature")
      .setMaxCategories(3)
      .fit(data)

    val rf = new RandomForestClassifier()
      .setLabelCol("indexedLabel")
      .setFeaturesCol("indexedFeature")
      .setNumTrees(10)

    val labelConverter = new IndexToString()
      .setInputCol("prediction")
      .setOutputCol("predictionLabel")
      .setLabels(labelIndexer.labels)

    val pipeline = new Pipeline()
      .setStages(Array(labelIndexer,featureIndexer,rf,labelConverter))

    val model = pipeline.fit(data)
    saveModel(sc,model,"C:\\Users\\dell\\Desktop\\hadoop\\randomForestModelForiris\\model")
    println("==================execute end========================")

  }

  def saveModel(sc:SparkContext,model:Any,path:String) = {
    sc.parallelize(Seq(model),1).saveAsObjectFile(path)
  }
}
