package com.wjb.springboot.service;

import java.util.List;

import com.wjb.springboot.entity.District;

/**
 * 省市信息业务层接口
 * @author wjb
 *
 */
public interface IDistrictService {
	/**
	 * 根据父级id查询省市信息
	 * @param parent
	 * @return
	 */
	List<District> listByParent(String parent);
	
	/**
	 * 根据地址编号查询对应名称
	 * @param code
	 * @return
	 */
	District getByCode(String code);
}
