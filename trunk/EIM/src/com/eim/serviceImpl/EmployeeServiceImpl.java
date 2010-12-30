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
	
	
	public EmployeeServiceImpl(int rowPerPage) {
		empDAO = new EmployeeDAOImpl(rowPerPage);
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
	 * 更新逻辑
	 */
	public boolean doUpdate(Employee emp) {
		return empDAO.updateElement(emp);
	}
	/**
	 * 查询逻辑
	 */
	public ArrayList<Employee> doSearch(String key, String val, int currentPage) {
		if("id".equals(key)){
			return empDAO.getElementById(val);
		}
		else if("name".equals(key)){
			return empDAO.getElementByName(val);
		}
		else if("gender".equals(key)){
			return empDAO.getElementBySex(Byte.parseByte(val), currentPage);
		}
		else if("all".equals(key)){
			return empDAO.getElements(currentPage);
		}
		else{
			return empDAO.getElementsByUser(val);
		}
		
	}
	public int getRows(String key, String val) {
		if("all".equals(key)){
			return empDAO.getElementsSize();
		}
		else if("gender".equals(key)){
			return empDAO.getElementsSizeBySex(Byte.parseByte(val));
		}
		else{
			return empDAO.getElementsSizeByUser(val);
		}
	}

}
