package com.sandalen.jwts.scala

import breeze.linalg.DenseVector
import org.apache.log4j.{Level, Logger}
import org.apache.spark.mllib.classification.SVMWithSGD
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.{SparkConf, SparkContext}

object svm {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setAppName("svm").setMaster("local[4]")

    val sc = new SparkContext(conf)
    sc.setLogLevel("ERROR")
    val data = sc.textFile("C:\\Users\\dell\\Desktop\\moment 2.csv")

    val  rows = data.map(lines => lines.split(",")).map(s => s.slice(2,s.length))
    val header = rows.first()

    val fetures = rows.filter(line => line(0) != header(0)).map(s => s.map(_.toDouble))

    val rows2 = data.map(lines => lines.split(",")).map(s => s(0))
    val header2 = rows2.first()
    val label = rows2.filter(s => s != header2).map(s => s.toInt)

    val processedData = label.zip(fetures)
    val use_data = processedData.map(line => LabeledPoint(line._1,Vectors.dense(line._2)))

    val splits = use_data.randomSplit(Array(0.6,0.4),seed = 1L)
    val train_data = splits(0).cache()
    train_data.collect().foreach(println)
    val test_data = splits(1)

    val numIter = 100
    val model = SVMWithSGD.train(train_data,numIter)

    val predictAndLabel = test_data.map(point => {
      val score = model.predict(point.features)
      (score,point.label)
    })

    val print_predict = predictAndLabel.take(20)
    print_predict.foreach(println)

    println("prediction" + "\t" + "label============================================================")
    for(i <- 0 to print_predict.length-1){
      println(print_predict(i)._1 + "\t" +print_predict(i)._2)
    }

    val accuracy = 1.0*predictAndLabel.filter(x => x._1 == x._2).count()/test_data.count()
    println("Area under Roc = " + accuracy)


  }
}
