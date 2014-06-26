package com.company.finance.web.controller.role;

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

import com.company.finance.bean.Role;
import com.company.finance.service.RoleService;
import com.company.finance.web.controller.BaseController;

@Controller
@RequestMapping(value="/role")
public class RoleController extends BaseController{
	
	@Autowired
	private RoleService	roleService;
	
	@RequestMapping(value = "/toEdit/{roleid}")
	public String toEdit(@PathVariable String roleid, ModelMap model) {
		Role role = null;
		if (roleid.isEmpty() || roleid.equals("%")) {
			role = new Role();
		} else {
//			role = authoritiesService
		}
		model.put("role", role);
		return "role/editRole";
	}
	
	@RequestMapping(value = "/addRole",method=RequestMethod.POST)
	public String addRole(Role role) {
		roleService.saveRole(role);
		return "redirect:/role/listRole/%25/%25/%25";
	}
	
	@RequestMapping(value = "/listRole/{offset}/{limit}/{keyword}")
	public String toList(@PathVariable String offset,@PathVariable String limit,@PathVariable String keyword,ModelMap model) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (!keyword.equals("%")) {
			map.put("keyword", keyword);
			model.put("keyword", keyword);
		} else {
			model.put("keyword", "");
		}
		Page<Role> roleList = roleService.findAll(map,offset, limit, true);
		
		model.put("result", roleList);
		return "role/listRole";
	} 
	
	/**
	 * 用户角色
	 * @param userid
	 * @return
	 */
	@RequestMapping(value = "/userRoleList/{userid}")
	@ResponseBody
	public Map<String, Object> userRoleList(@PathVariable(value="userid") String userid) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<Role> roleList = roleService.queryAll();
		map.put("allRoleList", roleList);
		
		List<Role> userRoleList = roleService.queryRoleByUser(userid);
		map.put("userRoleList", userRoleList);
		return ajaxData(map);
	}
	
	/**
	 * 分配角色
	 * @param userid
	 * @param roleArr
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addUserRole",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addUserRole(@RequestParam(value="userid") String userid,@RequestParam(value="roleArr[]") String[] roleArr,ModelMap model) {
		
		roleService.saveUserRole(userid, Arrays.asList(roleArr));
		
		return ajaxSave(true);
	}
	
}
