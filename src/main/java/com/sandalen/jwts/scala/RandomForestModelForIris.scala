package com.sandalen.jwts.scala

import org.apache.spark.ml.PipelineModel
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SparkSession
class RandomForestModelForIris{

}
object RandomForestModelForIris {
  def main(args: Array[String]): Double = {
    val conf = new SparkConf().setAppName("isoForest").setMaster("local[4]")
    val sc = new SparkContext(conf)
    val spark = SparkSession.builder().config(conf).getOrCreate()
    val mutiClassifierData = "C:\\Users\\dell\\Desktop\\spark-master\\data\\mllib\\sample_multiclass_classification_data.txt"


    val model = PipelineModel.load("C:\\Users\\dell\\Desktop\\hadoop\\randomForestModelForiris\\model")
    val tstData = spark.read.format("libsvm").load(mutiClassifierData)
    val predictions = model.transform(tstData)

    // Select example rows to display.
    predictions.select("predictionLabel", "label", "features").show(100)

    // Select (prediction, true label) and compute test error.
    val evaluator = new MulticlassClassificationEvaluator()
      .setLabelCol("indexedLabel")
      .setPredictionCol("prediction")
      .setMetricName("accuracy")
    val accuracy = evaluator.evaluate(predictions)
    println(s"Test Error = ${(1.0 - accuracy)}")

    accuracy

  }

  def tst(): Unit ={
    println("hello")
  }


}
