package com.wjb.springboot.controller;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wjb.springboot.entity.District;
import com.wjb.springboot.service.IDistrictService;
import com.wjb.springboot.util.JsonResult;

@RestController
@RequestMapping("districts")
public class DistrictController extends BaseController{
	@Autowired
	IDistrictService service;
	@RequestMapping("/")
	public JsonResult<List<District>> listByParent(String parent){
		List<District> list=service.listByParent(parent);
		return new JsonResult<List<District>>(20, list);
	}
	
	

	
	
	
}
