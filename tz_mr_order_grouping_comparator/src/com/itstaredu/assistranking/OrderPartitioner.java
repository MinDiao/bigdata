package com.itstaredu.assistranking;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * 
 * 类描述：自定义分区
 * 作者： LiuJinrong  
 * 创建日期：2018年12月23日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0
 */
public class OrderPartitioner extends Partitioner<OrderBean, NullWritable> {

	@Override
	public int getPartition(OrderBean key, NullWritable value, int numPartitions) {
		
		return (key.getOrder_id() & Integer.MAX_VALUE) % numPartitions;
	}
	
}
