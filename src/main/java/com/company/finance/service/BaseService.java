package com.company.finance.service;

import java.util.Map;

import org.springframework.data.domain.Page;

/**
 * 
* @ClassName: BaseService
* @Description: TODO(基础Service)
* @author linxiaomin@sina.cn
* @date 2014年5月28日 下午2:43:24
*
 */
public interface BaseService {
	
	public <T> Page<T> page(Class<?> clazz,String offset,String limit,boolean page);
	
	public <T> Page<T> page(Map<String, Object> paramMap,Class<?> clazz,String offset,String limit,boolean page);
	
}
