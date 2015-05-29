/*
 * @Title:  UserInfoController.java
 * @Copyright:  MrNnnn Co., Ltd. Copyright 2014-2020,  All rights reserved
 * @Description:  TODO 用户信息
 * @author:  MrNnnn
 * @data:  2015年5月15日 上午11:38:06
 * @version:  V1.0
 */
package com.fate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fate.entity.User;
import com.fate.service.UserInfoService;


/**
 * TODO 用户信息
 * @author  MrNnnn
 * @data:  2015年5月15日 上午11:38:06
 * @version:  V1.0
 */
@Controller
@RequestMapping("/UserInfo")
public class UserInfoController {
	
	@Autowired
	private UserInfoService userInfoService;
	
	private static String INDEX = "index";
	
	/**
	 * 
	 * TODO 用户注册
	 * @param user
	 * @return
	 * @throw
	 * @return String
	 */
	@RequestMapping(value = "/regist",method = RequestMethod.POST)
	public String regist(User user){
		userInfoService.saveRegist(user);
		return INDEX;
	}
	
}
