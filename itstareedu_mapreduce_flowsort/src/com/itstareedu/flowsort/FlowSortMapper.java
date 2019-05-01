package com.itstareedu.flowsort;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * 
 * 类描述：数据打散
 * 作者： LiuJinrong  
 * 创建日期：2018年12月18日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0
 */
public class FlowSortMapper extends Mapper<LongWritable, Text, FlowBean, Text> {

	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		// 1.获取第一行数据
		String line = value.toString();
		
		// 2.切割
		String[] fields = line.split("\t");
		
		// 3.取出关键字段
		long upFlow = Long.parseLong(fields[fields.length - 3]);
		long dfFlow = Long.parseLong(fields[fields.length - 2]);
		
		// 4.输出至reduce阶段
		context.write(new FlowBean(upFlow,dfFlow), new Text(fields[1]));
	}
	
}
