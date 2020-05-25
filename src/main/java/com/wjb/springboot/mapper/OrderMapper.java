package com.wjb.springboot.mapper;

import com.wjb.springboot.entity.Order;
import com.wjb.springboot.entity.OrderItem;

/**
 * 订单相关持久层接口
 * @author wjb
 *
 */
public interface OrderMapper {

	/**
	 * 保存一条订单数据
	 * @param order 订单数据
	 * @return 受影响的行数
	 */
	Integer saveOrder(Order order);

	/**
	 * 保存一条订单项数据
	 * @param item 订单项数据
	 * @return 受影响的行数
	 */
	Integer saveOrderItem(OrderItem item);
}
