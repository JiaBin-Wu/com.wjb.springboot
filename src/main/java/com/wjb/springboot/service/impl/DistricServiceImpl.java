package com.wjb.springboot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wjb.springboot.entity.District;
import com.wjb.springboot.mapper.DistrictMapper;
import com.wjb.springboot.service.IDistrictService;

@Service
public class DistricServiceImpl implements IDistrictService{
	@Autowired
	DistrictMapper mapper;
	@Override
	public List<District> listByParent(String parent) {
		
		return findByParent(parent);
	}
	
	@Override
	public District getByCode(String code){
		return findByCode(code);
	}
	
	
	private District findByCode(String code){
		return mapper.findByCode(code);
	}

	
	private List<District> findByParent(String parent){
		return mapper.findByParent(parent);
		
	}
}
