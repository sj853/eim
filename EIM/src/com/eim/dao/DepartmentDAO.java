package com.eim.dao;

import java.util.ArrayList;

import com.eim.beans.Department;

public interface DepartmentDAO extends CommonDAO<Department>{
	
	//查询某个部门的上级部门
	ArrayList<Department> getSuperDepartment(String sid);
	
}
