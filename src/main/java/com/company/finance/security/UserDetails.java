package com.company.finance.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * 
 * <p> 
 *  {@code org.springframework.security.core.userdetails.User}的扩展类
 * </p>
 *
 * @author  linxiaomin@sina.cn  
 * @date    2014年6月19日 下午4:04:21
 * @version V1.0
 */
public class UserDetails extends User {

	private static final long	serialVersionUID	= 1L;
	
	private com.company.finance.bean.User	user;

	public UserDetails(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}

	public com.company.finance.bean.User getUser() {
		return user;
	}

	public void setUser(com.company.finance.bean.User user) {
		this.user = user;
	}

}
