package com.company.finance.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * <p> 自定义权限异常
 *
 *
 * @author linxiaomin@sina.cn  
 * @date 2014年6月19日 下午3:42:16
 * @version V1.0
 */
@Deprecated
public class BadAuthenticationException extends AuthenticationException {

	private static final long	serialVersionUID	= 1L;

	public BadAuthenticationException(String msg) {
		super(msg);
	}
	
	public BadAuthenticationException(String msg, Throwable t) {
		super(msg,t);
	}

}
