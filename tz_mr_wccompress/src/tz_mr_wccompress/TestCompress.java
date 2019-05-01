package tz_mr_wccompress;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionOutputStream;
import org.apache.hadoop.util.ReflectionUtils;

/**
 * 
 * @ClassName:  TestCompress   
 * @Description:  压缩api 
 * @author:  LiuJinrong
 * @date:   2019年3月16日 下午3:35:14
 */
public class TestCompress {

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		
		Compress("c://2017级个人详细信息表.txt", "org.apache.hadoop.io.compress.GzipCodec");
	}
	
	/**
	 * 
	 * @Description:  测试压缩方法
	 * @author: LiuJinrong
	 * @date:   2019年3月16日 下午7:42:57    
	 * @param fileName 需压缩的文件
	 * @param method 指定压缩方法
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 */
	private static void Compress(String fileName, String method) throws ClassNotFoundException, IOException {
		
		// 1.获取输入流
		FileInputStream fis = new FileInputStream(new File(fileName));
		// 反射加载压缩方法类
		Class cName = Class.forName(method);
		
		CompressionCodec codec = (CompressionCodec) ReflectionUtils.newInstance(cName, new Configuration());
		
		// 2.输出流
		FileOutputStream fos = new FileOutputStream(new File(fileName) + codec.getDefaultExtension());
		
		// 3.创建压缩输出流
		CompressionOutputStream cos = codec.createOutputStream(fos);
		
		// 4.流的对拷贝
		IOUtils.copyBytes(fis, cos, 1024*1024*2, false);
		
		// 5.关闭资源
		fis.close();
		cos.close();
		fos.close();
	}
	
}




















