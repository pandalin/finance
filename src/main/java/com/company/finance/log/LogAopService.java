package com.company.finance.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 日志切面类
 * @author lin
 *
 */
@Aspect
@Component
public class LogAopService {
	
	private Logger	logger = LoggerFactory.getLogger(getClass());
	
	@After(value="execution(* com.company.finance.service.impl.*.*(..))")
	public void after(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		String className = joinPoint.getTarget().getClass().getSimpleName();
		
		if (logger.isDebugEnabled()) {
			logger.debug("进入切面类");
			logger.debug("类名:"+className+"方法名:"+methodName);
		}
	}
}
