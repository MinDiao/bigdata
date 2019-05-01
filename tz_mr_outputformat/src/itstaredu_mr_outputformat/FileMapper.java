package itstaredu_mr_outputformat;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * 
 * @ClassName:  FileMapper   
 * @Description:  Mapper数据截取 
 * @author:  LiuJinrong
 * @date:   2019年3月12日 下午11:47:01
 */
public class FileMapper extends Mapper<LongWritable, Text, Text, NullWritable> {

	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		// 输出
		context.write(value, NullWritable.get());
	}

}
