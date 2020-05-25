package com.wjb.springboot.service;

import java.util.List;

import com.wjb.springboot.entity.CartVO;
import com.wjb.springboot.service.ex.AccessDeniedException;
import com.wjb.springboot.service.ex.CartNotFoundException;
import com.wjb.springboot.service.ex.InsertException;
import com.wjb.springboot.service.ex.UpdateException;

/**
 * 购物车业务层接口
 * @author wjb
 *
 */
public interface ICartService {
	/**
	 * 添加商品到购物车
	 * @param num
	 * @param uid
	 * @param pid
	 * @param username
	 * @throws UpdateException
	 * @throws InsertException
	 */
	void createCart(Integer num,Integer uid, Integer pid,String username)throws UpdateException,InsertException;
	
	List<CartVO> getCartList(Integer uid);
	
	/**
	 *  增加购物车中的商品的数量
	 * @param cid 商品的id
	 * @param num 数量的增量
	 * @param uid 用户的id
	 * @param username 最后修改人姓名
	 */
	void addNum(Integer cid, Integer num, 
			Integer uid, String username)throws CartNotFoundException,
			AccessDeniedException, UpdateException;

	List<CartVO> getByCids(Integer[] cids,Integer uid);
	
	void removeByCids(Integer[] cids, Integer uid);
}
