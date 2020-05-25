package com.wjb.springboot.service;

import java.util.List;

import com.wjb.springboot.entity.Address;
import com.wjb.springboot.service.ex.AccessDeniedException;
import com.wjb.springboot.service.ex.AddressCountLimitException;
import com.wjb.springboot.service.ex.AddressNotFoundException;
import com.wjb.springboot.service.ex.DeleteException;
import com.wjb.springboot.service.ex.InsertException;
import com.wjb.springboot.service.ex.UpdateException;

/**
 * 收货地址相关业务层接口+
 * @author wjb
 *
 */
public interface IAddressService {
	/**
	 * 添加收货地址
	 * @param address
	 */
	void addAddress(Integer uid, String username, Address address) throws InsertException, AddressCountLimitException;
	
	/**
	 * 查询地址列表
	 * @param uid
	 * @return
	 */
	List<Address> listByUid(Integer uid);
	
	/**
	 * 设置默认地址
	 * @param aid
	 * @param uid
	 * @param username
	 * @throws AddressNotFoundException
	 * @throws AccessDeniedException
	 * @throws UpdateException
	 */
	void setDefault(Integer aid,Integer uid,String username)throws AddressNotFoundException, AccessDeniedException, UpdateException;
	
	void removeAddress(Integer aid,
			Integer uid, String username) throws AddressNotFoundException, AccessDeniedException, DeleteException,UpdateException;
	
	/**
	 * 根据收货地址id获取收获地址数据
	 * @param aid 收货地址id
	 * @return 收获地址数据
	 */
	Address getByAid(Integer aid);
}
