package itstaredu_mr_outputformat;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

/**
 * 
 * @ClassName:  FileRecordWriter   
 * @Description:  自定义out输出文件格式 
 * @author:  LiuJinrong
 * @date:   2019年3月10日 下午10:46:55
 */
public class FileRecordWriter extends RecordWriter<Text, NullWritable> {	
	
	/**
	 * 初始化
	 */
	Configuration conf;
	FSDataOutputStream itstarlog;
	FSDataOutputStream otherlog;
		
	/**
	 * 1.定义数据输出路径
	 */
	public FileRecordWriter(TaskAttemptContext job) throws IOException {
		
		// 获取配置信息
		conf = job.getConfiguration();
		// 获取文件系统
		FileSystem fs = FileSystem.get(conf);
		// 定义输出路径
		itstarlog = fs.create(new Path("c:/outitstaredu/itstar.logs"));
		otherlog = fs.create(new Path("c:/outputother/other.logs"));
	}
	
	/**
	 * 2.数据输出
	 */
	@Override
	public void write(Text key, NullWritable value) throws IOException, InterruptedException {
		
		// 根据key进行判断
		if (key.toString().contains("itstar")) {
			// 写出至文件
			itstarlog.write(key.getBytes());
		} else {
			otherlog.write(key.getBytes());
		}
	}
	
	/**
	 * 3.关闭资源
	 */
	@Override
	public void close(TaskAttemptContext context) throws IOException, InterruptedException {
		
		if (itstarlog != null) {
			itstarlog.close();
		}
		if (otherlog != null) {
			otherlog.close();
		}
	}

}
