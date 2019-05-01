package itstaredu_mr_inputformat;

import java.io.IOException;

import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
/**
 * 
 * @ClassName:  SequenceFileReducer   
 * @Description:  Reducer 
 * @author:  lilong
 * @date:   2019年1月13日 下午11:56:47
 */
public class SequenceFileReducer extends Reducer<Text, BytesWritable, Text, BytesWritable> {

	@Override
	protected void reduce(Text key, Iterable<BytesWritable> values,
			Context context) throws IOException, InterruptedException {
		
		// 遍历输出
		for (BytesWritable v : values) {
			context.write(key, v);
		}
	}

}
