/*
 * @Title:  UserInfoController.java
 * @Copyright:  MrNnnn Co., Ltd. Copyright 2014-2020,  All rights reserved
 * @Description:  TODO 首页
 * @author:  MrNnnn
 * @data:  2015年5月15日14:49:50
 * @version:  V1.0
 */
package com.fate.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.SecurityConstants;
import com.fate.entity.Menu;
import com.fate.log.Log;
import com.fate.log.LogMessageObject;
import com.fate.log.impl.LogUtils;
import com.fate.service.MenuService;
import com.fate.shiro.ShiroUser;
import com.fate.utils.SecurityUtils;

/**
 * 
 * TODO 首页
 * @author  MrNnnn
 * @data:  2015年5月15日 下午2:50:01
 * @version:  V1.0
 */
@Controller
@RequestMapping("/index")
public class IndexLoginController {
	
	@Autowired
	private MenuService menuService;
	
	private static final Logger LOG = LoggerFactory
			.getLogger(IndexLoginController.class);

	private static final String LOGIN_PAGE = "system/index";

	@RequestMapping(method = RequestMethod.GET)
	@Log(message="{0}登录了系统",override = true)
	public String login(ServletRequest request, Map<String, Object> map) {
		ShiroUser shiroUser = SecurityUtils.getShiroUser();
		
		List<Menu> listMenu = menuService.findAllByRole();
		Menu menu = null;
		if(null!=listMenu&&listMenu.size()>0){
			menu = buildMenu(listMenu);
		}
		map.put(SecurityConstants.LOGIN_USER, shiroUser.getUser());
		map.put("menu", menu);
		LogUtils.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{shiroUser.getLoginName()}));
		return LOGIN_PAGE;
	}

	/** 
	 * TODO 构造菜单
	 * @param listMenu
	 * @return 
	 * @throw
	 * @return void
	 */
	private Menu buildMenu(List<Menu> listMenu) {
		List<Menu> childMenu = new ArrayList<Menu>();
		Menu rootMenu = null;
		for (Menu menu : listMenu) {
			int id = menu.getParentId();
			if(0==id){
				rootMenu = menu;
				childMenu = bulid(menu,listMenu);
			}
		}
		rootMenu.setChildMenu(childMenu);
		return rootMenu;
	}

	/** 
	 * TODO<请描述这个方法是干什么的>
	 * @param menu
	 * @return
	 * @throw
	 * @return List<Menu>
	 */
	private List<Menu> bulid(Menu menu,List<Menu> listMenu) {
		List<Menu> children = new ArrayList<Menu>();
		int id = menu.getId();
		for (Menu child : listMenu) {
			if(id==child.getParentId()){
				children.add(child);
			}
		}
		menu.setChildMenu(children);
		if(!children.isEmpty()){
			for (Menu child : children) {
				bulid(child,listMenu);
			}
		}
		return children;
	}
}
