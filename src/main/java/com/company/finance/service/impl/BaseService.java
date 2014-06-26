package com.company.finance.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.company.finance.dao.BaseMapper;
import com.company.finance.utils.BeanUtil;

/**
 * 
 * @ClassName: BaseService
 * @Description: TODO(基础Service)
 * @author linxiaomin@sina.cn
 * @date 2014年5月28日 下午2:43:24
 * 
 */
public abstract class BaseService {
	
	private static final String DEFAULT_OFFSET = "0";
	private static final String DEFAULT_LIMIT = "10";
	
	private SqlSessionFactory getSqlSessionFactory() {
		return (SqlSessionFactory) BeanUtil.getBean("sqlSessionFactory");
	}
	
	public <T> Page<T> page(Class<?> clazz, String offset, String limit, boolean page) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		return page(paramMap,clazz, offset, limit, page);
	}
	
	public <T> Page<T> page(Map<String, Object> paramMap,Class<?> clazz, String offset, String limit, boolean page) {
		if ("%".equals(offset) || "%".equals(limit)) {
			offset = DEFAULT_OFFSET;
			limit = DEFAULT_LIMIT;
		} 
		return page(paramMap, clazz, Integer.valueOf(offset), Integer.valueOf(limit), page);
	}

	public <T> Page<T> page(Class<?> clazz, int offset, int limit, boolean page) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		return page(paramMap, clazz, offset, limit, page);
	}

	@SuppressWarnings("unchecked")
	public <T> Page<T> page(Map<String, Object> paramMap, Class<?> clazz, int offset, int limit, boolean page) {
		BaseMapper<T> baseMapper = (BaseMapper<T>) getSqlSessionFactory().openSession().getMapper(clazz);

		int totalCount = baseMapper.getCounts(paramMap);

		Pageable pageable = new PageRequest(offset, limit);
		Page<T> pageList = null;
		List<T> content = new ArrayList<T>();
		if (page) {

			content = baseMapper.findAll(new RowBounds(pageable.getOffset(), pageable.getPageSize()), paramMap);

		} else {
			content = baseMapper.findAll(RowBounds.DEFAULT, paramMap);
		}
		pageList = new PageImpl<T>(content, pageable, totalCount);
		return pageList;
	}
}
