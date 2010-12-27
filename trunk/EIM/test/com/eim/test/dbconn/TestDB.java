package com.eim.test.dbconn;


import java.util.ArrayList;

import com.eim.beans.Department;
import com.eim.dao.DepartmentDAO;
import com.eim.daoImpl.DepartmentDAOImpl;
import com.eim.db.DataBase;
import com.eim.util.PropertiesUtil;

public class TestDB {

	public static void main(String[] args) {
		new TestDB().test(null);
	}
	
	public void test(String path){
		DepartmentDAO deptDAO = new DepartmentDAOImpl();
		ArrayList<Department> depts = deptDAO.getElements();
		for (Department department : depts) {
			System.out.println(department.getName());
		}
	}
}
