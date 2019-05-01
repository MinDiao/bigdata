package com.tz.hdfs_02;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;
import org.junit.Before;
import org.junit.Test;

public class HdfsClientTest {
	
	// 设置成员变量
	FileSystem fs = null;
	/**
	 * 初始化连接
	 */
	@Before
	public void init() throws IOException, InterruptedException, URISyntaxException {
		
		// 1.加载配置
		Configuration conf = new Configuration();
		
		// 2.设置副本参数
		conf.set("dfs.replication", "2");
		
		// 3.设置块大小
		conf.set("dfs.blocksize", "64m");
		
		// 4.构造客户端
		fs = FileSystem.get(new URI("hdfs://192.168.255.134:9000"), conf, "root");
	}
	
	/**
	 * 创建文件夹
	 */
	@Test
	public void hdfsMkdir() throws IllegalArgumentException, IOException {
		
		// 1.调用创建文件夹的方法
		fs.mkdirs(new Path("/ljr"));
		// 2.关闭资源
		fs.close();
	}
	
	/**
	 * 移动/修改 文件
	 */
	@Test
	public void hdfsRename() throws IllegalArgumentException, IOException {
		
		// 1.调用移动并修改的方法
		fs.rename(new Path("/hd"), new Path("/ljr/hdfs.xml"));
		// 2.关闭资源
		fs.close();
	}
	
	/**
	 * 删除文件
	 */
	@Test
	public void hdfsDelete() throws IllegalArgumentException, IOException {
		
		// 1.调用删除文件夹的方法	参数1:删除的路径	参数2:是否递归删除
		fs.delete(new Path("/words111.txt"), true);
		// 2.关闭资源
		fs.close();
	}
	
	/**
	 * 查询hdfs下制定的目录信息
	 */
	@Test
	public void hdfsLs() throws FileNotFoundException, IllegalArgumentException, IOException {
		
		// 1.调用方法 返回远程迭代器
		RemoteIterator<LocatedFileStatus> iter = fs.listFiles(new Path("/"), true);
		// 2.循环迭代
		while (iter.hasNext()) {
			// 获取数据
			LocatedFileStatus status = iter.next();
			// 输出
			System.out.println("文件的路径为:"+ status.getPath());
			System.out.println("块大小为:"+ status.getBlockSize());
			System.out.println("文件长度为:"+ status.getLen());
			System.out.println("副本数量为"+ status.getReplication());
			System.out.println("块信息为:"+ Arrays.toString(status.getBlockLocations()));
			System.out.println();
		}
		// 3.关闭资源
		fs.close();
	}
	
	/**
	 * 判断文件还是文件夹
	 */
	@Test
	public void findAtHdfs() throws FileNotFoundException, IllegalArgumentException, IOException {
		
		// 1.展示状态信息
		FileStatus[] listStatus = fs.listStatus(new Path("/"));
		// 2.遍历所有文件
		for (FileStatus ls : listStatus) {
			if (ls.isFile()) {
				// 文件
				System.out.println("文件:"+ ls.getPath().getName());
			} else {
				// 文件夹
				System.out.println("文件夹:"+ ls.getPath().getName());
			}
		}
	}
}
