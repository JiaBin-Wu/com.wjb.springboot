package com.wjb.springboot.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.wjb.springboot.entity.User;

/**
 * 持久层用户相关查询
 * @author wjb
 *
 */
public interface UserMapper {
	/**
	 * 插入一条用户数据
	 * @param user 用户信息
	 * @return 插入条数
	 */
	Integer addNew(User user);
	/**
	 * 根据用户名查询用户信息
	 * @param username 用户名称
	 * @return username对应用户信息
	 */
	User findByUsername(String username);
	
	/**
	 * 用uid查询用户信息
	 * @param uid
	 * @return  用户信息
	 */
	User findByUid(Integer uid);
	
	/**
	 * 修改密码
	 * @param uid
	 * @param password
	 * @param modifiedUser
	 * @param modifiedTime
	 * @return 用户信息
	 */
	Integer updatePassword(
			@Param("uid") Integer uid,
			@Param("password") String password,
			@Param("modifiedUser") String modifiedUser,
			@Param("modifiedTime") Date modifiedTime);
	
	/**
	 * 修改用户信息
	 * @param user
	 * @return
	 */
	Integer updateInfo(User user);
	
	/**
	 * 更新用户头像
	 * @param uid
	 * @param avatar
	 * @param modifiedUser
	 * @param modifiedTime
	 * @return
	 */
	Integer updateAvatar(
			@Param("uid") Integer uid,
			@Param("avatar") String avatar,
			@Param("modifiedUser") String modifiedUser,
			@Param("modifiedTime") Date modifiedTime
			);
}
