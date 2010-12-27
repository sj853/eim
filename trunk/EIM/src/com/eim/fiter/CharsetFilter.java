package com.eim.fiter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharsetFilter implements Filter {

	String Charset;
	
	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain fiterchain) throws IOException, ServletException {
		request.setCharacterEncoding(Charset);

	}

	public void init(FilterConfig arg0) throws ServletException {
		Charset = "UTF-8";
	}

}
