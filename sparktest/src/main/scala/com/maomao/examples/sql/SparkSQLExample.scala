package com.maomao.examples.sql

import org.apache.spark.sql.Row
// $example on:init_session$
import org.apache.spark.sql.SparkSession
// $example off:init_session$
// $example on:programmatic_schema$
// $example on:data_types$
import org.apache.spark.sql.types._

object SparkSQLExample {

  case class Person(name: String, age: Long)

  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .appName("Spark SQL basic example")
      .master("local[4]")
      .getOrCreate()

    runBasicDataFrameExample(spark)
    spark.stop()
  }
  private def runBasicDataFrameExample(spark: SparkSession):Unit = {
    val df =spark.read.json("src/main/resources/people.json")
    df.show()

    df.printSchema()
    df.select("name").show()
    df.select("name","age").show()
    import spark.implicits._
    df.select($"name", $"age" + 1).show()

  }
}
