package com.eim.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.eim.beans.Department;
import com.eim.dao.DepartmentDAO;
import com.eim.db.DataBase;

public class DepartmentDAOImpl implements DepartmentDAO{

	private DataBase db;
	
	public DepartmentDAOImpl() {
		db = new DataBase();
	}
	
	public Department getSuperDepartment(String name) {
		return null;
	}

	public Department getElementById(String id) {
		Department dept = new Department();
		ResultSet rs = db.getResult("select * from department where dept_id=?", new String[]{id});
		try {
			dept.setId(rs.getInt("dept_id"));
			dept.setName(rs.getString("dept_name"));
			dept.setSuperid(rs.getInt("super_dept_id"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dept;
	}

	public ArrayList<Department> getElementByName(String name) {
		Department dept = new Department();
		ArrayList<Department> depts = new ArrayList<Department>();
		ResultSet rs = db.getResult("select * from department where dept_name=?", new String[]{name});
		try {
			dept.setId(rs.getInt("dept_id"));
			dept.setName(rs.getString("dept_name"));
			dept.setSuperid(rs.getInt("super_dept_id"));
			depts.add(dept);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return depts;
	}

	public ArrayList<Department> getElements() {
		return null;
	}

	public Department getElmmentById(int id) {
		return null;
	}

	public ArrayList<Department> getElementsByUser(String sqlStr) {
		return null;
	}
	

}
