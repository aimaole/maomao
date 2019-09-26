package com.maomao.demo;

import java.util.Map;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import org.apache.log4j.Logger;

import backtype.storm.task.TopologyContext;
import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;
/*
 * 
 * IBasicBolt:继承自IComponent,包括prepare,execut,cleanup等方法
 */
public class SplitBolt extends BaseBasicBolt {
    /**
     * 
     */
    private static final long serialVersionUID = 7104767103420386784L;
    private static final Logger logger = Logger.getLogger(SplitBolt.class);
    String component;
    /* cleanup方法在bolt被关闭的时候调用， 它应该清理所有被打开的资源。（基本只能在local mode使用）
     * 但是集群不保证这个方法一定会被执行。比如执行task的机器down掉了，那么根本就没有办法来调用那个方法。 
     * cleanup设计的时候是被用来在local mode的时候才被调用(也就是说在一个进程里面模拟整个storm集群), 
     * 并且你想在关闭一些topology的时候避免资源泄漏。
     * （非 Javadoc）
     * @see backtype.storm.topology.base.BaseBasicBolt#cleanup()
     */
    public void cleanup() {

    }
    //接收消息之后被调用的方法
    public void execute(Tuple input,BasicOutputCollector collector) {
        String sentence = input.getString(0);
        String[] words = sentence.split("[,|\\s+]");
        for(String word : words){
            word = word.trim();
            if(!word.isEmpty()){
                word = word.toLowerCase();
                collector.emit(new Values(word));
            }
        }
    }
    /*
     * prepare方法在worker初始化task的时候调用. 
     * 
     * prepare方法提供给bolt一个Outputcollector用来发射tuple。
     * Bolt可以在任何时候发射tuple — 在prepare, execute或者cleanup方法里面, 或者甚至在另一个线程里面异步发射。
     * 这里prepare方法只是简单地把OutputCollector作为一个类字段保存下来给后面execute方法 使用。
     */
    
    public void prepare(Map stromConf, TopologyContext context) {
        component = context.getThisComponentId();
    }

    /*
     * declearOutputFields方法仅在有新的topology提交到服务器, 
     * 用来决定输出内容流的格式(相当于定义spout/bolt之间传输stream的name:value格式), 
     * 在topology执行的过程中并不会被调用.
     * （非 Javadoc）
     * @see backtype.storm.topology.IComponent#declareOutputFields(backtype.storm.topology.OutputFieldsDeclarer)
     */
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("word"));
    }
}