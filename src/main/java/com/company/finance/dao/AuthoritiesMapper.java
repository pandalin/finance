package com.company.finance.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.company.finance.bean.Authorities;
import com.company.finance.bean.AuthoritiesToResources;
import com.company.finance.bean.RoleToAuthorities;

/**
* @ClassName: AuthoritiesMapper
* @Description: TODO(权限相关mapper)
* @author linxiaomin@sina.cn
* @date 2014年6月16日 上午9:56:04
*
 */
@Repository(value="authoritiesMapper")
public interface AuthoritiesMapper extends BaseMapper<Authorities>{

	/**
	 * 用户的权限列表
	 * @param map
	 * @return
	 */
	public List<Authorities> getUserAuthoritiesList(Map<String, Object> map);

	/**
	 * 删除角色权限
	 * @param roleid
	 */
	public void deleteRoleAuth(String roleid);

	/**
	 * 新增角色权限
	 * @param toAuthorities
	 */
	public void addRoleToAuthorities(RoleToAuthorities toAuthorities);
	
	/**
	 * 角色的权限列表
	 * @param roleid
	 * @return
	 */
	public List<Authorities> queryAuthByRoleId(String roleid);

	/**
	 * 删除权限资源关系
	 * @param authid
	 */
	public void deleteAuthResources(String authid);

	/**
	 * 分配权限-资源
	 * @param toResources
	 */
	public void addAuthoritiesToResources(AuthoritiesToResources toResources);
	
}
