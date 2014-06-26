package com.company.finance.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.company.finance.bean.Authorities;

/**
* @ClassName: AuthoritiesService
* @Description: TODO( 权限接口类)
* @author linxiaomin@sina.cn
* @date 2014年5月29日 下午5:36:29
*
 */
public interface AuthoritiesService {
	
	/**
	 * 新增、修改权限
	 * @param authorities
	 */
	public void saveAuthorities(Authorities authorities);

	/**
	 * 查询所有权限
	 * @return
	 */
	public List<Authorities> queryAll();
	
	/**
	 * 分页查询
	 * @param offset
	 * @param limit
	 * @param page
	 * @return
	 */
	public Page<Authorities> findAll(String offset,String limit,boolean page);
	
	/**
	 * 角色拥有的权限
	 * @param roleid
	 * @return
	 */
	public List<Authorities> queryAuthByRoleId(String roleid);

	/**
	 * 分配角色权限
	 * @param roleid
	 * @param asList
	 */
	public void saveRoleAuth(String roleid, List<String> authList);
	
	/**
	 * 分配权限资源
	 * @param authid
	 * @param resList
	 */
	public void saveAuthResources(String authid, List<String> resList);
	
}
