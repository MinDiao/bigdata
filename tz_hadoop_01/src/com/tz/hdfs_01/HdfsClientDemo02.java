package com.tz.hdfs_01;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class HdfsClientDemo02 {
	/**
	 * 从hdfs下载文件到本地
	 */
	public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {

		// 1.加载配置
		Configuration conf = new Configuration();
		
		// 2.设置副本数
		conf.set("dfs.repication", "2");
		
		// 3.设置块大小
		conf.set("dfs.blocksize", "64m");
		
		// 4.构造客户端
		FileSystem fs = FileSystem.get(new URI("hdfs://192.168.255.134:9000/"), conf, "root");
		
		// 5.hdfs数据下载到windows本地
		fs.copyToLocalFile(new Path("/hd"), new Path("d:/"));
		
		// 6.关闭资源
		fs.close();
	}
}
