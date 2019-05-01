package com.itstaredu.flow.partition;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * 
 * 类描述：数据汇总
 * 作者： LiuJinrong  
 * 创建日期：2018年12月16日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0
 */
public class FlowCountReducer extends Reducer<Text, FlowBean, Text, FlowBean> {

	@Override
	protected void reduce(Text key, Iterable<FlowBean> values, Context context)
			throws IOException, InterruptedException {
		// 1.相同手机号的流量使用再次汇总
		long upFlow_sum = 0;
		long dfFlow_sum = 0;
		
		// 2.累加
		for (FlowBean f : values) {
			upFlow_sum += f.getUpFlow();
			dfFlow_sum += f.getDfFlow();
		}
		
		// 3.封装到对象
		FlowBean rs = new FlowBean(upFlow_sum, dfFlow_sum);
		
		// 4.输出
		context.write(key, rs);
	}

}
