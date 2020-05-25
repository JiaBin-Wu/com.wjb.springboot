package com.wjb.springboot.service.impl;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.wjb.springboot.entity.User;
import com.wjb.springboot.mapper.UserMapper;
import com.wjb.springboot.service.IUserService;
import com.wjb.springboot.service.ex.InsertException;
import com.wjb.springboot.service.ex.PasswordNotMatchException;
import com.wjb.springboot.service.ex.UpdateException;
import com.wjb.springboot.service.ex.UserNotFoundException;
import com.wjb.springboot.service.ex.UsernameDuplicationException;

@Service
public class UserServiceImpl implements IUserService{
	@Autowired 
	UserMapper userMapper;
	
	private User findByUsername(String username) throws UserNotFoundException{
		User result=userMapper.findByUsername(username);
		if(result==null) {
			throw new UserNotFoundException("用户名或密码错误，请重试！");
		}
		Integer isDelete=result.getIsDelete();
		if(Integer.valueOf(1).equals(isDelete)) {
			throw new UserNotFoundException("用户名或密码错误，请重试！");
		}
		return result;
	}
	
	private Integer addNew(User user) throws InsertException{
		Integer row=userMapper.addNew(user);
		if(!row.equals(1)) {
			throw new InsertException("用户注册异常!请重试");
		}
		return row;
	}
	@Override
	public void reg(User user) throws UsernameDuplicationException,InsertException{
		String username=user.getUsername();
		User result=userMapper.findByUsername(username);
		if(result!=null) {
			throw new UsernameDuplicationException("用户名已存在，请重新输入!");
		}
		
		// 补全盐值
		String salt=UUID.randomUUID().toString();
		user.setSalt(salt);
		// 获取用户输入的密码
		String password=user.getPassword();
		// 基于盐值对密码进行加密
		String md5Password=getMd5Password(password, salt);
		// 将加密后的密码添加到user中
		user.setPassword(md5Password);
		
		user.setIsDelete(0);
		Date now=new Date();
		user.setCreatedUser(username);
		user.setCreatedTime(now);
		user.setModifiedUser(username);
		user.setModifiedTime(now);
		addNew(user);
		
		
	}
	
	@Override
	public User login(String username, String password) throws UserNotFoundException,PasswordNotMatchException{
		User user=findByUsername(username);
		String salt=user.getSalt();
		String msg=getMd5Password(password, salt);
		if(!msg.equals(user.getPassword())) {
			throw new PasswordNotMatchException("用户名或密码错误，请重试！");
		}
		user.setSalt(null);
		user.setPassword(null);
		user.setIsDelete(null);
		return user;
	}
	
	private String getMd5Password(String password,String salt) {
		// salt+password+salt 进行3次加密
		String msg=salt+password+salt;
		for(int i=0;i<3;i++) {
			msg=DigestUtils.md5DigestAsHex(msg.getBytes());
		}
		return msg;
	}

	private User findByUid(Integer uid) {
		User user=userMapper.findByUid(uid);
		if(user==null) {
			throw new UserNotFoundException("修改密码异常！用户数据不存在");
		}
		if(user.getIsDelete().equals(1)) {
			// 是：UserNotFoundException
			throw new UserNotFoundException("修改密码异常！用户数据不存在");	
		}
		return user;
		
	}
	
	@Override
	public void changePassword(Integer uid, String oldPassword, String newPassword, String modifiedUser)
			throws UserNotFoundException, PasswordNotMatchException, UpdateException {
		User user=findByUid(uid);
		String salt=user.getSalt();
		String oldMd5Password=getMd5Password(oldPassword, salt);
		if(!oldMd5Password.equals(user.getPassword())) {
			// 是：PasswordNotMatchException
			throw new PasswordNotMatchException("修改密码异常！原始密码错误");
		}
		String newMd5Password=getMd5Password(newPassword, salt);
		updatePassword(uid, newMd5Password, modifiedUser);
		
		
	}
	
	private Integer updatePassword(Integer uid, String newMd5Password, String modifiedUser) throws UpdateException{
		Integer row=userMapper.updatePassword(uid, newMd5Password, modifiedUser, new Date());
		if(!row.equals(1)) {
			// 是：UpdateException
			throw new UpdateException("修改密码异常！请联系管理员");
		}
		return row;
	}

	@Override
	public void changeAvatar(Integer uid, String avatar, String modifiedUser)
			throws UserNotFoundException, UpdateException {
		findByUid(uid);
		Date modifiedTime=new Date();
		updateAvatar(uid, avatar, modifiedUser, modifiedTime);
		
		
	}
	
	private Integer updateAvatar(Integer uid,
			String avatar,String modifiedUser,Date modifiedTime
			) {
		Integer row=userMapper.updateAvatar(uid, avatar, modifiedUser, modifiedTime);
		if(!Integer.valueOf(1).equals(row)) {
			throw new UpdateException("更新异常，请重试！");
		}
		
		return row;
	}

	@Override
	public void changeInfo(Integer uid, String username, User user) throws UserNotFoundException, UpdateException {
		findByUid(uid);
		user.setUid(uid);
		user.setModifiedUser(username);
		user.setModifiedTime(new Date());
		updateInfo(user);
	}
	
	private Integer updateInfo(User user) throws UpdateException{
		Integer row=userMapper.updateInfo(user);
		if(!Integer.valueOf(1).equals(row)) {
			throw new UpdateException("个人信息更新异常！");
		}
		return row;
		
	}

	@Override
	public User getByUid(Integer uid) {
		return findByUid(uid);
	}
	

	
	
	


}
