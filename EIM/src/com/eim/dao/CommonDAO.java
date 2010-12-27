package com.eim.dao;

import java.util.ArrayList;

/**
 * 
 * @author element
 *	公共DAO
 */
public interface CommonDAO<T> {

	//根据ID得到实体
	T getElementById(String id); 
	T getElmmentById(int id);
	//根据Name得到实体
	ArrayList<T> getElementByName(String name);
	//得到所有实体
	ArrayList<T> getElements();
	//多个条件查询
	ArrayList<T> getElementsByUser(String sqlStr);
}
