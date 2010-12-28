package com.eim.service;

import java.util.ArrayList;
import java.util.Map;


/**
 * 
 * @author element
 *服务接口（逻辑判断类）
 */
public interface InfoService<T> {

	
	//处理查询模块
	ArrayList<T> doSearch(Map<String,String> condition);
	
	//处理添加模块
	boolean doAdd(T t);
	
	//处理删除模块
	boolean doDel(ArrayList<T> ts);
	
	//处理更新模块
	boolean doUpdate(T t);

}
