/*
 * @Title:  Menu.java
 * @Copyright:  MrNnnn Co., Ltd. Copyright 2014-2020,  All rights reserved
 * @Description:  TODO<请描述此文件是做什么的>
 * @author:  MrNnnn
 * @data:  2015年5月17日 上午9:40:59
 * @version:  V1.0
 */
package com.fate.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO 菜单实体
 * @author  MrNnnn
 * @data:  2015年5月17日 上午9:40:59
 * @version:  V1.0
 */
public class Menu {

	private int id;
	private String title;
	private String url;
	private int parentId;
	private List<Menu> childMenu = new ArrayList<Menu>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public List<Menu> getChildMenu() {
		return childMenu;
	}
	public void setChildMenu(List<Menu> childMenu) {
		this.childMenu = childMenu;
	}
	@Override
	public String toString() {
		return "Menu [id=" + id + ", title=" + title + ", url=" + url
				+ ", parentId=" + parentId + ", childMenu=" + childMenu + "]";
	}
	
}
