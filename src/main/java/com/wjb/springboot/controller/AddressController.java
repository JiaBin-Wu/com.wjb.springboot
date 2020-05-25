package com.wjb.springboot.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wjb.springboot.entity.Address;
import com.wjb.springboot.service.IAddressService;
import com.wjb.springboot.util.JsonResult;

@RestController
@RequestMapping("addresses")
public class AddressController extends BaseController{
	@Autowired
	IAddressService service;
	@RequestMapping("create_address")
	public JsonResult<Void> createAddress(Address address,HttpSession session){
		Integer uid=getUidFromSession(session);
		String username=getUsernameFromSession(session);
		service.addAddress(uid, username, address);
		return new JsonResult<>(SUCCESS);
	}
	
	@GetMapping("list")
	public JsonResult<List<Address>> listByUid(HttpSession session){
		Integer uid=getUidFromSession(session);
		List<Address> data=service.listByUid(uid);
		return new JsonResult<>(SUCCESS,data);
	}

	@RequestMapping("set_default")
	public JsonResult<Void> setDefault(Integer aid,HttpSession session){
		String username=getUsernameFromSession(session);
		Integer uid=getUidFromSession(session);
		service.setDefault(aid, uid, username);
		return new JsonResult<Void>(SUCCESS);
	}
	
	@RequestMapping("delete")
	public JsonResult<Void> removeAddress(Integer aid,HttpSession session){
		Integer uid=getUidFromSession(session);
		String username=getUsernameFromSession(session);
		service.removeAddress(aid, uid, username);
		return new JsonResult<Void>(SUCCESS);
		
	}
	
	
}
