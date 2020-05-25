package com.wjb.springboot.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wjb.springboot.entity.User;
import com.wjb.springboot.service.ex.PasswordNotMatchException;
import com.wjb.springboot.service.ex.UserNotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
	@Autowired
	IUserService userService;
	
	@Test
	public void reg() {
		User user=new User();
		user.setUsername("wjb");
		user.setPassword("wjb");
		userService.reg(user);
	}
	
	@Test
	public void login() throws UserNotFoundException,PasswordNotMatchException{
		
		User user=userService.login("wjb", "wjb");
		System.err.println(user);
	
	}

	@Test
	public void changePassword() {
		try {
			Integer uid=13;
			String oldPassword="5678";
			String newPassword="wjb";
			String modifiedUser="管理员";
			userService.changePassword(uid, oldPassword, newPassword, modifiedUser);
		}catch (Exception e) {
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}
	}
	@Test
	public void changeAvatar() {
		try {
			Integer uid=20;
			String avatar="/ccc/1.png";
			String modifiedUser="管理员";
			userService.changeAvatar(uid, avatar, modifiedUser);
		}catch (Exception e) {
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}
	}

}
