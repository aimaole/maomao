package com.maomao.sparkWork.scala

import org.apache.spark.sql.SparkSession

object sparksqltext {
  def main(args: Array[String]): Unit = {
    val sparkSession = SparkSession.builder.master("local").appName("sparksqlexample")
      .getOrCreate()
    val tt = sparkSession.read.json("E://sparksql.txt")
    tt.createOrReplaceTempView {
      "user"
    }
    tt.show()
    sparkSession.sql("select name from user where id='1' or id = '3'").show()
    sparkSession.stop()


  }

}
