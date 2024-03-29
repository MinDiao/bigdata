package tz_mr_mapjoin;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * 
 * @ClassName:  CacheDriver   
 * @Description:  驱动类 
 * @author:  LiuJinrong
 * @date:   2019年3月14日 下午10:20:21
 */
public class CacheDriver {
	
	public static void main(String[] args) throws IOException, URISyntaxException, ClassNotFoundException, InterruptedException {
		// 1.获取job信息
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf);
		
		// 2.获取jar包
		job.setJarByClass(CacheDriver.class);
		
		// 3.获取自定义的Mapper与Reducer类
		job.setMapperClass(CacheMapper.class);
		
		// 4.设置Reduce输出的数据类型(最终的数据类型)
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(NullWritable.class);
		
		// 5.设置输入存在的路径与处理后的结果路径
		FileInputFormat.setInputPaths(job, new Path("c://table1029//in"));
		FileOutputFormat.setOutputPath(job, new Path("c://table1029//out"));
		
		// 加载缓存商品数据
		job.addCacheFile(new URI("file:///c:/inputcache/pd.txt"));
		
		// 设置ReduceTask的数量
		job.setNumReduceTasks(0);
		
		// 7.提交任务
		boolean rs = job.waitForCompletion(true);
		System.out.println(rs ? 0 : 1);
	}
	
}
