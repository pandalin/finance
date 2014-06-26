package com.company.finance.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;

/**
 * 
 * <p> 
 *  异常处理类,当为json请求错误时返回json:{success:false,message:异常信息}
 *  其余错误返回错误界面@param errorView
 * </p>
 *
 * @author  linxiaomin@sina.cn  
 * @date    2014年6月19日 下午4:08:41
 * @version v1.0
 */
public class MyExceptionHandler implements HandlerExceptionResolver {
	
	private Logger						logger			= LoggerFactory.getLogger(getClass());

	/**
	 * errorView
	 */
	private String						errorView;
	
	/**
	 * <bean id="contentNegotiationManager">
	 */
	@Autowired
	private ContentNegotiationManager	contentNegotiationManager;

	public String getErrorView() {
		return errorView;
	}

	public void setErrorView(String errorView) {
		this.errorView = errorView;
	}

	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		
		logger.error(ex.getMessage(),ex);
		
		List<MediaType> mediaTypes = null;
		
		try {
			mediaTypes = contentNegotiationManager.resolveMediaTypes(new ServletWebRequest(request));
		} catch (HttpMediaTypeNotAcceptableException e) {
			logger.error(e.getMessage(), e);
			return null;
		}
		
		if (mediaTypes == null || mediaTypes.isEmpty()) {
			return null;
		}
		
		//json异常
		for (MediaType mediaType : mediaTypes) {
			if (mediaType.equals(MediaType.APPLICATION_JSON)) {
				ModelAndView modelAndView = new ModelAndView(new FastJsonJsonView());
				modelAndView.addObject("success", false);
				modelAndView.addObject("message", ex.getMessage());
				return modelAndView;
			}
		}
		
		//其他异常
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("exception", ex);
		return new ModelAndView(errorView, model);

	}

}
