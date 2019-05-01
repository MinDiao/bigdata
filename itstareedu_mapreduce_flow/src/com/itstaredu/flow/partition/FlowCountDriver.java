package com.itstaredu.flow.partition;

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
 * 创建日期：2018年12月16日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0
 */
public class FlowCountDriver {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		// 1.获取job信息
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf);
		
		// 2.获取jar包
		job.setJarByClass(FlowCountDriver.class);
		
		// 3.获取自定义mapper与reducer类
		job.setMapperClass(FlowCountMapper.class);
		job.setReducerClass(FlowCountReducer.class);
		
		// 4.设置map输出的数据类型
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(FlowBean.class);
		
		// 5.设置reduce输出的数据类型
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(FlowBean.class);
		
		// 6.设置自定义的分区类
		job.setPartitionerClass(PhonenumPartitioner.class);
		// 不指定默认为一个，并且需要比设置的多一个
		job.setNumReduceTasks(5);
		
		// 7.设置输入存在的路径与处理后的结果路径
		FileInputFormat.setInputPaths(job, new Path("c:/flow2/in"));
		FileOutputFormat.setOutputPath(job, new Path("c:/flow2/out"));
		
		// 8.提交任务
		boolean rs = job.waitForCompletion(true);
		System.out.println(rs ? "成功" : "失败");
	}
}
