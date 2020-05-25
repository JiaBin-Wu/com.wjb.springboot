package com.wjb.springboot.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wjb.springboot.entity.Address;
import com.wjb.springboot.service.ex.ServiceException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressServiceTest {
	@Autowired
	IAddressService service;
	
	@Test
	public void addAddress() {
		try {
			Address address=new Address();
			address.setName("hahah");
			address.setAreaCode(23);
			service.addAddress(20, "wjb4", address);
		}catch(ServiceException e) {
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}
		
	}
	
	@Test
	public void removeAddress() {
		try {
			service.removeAddress(22, 20, "wjb4");
		}catch(ServiceException e) {
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}
	}

}
