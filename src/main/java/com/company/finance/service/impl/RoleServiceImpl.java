package com.company.finance.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.company.finance.bean.Role;
import com.company.finance.bean.UserToRole;
import com.company.finance.dao.RoleMapper;
import com.company.finance.service.RoleService;

@Service(value="roleService")
public class RoleServiceImpl extends com.company.finance.service.impl.BaseService implements RoleService {
	
	@Autowired
	private RoleMapper	roleMapper;

	public Page<Role> findAll(Map<String, Object> map ,String offset,String limit,boolean page) {
		return page(map,RoleMapper.class, offset, limit, page);
	}

	public List<Role> queryAll() {
		return roleMapper.queryAll();
	}

	public void saveUserRole(String userid, List<String> roleList) {
		if (!CollectionUtils.isEmpty(roleList)) {
			roleMapper.deleteUserRole(userid);
			for (String roleid : roleList) {
				UserToRole userToRole = new UserToRole();
				userToRole.setUser_id(userid);
				userToRole.setRole_id(roleid);
				roleMapper.addUserToRole(userToRole);
			}
		}
	}

	public void saveRole(Role role) {
		if(StringUtils.isEmpty(role.getId())) {
			roleMapper.addEntity(role);
		} else {
			roleMapper.modifyEntity(role);
		}
	}

	public List<Role> queryRoleByUser(String userid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userid", userid);
		return roleMapper.getUserRoleList(map);
	}

}
