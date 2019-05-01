package itstaredu_mr_outputformat;

import java.io.IOException;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * 
 * @ClassName:  FuncFileOutputFormat   
 * @Description:  继承文件输出格式类 
 * @author:  LiuJinrong
 * @date:   2019年3月12日 下午11:32:15
 */
public class FuncFileOutputFormat extends FileOutputFormat<Text, NullWritable> {

	@Override
	public RecordWriter<Text, NullWritable> getRecordWriter(TaskAttemptContext job)
			throws IOException, InterruptedException {
		// 实例化自定义out输出文件格式 类
		FileRecordWriter fileRecordWriter = new FileRecordWriter(job);
		
		return fileRecordWriter;
	}

}
