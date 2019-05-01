package com.itstaredu.rpc.server;

import com.itstaredu.rpc.protocol.ClientNamenodeProtocol;
/**
 * 模拟: HDFS中namenode服务端的rpc通讯协议的实现类
 * @author Administrator
 *
 */
public class MyNameNode implements ClientNamenodeProtocol {

	@Override
	public String getMetaData(String path) {

		return path + ": 3 - {BLK_1,BLK_2,BLK_3...}";
	}

}
