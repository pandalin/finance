package com.company.finance.web.controller.resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.finance.bean.Resources;
import com.company.finance.service.ResourcesService;
import com.company.finance.web.controller.BaseController;

/**
 * 
 * <p> 
 *  资源控制类
 * </p>
 *
 * @author  linxiaomin@sina.cn  
 * @date    2014年6月20日 下午3:36:19
 * @version v1.0
 */
@Controller
@RequestMapping(value="/resources")
public class ResourcesController extends BaseController{
	
	@Autowired
	private ResourcesService	resourcesService;
	
	/**
	 * 新增修改跳转
	 * @param resourcesid
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toEdit/{resourcesid}")
	public String toEdit(@PathVariable String resourcesid, ModelMap model) {
		Resources resources = null;
		if (resourcesid.isEmpty() || resourcesid.equals("%")) {
			resources = new Resources();
		} else {
			resources = resourcesService.getResourcesById(resourcesid);
		}
		model.put("resource", resources);
		return "resources/editResources";
	}
	
	/**
	 * 保存、修改
	 * @param resources
	 * @return
	 */
	@RequestMapping(value = "/save",method=RequestMethod.POST)
	public String save(Resources resources) {
		resourcesService.saveResources(resources);
		return "redirect:/resources/toList/%25/%25";
	}
	
	/**
	 * 作废
	 * @param resources
	 * @return
	 */
	@RequestMapping(value = "/toDel/{resourceid}")
	public String toDel(@PathVariable(value="resourceid") String resourceid) {
		resourcesService.deleteResources(resourceid);
		return "redirect:/resources/toList/%25/%25";
	}
	
	/**
	 * 列表
	 * @param offset
	 * @param limit
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toList/{offset}/{limit}")
	public String toList(@PathVariable String offset,@PathVariable String limit,ModelMap model) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("resource_status", 0);
		Page<Resources> resourcesList = resourcesService.findAll(map,offset, limit, true);
		model.put("result", resourcesList);
		return "resources/listResources";
	} 
	
	/**
	 * 资源json
	 * @return
	 */
	@RequestMapping(value = "/getResources",method = RequestMethod.GET)
	@ResponseBody
	public List<Resources> getResourcesJson() {
		List<Resources> rList = resourcesService.queryAll();
		
		Resources resources = new Resources();
		resources.setId("0");
		resources.setResource_name("资源树");
		resources.setResource_parent("");
		rList.add(resources);
		if (!CollectionUtils.isEmpty(rList)) {
			for (Resources r : rList) {
				if (StringUtils.isEmpty(r.getResource_parent())) {
					r.setResource_parent("0");
				}
			}
		}
		
		return rList;
	}
	
	/**
	 * 权限对应的json
	 * @return
	 */
	@RequestMapping(value = "/getAuthResources/{authid}",method = RequestMethod.GET)
	@ResponseBody
	public List<String> getAuthResources(@PathVariable(value="authid") String authid) {
		
		List<String> rList = new ArrayList<String>();
		
		List<Resources> resources = resourcesService.getAuthoritiesResourcesList(authid);
		if (!CollectionUtils.isEmpty(resources)) {
			for (Resources r : resources) {
				rList.add(r.getId());
			}
		}
		
		return rList;
	}
	
	
	
}
