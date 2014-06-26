package com.company.finance.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.company.finance.bean.Resources;
import com.company.finance.dao.ResourcesMapper;
import com.company.finance.service.ResourcesService;

@Service(value="resourcesService")
public class ResourcesServiceImpl extends com.company.finance.service.impl.BaseService implements ResourcesService {
	
	@Autowired
	private ResourcesMapper	resourcesMapper;
	
	public Page<Resources> findAll(Map<String, Object> map,String offset, String limit, boolean page) {
		return page(map,ResourcesMapper.class, offset, limit, page);
	}

	public void saveResources(Resources resources) {
		if (StringUtils.isEmpty(resources.getId())) {
			resourcesMapper.addEntity(resources);
		} else {
			resourcesMapper.modifyEntity(resources);
		}
	}

	public List<Resources> queryAll() {
		return resourcesMapper.queryAll();
	}

	public Resources getResourcesById(String id) {
		return resourcesMapper.getObjectById(id);
	}

	public void deleteResources(String resourceid) {
		Resources resources = getResourcesById(resourceid);
		if (resources != null) {
			resources.setResource_status(1);
			resourcesMapper.modifyEntity(resources);
		}
	}

	@Override
	public List<Resources> getAuthoritiesResourcesList(String auth_id) {
		return resourcesMapper.getAuthoritiesResourcesList(auth_id);
	}

}
