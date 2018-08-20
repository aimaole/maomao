package com.maomao

import org.apache.spark.{SparkConf, SparkContext}

object WordCount {
  def main(args: Array[String]): Unit = {
    //创建SparkConf()并且设置App的名称
    val conf = new SparkConf().setAppName("wordCount")
    //创建SparkContext,该对象是提交spark app的入口
    val sc = new SparkContext(conf)
    //使用sc创建rdd,并且执行相应的transformation和action
    sc.textFile(args(0)).flatMap(_.split(" ")).map((_ ,1)).reduceByKey(_ + _,1).sortBy(_._2,false).saveAsTextFile(args(1))
    //停止sc，结束该任务
    sc.stop()
  }
}
