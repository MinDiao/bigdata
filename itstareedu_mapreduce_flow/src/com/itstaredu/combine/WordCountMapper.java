package com.itstaredu.combine;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * 
 * 类描述：单词打散Mapper
 * 作者： LiuJinrong  
 * 创建日期：2018年12月16日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0
 */
public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

	/**
	 * key其实偏移量,value数据,context上下文
	 */
	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		// 1.读取数据
		String line = value.toString();
		
		// 2.切割
		String[] words = line.split(" ");
		
		// 3.遍历
		for (String w : words) {
			// 4.输出到reducer阶段
			context.write(new Text(w), new IntWritable(1));
		}
	}

}
