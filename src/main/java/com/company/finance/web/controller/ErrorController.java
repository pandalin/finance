package com.company.finance.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
* @ClassName: ErrorController
* @Description: 错误控制器
* @author linxiaomin@sina.cn
* @date 2014年6月19日 上午11:38:34
*
 */
@Controller
@RequestMapping(value="/error")
public class ErrorController {
	
	/**
	 * 错误跳转的界面
	 * @param errorCode
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/error/{errorCode}", method = RequestMethod.GET)
	public String error(@PathVariable Integer errorCode,HttpServletRequest request,ModelMap model) {
		request.setAttribute("javax.servlet.error.status_code", errorCode);
		return "error/error";
	}
	
	/**
	 * 错误处理方式
	 * @param request
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="/error")
    //@ResponseBody
	@Deprecated
    public String handle(HttpServletRequest request,ModelMap modelMap) {
		
        modelMap.put("status", request.getAttribute("javax.servlet.error.status_code"));
        modelMap.put("reason", request.getAttribute("javax.servlet.error.message"));
        modelMap.put("exception", request.getAttribute("javax.servlet.error.exception"));
        modelMap.put("exception_type", request.getAttribute("javax.servlet.error.exception_type"));
        modelMap.put("request_uri", request.getAttribute("javax.servlet.error.request_uri"));
        modelMap.put("servlet_name", request.getAttribute("javax.servlet.error.servlet_name"));
 
        return "error/error";
    }
	
}
