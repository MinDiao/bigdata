package itstaredu_mr_outputformat;

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
 * @ClassName:  FileDriver   
 * @Description:  驱动类 
 * @author:  LiuJinrong
 * @date:   2019年3月12日 下午11:54:06
 */
public class FileDriver {
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		
		// 1.获取job信息
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf);
		
		// 2.获取jar包
		job.setJarByClass(FileDriver.class);
		
		// 3.获取自定义mapper与reducer类
		job.setMapperClass(FileMapper.class);
		job.setReducerClass(FileReducer.class);
		
		// 4.设置map输出的数据类型
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(NullWritable.class);
		
		// 5.设置reduce输出的数据类型(最终的数据类型)
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(NullWritable.class);
		
		// 设置自定义数据输出类型outputFormat
		job.setOutputFormatClass(FuncFileOutputFormat.class);
		
		// 6.设置输入存在的路径与处理后的结果路径
		FileInputFormat.setInputPaths(job, new Path("c:/in1029/"));
		FileOutputFormat.setOutputPath(job, new Path("c:/out1029/"));
		
		// 7.提交任务
		boolean rs = job.waitForCompletion(true);
		System.out.println(rs ? 0 : 1);
		
	}

}
