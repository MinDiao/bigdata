package itstaredu_mr_outputformat;

import java.io.IOException;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * 
 * @ClassName:  FileReducer   
 * @Description:  Reducer数据归并 
 * @author:  LiuJinrong
 * @date:   2019年3月12日 下午11:46:43
 */
public class FileReducer extends Reducer<Text, NullWritable, Text, NullWritable> {

	@Override
	protected void reduce(Text key, Iterable<NullWritable> value,
			Context context) throws IOException, InterruptedException {
		// 数据换行
		String k = key.toString()+"\r\n";
		// 输出
		context.write(new Text(k), NullWritable.get());
	}

}
