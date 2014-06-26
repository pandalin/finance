package com.spring.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.company.finance.bean.User;
import com.company.finance.dao.UserMapper;
import com.company.finance.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ContextConfiguration(locations={
					"file:src/main/resources/applicationContext-context.xml",
					"file:src/main/resources/applicationContext-db.xml",
					"file:src/main/resources/spring-security.xml",
					"file:src/main/webapp/WEB-INF/springmvc-servlet.xml"
				})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {

	@Autowired
	private UserService	userService;
	@Autowired
	private UserMapper userMapper;

	@org.junit.Test
	public void testUser() {
		MessageDigestPasswordEncoder encoder = new MessageDigestPasswordEncoder("MD5");
		String pwd = encoder.encodePassword("1", null);
		
		User user = new User();
		user.setUser_code("test01");
		user.setUser_password(pwd);
		user.setUser_status(0);
		userService.saveUser(user);
		
		//User user1 = userService.getUserById("u01");
		//org.junit.Assert.assertNotNull(user1);
	}
	
	@org.junit.Test
	public void testMd5() {
		MessageDigestPasswordEncoder encoder = new MessageDigestPasswordEncoder("MD5");
		String pwd = encoder.encodePassword("1", null);
		System.out.println(pwd);
	      
	}
	
	@org.junit.Test
	public void testJson() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		List<User> userList = new ArrayList<User>();
		User u1 = new User();
		u1.setUser_name("haha");
		
		User u2 = new User();
		u2.setUser_name("hehe");
		
		userList.add(u1);
		userList.add(u2);
		
		System.out.println(mapper.writeValueAsString(userList));
	}
	
	@org.junit.Test
	public void testFindAll() {
		
		User user = userMapper.loadUserByCode("admin");
		System.out.println(user.getUser_name());
		
		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("username", "admin");
		//这种方式得用com.company.finance.dialect.MybatisPageableInterceptor
//		PageRequest pageRequest = new PageRequest(0, 10);
//		Page<User> userList = userMapper.findUserByPage(map, pageRequest);
		
		Page<User> userList = userService.findAll(map,"0","10",true);
		System.out.println(userList.getSize());
	}
}
