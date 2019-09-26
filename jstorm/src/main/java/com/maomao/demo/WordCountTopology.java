package com.maomao.demo;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;

public class WordCountTopology {
    public static void main(String[] args) throws Exception {
        /**第一步，设计一个Topolgy*/
        TopologyBuilder builder = new TopologyBuilder();
        /*
         * 设置spout和bolt,完整参数为
         * 1,spout的id(即name)
         * 2,spout对象
         * 3,executor数量即并发数，也就是设置多少个executor来执行spout/bolt(此项没有默认null)
         */
        //setSpout
        builder.setSpout("sentence-spout",new RandomSentenceSpout(),1);
        //setBolt:SplitBolt的grouping策略是上层随机分发，CountBolt的grouping策略是按照上层字段分发
        //如果想要从多个Bolt获取数据，可以继续设置grouping
        builder.setBolt("split-bolt", new SplitBolt(),1)
            .shuffleGrouping("sentence-spout");
        builder.setBolt("count-bolt", new CountBoltB(),1)
            .fieldsGrouping("split-bolt", new Fields("word"))
            .fieldsGrouping("sentence-spout",new Fields("word"));
        /**第二步，进行基本配置*/  
        Config conf = new Config();
        //作用和影响？？？？？？？？？？？
        conf.setDebug(true);
        if (args != null && args.length > 0) {
            conf.setNumWorkers(1);
            StormSubmitter.submitTopology(args[0], conf, builder.createTopology());
            }
        else {
            /*
             * run in local cluster, for test in eclipse.
             */
            conf.setMaxTaskParallelism(3);
            LocalCluster cluster = new LocalCluster();
            cluster.submitTopology("Getting-Started-Toplogie", conf, builder.createTopology());  
            Thread.sleep(Integer.MAX_VALUE);  
            cluster.shutdown();  
        }
    }
}