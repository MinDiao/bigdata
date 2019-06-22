package com.tz.wordcount;

import java.util.HashMap;
import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;

/**
 * 
 * @ClassName:  WordCountBolt   
 * @Description:  TODO 
 * @author:  LiuJinrong
 * @date:   2019年6月22日 下午6:33:25
 */
public class WordCountBolt extends BaseRichBolt {
	
	private Map<String, Integer> map = new HashMap<String, Integer>();

	/**
	 * 累加求和
	 */
	@Override
	public void execute(Tuple in) {
		// 1.获取数据
		String word = in.getStringByField("word");
		Integer sum = in.getIntegerByField("sum");
		
		// 2.业务处理
		if (map.containsKey(word)) {
			// 之前出现几次
			Integer count = map.get(word);
			// 已有的
			map.put(word, count + sum);
		} else {
			map.put(word, sum);
		}
	}

	@Override
	public void prepare(Map arg0, TopologyContext arg1, OutputCollector arg2) {
		// なし
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer arg0) {
		// なし
	}

}
