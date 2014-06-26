package com.company.finance.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.company.finance.bean.Role;
/**
 * 
* @ClassName: RoleService
* @Description: TODO(角色)
* @author linxiaomin@sina.cn
* @date 2014年5月29日 上午10:54:59
*
 */
public interface RoleService {
	
	/**
	 * 新增、修改角色
	 * @param role
	 */
	public void saveRole(Role role);

	public Page<Role> findAll(Map<String, Object> map ,String offset,String limit,boolean page);
	
	public List<Role> queryAll();
	
	/**
	 * 保存用户角色
	 * @param userid
	 * @param roleList
	 */
	public void saveUserRole(String userid,List<String> roleList);
	
	/**
	 * 用户拥有的角色
	 * @param userid
	 * @return
	 */
	public List<Role> queryRoleByUser(String userid);

}
