package com.company.finance.security;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Service;

/**
 * 
 * 核心的InterceptorStatusToken token =
 * super.beforeInvocation(fi);会调用我们定义的accessDecisionManager:decide(Object
 * object)和securityMetadataSource
 * 
 * :getAttributes(Object object)方法。 自己实现的过滤用户请求类，也可以直接使用
 * FilterSecurityInterceptor
 * 
 * AbstractSecurityInterceptor有三个派生类：
 * FilterSecurityInterceptor，负责处理FilterInvocation，实现对URL资源的拦截。
 * MethodSecurityInterceptor，负责处理MethodInvocation，实现对方法调用的拦截。
 * AspectJSecurityInterceptor，负责处理JoinPoint，主要是用于对切面方法(AOP)调用的拦截。
 * 
 * 还可以直接使用注解对Action方法进行拦截，例如在方法上加：
 * 
 * @PreAuthorize("hasRole('ROLE_SUPER')")
 * 
 * 
 * 
 */
@Service(value = "mySecurityFilter")
public class MySecurityFilter extends AbstractSecurityInterceptor implements Filter{
	
	// 与spring-security.xml里的mySecurityFilter的属性securityMetadataSource对应，
	// 其他的两个组件，已经在AbstractSecurityInterceptor定义
	
	@Autowired
	private MySecurityMetadataSource	mySecurityMetadataSource;
	@Autowired
	private MyAccessDecisionManager		myAccessDecisionManager;
	@Autowired
	private AuthenticationManager		authenticationManager;

	@PostConstruct
	public void init() {
		
		logger.debug("..init..MySecurityFilter..");
		
		super.setAuthenticationManager(authenticationManager);
		super.setAccessDecisionManager(myAccessDecisionManager);
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		FilterInvocation invocation = new FilterInvocation(request, response, filterChain);
		InterceptorStatusToken statusToken = super.beforeInvocation(invocation);
		try {
			invocation.getChain().doFilter(request, response);
		} finally {
			super.afterInvocation(statusToken, null);
		}
	}
	
	@Override
	public Class<?> getSecureObjectClass() {
		return FilterInvocation.class;
	}

	@Override
	public SecurityMetadataSource obtainSecurityMetadataSource() {
		return this.mySecurityMetadataSource;
	}

	public void destroy() {
		
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

}
