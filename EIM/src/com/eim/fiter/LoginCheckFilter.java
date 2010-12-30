package com.eim.fiter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
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
			FilterChain filterchain) throws IOException, ServletException {
			request.setCharacterEncoding("UTF-8");
			HttpServletRequest req = (HttpServletRequest) request;
			HttpSession session = req.getSession();
			String isLogin = (String) session.getAttribute("islogin");
			if(!"yes".equals(isLogin)){
				//没有登录跳转到登录界面
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
			filterchain.doFilter(request, response);

	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
