package com.maomao.sparkWork.scala

object ScalaWordCount {
  def main(args: Array[String]) {
    //    if (args.length < 1) {
    //      System.err.println("Usage: <file>")
    //      System.exit(1)
    //    }

    //    val conf = new SparkConf()
    //    val sc = new SparkContext(conf)
    //    var line = sc.textFile(args(0))
    //    line.flatMap(_.split(" ")).map((_, 1)).reduceByKey(_+_).collect().foreach(println)
    val lines = List("hello tom hello jerry", "hello tom hello kitty hello china")
    //    方法一:
    //    val wc = lines.flatMap(_.split(" ")).map((_, 1)).groupBy(_._1).map(t => (t._1, t._2.size)).toList.sortBy(_._2).reverse
    //    方法二：
    //    val wc2 = lines.flatMap(_.split(" ")).map((_, 1)).groupBy(_._1).mapValues(_.size)
    val wc2 = lines.flatMap(_.split(" ")).map((_, 1))
    val wc22 = lines.flatMap(_.split(" ")).map((_, 1)).groupBy(_._1)
    //    方法三：
    //    val wc3 = lines.flatMap(_.split(" ")).map((_, 1)).groupBy(_._1).mapValues(_.foldLeft(0)(_ + _._2))
    //    如果是在spark上：
    //    val wc4 = lines.flatMap(_.split(" ")).map((_, 1)).reduceByKey(_ + _).sortBy(_._2, false).collect
    println(wc2)
    println(wc22)
    //    sc.stop()
  }
}
