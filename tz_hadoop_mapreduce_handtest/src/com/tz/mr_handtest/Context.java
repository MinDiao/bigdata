package com.tz.mr_handtest;

import java.util.HashMap;

/**
 * 类描述:存放数据集合
 * @author Administrator
 *
 */
public class Context {
	// 数据封装
	private HashMap<Object, Object> contextMap = new HashMap<>();
	
	// 写数据
	public void write(Object key, Object value) {
		contextMap.put(key, value);
	}
	
	// 定义根据key拿到值方法
	public Object get(Object key) {
		return contextMap.get(key);
	}
	
	// 拿到map当中的大数据内容
	public HashMap<Object, Object> getContextMap(){
		return contextMap;
	}
}
