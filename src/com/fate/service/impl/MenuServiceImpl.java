/*
 * @Title:  MenuServiceImpl.java
 * @Copyright:  MrNnnn Co., Ltd. Copyright 2014-2020,  All rights reserved
 * @Description:  TODO<请描述此文件是做什么的>
 * @author:  MrNnnn
 * @data:  2015年5月17日 上午9:47:49
 * @version:  V1.0
 */
package com.fate.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fate.dao.DaoSupport;
import com.fate.entity.Menu;
import com.fate.service.MenuService;

/**
 * TODO 菜单业务类
 * @author  MrNnnn
 * @data:  2015年5月17日 上午9:47:49
 * @version:  V1.0
 */
@Service
public class MenuServiceImpl implements MenuService {
	@Autowired
	private DaoSupport daoSupport;
	/**
	 * 重载方法
	 * @return 根据权限查询菜单
	 */
	@Override
	public List<Menu> findAllByRole() {
		try {
			return (List<Menu>) daoSupport.findForList("MenuInfoMapper.findAllByRole", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
