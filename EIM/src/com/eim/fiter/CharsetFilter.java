package com.eim.fiter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 
 * @author element
 *字符集过滤类
 */
public class CharsetFilter implements Filter {

	String Charset;
	
	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain fiterchain) throws IOException, ServletException {
		request.setCharacterEncoding(Charset);
		response.setCharacterEncoding(Charset);
		fiterchain.doFilter(request, response);
	}

	public void init(FilterConfig arg0) throws ServletException {
		Charset = "UTF-8";
	}

}
