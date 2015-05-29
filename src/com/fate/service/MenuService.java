/*
 * @Title:  MenuService.java
 * @Copyright:  MrNnnn Co., Ltd. Copyright 2014-2020,  All rights reserved
 * @Description:  TODO<请描述此文件是做什么的>
 * @author:  MrNnnn
 * @data:  2015年5月17日 上午9:46:53
 * @version:  V1.0
 */
package com.fate.service;

import java.util.List;

import com.fate.entity.Menu;

/**
 * TODO　菜单
 * @author  MrNnnn
 * @data:  2015年5月17日 上午9:46:53
 * @version:  V1.0
 */
public interface MenuService {
	public List<Menu> findAllByRole();
}
