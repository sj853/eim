package com.eim.serviceImpl;

import java.util.ArrayList;
import java.util.Map;

import com.eim.beans.Employee;
import com.eim.dao.EmployeeDAO;
import com.eim.daoImpl.EmployeeDAOImpl;
import com.eim.service.InfoService;

/**
 * 
 * @author element
 *员工服务类实现
 */
public class EmployeeServiceImpl implements InfoService<Employee>{

	private EmployeeDAOImpl empDAO;
	
	public EmployeeServiceImpl() {
		empDAO = new EmployeeDAOImpl();
	}
	/**
	 * 添加逻辑
	 */
	public boolean doAdd(Employee emp) {
		return empDAO.addElement(emp);
	}

	/**
	 * 删除逻辑
	 */
	public boolean doDel(ArrayList<Employee> emps) {
		int len = emps.size();
		String[] ids = new String[len];
		for(int i=0;i<len;i++){
			ids[i] = emps.get(i).getId();
		}
		return empDAO.delElements(ids);
	}

	/**
	 * 查询逻辑
	 */
	public ArrayList<Employee> doSearch(Map<String, String> condition) {
		if(condition.containsKey("id")){
			return empDAO.getElementById(condition.get("id"));
		}
		else if(condition.containsKey("gender")){
			return empDAO.getElementBySex(Byte.parseByte(condition.get("gender")));
		}
		else if(condition.containsKey("name")){
			return empDAO.getElementByName(condition.get("name"));
		}
		else if(condition.containsKey("all")){
			return empDAO.getElements();
		}
		else{
			return empDAO.getElementsByUser(condition.get("customer"));
		}
	}

	/**
	 * 更新逻辑
	 */
	public boolean doUpdate(Employee emp) {
		return empDAO.updateElement(emp);
	}

}
