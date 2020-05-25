package com.wjb.springboot.mapper;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.query.Param;

import com.wjb.springboot.entity.Cart;
import com.wjb.springboot.entity.CartVO;

/**
 * 购物车相关持久层接口
 * @author wjb
 *
 */
public interface CartMapper {
	Integer saveCart(Cart cart);

	Integer updateNum(@Param("cid") Integer cid, @Param("num") Integer num, @Param("username") String username, @Param("modifiedTime") Date modifiedTime);

	Cart getByUidAndPid(@Param("uid") Integer uid,@Param("pid") Integer pid);
	
	 List<CartVO> findCartList(Integer uid);
	 
	 Cart findByCid(Integer cid);
	 
	 List<CartVO> findByCids(Integer[] cid);
	 
	 Integer deleteByCids(Integer[] cids);
}
