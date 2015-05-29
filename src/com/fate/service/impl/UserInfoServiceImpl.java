/*
 * @Title:  UserInfoServiceImpl.java
 * @Copyright:  MrNnnn Co., Ltd. Copyright 2014-2020,  All rights reserved
 * @Description:  TODO<请描述此文件是做什么的>
 * @author:  MrNnnn
 * @data:  2015年5月15日 下午1:56:24
 * @version:  V1.0
 */
package com.fate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fate.dao.DaoSupport;
import com.fate.entity.User;
import com.fate.service.UserInfoService;
import com.fate.shiro.ShiroDbRealm;
import com.fate.shiro.ShiroDbRealm.HashPassword;

/**
 * TODO 用户信息
 * @author  MrNnnn
 * @data:  2015年5月15日 下午1:56:24
 * @version:  V1.0
 */
@Service(value="UserInfo")
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	private DaoSupport daoSupport;
	
	/**
	 * 重载方法
	 * @param user 用户注册
	 * @throws Exception 
	 */
	@Override
	public void saveRegist(User user){
		try {
			//根据原密码获取加密的密码与key
			HashPassword result = ShiroDbRealm.encryptPassword(user.getPlainPassword());
			user.setPassword(result.password);
			user.setSalt(result.salt);
			daoSupport.save("UserInfoMapper.saveRegist", user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 重载方法
	 * @param userName 根据用户名获取用户信息
	 * @return
	 */
	@Override
	public User getUserByName(String userName) {
		User user = null;
		try {
			user = (User) daoSupport.findForObject("UserInfoMapper.findUserByName", userName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
}
