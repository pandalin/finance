package com.company.finance.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.company.finance.bean.Role;
import com.company.finance.bean.UserToRole;

/**
 * 
* @ClassName: RoleMapper
* @Description: TODO(角色相关)
* @author linxiaomin@sina.cn
* @date 2014年5月29日 下午2:53:32
*
 */
@Repository(value="roleMapper")
public interface RoleMapper extends BaseMapper<Role>{

	/**
	 * 用户的角色列表
	 * @param map
	 * @return
	 */
	public List<Role> getUserRoleList(Map<String, Object> map);
	
	/**
	 * 新增用户角色关系
	 * @param userToRole
	 */
	public void addUserToRole(UserToRole userToRole);

	/**
	 * 删除用户角色
	 * @param userid
	 */
	public void deleteUserRole(String userid);
	
}
