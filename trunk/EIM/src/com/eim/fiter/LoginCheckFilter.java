package com.eim.fiter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 
 * @author element
 *登录过滤类
 */
public class LoginCheckFilter implements Filter {

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
			request.setCharacterEncoding("UTF-8");
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse rep = (HttpServletResponse)response;
			HttpSession session = req.getSession();
			
			  String url=req.getServletPath();
			   if(url.indexOf("login.jsp")>=0)
			   { 
			    chain.doFilter(request, response);
			   }
			    else
			   {
			     //判断用户session是否过期或是否登录
			           if (session!= null&&session.getAttribute("user")!=null)
			           {
			            chain.doFilter(request, response);
			           }
			           else
			           {
			            //转向登录页面
			        	  req.getRequestDispatcher("notLogin.jsp").forward(request, response);
			           }
			   }

			
			
	}

	public void init(FilterConfig arg0) throws ServletException {

	}

}
