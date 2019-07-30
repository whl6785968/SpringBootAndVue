package com.sandalen.jwts.scala.bean

import com.sandalen.jwts.utils.NBF

class DataPoint(val data0:Double,val data1:Double,val data2:Double,val data3:Double,val data4:Double,val data5:Double,val score:Double) extends Product with Serializable {
  override def productElement(n: Int): Any = n match {
    case 0 => data1
    case 1 => data2
    case 2 => data2
    case 3 => data3
    case 4 => data4
    case 5 => data5
    case 6 => score
  }

  override def productArity: Int = 7

  override def canEqual(that: Any): Boolean = that.isInstanceOf[DataPoint]
}

object DataPoint {
  def apply(arr:Array[String]):DataPoint = new DataPoint(
    NBF.toDouble(arr(0)),
    NBF.toDouble(arr(1)),
    NBF.toDouble(arr(2)),
    NBF.toDouble(arr(3)),
    NBF.toDouble(arr(4)),
    NBF.toDouble(arr(5)),
    NBF.toDouble(arr(6))
  )
}
