package com.eim.serviceImpl;

import java.util.ArrayList;
import java.util.Map;

import com.eim.beans.Department;
import com.eim.dao.DepartmentDAO;
import com.eim.daoImpl.DepartmentDAOImpl;
import com.eim.service.DepartmentService;

public class DepartmentServiceImpl implements DepartmentService {

	private DepartmentDAO deptDAO;
	
	DepartmentServiceImpl(){
		deptDAO = new DepartmentDAOImpl();
	}
	
	public boolean doAdd(Department dept) {
		return deptDAO.addElement(dept);
	}

	public boolean doDel(ArrayList<Department> depts) {
		int len = depts.size();
		int[] ids = new int[len];
		for(int i=0;i<len;i++){
			ids[i] = depts.get(i).getId();
		}
		return deptDAO.delElements(ids);
	}

	public ArrayList<Department> doSearch(Map<String, String> condition) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean doUpdate(Department dept) {
		return deptDAO.updateElement(dept);
	}


}
