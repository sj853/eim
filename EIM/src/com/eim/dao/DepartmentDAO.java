package com.eim.dao;

import com.eim.beans.Department;

public interface DepartmentDAO extends CommonDAO<Department>{
	
	//查询某个部门的上级部门
	Department getSuperDepartment(String name);
	
}
