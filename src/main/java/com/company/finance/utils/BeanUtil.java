package com.company.finance.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
/**
* @ClassName: BeanUtil
* @Description: 获取spring的bean工具类
* @author linxiaomin@sina.cn
* @date 2014年6月19日 上午11:18:51
*
 */
public class BeanUtil implements ApplicationContextAware {

	private static ApplicationContext	applicationContext;

	public static Object getBean(String beanName) {
		return applicationContext.getBean(beanName);
	}

	public static <T> T getBean(String beanName, Class<T> clazz) {
		return clazz.cast(getBean(beanName));
	}

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		BeanUtil.applicationContext = applicationContext;
	}

}
