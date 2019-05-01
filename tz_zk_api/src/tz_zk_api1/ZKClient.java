package tz_zk_api1;

import java.io.IOException;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.ZooDefs.Ids;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @ClassName:  ZKClient   
 * @Description:  客户端 
 * @author:  LiuJinrong
 * @date:   2019年3月17日 下午10:10:08
 */
public class ZKClient {

	// 连接服务器地址
	private String connectString = "192.168.133.128:2181,192.168.133.129:2181,192.168.133.130:2181";
	// 连接超时
	private int sessionTimeout = 3000;
	// 初始化ZooKeeper客户端对象
	ZooKeeper zkCli;

	/**
	 * 初始化客户端
	 */
	@Before
	public void init() throws IOException {

		zkCli = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
			/**
			 * 回调监听
			 */
			@Override
			public void process(WatchedEvent event) {

				List<String> children = null;
				try {
					children = zkCli.getChildren("/", true);
				} catch (KeeperException | InterruptedException e) {
					e.printStackTrace();
				}

				/*for (String c : children) {
					System.out.println(c);
				}*/

			}
		});
	}

	/**
	 * @Description:  获取子节点
	 */
	@Test
	public void getChild() throws KeeperException, InterruptedException {

		List<String> children = zkCli.getChildren("/", true);

		for (String c : children) {
			System.out.println(c);
		}
		// 线程休眠最大值
		Thread.sleep(Long.MAX_VALUE);
	}

	/**
	 * @Description:  创建子节点
	 */
	@Test
	public void createZnode() throws KeeperException, InterruptedException {

		String path = zkCli.create("/bbq", "shaokao".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		System.out.println(path);
	}

	/**
	 * @Description:  删除节点
	 */
	@Test
	public void rmChildData() throws KeeperException, InterruptedException {
		/**
		 * 获取节点数据
		 */
		/*byte[] data = zkCli.getData("/bbq", true, null);
		System.out.println(new String(data));*/
		/**
		 * 删除节点
		 */
		zkCli.delete("/bbq", -1);
	}

	/**
	 * @Description:  修改数据
	 */
	@Test
	public void setData() throws KeeperException, InterruptedException {
		
		zkCli.setData("/ljr", "17".getBytes(), -1);
	}

	/**
	 * @Description:  判断节点是否存在
	 */
	@Test
	public void testExist() throws KeeperException, InterruptedException {
		
		Stat exists = zkCli.exists("/ljr", false);
		System.out.println(exists == null ? "not exists" : "exist");
	}

}
