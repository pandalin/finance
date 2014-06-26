package com.company.finance.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.company.finance.bean.User;

@Repository(value="userMapper")
public interface UserMapper extends BaseMapper<User>{
	
	public User loadUserByCode(String usercode);
	
	/**
	 * 这种方式得用com.company.finance.dialect.MybatisPageableInterceptor
	 * @param map
	 * @param pageRequest
	 * @return
	 */
	public Page<User> findUserByPage(@Param("map") Map<String, Object> map,PageRequest pageRequest);

}
