package com.itstareedu.flowsort;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


/**
 * 
 * 类描述：驱动类
 * 作者： LiuJinrong  
 * 创建日期：2018年12月18日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0
 */
public class FlowSortDriver {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		// 1.获取job信息
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf);
		
		// 2.获取jar包
		job.setJarByClass(FlowSortDriver.class);
		
		// 3.获取自定义的mapper类与reducer类
		job.setMapperClass(FlowSortMapper.class);
		job.setReducerClass(FlowSortReducer.class);
		
		// 4.设置map输出格式
		job.setMapOutputKeyClass(FlowBean.class);
		job.setMapOutputValueClass(Text.class);
		
		// 5.设置reduce输出格式
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(FlowBean.class);
		
		// 6.添加自定义分区
		job.setPartitionerClass(FlowSortPartitioner.class);
		job.setNumReduceTasks(5);
		
		// 7.设置输入存在路径与处理后的结果
		FileInputFormat.setInputPaths(job, new Path("c:/flow/in"));
		FileOutputFormat.setOutputPath(job, new Path("c:/flowSort/out"));
		
		// 8.提交任务
		boolean result = job.waitForCompletion(true);
		System.out.println(result ? "成功" : "失败");
	}
}
