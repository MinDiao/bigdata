package com.itstareedu.flowsort;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * 
 * 类描述：自定义分区类
 * 作者： LiuJinrong  
 * 创建日期：2018年12月18日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0
 */
public class FlowSortPartitioner extends Partitioner<FlowBean, Text> {

	/**
	 * 根据手机号前三位进行分区
	 */
	@Override
	public int getPartition(FlowBean key, Text value, int numPartitions) {
		// 1.获取手机号前三位
		String phoneNum = value.toString().substring(0, 3);
		
		// 2.默认分区数量
		int partitioner = 4;
		
		if ("135".equals(phoneNum)) {
			return 0;
		} else if ("137".equals(phoneNum)) {
			return 1;
		} else if ("138".equals(phoneNum)) {
			return 2;
		} else if ("139".equals(phoneNum)) {
			return 3;
		}
		return partitioner;
	}
	
}
