package itstaredu_mr_inputformat;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.ByteWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

/**
 * 
 * @ClassName:  SequenceFileMapper   
 * @Description:  inputformat的mapper 
 * @author:  lilong
 * @date:   2019年1月13日 下午5:02:40
 */
public class SequenceFileMapper extends Mapper<NullWritable, ByteWritable, Text, ByteWritable> {
	
	// 初始化hadoop字符串对象
	Text k = new Text();
	
	protected void setup(Context context) {
		
		// 1.拿到切片信息
		FileSplit split = (FileSplit) context.getInputSplit();
		
		// 2.路径
		Path path = split.getPath();
		
		// 3.既带路径又带名称
		k.set(path.toString());
	}
	
	
	protected void map(NullWritable key, ByteWritable value, Context context) throws IOException, InterruptedException {
		
		// 输出
		context.write(k, value);
	}

}
