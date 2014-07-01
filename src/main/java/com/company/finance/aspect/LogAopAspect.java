package com.company.finance.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
* @ClassName: LogAopAspectService
* @Description: 日志切面类
* @author linxiaomin@sina.cn
* @date 2014年6月19日 上午11:19:41
*
 */
@Aspect
@Component
public class LogAopAspect {
	
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
