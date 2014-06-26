package com.company.finance.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.company.finance.bean.Resources;

/**
* @ClassName: ResourcesService
* @Description: TODO(资源service接口)
* @author linxiaomin@sina.cn
* @date 2014年6月16日 上午10:29:13
*
 */
public interface ResourcesService {
	
	/**
	 * Single资源
	 * @param id
	 * @return
	 */
	public Resources getResourcesById(String id);

	/**
	 * 分页加载资源
	 * @param offset
	 * @param limit
	 * @param page
	 * @return
	 */
	public Page<Resources> findAll(Map<String, Object> map,String offset,String limit,boolean page);
	
	/**
	 * 新增修改资源
	 * @param resources
	 */
	public void saveResources(Resources resources);
	
	/**
	 * 作废资源
	 * @param resources
	 */
	public void deleteResources(String resourceid);
	
	/**
	 * 所有资源
	 * @return
	 */
	public List<Resources> queryAll();
	
	/**
	 * 权限下的资源列表
	 * @param auth_id 权限id
	 * @return
	 */
	public List<Resources> getAuthoritiesResourcesList(String auth_id);
	
}
