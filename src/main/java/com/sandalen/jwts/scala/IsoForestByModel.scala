package com.sandalen.jwts.scala

import com.sandalen.jwts.scala.iForest.loadModel
import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}

class IsoForestByModel {
}

object IsoForestByModel {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("isoForest").setMaster("local[4]")
    val sc = new SparkContext(conf)
    val sqlContext = SparkSession.builder().config(conf).getOrCreate()


    val isoforest = loadModel(sc,"C:/Users/dell/Desktop/hadoop/isoForest/model/forest")

    val lines = sc.textFile("C:/Users/dell/Desktop/spark_if_train.csv")
    val data = lines.map(line => line.split(",")).map(s => s.slice(1,s.length))
    val header = data.first()
    val rows = data.filter(line => line(0) != header(0)).map(s => s.map(_.toDouble))

    val result_rdd_save = rows.map(row => row ++ Array(isoforest.predict(row))).cache()



    result_rdd_save.map(lines => lines.mkString(",")).repartition(1).saveAsTextFile("C:/Users/dell/Desktop/hadoop/isoForest/test_predict_labels_save")
  }
}
