package tz_zk_api1;

import java.io.IOException;
import java.util.List;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

/**
 * 
 * @ClassName:  WatchDemo2   
 * @Description:  监听2 (注意Zookeeper只能单次监听) 
 * @author:  LiuJinrong
 * @date:   2019年3月19日 上午12:40:58
 */
public class WatchDemo2 {

	static List<String> children;

	public static void main(String[] args) throws IOException, KeeperException, InterruptedException {

		ZooKeeper zkCli = new ZooKeeper("192.168.133.128:2181,192.168.133.129:2181,192.168.133.130:2181", 
				300, new Watcher() {
			/**
			 * 监听回调
			 */
			@Override
			public void process(WatchedEvent event) {
				System.out.println("正在监听中");
			}
		});
		
		/**
		 * 监听目录
		 */
		children = zkCli.getChildren("/", new Watcher() {
			/**
			 * 监听回调
			 */
			@Override
			public void process(WatchedEvent event) {
				
				System.out.println("监听路径为:"+ event.getPath());
				System.out.println("监听的类型为:"+ event.getType());
				System.out.println("数据被修改了");
				
				for (String c : children) {
					System.out.println(c);
				}
			}
		});
		
		// 线程阻塞
		Thread.sleep(Long.MAX_VALUE);
	}

}
