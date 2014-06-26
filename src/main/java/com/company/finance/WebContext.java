package com.company.finance;

import org.springframework.security.core.context.SecurityContextHolder;

import com.company.finance.bean.User;
import com.company.finance.security.UserDetails;

/**
 * 
 * <p> 
 *  常量
 *
 * @author  linxiaomin@sina.cn  
 * @date    2014年6月23日 下午1:07:12
 * @version v1.0
 */
public abstract class WebContext {
	
	/**
	 * 当前登录用户的session key
	 */
	public final static String CURRENT_USER = "CURRENT_USER";
	
	/**
	 * 授权认证成功后的用户
	 * @return
	 */
	protected User getUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			UserDetails userDetails = ((UserDetails) principal);
			return userDetails.getUser();
		} 
		
		return null;
	}
	
}
