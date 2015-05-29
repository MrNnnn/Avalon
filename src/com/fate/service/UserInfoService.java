/*
 * @Title:  UserInfoService.java
 * @Copyright:  MrNnnn Co., Ltd. Copyright 2014-2020,  All rights reserved
 * @Description:  TODO 用户信息处理
 * @author:  MrNnnn
 * @data:  2015年5月15日 下午1:54:51
 * @version:  V1.0
 */
package com.fate.service;

import com.fate.entity.User;

/**
 * TODO 用户信息
 * @author  MrNnnn
 * @data:  2015年5月15日 下午1:54:51
 * @version:  V1.0
 */
public interface UserInfoService {
	public void saveRegist(User user);
	public User getUserByName(String userName);
}
