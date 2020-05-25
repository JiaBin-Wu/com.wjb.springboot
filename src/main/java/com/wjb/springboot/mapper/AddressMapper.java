package com.wjb.springboot.mapper;


import java.util.Date;
import java.util.List;

import org.springframework.data.repository.query.Param;

import com.wjb.springboot.entity.Address;

/**
 * 收货地址相关持久层接口
 * @author wjb
 *
 */
public interface AddressMapper {
	/**
	 * 保存收货地址
	 * @param address
	 * @return
	 */
	Integer saveAddress(Address address);
	
	/**
	 * 查询用户收货地址条数
	 * @param uid
	 * @return
	 */
	Integer countByUid(Integer uid);
	
	/**
	 * 查询用户的地址列表
	 * @param uid
	 * @return
	 */
	List<Address> findByUid(Integer uid);
	
	/**
	 * 修改默认地址
	 * @param aid
	 * @param username
	 * @param modifiedTime
	 * @return
	 */
	Integer updateDefault(
			@Param("aid") Integer aid,
			@Param("username") String username,
			@Param("modifiedTime") Date modifiedTime);

	/**
	 * 将用户所有地址设为非默认
	 * @param uid
	 * @return
	 */
	Integer updateNonDefault(Integer uid);

	/**
	 * 用地址id查询地址信息
	 * @param aid
	 * @return
	 */
	Address findByAid(Integer aid);
	
	/**
	 * 删除用户地址
	 * @param aid
	 * @return
	 */
	Integer deleteByAid(Integer aid);
	
	/**
	 * 查询用户最后更新的地址
	 * @param uid
	 * @return
	 */
	Address findLastModified(Integer uid);
	
}
