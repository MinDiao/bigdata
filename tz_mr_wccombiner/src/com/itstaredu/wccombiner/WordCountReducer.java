package com.itstaredu.wccombiner;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * 
 * 类描述：单词数据合并
 * 作者： LiuJinrong  
 * 创建日期：2018年12月18日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0
 */
public class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

	@Override
	protected void reduce(Text key, Iterable<IntWritable> values,
			Context context) throws IOException, InterruptedException {
		// 1.统计单词出现次数
		int sum = 0;
		
		// 2.累加求和
		for (IntWritable count : values) {
			// 拿到值累加
			sum += count.get();
		}
		
		// 3.结果输出
		context.write(key, new IntWritable(sum));
	}

}
