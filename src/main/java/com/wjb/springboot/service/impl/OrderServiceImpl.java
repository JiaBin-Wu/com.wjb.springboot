package com.wjb.springboot.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wjb.springboot.entity.Address;
import com.wjb.springboot.entity.CartVO;
import com.wjb.springboot.entity.Order;
import com.wjb.springboot.entity.OrderItem;
import com.wjb.springboot.mapper.OrderMapper;
import com.wjb.springboot.service.IAddressService;
import com.wjb.springboot.service.ICartService;
import com.wjb.springboot.service.IOrderService;
import com.wjb.springboot.service.IProductService;
import com.wjb.springboot.service.ex.AddressNotFoundException;
import com.wjb.springboot.service.ex.CartNotFoundException;
import com.wjb.springboot.service.ex.InsertException;
@Service
public class OrderServiceImpl implements IOrderService{
	@Autowired
	OrderMapper mapper;
	@Autowired
	IAddressService addressService;
	@Autowired
	ICartService cartService;
	@Autowired
	IProductService productService;
	@Override
	public void createOrder(Integer aid, Integer[] cids, Integer uid, String username) 
			throws InsertException,CartNotFoundException,AddressNotFoundException {
		
			// 创建当前时间的对象 now
			Date now=new Date();

			// 根据cids查询对应的CartVO的集合
			List<CartVO> cartVOs=cartService.getByCids(cids, uid);
			// 判断结果集合的长度是否为0
			if(cartVOs.size()==0) {
				// 是：CartNotFoundException
				throw new CartNotFoundException("创建订单记录异常！未找到相关购物车记录");
			}

			// 计算totalPrice
			Long totalPrice=0L;
			for(CartVO vo:cartVOs) {
				totalPrice+=(vo.getRealPrice()*vo.getNum());
			}

			// 创建一个Order对象
			Order order=new Order();
			// 补充uid
			order.setUid(uid);
			// 根据aid查询收获地址数据
			Address address=addressService.getByAid(aid);
			// 判断结果是否为null
			if(address==null) {
				// 是：AddressNotFoundException
				throw new AddressNotFoundException("创建订单记录异常！未查到对应的收货地址！");
			}

			// 补充recv*数据
			order.setRecvName(address.getName());
			order.setRecvPhone(address.getPhone());
			order.setRecvProvince(address.getProvinceName());
			order.setRecvCity(address.getCityName());
			order.setRecvArea(address.getAreaName());
			order.setRecvAddress(address.getAddress());
			// 补充status -> 0 未支付 1 已支付 2已取消 
			order.setStatus(0);
			// 补充price -> 总价，在上面已经计算过
			order.setPrice(totalPrice);
			// 补充orderTime -> now
			order.setOrderTime(now);
			// 补充4项日志数据
			order.setCreatedUser(username);
			order.setCreatedTime(now);
			order.setModifiedUser(username);
			order.setModifiedTime(now);
			// 添加订单数据
			saveOrder(order);

			List<Integer> list=new ArrayList<Integer>();

			// 遍历上面查到的CartVO的集合
			for(CartVO vo:cartVOs) {
				list.add(vo.getCid());

				//-- 创建一个OrderItem对象
				OrderItem item=new OrderItem();
				//-- 补充 oid
				item.setOid(order.getId());
				//-- 补充 pid,num,price,image,titel
				item.setPid(vo.getPid());
				item.setNum(vo.getNum());
				item.setPrice(vo.getRealPrice());
				item.setImage(vo.getImage());
				item.setTitle(vo.getTitle());
				//-- 补充 4项日志数据
				item.setCreatedUser(username);
				item.setCreatedTime(now);
				item.setModifiedUser(username);
				item.setModifiedTime(now);
				//-- 添加一个OrderItem数据
				saveOrderItem(item);
				productService.reduceNum(vo.getPid(), vo.getNum());
			}

			// 未完，待续

			cartService.removeByCids(cids, uid);
			



		}

	/**
	 * 保存一条订单数据
	 * @param order 订单数据
	 * @return 受影响的行数
	 */
	private void saveOrder(Order order) throws InsertException {
		Integer row=mapper.saveOrder(order);
		if(row!=1) {
			throw new InsertException("创建订单记录异常！插入订单数据异常！");
		}
	}

	/**
	 * 保存一条订单项数据
	 * @param item 订单项数据
	 * @return 受影响的行数
	 */
	private void saveOrderItem(OrderItem item) throws InsertException{
		Integer row=mapper.saveOrderItem(item);
		if(row!=1) {
			throw new InsertException("创建订单记录异常！插入订单项数据异常！");
		}
	}

}
