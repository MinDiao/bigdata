package com.itstaredu.assistranking;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * 
 * 类描述：辅助排序-方法重写
 * 作者： LiuJinrong  
 * 创建日期：2018年12月23日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0
 */
public class OrderGroupingComparator extends WritableComparator {

	/**
	 * 构造方法必须添加
	 */
	protected OrderGroupingComparator() {
		super(OrderBean.class, true);
	}
	
	/**
	 * 重写比较
	 */
	@Override
	public int compare(WritableComparable a, WritableComparable b) {
		
		OrderBean aBean = (OrderBean) a;
		OrderBean bBean = (OrderBean) b;
		
		int rs;
		// id不同则不是同一对象
		if (aBean.getOrder_id() > bBean.getOrder_id()) {
			rs = 1;
		} else if (aBean.getOrder_id() < bBean.getOrder_id()) {
			rs = -1;
		} else {
			rs = 0;
		}
		
		return rs;
	}

}
