package com.itstareedu.flow;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * 
 * 类描述：Mapper数据打散
 * 作者： LiuJinrong  
 * 创建日期：2018年12月16日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0
 */
public class FlowCountMapper extends Mapper<LongWritable, Text, Text, FlowBean> {

	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		// 1.获取数据(按行获取)
		String line = value.toString();
		
		// 2.切割
		String[] fields = line.split("\t");
		
		// 3.封装对象,拿到关键字段,数据清洗
		// 手机号在下标位置1
		String phoneN = fields[1];
		// 将String拆箱成long
		// 上传流量在倒数下标位置3
		long upFlow = Long.parseLong(fields[fields.length - 3]);
		// 下载流量在倒数下标位置2
		long dfFlow = Long.parseLong(fields[fields.length - 2]);
		
		// 4.输出到reduce端
		context.write(new Text(phoneN), new FlowBean(upFlow, dfFlow));
	}
	
}
