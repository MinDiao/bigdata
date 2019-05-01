package com.itstareedu.flow;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * 
 * 类描述：Reducer数据累加
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
		
		// 2.累加计算
		for (FlowBean f : values) {
			// 上传流量
			upFlow_sum += f.getUpFlow();
			// 下载流量
			dfFlow_sum += f.getDfFlow();
		}
		
		// 3.封装实体类并返回上传与下载流量总和
		FlowBean rs = new FlowBean(upFlow_sum, dfFlow_sum);
		
		// 4.输出
		context.write(key, rs);
	}

}
