package com.maomao.sparkWork.java;

import java.util.Arrays;
import java.util.Iterator;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;

import scala.Tuple2;

public class wordCountLocal {

	public static void main(String[] args) {
		SparkConf conf = new SparkConf().setAppName("wordCount").setMaster("local");
		JavaSparkContext sc = new JavaSparkContext(conf);
		JavaRDD<String> lines = sc.textFile("E://test.txt");
		JavaRDD<String> words = lines.flatMap(new FlatMapFunction<String, String>() {
			private static final long serialVersionUID = 1L;
			public Iterator<String> call(String arg0) throws Exception {
				return Arrays.asList(arg0.split(" ")).iterator();
			}
		});
		JavaPairRDD<String, Integer> parirs = words.mapToPair(new PairFunction<String, String, Integer>() {
			private static final long serialVersionUID = 1L;

			public Tuple2<String, Integer> call(String arg0) throws Exception {
				return new Tuple2<String, Integer>(arg0, 1);
			}
		});
		JavaPairRDD<String, Integer> wordcount = parirs.reduceByKey(new Function2<Integer, Integer, Integer>() {
			private static final long serialVersionUID = 1L;
			public Integer call(Integer arg0, Integer arg1) throws Exception {
				return arg0 + arg1;
			}
		});
		
		wordcount.foreach(new VoidFunction<Tuple2<String, Integer>>() {
			private static final long serialVersionUID = 1L;
			public void call(Tuple2<String, Integer> arg0) throws Exception {
				System.out.println(arg0._1 + " 出现了：" + arg0._2 + "次");
			}
		});
		sc.close();
	}
}
