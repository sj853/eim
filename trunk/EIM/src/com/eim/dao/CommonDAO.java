package com.eim.dao;

import java.util.ArrayList;

/**
 * 
 * @author element
 *	公共DAO
 */
public interface CommonDAO<T> {

	//根据ID得到实体
	ArrayList<T> getElementById(String id); 
	//根据Name得到实体
	ArrayList<T> getElementByName(String name);
	//得到所有实体
	ArrayList<T> getElements(int currentPage);
	//多个条件查询
	ArrayList<T> getElementsByUser(String sqlStr);
	
	int getElementsSize();
	
	int getElementsSizeByUser(String sqlStr);
	
	
	//删除实体
	boolean delElements(String[] ids);
	
	//添加实体
	boolean addElement(T t);
	
	//更改实体
	boolean updateElement(T t);
}
