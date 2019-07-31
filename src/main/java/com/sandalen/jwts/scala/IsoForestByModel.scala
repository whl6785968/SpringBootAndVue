package com.sandalen.jwts.scala

import java.util.Properties

import com.sandalen.jwts.scala.bean.DataPoint
import com.sandalen.jwts.scala.iForest.loadModel
import com.typesafe.config.ConfigFactory
import org.apache.spark.sql.{SaveMode, SparkSession}
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

    val dataPoint = result_rdd_save.map(arr => DataPoint(arr))

    val df = sqlContext.createDataFrame(dataPoint)

    val load =ConfigFactory.load()
    val props = new Properties()

    props.setProperty("user",load.getString("jdbc.user"))
    props.setProperty("password",load.getString("jdbc.password"))
    df.write.mode(SaveMode.Overwrite).jdbc(load.getString("jdbc.url"),load.getString("jdbc.tableName"),props)
    sc.stop()

//    result_rdd_save.map(lines => lines.mkString(",")).repartition(1).saveAsTextFile("C:/Users/dell/Desktop/hadoop/isoForest/test_predict_labels_save")
  }
}
