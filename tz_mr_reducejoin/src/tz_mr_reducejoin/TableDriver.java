package tz_mr_reducejoin;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * 
 * @ClassName:  TableDriver   
 * @Description:  驱动类 
 * @author:  LiuJinrong
 * @date:   2019年3月16日 下午2:43:27
 */
public class TableDriver {
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		// 1.获取job信息
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf);
		
		// 2.获取jar包
		job.setJarByClass(TableDriver.class);
		
		// 3.获取自定义的Mapper与Reducer类
		job.setMapperClass(TableMapper.class);
		job.setReducerClass(TableReducer.class);
		
		// 4.设置Map端输出类型
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(NullWritable.class);
		
		// 5.设置Reduce输出的数据类型(最终的数据类型)
		job.setOutputKeyClass(TableBean.class);
		job.setOutputValueClass(NullWritable.class);
		
		// 6.设置输入存在的路径与处理后的结果路径
		FileInputFormat.setInputPaths(job, new Path("c://ljr//in"));
		FileOutputFormat.setOutputPath(job, new Path("c://ljr//out"));
		
		// 7.提交任务
		boolean rs = job.waitForCompletion(true);
		System.out.println(rs ? 0 : 1);
	}

}
