package com.maomao.app

import com.maomao.sparkWork.scala.WordCount
import org.apache.hadoop.util.ProgramDriver

class SparkAppDriver {
  def main(args: Array[String]): Unit = {
    val pgd = new ProgramDriver
    pgd.addClass("test",WordCount.getClass,"test WorldCount")
  }

}
