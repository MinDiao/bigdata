package tz_zk_serverclient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

/**
 * 
 * @ClassName:  ZkClient   
 * @Description:  客户端 
 * @author:  LiuJinrong
 * @date:   2019年3月20日 上午1:29:33
 */
public class ZkClient {
	
	/**
	 * 初始化
	 */
	private String connectString = "192.168.133.128:2181,192.168.133.129:2181,192.168.133.130:2181";
	private int sessionTimeout = 3000;
	ZooKeeper zkCli;
	
	public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
		
		// 1.获取连接
		ZkClient zkClient = new ZkClient();
		zkClient.getConnect();
		
		// 2.监听服务的节点信息
		zkClient.getServers();
		
		// 3.业务逻辑(一直监听)
		zkClient.getWatch();
	}
	
	/**
	 * 1.连接集群
	 */
	public void getConnect() throws IOException {
		
		zkCli = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
			
			@Override
			public void process(WatchedEvent event) {
				
				List<String> children = null;
				
				try {
					// 监听父节点
					children = zkCli.getChildren("/servers", true);
					// 创建集合存储服务器列表
					ArrayList<String> serverList = new ArrayList<String>();
					// 获取每个节点的数据
					for (String c : children) {
						
						byte[] data = zkCli.getData("/servers/"+ c, true, null);
						serverList.add(new String(data));
					}
					// 打印服务器列表
					System.out.println(serverList);
					
				} catch (KeeperException | InterruptedException e) {
					e.printStackTrace();
				}

			}
		});
		
	}
	
	/**
	 * 2.监听服务的节点信息
	 */
	public void getServers() throws KeeperException, InterruptedException {
		
		List<String> children = zkCli.getChildren("/servers", true);
		ArrayList<String> serverList = new ArrayList<String>();
		
		// 获取每个节点的数据
		for (String c : children) {
			
			byte[] data = zkCli.getData("/servers/"+ c, true, null);
			serverList.add(new String(data));
		}
		// 打印服务器列表
		System.out.println(serverList);
	}
	
	/**
	 * 业务逻辑
	 */
	public void getWatch() throws InterruptedException {
		// 线程阻塞
		Thread.sleep(Long.MAX_VALUE);
	}

}
