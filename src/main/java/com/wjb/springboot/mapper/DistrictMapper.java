package com.wjb.springboot.mapper;

import java.util.List;

import com.wjb.springboot.entity.District;
/**
 * 省市信息持久层相关接口
 * @author wjb
 *
 */
public interface DistrictMapper {
	/**
	 * 根据父级名称查询省市信息
	 * @param parent
	 * @return
	 */
	List<District> findByParent(String parent);
	
	/**
	 * 根据地址编号查询对应名称
	 * @param code
	 * @return
	 */
	District findByCode(String code);
}
