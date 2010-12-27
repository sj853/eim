package com.eim.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeptServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		String type = request.getParameter("type");
		String DeptName =request.getParameter("name");
		
		
		if(type==null){
				
			
		}
		else if("search".equals(type)){
			
		}
		else if("add".equals(type)){
			
		}
		else if("del".equals(type)){
			
		}
		else if("update".equals(type)){
			
		}
		
		
		
	}

}
