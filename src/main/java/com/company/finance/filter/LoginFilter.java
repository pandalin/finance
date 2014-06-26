package com.company.finance.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.company.finance.WebContext;
import com.company.finance.bean.User;

/**
 * 
 * <p> 
 *  登录过滤器,未登录验证,
 *  <tt>web.xml</tt>中<{@link metadata-complete="true"}不会扫描@WebFilter,默认为false
 * </p>
 *
 * @author  linxiaomin@sina.cn  
 * @date    2014年6月25日 上午11:14:35
 * @version v1.0
 */
@WebFilter(filterName="loginFilter",urlPatterns={"/*"})
public class LoginFilter implements Filter {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession(false);
		logger.debug("session"+session);
		
		if (!(req.getRequestURI().equals("/login")) && session == null ||  session.getAttribute(WebContext.CURRENT_USER) == null) {

			req.getRequestDispatcher(req.getContextPath()+"/index.jsp").forward(req, res);
			return;
//			res.sendRedirect(req.getContextPath()+"/index.jsp");
		} else {
			
			Object object = session.getAttribute(WebContext.CURRENT_USER);
			if (object != null) {
				User user = (User) object;
				logger.debug(user.getUser_name());
			}
			
			chain.doFilter(req, res);
		}
		
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		logger.debug("...init...config...");
	}

}
