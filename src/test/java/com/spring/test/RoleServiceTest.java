package com.spring.test;

import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.company.finance.bean.Role;
import com.company.finance.service.RoleService;

@Profile("dev")
@ContextConfiguration(locations = { "file:src/main/resources/spring/spring-context.xml", "file:src/main/resources/spring/spring-db.xml",
		"file:src/main/resources/spring/spring-security.xml", "file:src/main/resources/spring/spring-mvc.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class RoleServiceTest {

	@Autowired
	private RoleService roleService;
	
	@Autowired
	
	private Pageable pageable;

	@org.junit.Test
	public void testListRole() {
		Page<Role> roleList = roleService.findAll(null,"0", "10", true);
		Assert.assertEquals(3, roleList.getTotalElements());
		
		Page<Role> roleList1 = roleService.findAll(null,"0", "10", true);
		Assert.assertEquals(3, roleList1.getTotalElements());
	}


}
