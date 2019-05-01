package com.itstaredu.assistranking;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * 
 * 类描述：驱动类
 * 作者： LiuJinrong  
 * 创建日期：2018年12月23日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0
 */
public class OrderDriver {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		// 1.获取job信息
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf);
		
		// 2.获取jar包
		job.setJarByClass(OrderDriver.class);
		
		// 3.获取mapper与reducer
		job.setMapperClass(OrderMapper.class);
		job.setReducerClass(OrderReducer.class);
		
		// 4.定义mapper输出类型
		job.setMapOutputKeyClass(OrderBean.class);
		job.setMapOutputValueClass(NullWritable.class);
		
		// 5.定义reducer输出类型
		job.setOutputKeyClass(OrderBean.class);
		job.setOutputValueClass(NullWritable.class);
		
		// 6.设置辅助排序-reducer端的分组
		job.setGroupingComparatorClass(OrderGroupingComparator.class);
		
		// 7.设置分区
		job.setPartitionerClass(OrderPartitioner.class);
		
		// 8.设置reduceTask个数
		job.setNumReduceTasks(3);
		
		// 9.设置数据的输入与输出
		FileInputFormat.setInputPaths(job, new Path("c://inorder"));
		FileOutputFormat.setOutputPath(job, new Path("c://outorder"));
		
		// 10.提交任务
		boolean rs = job.waitForCompletion(true);
		System.out.println(rs ? "成功" : "失败");
	}
}
