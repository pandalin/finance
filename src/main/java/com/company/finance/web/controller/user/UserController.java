package com.company.finance.web.controller.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.company.finance.bean.User;
import com.company.finance.service.UserService;
import com.company.finance.web.controller.BaseController;

@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController{
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/toEdit/{userid}")
	public String toEdit(@PathVariable String userid, ModelMap model) {
		User user = null;
		if (userid.isEmpty() || userid.equals("%")) {
			user = new User();
		} else {
			user = userService.getUserById(userid);
		}
		model.put("user", user);
		return "user/editUser";
	}
	
	@RequestMapping(value = "/addUser",method=RequestMethod.POST)
	public String add(User user) {
		userService.saveUser(user);
		return "redirect:/user/listUser/%25/%25/%25";
	}
	
	@RequestMapping(value = "/toDel/{userid}",method=RequestMethod.GET)
	public String delUser(@PathVariable String userid) {
		userService.deleteUserById(userid);
		return "redirect:/user/listUser/%25/%25/%25";
	}
	
	@RequestMapping(value = "/listUser/{offset}/{limit}/{keyword}",method={RequestMethod.GET,RequestMethod.POST})
	public String toList(@PathVariable String offset,@PathVariable String limit,@PathVariable String keyword,ModelMap model) {
			
		Map<String, Object> map = new HashMap<String, Object>();
		if (!keyword.equals("%")) {
			map.put("keyword", keyword);
		} else {
			model.put("keyword", "");
		}
		Page<User>	userList = userService.findAll(map, offset, limit,true);
		
		model.put("result", userList);
		return "user/listUser";
	}
	
}
