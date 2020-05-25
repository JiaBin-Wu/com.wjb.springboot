package com.wjb.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wjb.springboot.entity.Product;
import com.wjb.springboot.service.IProductService;
import com.wjb.springboot.util.JsonResult;

@RestController
@RequestMapping("products")
public class ProductController extends BaseController {
	@Autowired
	IProductService service;
	@RequestMapping("hot")
	public JsonResult<List<Product>> getHotList(){
		// 查询
		List<Product> data = service.getHotList();
		// 返回
		return new JsonResult<List<Product>>(SUCCESS, data);
	}

	@GetMapping("{id}/get")
	public JsonResult<Product> getById(@PathVariable("id")Integer id){
		Product product=service.getById(id);
		return new JsonResult<Product>(SUCCESS,product);
	}
}
