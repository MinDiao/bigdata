package com.itstaredu.wccombiner;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * 
 * 类描述：Combiner合并优化
 * 作者： LiuJinrong  
 * 创建日期：2018年12月18日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0
 */
public class WordCountCombiner extends Reducer<Text, IntWritable, Text, IntWritable> {

	@Override
	protected void reduce(Text key, Iterable<IntWritable> values,
			Context context) throws IOException, InterruptedException {
		// 1.计数
		int count = 0;
		
		// 2.遍历累加求和
		for (IntWritable v : values) {
			// 累加求和
			count += v.get();
		}
		
		// 3.输出
		context.write(key, new IntWritable(count));
	}

}
