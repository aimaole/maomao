package com.maomao.app.flink.dataset

import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.api.scala._

object WordCountScala{
  def main(args: Array[String]) {
    //初始化环境
    val env = ExecutionEnvironment.getExecutionEnvironment
    //从字符串中加载数据
    val text = env.fromElements(
      "Who's there?",
      "I think I hear them. Stand, ho! Who's there?")
    //分割字符串、汇总tuple、按照key进行分组、统计分组后word个数
    val counts = text.flatMap { _.toLowerCase.split("\\W+") filter { _.nonEmpty } }
      .map { (_, 1) }
      .groupBy(0)
      .sum(1)
    //打印
    counts.print()
  }
}