package com.itstaredu.rpc.protocol;
/**
 * 模拟: HDFS中rpc通讯协议接口,实际Apache中jar包已经封装完毕,
 * 		此处只是模拟
 * @author Administrator
 *
 */
public interface ClientNamenodeProtocol {

	// 1.定义协议的id
	public static final long versionID = 1L;
	
	// 2.定义方法(拿到元数据的方式)
	public String getMetaData(String path);
}
