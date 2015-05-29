/*
 * @Title:  LogServiceImpl.java
 * @Copyright:  MrNnnn Co., Ltd. Copyright 2014-2020,  All rights reserved
 * @Description:  TODO<请描述此文件是做什么的>
 * @author:  MrNnnn
 * @data:  2015年5月15日 下午4:06:14
 * @version:  V1.0
 */
package com.fate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fate.dao.DaoSupport;
import com.fate.entity.LogInfo;
import com.fate.service.LogService;

/**
 * TODO 日志记录
 * @author  MrNnnn
 * @data:  2015年5月15日 下午4:06:14
 * @version:  V1.0
 */
@Service(value="logServiceImpl")
public class LogServiceImpl implements LogService {

	@Autowired
	private DaoSupport daoSupport;
	
	/**
	 * 重载方法
	 * @param log 保存日志
	 */
	@Override
	public void save(LogInfo log) {
		try {
			daoSupport.save("LogInfoMapper.save", log);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
