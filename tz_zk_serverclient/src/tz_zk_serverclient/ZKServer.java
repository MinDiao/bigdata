package tz_zk_serverclient;

import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZooDefs.Ids;

/**
 * 需求:
 * 		某分布式系统中,主节点有多台,可以进行动态上下线,
 * 		当有任何一台机器发生了动态的上下线,任何一台客户端都能感知得到.
 * 思路:
 * 		1.创建客户端与服务器
 * 		2.启动client端监听
 * 		3.启动server端注册
 * 		4.当server端发生上下线
 * 		5.client端都能感知得到
 * 
 * @ClassName:  ZKClient   
 * @Description:  服务端 
 * @author:  LiuJinrong
 * @date:   2019年3月20日 上午12:12:24
 */
public class ZKServer {
	
	public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
		
		// 1.连接zkServer
		ZKServer zkServer = new ZKServer();
		zkServer.getConnect();
		
		// 2.注册节点信息,服务器ip添加到zk中
		zkServer.regist(args[0]);
		
		// 3.业务逻辑处理
		zkServer.build(args[0]);
	}
	
	/**
	 * 初始化
	 */
	private String connectString = "192.168.133.128:2181,192.168.133.129:2181,192.168.133.130:2181";
	private int sessionTimeout = 3000;
	ZooKeeper zkCli;
	// 定义父节点
	private String parentNode = "/servers";
	
	/**
	 * 1.连接zkServer
	 */
	public void getConnect() throws IOException {
		
		zkCli = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
			
			@Override
			public void process(WatchedEvent event) {
				
			}
		});
	}
	
	/**
	 * 2.注册信息
	 */
	public void regist(String hostname) throws KeeperException, InterruptedException {
		
		String node = zkCli.create(parentNode +"/server", hostname.getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
		System.out.println(node);
	}
	
	/**
	 * 3.构造服务端
	 */
	public void build(String hostname) throws InterruptedException {
		
		System.out.println(hostname +":服务器上线了!");
		// 线程阻塞,等待
		Thread.sleep(Long.MAX_VALUE);
	}

}
