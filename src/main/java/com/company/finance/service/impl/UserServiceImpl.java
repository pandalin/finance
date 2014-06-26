package com.company.finance.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.company.finance.bean.Authorities;
import com.company.finance.bean.User;
import com.company.finance.dao.AuthoritiesMapper;
import com.company.finance.dao.UserMapper;
import com.company.finance.service.BaseService;
import com.company.finance.service.UserService;

@Service(value="userService")
public class UserServiceImpl implements UserService,UserDetailsService {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private AuthoritiesMapper authoritiesMapper;
	@Autowired
	private BaseService	baseService;
	
	@com.googlecode.ehcache.annotations.Cacheable(cacheName="userCache")
	public User getUserById(String userid) {
		return userMapper.getObjectById(userid);
	}

	public UserDetails loadUserByUsername(String usercode) throws UsernameNotFoundException {
		
		User user = userMapper.loadUserByCode(usercode);
		if (user == null) {
			throw new UsernameNotFoundException("["+usercode+"]不存在");
		}
		
		/**
		 * 用户角色拥有的权限列表
		 */
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userid", user.getUser_id());
		List<Authorities> authorityList = authoritiesMapper.getUserAuthoritiesList(map);
		
		Collection<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
		if (!authorityList.isEmpty()) {
			for (Authorities auth : authorityList) {
				authList.add(new SimpleGrantedAuthority(auth.getAuthority_code()));
			}
		} 
		
		com.company.finance.security.UserDetails sysUser = new com.company.finance.security.UserDetails(user.getUser_code(), user.getUser_password(),true,true,true,true, authList);
		sysUser.setUser(user);
		
//		UserDetails details = new org.springframework.security.core.userdetails.User(user.getUser_code(), user.getUser_password(),true,true,true,true, authList);
		return sysUser;
	}

	public List<User> findAll() {
		return this.userMapper.queryAll();
	}

	public void saveUser(User user) {
		if (!StringUtils.isEmpty(user.getUser_id())) {
			this.userMapper.modifyEntity(user);
			loadUserByUsername(user.getUser_code());
		} else {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			user.setUser_password(encoder.encode(user.getUser_password()));
			this.userMapper.addEntity(user);
		}
	}

	public void deleteUserById(String userid) {
		userMapper.deleteEntity(userid);
	}

	public Page<User> findAll(Map<String, Object> map, String offset,String limit,boolean page) {
		return baseService.page(map,UserMapper.class, offset, limit, page);
	}

	public User loadUserByCode(String usercode) {
		return userMapper.loadUserByCode(usercode);
	}

}
