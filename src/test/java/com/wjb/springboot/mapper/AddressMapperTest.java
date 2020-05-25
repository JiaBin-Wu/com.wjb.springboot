package com.wjb.springboot.mapper;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wjb.springboot.entity.Address;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressMapperTest {
	@Autowired
	AddressMapper addressMapper;
	
	@Test
	public void addAddress() {
		Address address=new Address();
		address.setUid(20);
		address.setAreaCode(678890);
		addressMapper.saveAddress(address);
	}
	
	@Test
	public void countByUid() {
		Integer r=addressMapper.countByUid(20);
		System.err.println(r);
	}
	
	@Test
	public void updateNonDefault() {
		Integer rows=addressMapper.updateNonDefault(10);
		System.err.println(rows);
	}
	
	@Test
	public void updateDefault() {
		Integer row=addressMapper.updateDefault(14, "张三丰", new Date());
		System.err.println(row);
	}
	
	@Test
	public void findByAid() {
		Address addr=addressMapper.findByAid(14);
		System.err.println(addr);
	}


	
	
}
