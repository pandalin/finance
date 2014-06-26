package com.company.finance.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.company.finance.bean.Resources;

@Repository(value="resourcesMapper")
public interface ResourcesMapper extends BaseMapper<Resources>{

	/**
	 * 权限下的资源列表
	 * @param auth_id 权限id
	 * @return
	 */
	public List<Resources> getAuthoritiesResourcesList(String auth_id);
	
	/**
	 * 用户拥有的菜单
	 * @param user_id
	 * @return
	 */
	public List<Resources> getUserResourcesList(String user_id);
	
	
}
