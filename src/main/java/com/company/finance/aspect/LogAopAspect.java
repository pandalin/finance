package com.company.finance.aspect;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
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
	
	@After(value="execution(* com.company.finance.service.impl.*.*(..))")
	public void after(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		String className = joinPoint.getTarget().getClass().getSimpleName();
		final Logger log = getLog(joinPoint);
		if (log.isDebugEnabled()) {
			log.debug("进入切面类");
			log.debug("类名:"+className+"方法名:"+methodName);
		}
	}
	
	@Around(value="execution (public * com.company.finance.service.impl.*.*(..))")
	public Object traceMethod(final ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		Object returnVal = null;
        final Logger log = getLog(proceedingJoinPoint);
        final String methodName = proceedingJoinPoint.getSignature().getName();

        try {
            if (log.isDebugEnabled()) {
                final Object[] args = proceedingJoinPoint.getArgs();
                final String arguments;
                if (args == null || args.length == 0) {
                    arguments = "";
                } else {
                    arguments = Arrays.deepToString(args);
                }
                log.debug("Entering method [" + methodName + "] with arguments [" + arguments + "]");
            }
            returnVal = proceedingJoinPoint.proceed();
            return returnVal;
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("Leaving method [" + methodName + "] with return value [" + (returnVal != null ? returnVal.toString() : "null") + "].");
            }
        }
	}
	
	protected Logger getLog(final JoinPoint joinPoint) {
        final Object target = joinPoint.getTarget();

        if (target != null) {
            return LoggerFactory.getLogger(target.getClass());
        }

        return LoggerFactory.getLogger(getClass());
    }
}
