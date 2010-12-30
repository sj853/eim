package com.eim.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eim.util.Md5;
import com.eim.util.PropertiesUtil;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		PropertiesUtil pu = new PropertiesUtil("admin.properties");
		String realun = pu.read("username");
		if(realun.equals(username) && Md5.check(password)){
			HttpSession session = request.getSession(true);
			session.setAttribute("user", username);
		request.getRequestDispatcher("../main.jsp").forward(request, response);
		}
		else{
			out.print("false");
		}
		
	}

}
