package com.company.finance.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 * <p> 
 *  登录控制器
 * </p>
 *
 * @author  linxiaomin@sina.cn  
 * @date    2014年6月21日 下午6:41:36
 * @version v1.0
 */
@Controller
public class LoginController extends BaseController{

	/**
	 * 主页
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String welcome(ModelMap model) {
		model.put("username", getUserName());
		return "welcome";

	}

	/**
	 * 未授权时的登录地址,返回登录页
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";

	}
	
	/**
	 * 登录失败
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
	public String loginerror(ModelMap model) {
		model.addAttribute("error", "true");
		return "login";
	}

	/**
	 * 成功执行/j_spring_security_logout后重定向的地址
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(ModelMap model) {
		return "login";
	}
	
	@RequestMapping(value = "/sessionExpired", method = RequestMethod.GET)
	public String sessionExpired(ModelMap model) {
		return "sessionExpired";
	}
	

	
	
}
