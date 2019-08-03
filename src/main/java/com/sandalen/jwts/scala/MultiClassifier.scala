package com.sandalen.jwts.scala

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.ml.classification.{LogisticRegression, OneVsRest}
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession


object MultiClassifier {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setAppName("MultiClassifier").setMaster("local[4]")
    val sc = new SparkContext(conf)
    sc.setLogLevel("ERROR")
    val session = SparkSession.builder().getOrCreate()


    val data = sc.textFile("C:\\Users\\dell\\Desktop\\moment.csv")
//
//    val rows1 = data.map(lines => lines.split(","))
//      .map(lines => lines.slice(2,lines.length))
//    val header1 = rows1.first()
//    val features = rows1.filter(lines => lines(0) != header1(0))
//
//    val rows2 = data.map(lines => lines.split(","))
//      .map(lines => lines(0))
//    val header2 = rows2.first()
//    val label = rows2.filter(lines => lines(0) != header2(0))
//    //(String,Array(String))
//    val realData = label.zip(features).map(lines => {
//      List(lines._1) ++ lines._2
//    }).map(lines => lines.map( s => s.toDouble))
//
//    val Array(train_data,test_data) = realData.randomSplit(Array(0.6,0.4))

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

    import session.implicits._

    val train = session.createDataset(train_data)
    val test = session.createDataset(test_data)


//    val inputData = session.read.format("libsvm").load("C:\\Users\\dell\\Desktop\\sample_libsvm_data.txt")
//
////    val Array(train,test) = inputData.randomSplit(Array(0.6,0.4))
//
    val lr = new LogisticRegression().setMaxIter(10)
      .setRegParam(0.3)
      .setElasticNetParam(0.2)
      .setThreshold(0.5)

    val model = new OneVsRest()
      .setClassifier(lr)
      .fit(train)

    val predictions = model.transform(test)
    println(predictions)

    val evaluator = new MulticlassClassificationEvaluator().setMetricName("accuracy")

    val accuracy = evaluator.evaluate(predictions)

    println(s"Tess error : ${1-accuracy}")
  }

}
