package com.wjb.springboot.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wjb.springboot.entity.Address;
import com.wjb.springboot.entity.District;
import com.wjb.springboot.mapper.AddressMapper;
import com.wjb.springboot.service.IAddressService;
import com.wjb.springboot.service.IDistrictService;
import com.wjb.springboot.service.ex.AccessDeniedException;
import com.wjb.springboot.service.ex.AddressCountLimitException;
import com.wjb.springboot.service.ex.AddressNotFoundException;
import com.wjb.springboot.service.ex.DeleteException;
import com.wjb.springboot.service.ex.InsertException;
import com.wjb.springboot.service.ex.UpdateException;
@Service
public class AddressServiceImpl implements IAddressService{
	public static final Integer MAX_ADDRESS_COUNT=10;
	@Autowired
	AddressMapper addressMapper;
	@Autowired 
	IDistrictService service;
	@Override
	public void addAddress(Integer uid, String username, Address address)
			throws InsertException, AddressCountLimitException {
		Integer row=countByUid(uid);
		if(row>=MAX_ADDRESS_COUNT) {
			throw new AddressCountLimitException("超过最大地址数量:"+MAX_ADDRESS_COUNT);
		}
		address.setUid(uid);
		int isDefault=row==0 ? 1 : 0;
		address.setIsDefault(isDefault);
		String provinceName=getNameByCode(address.getProvinceCode().toString());
		String cityName=getNameByCode(address.getCityCode().toString());
		String areaName=getNameByCode(address.getAreaCode().toString());
		address.setProvinceName(provinceName);
		address.setCityName(cityName);
		address.setAreaName(areaName);
		Date now =new Date();
		address.setCreatedUser(username);
		address.setCreatedTime(now);
		address.setModifiedUser(username);
		address.setModifiedTime(now);
		saveAddress(address);
	}
	
	public String getNameByCode(String code){
		District dist=service.getByCode(code);
		return dist==null?"":dist.getName();
	}
	
	@Override
	public List<Address> listByUid(Integer uid){
		return findByUid(uid);
	}
	
	@Override
	@Transactional
	public void setDefault(Integer aid, Integer uid, String username)
			throws AddressNotFoundException, AccessDeniedException, UpdateException {
		// 使用aid查地址数据
		Address address=findByAid(aid);
		// 判断结果是否为null
		if(address==null) {
			// 是：AddressNotFoundException
			throw new AddressNotFoundException("设置默认收货地址异常！地址数据不存在");
		}

		// 查询结果中的uid和方法参数的uid是否不一致
		if(!address.getUid().equals(uid)) {
			// 是：AccessDeniedException
			throw new AccessDeniedException("设置默认收货地址异常！访问权限不足");
		}

		// 将该用户的所有收货地址设为非默认
		updateNonDefault(uid);
		// 将该用户指定的收货地址设为默认
		updateDefault(aid, username, new Date());
	}
	
	@Override
	@Transactional
	public void removeAddress(Integer aid, Integer uid, String username)
			throws AddressNotFoundException, AccessDeniedException, DeleteException, UpdateException {
		// 使用aid查地址数据
		Address result=findByAid(aid);
		// 判断结果是否为null
		if(result==null) {
			// 是：AddressNotFoundException
			throw new AddressNotFoundException("删除收货地址异常！地址数据不存在");
		}

		// 查询结果中的uid和方法参数的uid是否不一致
		if(!result.getUid().equals(uid)) {
			// 是：AccessDeniedException
			throw new AccessDeniedException("删除收货地址异常！访问权限不足");
		}

		// 删除aid对应的地址数据
		deleteByAid(aid);
		
		// 判断刚才的查询结果中的isDefault是否不为1
		if(result.getIsDefault()!=1) {
			return;
		}

		// 查看当前用户剩余的收货地址条数
		Integer count = countByUid(uid);
		// 判断条数是否为0
		if(count==0) {
			return;
		}

		// 查询该用户的最后修改的收货地址
		Address lastModifiedAddress=findLastModified(uid);
		// 将该条记录设为该用户的默认收货地址
		updateDefault(lastModifiedAddress.getAid(), username, new Date());
	}
	
	@Override
	public Address getByAid(Integer aid) {
		return findByAid(aid);
	};

	
	private Integer saveAddress(Address address) throws InsertException{
		Integer row=addressMapper.saveAddress(address);
		if(!Integer.valueOf(1).equals(row)) {
			throw new InsertException("用户信息插入异常！");
		}
		return row;
	}
	
	private Integer countByUid(Integer uid){
		if(uid==null || uid<1){
			throw new IllegalArgumentException("添加收货地址异常！");
		}
		return addressMapper.countByUid(uid);
	}

	private List<Address> findByUid(Integer uid){
		List<Address> list=addressMapper.findByUid(uid);
		for(Address addr:list){
			addr.setZip(null);
			addr.setTel(null);
			// 将4项日志数据设为null
		}
		return list;
	}
	
	/**
	 * 将一条收货地址设为默认收货地址
	 * @param aid 收货地址id
	 * @param uesrname 最后修改人姓名
	 * @param modifiedTime 最后修改时间
	 * @return 受影响的行数
	 */
	private void updateDefault(Integer aid,
			String username, Date modifiedTime) throws UpdateException {
		Integer row=addressMapper.updateDefault(aid, username, modifiedTime);
		if(row!=1) {
			throw new UpdateException("设置默认收货地址异常！请联系管理员");
		}
		
	}

	/**
	 * 将一个用户的所有收货地址设为非默认
	 * @param uid 用户id
	 * @return 受影响的行数
	 */
	private void updateNonDefault(Integer uid) throws UpdateException{
		Integer rows=addressMapper.updateNonDefault(uid);
		if(rows<1) {
			throw new UpdateException("设置默认收货地址异常！请联系管理员");
		}
	}

	/**
	 * 根据aid查询一条收货地址数据
	 * @param aid 收货地址id
	 * @return 收货地址数据 或 null
	 */
	private Address findByAid(Integer aid) {
		return addressMapper.findByAid(aid);
	}

	/**
	 * 根据aid删除一条收货地址记录
	 * @param aid 收货地址id
	 * @return 受影响的行数
	 */
	private void deleteByAid(Integer aid) throws DeleteException {
		Integer row=addressMapper.deleteByAid(aid);
		if(row!=1) {
			throw new DeleteException("删除收货地址异常！请联系管理员");
		}
	};
	
	/**
	 * 查询一个用户的最后修改的收货地址
	 * @param uid 用户的id
	 * @return 最后修改的收货地址
	 */
	private Address findLastModified(Integer uid) {
		return addressMapper.findLastModified(uid);
	};


}
