package com.wjb.springboot.service;

import com.wjb.springboot.entity.User;
import com.wjb.springboot.service.ex.InsertException;
import com.wjb.springboot.service.ex.PasswordNotMatchException;
import com.wjb.springboot.service.ex.UpdateException;
import com.wjb.springboot.service.ex.UserNotFoundException;
import com.wjb.springboot.service.ex.UsernameDuplicationException;

public interface IUserService {
	/**
	 * 用户注册
	 * @param user 用户信息
	 * @throws UsernameDuplicationException
	 * @throws InsertException
	 */
	void reg(User user) throws UsernameDuplicationException,InsertException;
	
	/**
	 * 用户登录
	 * @param username 用户名
	 * @param password 用户密码
	 * @return 用户信息
	 * @throws UserNotFoundException
	 * @throws PasswordNotMatchException
	 */
	User login(String username,String password) throws UserNotFoundException,PasswordNotMatchException;

	/**
	 * 修改用户密码
	 * @param uid
	 * @param oldPassword
	 * @param newPassword
	 * @param modifiedUser
	 * @throws UserNotFoundException
	 * @throws PasswordNotMatchException
	 * @throws UpdateException
	 */
	void changePassword (Integer uid,
			String oldPassword, String newPassword,
			String modifiedUser)throws 	
					UserNotFoundException, PasswordNotMatchException, UpdateException;
	
	/**
	 * 修改用户信息
	 * @param uid
	 * @param username
	 * @param user
	 * @throws UserNotFoundException
	 * @throws UpdateException
	 */
	void changeInfo(Integer uid, String username, User user)throws UserNotFoundException,UpdateException;
	
	/**
	 * 获取个人信息
	 * @param uid
	 * @return
	 */
	User getByUid(Integer uid);
	
	/**
	 * 更新头像
	 * @param uid
	 * @param avatar
	 * @param modifiedUser
	 * @throws UserNotFoundException
	 * @throws UpdateException
	 */
	void changeAvatar (
			Integer uid,
			String avatar,
			String modifiedUser)
				throws 	
					UserNotFoundException, UpdateException;
	
	
		
	}



