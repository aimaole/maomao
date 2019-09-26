package com.maomao.demo;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.jstorm.callback.AsyncLoopThread;
import com.alibaba.jstorm.callback.RunnableCallback;

import backtype.storm.task.TopologyContext;
import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import clojure.inspector__init;

public class CountBoltB extends BaseBasicBolt {
    Integer id;  
    String name;  
    Map<String, Integer> counters;     
    String component;
    private static final Logger LOG = Logger.getLogger(CountBolt.class);
    private AsyncLoopThread statThread;
    /** 
     * On create  
     */  
    @Override  
    public void prepare(Map stormConf, TopologyContext context) {  
        this.counters = new HashMap<String, Integer>();  
        this.name = context.getThisComponentId();  
        this.id = context.getThisTaskId();  
        this.statThread = new AsyncLoopThread(new statRunnable());
        
        LOG.info(stormConf.get("abc")+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        component = context.getThisComponentId();
    }  
  
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
         declarer.declare(new Fields("word","count"));
         // declarer.declareStream("coord-"+"word-counter", new Fields("epoch","ebagNum"));
         // LOG.info("set stream coord-"+component);
    }  
  
    //接收消息之后被调用的方法
    public void execute(Tuple input, BasicOutputCollector collector) {
//        String str = input.getString(0);
        String str = input.getStringByField("word");
        if(!counters.containsKey(str)){  
            counters.put(str, 1);  
        }else{
            Integer c = counters.get(str) + 1;  
            counters.put(str, c);  
        }  
    }  
    class statRunnable extends RunnableCallback {

        @Override
        public void run() {
            while(true){
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {

                }
                LOG.info("\n-- Word Counter ["+name+"-"+id+"] --");  
                for(Map.Entry<String, Integer> entry : counters.entrySet()){  
                    LOG.info(entry.getKey()+": "+entry.getValue());  
                } 
                LOG.info("");
            }
            
        }
    }

}