package com.sandalen.jwts.utils

object NBF {
  def toInt(str:String):Int = {
    str.toInt
  }

  def toDouble(str:Any):Double = {
    str.toString.toDouble
  }
}
