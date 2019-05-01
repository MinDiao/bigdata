package tz_zk_api1;

import java.io.IOException;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

/**
 * 
 * @ClassName:  WatchDemo1   
 * @Description:  监听1 (注意Zookeeper只能单次监听)
 * @author:  LiuJinrong
 * @date:   2019年3月18日 下午9:33:39
 */
public class WatchDemo1 {

	public static void main(String[] args) throws IOException, KeeperException, InterruptedException {

		ZooKeeper zkCli = new ZooKeeper("192.168.133.128:2181,192.168.133.129:2181,192.168.133.130:2181", 
				3000, new Watcher() {
			/**
			 * 监听回调
			 */
			@Override
			public void process(WatchedEvent event) {
				
			}
		});
		
		byte[] data = zkCli.getData("/ljr", new Watcher() {
			/**
			 * 监听的具体内容
			 */
			@Override
			public void process(WatchedEvent event) {
				
				System.out.println("监听路径为:"+ event.getPath());
				System.out.println("监听的类型为:"+ event.getType());
				System.out.println("数据被修改了");
			}
		}, null);
		
		System.out.println(new String(data));
		
		// 线程阻塞
		Thread.sleep(Long.MAX_VALUE);
	}

}
