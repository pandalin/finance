package com.finance.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.AntPathMatcher;

public class Test {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private AntPathMatcher	urlMatcher	= new AntPathMatcher();
	
	public String encode(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		password = encoder.encode(password);
		logger.info(password);
		return password;
	}
	
	@org.junit.Test
	public void test() {
		encode("admin");
	}
	
	@org.junit.Test
	public void testMatchUrl() {
		String str = "/user/listUser/%25/%25/%25";
		//System.out.println(str.substring(0,str.indexOf("%25")));
		str = str.substring(0,str.indexOf("%25"))+"**";
		boolean flag = urlMatcher.match(str,"/user/listUser/1/10/%25");
		logger.debug("flag:"+flag);
	}
}
