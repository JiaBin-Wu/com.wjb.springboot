package com.wjb.springboot.mapper;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wjb.springboot.entity.District;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DistrictMapperTest {
	@Autowired
	DistrictMapper Mapper;
	
	@Test
	public void findByparent() {
		List<District> l=new ArrayList<District>();
		l=Mapper.findByParent("86");
		for(District d:l) {
			System.err.println(d);
		}
		
	}
	
}
