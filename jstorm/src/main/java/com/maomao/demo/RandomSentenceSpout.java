package com.maomao.demo;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichSpout;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
import backtype.storm.utils.Time;
import backtype.storm.utils.Utils;
import org.apache.log4j.Logger;

import java.util.Map;
import java.util.Random;

public class RandomSentenceSpout implements IRichSpout {
    
    /**
     * 
     */
    private static final long serialVersionUID = 4058847280819269954L;
    private static final Logger logger = Logger.getLogger(RandomSentenceSpout.class);
    SpoutOutputCollector _collector;
    Random _rand;
    String component;
    /*
     * Spout初始化的时候调用
     */
    public void open(Map conf, TopologyContext context, SpoutOutputCollector collector){
        _collector = collector;
        _rand = new Random();
        component = context.getThisComponentId();
    }
    /*
     * 系统框架会不断调用
     */
    public void nextTuple() {
        String[] sentences = new String[] { "Hello world! This is my first programme of JStorm",
                "Hello JStorm,Nice to meet you!", "Hi JStorm, do you have a really good proformance",
                "Goodbye JStorm,see you tomorrow" };
        String sentence = sentences[_rand.nextInt(sentences.length)];
        _collector.emit(new Values(sentence), Time.currentTimeSecs());
        Utils.sleep(1000);
    }
    @Override
    public void ack(Object arg0) {
        logger.debug("ACK!");
    }

    public void activate() {
        logger.debug("ACTIVE!");
    }

    public void close() {

    }

    public void deactivate() {

    }

    public void fail(Object arg0) {
        logger.debug("FAILED!");
    }
    /*
     * 声明框架有哪些输出的字段
     */
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("word"));
    }

    public Map<String, Object> getComponentConfiguration() {
        return null;
    }

}