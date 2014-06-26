package com.company.finance.service;

import java.util.Map;

import org.springframework.data.domain.Page;

import com.company.finance.bean.User;

/**
* @ClassName: UserService
* @Description: TODO(用户service)
* @author linxiaomin@sina.cn
* @date 2014年6月16日 下午4:04:45
*
 */
public interface UserService {

	/**
	 * 保存
	 * @param user
	 */
	public void saveUser(User user);
	
	/**
	 * 根据id查询用户
	 * @param userid
	 * @return
	 */
	public User getUserById(String userid);
	
	/**
	 * 根据编码查询用户
	 * @param usercode
	 * @return
	 */
	public User loadUserByCode(String usercode);
	
	/**
	 * 分页查询
	 * @param map
	 * @param offset
	 * @param limit
	 * @param page
	 * @return
	 */
	public Page<User> findAll(Map<String, Object> map, String offset,String limit,boolean page);
	
	/**
	 * 删除用户
	 * @param userid
	 */
	public void deleteUserById(String userid);
	
	
	
}
