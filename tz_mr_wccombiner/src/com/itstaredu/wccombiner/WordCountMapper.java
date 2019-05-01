package com.itstaredu.wccombiner;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * 
 * 类描述：单词数据打散
 * 作者： LiuJinrong  
 * 创建日期：2018年12月18日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0
 * 
 * 思路？
 * wordcount单词计数
 * <单词,1>
 * 
 * 数据传输
 * KEYIN:数据的起始偏移量0~10 11~20 21~30 
 * VALUEIN:数据
 * 
 * KEYOUT：mapper输出到reduce阶段 k的类型
 * VALUEOUT：mapper输出到reduce阶段v的类型
 * <hello,1><hunter,1><henshuai,1>
 */
public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

	@Override
	// key 起始偏移量 value 数据  context 上下文 
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		// 1.读取数据
		String line = value.toString();
		
		// 2.切割
		String[] words = line.split(" ");
		
		// 3.循环地写到下一个阶段
		for (String w : words) {
			// 4.输出到reducer阶段
			context.write(new Text(w), new IntWritable(1));
		}
	}
	
}






















