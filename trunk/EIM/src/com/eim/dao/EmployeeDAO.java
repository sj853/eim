package com.eim.dao;

import java.util.ArrayList;

import com.eim.beans.Employee;

/**
 * 
 * @author element
 *employee的DAO
 */
public interface EmployeeDAO extends CommonDAO<Employee>{


	//在某个部门下，根据姓名查找员工
	 ArrayList<Employee> getElementByNameAtDept(int dept_id,String name);
	//根据性别查询所有员工
	 ArrayList<Employee> getElementBySex(byte genderint,int currentPage);
	
	 int getElementsSizeBySex(byte gender); 
}
