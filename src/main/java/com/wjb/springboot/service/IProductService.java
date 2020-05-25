package com.wjb.springboot.service;

import java.util.List;

import com.wjb.springboot.entity.Product;

/**
 * 商品相关业务层接口
 * @author wjb
 *
 */
public interface IProductService {
	/**
	 * 获取热销商品
	 * @return
	 */
	List<Product> getHotList();
	
	/**
	 * 根据id查商品详情
	 * @param id
	 * @return
	 */
	Product getById(Integer id);
	
	void reduceNum(Integer pid, Integer num);
}
