package com.company.finance.web.controller;

import java.math.BigDecimal;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jasig.cas.client.util.AbstractCasFilter;
import org.jasig.cas.client.validation.Assertion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomBooleanEditor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.WebDataBinder;

import com.company.finance.bean.User;
import com.company.finance.security.UserDetails;

/**
 * @ClassName: BaseController
 * @Description: 基础Controller
 * @author linxiaomin@sina.cn
 * @date 2014年6月17日 下午4:15:09
 * 
 */
// @SessionAttributes(value=WebContext.CURRENT_USER)--获取登录用户session,
// 只需在方法中加入@ModelAttribute(value=WebContext.CURRENT_USER) User
// user即可获取,本系统改为用springsecurity
public abstract class BaseController {

	protected Logger	logger	= LoggerFactory.getLogger(getClass());

	@org.springframework.web.bind.annotation.InitBinder
	protected void InitBinder(WebDataBinder dataBinder) {
		dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
		dataBinder.registerCustomEditor(Boolean.class, new CustomBooleanEditor(true));
		dataBinder.registerCustomEditor(Byte.class, new CustomNumberEditor(Byte.class, true));
		dataBinder.registerCustomEditor(Float.class, new CustomNumberEditor(Float.class, true));
		dataBinder.registerCustomEditor(Double.class, new CustomNumberEditor(Double.class, true));
		dataBinder.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class, true));
		dataBinder.registerCustomEditor(BigDecimal.class, new CustomNumberEditor(BigDecimal.class, true));
	}

	/**
	 * 当前认证成功用户信息
	 * 
	 * @return
	 */
	protected User getUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			UserDetails userDetails = ((UserDetails) principal);
			return userDetails.getUser();
		}

		return null;
	}

	protected String getUserName() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		}

		if (principal instanceof Principal) {
			return ((Principal) principal).getName();
		}

		return (principal == null) ? "" : principal.toString();
	}
	
	/**
	 * 这里获取不到,暂时不知道如何处理
	 * @param request
	 * @return
	 */
	protected String getCASUserName(HttpServletRequest request) {
		final HttpSession session = request.getSession(false);
        final Assertion assertion = (Assertion) (session == null ? request.getAttribute(AbstractCasFilter.CONST_CAS_ASSERTION) : session.getAttribute(AbstractCasFilter.CONST_CAS_ASSERTION));

		return assertion.getPrincipal().getName();
	}

	/**
	 * 返回json数据
	 * 
	 * @param data
	 * @return
	 */
	protected Map<String, Object> ajaxData(Map<String, Object> data) {
		data.put("success", true);
		return data;
	}

	/**
	 * 返回json数据
	 * 
	 * @param success
	 * @param data
	 * @return
	 */
	protected Map<String, Object> ajaxData(boolean success, Map<String, Object> data) {
		data.put("success", success);
		return data;
	}

	/**
	 * ajax保存
	 * 
	 * @param success
	 * @return
	 */
	protected Map<String, Object> ajaxSave(boolean success) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", success);
		map.put("message", null);
		return map;
	}

	/**
	 * ajax保存
	 * 
	 * @param success
	 * @param message
	 * @return
	 */
	protected Map<String, Object> ajaxSave(boolean success, String message) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", success);
		map.put("message", message);
		return map;
	}

}
