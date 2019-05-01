package com.itstaredu.assistranking;

import java.io.IOException;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * 
 * 类描述：数据汇总
 * 作者： LiuJinrong  
 * 创建日期：2018年12月23日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0
 */
public class OrderReducer extends Reducer<OrderBean, NullWritable, OrderBean, NullWritable> {

	@Override
	protected void reduce(OrderBean key, Iterable<NullWritable> values,
			Context context)
					throws IOException, InterruptedException {
		// 输出
		context.write(key, NullWritable.get());
	}

}
