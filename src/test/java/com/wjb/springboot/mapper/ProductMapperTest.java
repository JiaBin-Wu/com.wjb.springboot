package com.wjb.springboot.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wjb.springboot.entity.Product;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductMapperTest {
	@Autowired
	ProductMapper mapper;
	
	@Test
	public void findHotProducs() {
		List<Product> list=mapper.findHotList();
		for(Product p:list) {
			System.err.println(p);
		}
	}
	
	


	
	
}
