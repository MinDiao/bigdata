package com.tz.mr_handtest;
/**
 * 类描述:将数据切开
 * @author Administrator
 * 思路:添加一个map方法  单词切分 相同key的value++
 */
public class WordCountMapper implements Mapper {

	@Override
	public void map(String line, Context context) {

		// 1.拿到这行数据 切分
		String[] words = line.split(" ");
		
		// 2.拿到单词相同的key value++
		for (String w : words) {
			Object value = context.get(w);
			// 判断单词存在个数
			if (value == null) {
				// 为空
				context.write(w, 1);
			} else {
				// 不为空
				int v = (int)value;
				context.write(w, v+1);
			}
		}
	}
}
