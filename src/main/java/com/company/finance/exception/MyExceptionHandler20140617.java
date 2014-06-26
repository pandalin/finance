package com.company.finance.exception;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

@Deprecated
public class MyExceptionHandler20140617 extends ExceptionHandlerExceptionResolver {

	private Logger	logger	= LoggerFactory.getLogger(getClass());

	private String	defaultErrorView;

	public ModelAndView doResolveHandlerMethodException(HttpServletRequest request, HttpServletResponse response, HandlerMethod handler, Exception ex) {

		if (handler != null) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Method method = handlerMethod.getMethod();
			ModelAndView returnValue = super.doResolveHandlerMethodException(request, response, handlerMethod, ex);
			if (method != null) {
				ResponseBody responseBodyAnn = AnnotationUtils.findAnnotation(method, ResponseBody.class);
				if (responseBodyAnn != null) {
					try {
						
						ResponseStatus responseStatus = AnnotationUtils.findAnnotation(method, ResponseStatus.class);
						if (responseStatus != null) {
							HttpStatus httpStatus = responseStatus.value();
							String reason = responseStatus.reason();
							if (!StringUtils.isEmpty(reason)) {
								try {
									response.sendError(httpStatus.value(), reason);
								} catch (IOException e) {
									e.printStackTrace();
									return null;
								}
							} else {
								response.setStatus(httpStatus.value());
							}
						}
						return handleResponseBody(returnValue,request,response);
					} catch (Exception e) {
						return null;
					}
				}
				
			}
		}

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("exception", ex);
		logger.error(ex.getMessage());
		return new ModelAndView(defaultErrorView, model);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private ModelAndView handleResponseBody(ModelAndView returnValue, HttpServletRequest request, HttpServletResponse response) throws HttpMessageNotWritableException, IOException {
		Map value = returnValue.getModelMap();
		HttpInputMessage inputMessage = (HttpInputMessage) request;
		List<MediaType> acceptedMediaTypes = inputMessage.getHeaders().getAccept();
		if (acceptedMediaTypes.isEmpty()) {
			acceptedMediaTypes = Collections.singletonList(MediaType.ALL);
		}
		MediaType.sortByQualityValue(acceptedMediaTypes);
		
		HttpOutputMessage outputMessage = (HttpOutputMessage) response;
		Class<?> returnValueType = value.getClass();  
        List<HttpMessageConverter<?>> messageConverters = super.getMessageConverters();  
        if (messageConverters != null) {  
            for (MediaType acceptedMediaType : acceptedMediaTypes) {  
                for (HttpMessageConverter messageConverter : messageConverters) {  
                    if (messageConverter.canWrite(returnValueType, acceptedMediaType)) {  
                        messageConverter.write(value, acceptedMediaType, outputMessage);  
                        return new ModelAndView();  
                    }  
                }  
            }  
        }  
		
		return null;
	}

	public String getDefaultErrorView() {
		return defaultErrorView;
	}

	public void setDefaultErrorView(String defaultErrorView) {
		this.defaultErrorView = defaultErrorView;
	}

}
