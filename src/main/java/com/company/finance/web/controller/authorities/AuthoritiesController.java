package com.company.finance.web.controller.authorities;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.finance.bean.Authorities;
import com.company.finance.service.AuthoritiesService;
import com.company.finance.web.controller.BaseController;

/**
 * 
 * <p> 
 *  权限控制器
 * </p>
 *
 * @author  linxiaomin@sina.cn  
 * @date    2014年6月21日 下午6:44:36
 * @version v1.0
 */
@Controller
@RequestMapping(value="/authorities")
public class AuthoritiesController extends BaseController{
	
	@Autowired
	private AuthoritiesService	authoritiesService;
	
	/**
	 * 编辑跳转
	 * @param authid
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toEdit/{authid}")
	public String toEdit(@PathVariable String authid, ModelMap model) {
		Authorities authorities = null;
		if (authid.isEmpty() || authid.equals("%")) {
			authorities = new Authorities();
		} else {
//			authorities = authoritiesService
		}
		model.put("auth", authorities);
		return "authorities/editAuthorities";
	}
	
	/**
	 * 权限保存
	 * @param authorities
	 * @return
	 */
	@RequestMapping(value = "/save",method=RequestMethod.POST)
	public String save(Authorities authorities) {
		authoritiesService.saveAuthorities(authorities);
		return "redirect:/authorities/toList/%25/%25";
	}
	
	@RequestMapping(value = "/toList/{offset}/{limit}")
	public String toList(@PathVariable String offset,@PathVariable String limit,ModelMap model) {
		Page<Authorities> authorities = authoritiesService.findAll(offset, limit, false);
		model.put("result", authorities);
		return "authorities/listAuthorities";
	} 
	
	
	/**
	 * 角色权限列表json
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/getRoleAuth/{roleid}")
	@ResponseBody
	public Map<String, Object> getRoleAuth(@PathVariable String roleid,ModelMap model) {
		
		Map<String, Object> reMap = new HashMap<String, Object>();
		
		List<Authorities> authorities = authoritiesService.queryAll();
		reMap.put("allAuthList", authorities);
		
		List<Authorities> roleAuthList = authoritiesService.queryAuthByRoleId(roleid);
		reMap.put("roleAuthList", roleAuthList);
		
		return ajaxData(reMap);
	}
	
	/**
	 * 分配权限
	 * @param roleid
	 * @param authArr
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/saveRoleAuth",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveRoleAuth(@RequestParam(value="roleid") String roleid,@RequestParam(value="authArr[]") String[] authArr,ModelMap model) {
		authoritiesService.saveRoleAuth(roleid,Arrays.asList(authArr));
		return ajaxSave(true);
	}
	
	/**
	 * 分配资源
	 * @param resArr 资源ids
	 * @return
	 */
	@RequestMapping(value="/saveAuthRes",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveAuthRes(@RequestParam(value="authid") String authid,@RequestParam(value="resArr[]") String[] resArr) {
		authoritiesService.saveAuthResources(authid,Arrays.asList(resArr));
		return ajaxSave(true);
	}
	
}
