package com.company.finance.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.company.finance.dao.BaseMapper;
import com.company.finance.service.BaseService;

/**
 * 
* @ClassName: BaseServiceImpl
* @Description: TODO(基础service实现类)
* @author linxiaomin@sina.cn
* @date 2014年5月28日 下午2:43:54
*
 */
@Service
public class BaseServiceImpl extends SqlSessionDaoSupport implements BaseService {

	@SuppressWarnings("unchecked")
	public <T> Page<T> page(Map<String, Object> paramMap, Class<?> clazz, String offset, String limit,boolean page) {
		
		BaseMapper<T> baseMapper = (BaseMapper<T>) this.getSqlSession().getMapper(clazz);
		
		int totalCount = baseMapper.getCounts(paramMap);
		Pageable pageable = null;
		if ("%".equals(offset) || "%".equals(limit)) {
			pageable = new PageRequest(0, 10);
		} else {
			pageable = new PageRequest(Integer.valueOf(offset), Integer.valueOf(limit));
		}
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

	public <T> Page<T> page(Class<?> clazz, String offset, String limit, boolean page) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		return page(paramMap, clazz, offset, limit, page);
	}
	
}
