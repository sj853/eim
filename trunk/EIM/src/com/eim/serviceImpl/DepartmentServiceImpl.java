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
public class DepartmentServiceImpl implements InfoService<Department> {

	private DepartmentDAOImpl deptDAO;
	
	public DepartmentServiceImpl(int rowPerPage){
		deptDAO = new DepartmentDAOImpl(rowPerPage);
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
	 * 更新逻辑
	 */
	public boolean doUpdate(Department dept) {
		return deptDAO.updateElement(dept);
	}
	
	/**
	 * 查询逻辑
	 */

	public ArrayList<Department> doSearch(String key, String val,int currentPage){
			if("id".equals(key)){
				return deptDAO.getElementById(val);
			}
			else if("name".equals(key)){
				return deptDAO.getElementByName(val);
			}
			
			else if("all".equals(key)){
				return deptDAO.getElements(currentPage);
			}
			else{
				return deptDAO.getElementsByUser(val);
			}
	}

	public int getRows(String key, String val) {

		if("all".equals(key)){
			return deptDAO.getElementsSize();
		}
		else{
			return deptDAO.getElementsSizeByUser(val);
		}
	
	}


}
