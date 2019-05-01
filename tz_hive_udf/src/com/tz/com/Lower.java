package com.tz.com;

import org.apache.hadoop.hive.ql.exec.UDF;

/**
 * 
 * @ClassName:  UDF   
 * @Description:  TODO 
 * @author:  LiuJinrong
 * @date:   2019年4月1日 上午12:11:47
 */
public class Lower extends UDF {
	
	/**
	 * 大写字符串转换为小写
	 */
	public String evaluate(final String s) {
		
		// 判空
		if (s == null) {
			return null;
		}
		
		return s.toString().toLowerCase();
	}

}
