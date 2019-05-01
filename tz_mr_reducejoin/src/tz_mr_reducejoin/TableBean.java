package tz_mr_reducejoin;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;
/**
 * 
 * @ClassName:  TableBean   
 * @Description:  对应字段实体类 
 * @author:  LiuJinrong
 * @date:   2019年3月15日 下午11:13:14
 */
public class TableBean implements Writable {
	
	// 封装对应字段
	/**
	 * 订单id
	 */
	private String order_id;
	/**
	 * 产品id
	 */
	private String pid;
	/**
	 * 产品数量
	 */
	private int amount;
	/**
	 * 产品名称
	 */
	private String pname;
	/**
	 * 判断是订单表还是商品表
	 */
	private String flag;
	
	public TableBean() {
		
	}
	
	public TableBean(String order_id, String pid, int amount, String pname, String flag) {
		this.order_id = order_id;
		this.pid = pid;
		this.amount = amount;
		this.pname = pname;
		this.flag = flag;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Override
	public void readFields(DataInput input) throws IOException {
		order_id = input.readUTF();
		pid = input.readUTF();
		amount = input.readInt();
		pname = input.readUTF();
		flag = input.readUTF();
	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeUTF(order_id);
		out.writeUTF(pid);
		out.writeInt(amount);
		out.writeUTF(pname);
		out.writeUTF(flag);
	}

	@Override
	public String toString() {
		return order_id + "\t" + pname + "\t" + amount;
	}

}
