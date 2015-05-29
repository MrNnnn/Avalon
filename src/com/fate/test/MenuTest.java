///*
// * @Title:  MenuTest.java
// * @Copyright:  MrNnnn Co., Ltd. Copyright 2014-2020,  All rights reserved
// * @Description:  TODO<请描述此文件是做什么的>
// * @author:  MrNnnn
// * @data:  2015年5月17日 上午10:31:36
// * @version:  V1.0
// */
//package com.fate.test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import com.fate.controller.IndexLoginController;
//import com.fate.entity.Menu;
//import com.mysql.fabric.xmlrpc.base.Array;
//
///**
// * TODO<请描述这个类是干什么的>
// * @author  MrNnnn
// * @data:  2015年5月17日 上午10:31:36
// * @version:  V1.0
// */
//public class MenuTest {
//	static List<Menu> list = new ArrayList<Menu>();
//	public static void main(String[] args) {
//		list.add(new Menu(1,"根","#",0));
//		list.add(new Menu(2,"子1","#",1));
//		list.add(new Menu(3,"子2","/a/ab",1));
//		list.add(new Menu(4,"子子1","/a/b/c",2));
//		list.add(new Menu(5,"子子2","/a/b/ac",2));
//		buildMenu(list);
//	}
//	private static void buildMenu(List<Menu> listMenu) {
//		List<Menu> childMenu = new ArrayList<Menu>();
//		Menu rootMenu = null;
//		for (Menu menu : listMenu) {
//			int id = menu.getParentId();
//			if(0==id){
//				rootMenu = menu;
//				childMenu = bulid(menu,listMenu);
//			}
//		}
//		rootMenu.setChildMenu(childMenu);
//		System.out.println(rootMenu);
//	}
//
//	/** 
//	 * TODO<请描述这个方法是干什么的>
//	 * @param menu
//	 * @return
//	 * @throw
//	 * @return List<Menu>
//	 */
//	private static List<Menu> bulid(Menu menu,List<Menu> listMenu) {
//		List<Menu> children = new ArrayList<Menu>();
//		int id = menu.getId();
//		for (Menu child : listMenu) {
//			if(id==child.getParentId()){
//				children.add(child);
//			}
//		}
//		menu.setChildMenu(children);
//		if(!children.isEmpty()){
//			for (Menu child : children) {
//				bulid(child,listMenu);
//			}
//		}
//		return children;
//	}
//}
