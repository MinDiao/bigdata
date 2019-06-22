package com.tz.wordcount;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.InvalidTopologyException;
import org.apache.storm.topology.TopologyBuilder;

public class WordCountDriver {

	public static void main(String[] args) {
		
		/**
		 * 1.hadoop -> Job storm -> topology 创建拓扑
		 */
		TopologyBuilder builder = new TopologyBuilder();
		
		/**
		 * 2.指定设置
		 */
		builder.setSpout("WordCountSpout", new WordCountSpout(), 2);
		builder.setBolt("WordCountSplitBolt", new WordCountSplitBolt(), 2).setNumTasks(4).shuffleGrouping("WordCountSpout");
		builder.setBolt("WordCountBolt", new WordCountBolt(), 1).shuffleGrouping("WordCountSplitBolt");
		
		/**
		 * 3.创建配置信息
		 */
		Config conf = new Config();
		// conf.setNumWorkers(10);
		
		/**
		 * 集群模式运行
		 */
		/*try {
			StormSubmitter.submitTopology(args[0], conf, builder.createTopology());
		} catch (AlreadyAliveException | InvalidTopologyException | AuthorizationException e) {
			e.printStackTrace();
		}*/
		
		/**
		 * 4.提交任务
		 */
		LocalCluster localCluster = new LocalCluster();
		localCluster.submitTopology("wordcounttopology", conf, builder.createTopology());
	}
}
