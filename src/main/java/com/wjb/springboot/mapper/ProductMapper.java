package com.wjb.springboot.mapper;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.wjb.springboot.entity.Product;

/**
 * 商品相关持久层接口
 * @author wjb
 *
 */
public interface ProductMapper {
	/**
	 * 查热销商品
	 * @return
	 */
	List<Product> findHotList();
	
	/**
	 * 根据商品id查商品详情
	 * @param id
	 * @return
	 */
	Product findById(Integer id);
	
	Integer updateNum(
			@Param("num") Integer num,
			@Param("id") Integer id);
}
