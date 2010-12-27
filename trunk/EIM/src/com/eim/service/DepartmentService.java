package com.eim.service;

import java.util.ArrayList;
import java.util.Map;

import com.eim.beans.Department;

public interface DepartmentService {

	
	//处理查询模块
	ArrayList<Department> doSearch(Map<String,String> condition);
	
	//处理添加模块
	boolean doAdd(Department dept);
	
	//处理删除模块
	boolean doDel(ArrayList<Department> depts);
	
	//处理更新模块
	boolean doUpdate(Department dept);

}
