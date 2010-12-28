package com.eim.serviceImpl;

import java.util.ArrayList;
import java.util.Map;

import com.eim.beans.Department;
import com.eim.dao.DepartmentDAO;
import com.eim.daoImpl.DepartmentDAOImpl;
import com.eim.service.InfoService;

/**
 * 
 * @author element
 *部门服务类实现
 */
public class DepartmentServiceImpl implements InfoService {

	private DepartmentDAO deptDAO;
	
	DepartmentServiceImpl(){
		deptDAO = new DepartmentDAOImpl();
	}
	
	/**
	 * 添加逻辑
	 */
	public boolean doAdd(Department dept) {
		return deptDAO.addElement(dept);
	}

	/**
	 * 删除逻辑
	 */
	public boolean doDel(ArrayList<Department> depts) {
		int len = depts.size();
		String[] ids = new String[len];
		for(int i=0;i<len;i++){
			ids[i] = String.valueOf(depts.get(i).getId());
		}
		return deptDAO.delElements(ids);
	}

	/**
	 * 查询逻辑
	 */
	public ArrayList<Department> doSearch(Map<String, String> condition) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 更新逻辑
	 */
	public boolean doUpdate(Department dept) {
		return deptDAO.updateElement(dept);
	}


}
