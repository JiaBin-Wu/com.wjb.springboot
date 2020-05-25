package com.wjb.springboot.mapper;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wjb.springboot.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {
	@Autowired
	UserMapper userMapper;
	
	@Test
	public void addNew() {
		User user=new User();
		user.setUsername("wjb2");
		user.setPassword("wjb2");
		
		System.err.println("before uid"+user.getUid());
		Integer row=userMapper.addNew(user);
		System.err.println(row);
		System.err.println("after uid"+user.getUid());
		
		
	}
	
	@Test
	public void findByUsername() {
		User user=userMapper.findByUsername("wjb2");
		System.err.println(user);
	}
	

	@Test
	public void findByUid() {
		User user=userMapper.findByUid(14);
		System.err.println(user);
	}

	@Test
	public void updatePassword() {
		Integer uid=14;
		String password="6789";
		String modifiedUser="炒鸡管理员";
		Date modifiedTime=new Date();
		Integer row=userMapper.updatePassword(
				uid, password, modifiedUser, modifiedTime);
		System.err.println("row="+row);
	}
	
	@Test
	public void updateInfo() {
		User user=new User();
		user.setUid(20);
		user.setPhone("12345678");
		user.setEmail("1234@qq.com");
		user.setGender(1);
		user.setModifiedUser("管理员");
		user.setModifiedTime(new Date());
		Integer row=userMapper.updateInfo(user);
		System.err.println("row="+row);
	}

	
	
}
