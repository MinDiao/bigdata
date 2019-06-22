package com.tz.wordcount;

import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
/**
 * 
 * @ClassName:  WordCountSplitBolt   
 * @Description:  TODO 
 * @author:  LiuJinrong
 * @date:   2019年6月22日 下午3:00:06
 */
public class WordCountSplitBolt extends BaseRichBolt {

	/**
	 * 数据继续发送到下一个bolt
	 */
	private OutputCollector collector;
	
	/**
	 * 业务逻辑
	 */
	@Override
	public void execute(Tuple in) {
		// 1.获取数据
	}

	/**
	 * 初始化
	 */
	@Override
	public void prepare(Map arg0, TopologyContext arg1, OutputCollector collector) {
		this.collector = collector;
	}

	/**
	 * 声明概述
	 */
	@Override
	public void declareOutputFields(OutputFieldsDeclarer declare) {
		declare.declare(new Fields("word", "sum"));
	}

}
