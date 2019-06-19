package com.maomao.sparkwork

import java.util.Properties

import org.apache.hadoop.hbase.{HBaseConfiguration, HConstants}
import org.apache.hadoop.hbase.client.{Put, Result}
import org.apache.hadoop.hbase.io.ImmutableBytesWritable
import org.apache.hadoop.hbase.mapreduce.TableInputFormat
import org.apache.hadoop.hbase.util.Bytes
import org.apache.spark.{SparkConf, SparkContext}

object sparkOnHbase {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local").setAppName("hbaseTest")
    val sc = new SparkContext(sparkConf)
    val hconf = HBaseConfiguration.create()
    val zooKeeper = "127.0.0.1:2181"
    hconf.set(HConstants.ZOOKEEPER_QUORUM, zooKeeper)
    hconf.set(TableInputFormat.INPUT_TABLE, "tableName")
    hconf.set(TableInputFormat.SCAN_ROW_START, "COHUTTA 3/10/14")
    hconf.set(TableInputFormat.SCAN_ROW_STOP, "COHUTTA 3/11/14")
    hconf.set(TableInputFormat.SCAN_COLUMNS, "data:psi")

    val hbaseRDD = sc.newAPIHadoopRDD(hconf, classOf[TableInputFormat], classOf[ImmutableBytesWritable], classOf[Result])
    hbaseRDD.count()
    val rowKeyRDD = hbaseRDD.map(tuple=>tuple._1).map(item=>Bytes.toString(item.get()))
    rowKeyRDD.take(3).foreach(println)
    val resultRDD = hbaseRDD.map(tuple=>tuple._2)
    resultRDD.count()



  }

}
