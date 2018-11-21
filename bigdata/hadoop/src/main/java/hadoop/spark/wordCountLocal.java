package hadoop.spark;

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
        SparkConf conf = new SparkConf().setMaster("local").setAppName("wordcount");
        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<String> lines = sc.textFile(args[0]);
        lines.flatMap(f -> {
            return Arrays.asList(f.split(" ")).iterator();

        }).mapToPair(f -> {
            return new Tuple2<String, Integer>(f, 1);
        }).reduceByKey((a,b) ->{
            return a+b;
        }).foreach(f->{
            System.out.println(f._1+"---------"+f._2);
        });

        sc.close();
    }
}
