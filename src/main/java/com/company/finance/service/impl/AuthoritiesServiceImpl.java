package com.company.finance.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.company.finance.bean.Authorities;
import com.company.finance.bean.AuthoritiesToResources;
import com.company.finance.bean.RoleToAuthorities;
import com.company.finance.dao.AuthoritiesMapper;
import com.company.finance.service.AuthoritiesService;
import com.company.finance.service.BaseService;

/**
* @ClassName: AuthoritiesServiceImpl
* @Description: TODO(权限接口实现类)
* @author linxiaomin@sina.cn
* @date 2014年5月29日 下午5:38:34
*
 */
@Service(value="authoritiesService")
public class AuthoritiesServiceImpl implements AuthoritiesService {

	@Autowired
	private BaseService	baseService;
	@Autowired
	private AuthoritiesMapper	authoritiesMapper;
	
	public List<Authorities> queryAll() {
		return authoritiesMapper.queryAll();
	}

	public void saveRoleAuth(String roleid, List<String> authList) {
		if (!CollectionUtils.isEmpty(authList)) {
			authoritiesMapper.deleteRoleAuth(roleid);
			for (String authid : authList) {
				RoleToAuthorities	toAuthorities = new RoleToAuthorities();
				toAuthorities.setRole_id(roleid);
				toAuthorities.setAuthority_id(authid);
				authoritiesMapper.addRoleToAuthorities(toAuthorities);
			}
		}
	}

	public List<Authorities> queryAuthByRoleId(String roleid) {
		return authoritiesMapper.queryAuthByRoleId(roleid);
	}

	public void saveAuthorities(Authorities authorities) {
		if (StringUtils.isEmpty(authorities.getId())) {
			authoritiesMapper.addEntity(authorities);
		} else {
			authoritiesMapper.modifyEntity(authorities);
		}
	}

	public Page<Authorities> findAll(String offset, String limit, boolean page) {
		return baseService.page(AuthoritiesMapper.class, offset, limit, page);
	}

	public void saveAuthResources(String authid, List<String> resList) {
		if (!CollectionUtils.isEmpty(resList)) {
			authoritiesMapper.deleteAuthResources(authid);
			for (String resid : resList) {
				AuthoritiesToResources	toResources = new AuthoritiesToResources();
				toResources.setAuthority_id(authid);
				toResources.setResource_id(resid);
				authoritiesMapper.addAuthoritiesToResources(toResources);
			}
		}
	}

}
