package com.company.finance.exception;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

@Deprecated
public class MyExceptionHandler20140618 implements HandlerExceptionResolver {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private String	errorView;
	
	private static final String XMLHTTPREQUEST = "XMLHttpRequest";
	
	public String getErrorView() {
		return errorView;
	}

	public void setErrorView(String errorView) {
		this.errorView = errorView;
	}

	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		
		if ( !(request.getHeader("accept").indexOf(MediaType.APPLICATION_JSON_VALUE) > -1) 
				|| (StringUtils.isEmpty(request.getHeader("X-Requested-With")) 
						&& request.getHeader("X-Requested-With").indexOf(XMLHTTPREQUEST) > -1)) {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("exception", ex);
			logger.error(ex.getMessage());
			return new ModelAndView(errorView, model);
		} else { //JSON返回
			try {
				PrintWriter printWriter = response.getWriter();
				printWriter.write(ex != null ? ex.getMessage() : "JSON请求异常");
				printWriter.flush();
				printWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		
	}
	
}
