package com.eim.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.eim.beans.Department;
import com.eim.service.InfoService;
import com.eim.serviceImpl.DepartmentServiceImpl;
import com.eim.util.JsonFormat;

public class DeptServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String rows = null;
	private InfoService<Department> deptser;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		JsonFormat<Department> jf = new JsonFormat<Department>();
		String type = request.getParameter("type");
	
	
		
		

		String rows = request.getParameter("rows");
		if(rows!=null){
			
			int rowPerPage = Integer.parseInt(rows);
			deptser = new DepartmentServiceImpl(rowPerPage);
		}
		if(type==null){
				
			
		}
		else if("search".equals(type)){
			int currentPage = Integer.parseInt(request.getParameter("page"));
			String key = request.getParameter("key");
			String val = request.getParameter("val");
		   ArrayList<Department> depts = deptser.doSearch(key, val,currentPage);
		   int rowSize = deptser.getRows(key,val);
			out.println(jf.format(depts, rowSize));
		}
		else{ 
			int id = 0;
			int superid = 0;
			try{
				id = Integer.parseInt(request.getParameter("id"));
			    superid = Integer.parseInt(request.getParameter("superid"));
			}catch (Exception e) {
				out.print(false);
				return;
			}
			String name = request.getParameter("name");
			Department dept = new Department();
			dept.setId(id);
			dept.setName(name);
			dept.setSuperid(superid);
			
			
	   if("add".equals(type)){
				
			out.println(deptser.doAdd(dept));
		}
		else if("del".equals(type)){
			System.out.println("come in");
			ArrayList<Department> depts = new ArrayList<Department>();
			depts.add(dept);
			out.println(deptser.doDel(depts));
		}
		else {
		
			out.println(deptser.doUpdate(dept));
		}
		
		}
		
		
	}

}
