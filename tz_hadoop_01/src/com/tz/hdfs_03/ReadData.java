package com.tz.hdfs_03;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.zookeeper.common.IOUtils;
import org.junit.Before;
import org.junit.Test;

public class ReadData {
	
	/**
	 * 初始化连接
	 */
	FileSystem fs = null;
	
	@Before
	public void init() throws IOException, InterruptedException, URISyntaxException{
		
		// 1.加载配置
		Configuration conf = new Configuration();
		
		// 2.构造客户端
		fs = FileSystem.get(new URI("hdfs://192.168.255.134:9000/"), conf, "root");
	}
	
	/**
	 * 读数据方式1
	 */
	@Test
	public void testReadData1() throws IllegalArgumentException, IOException{
		
		// 1.获取流
		FSDataInputStream in = fs.open(new Path("/words222.txt"));
		
		byte[] buf = new byte[1024];
		
		in.read(buf);
		
		System.out.println(new String(buf));
		
		// 关闭资源
		in.close();
		fs.close();
	}
	
	/**
	 * 读数据方式2
	 */
	@Test
	public void testReadData2() throws IllegalArgumentException, IOException{
		
		// 1.获取流
		FSDataInputStream in = fs.open(new Path("/words222.txt"));
		
		// 2.缓冲流
		BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
		
		// 3.按行读取
		String line = null;
		
		// 4.读数据
		while ((line = br.readLine()) != null) {
			System.out.println(line);
		}
		
		// 5.关闭资源
		br.close();
		in.close();
		fs.close();
	}
	
	/**
	 * 读hdfs中指定偏移量
	 */
	@Test
	public void restRandomRead() throws IOException{
		
		// 1.获取流
		FSDataInputStream in = fs.open(new Path("/words222.txt"));
		
		// 从下标14开始
		in.seek(14);
		
		// 读5个
		byte[] b = new byte[5];
		
		in.read(b);
		
		System.out.println(new String(b));
		
		// 关闭资源
		in.close();
	}
	
	/**
	 * 在hdfs中上传文件
	 */
	@Test
	public void testWriteData1() throws IllegalArgumentException, IOException{
		
		// 1.输出流
		FSDataOutputStream out = fs.create(new Path("/alg222.txt"), false);
		
		// 2.输入流
		FileInputStream in = new FileInputStream("d:/alg.txt");
		
		byte[] buf = new byte[1024];
		
		int read = 0;
		
		while ((read = in.read(buf)) != -1) {
			out.write(buf, 0, read);
		}
		
		// 关闭资源
		in.close();
		out.close();
		fs.close();
	}
	
	/**
	 * 在hdfs中上传并中途写入文件
	 */
	@Test
	public void testWriteData2() throws IllegalArgumentException, IOException{
		
		// 1.创建输出流
		FSDataOutputStream out = fs.create(new Path("/henpi222.txt"));
		
		// 2.创建输入流
		FileInputStream in = new FileInputStream(new File("d:/henpi.txt"));
		
		// 2.写数据
		out.write("henpi很皮".getBytes());
		
		// 4.关闭资源
		in.close();
		IOUtils.closeStream(out);
		fs.close();
	}
}
