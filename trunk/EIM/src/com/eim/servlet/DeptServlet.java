package com.eim.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eim.beans.Department;
import com.eim.service.InfoService;
import com.eim.serviceImpl.DepartmentServiceImpl;

public class DeptServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		
		String type = request.getParameter("type");
		String deptname =request.getParameter("name");
		
		String key = request.getParameter("key");
		String val = request.getParameter("val");
		Map<String, String> condition = new HashMap<String, String>();
		condition.put(key, val);
		
		InfoService<Department> deptser = new DepartmentServiceImpl();
		
		if(type==null){
				
			
		}
		else if("search".equals(type)){
			deptser.doSearch(condition);
		}
		else if("add".equals(type)){

		}
		else if("del".equals(type)){
			
		}
		else if("update".equals(type)){
			
		}
		
		
		
	}

}
