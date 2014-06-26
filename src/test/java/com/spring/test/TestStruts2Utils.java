package com.spring.test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


//import org.apache.struts2.util.ContainUtil;
import org.springframework.core.JdkVersion;
import org.springframework.core.SpringVersion;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.AlternativeJdkIdGenerator;
import org.springframework.util.CollectionUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;

public class TestStruts2Utils {

	public static void main(String[] args) throws IOException {
//		boolean f = ContainUtil.contains("asdfsdf", "a");
//		System.out.println(f);
		System.out.println(JdkVersion.getJavaVersion());
		
		System.out.println(SpringVersion.getVersion());
		
		AlternativeJdkIdGenerator ag = new AlternativeJdkIdGenerator();
		System.out.println(ag.generateId());
		
		List<String> list = new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		list.add("e");
		System.out.println(CollectionUtils.isEmpty(list));
		System.out.println(CollectionUtils.containsInstance(list, "b"));
		
		List<String> tList = Arrays.asList("a,b,c");
		System.out.println(tList.size());
		

//		FileCopyUtils.copy(new File("d:\\mc.jpg"), new File("e:\\a.jpg"));
		
		System.out.println(StringUtils.quote("a"));
		
		String str = "a";
//		Files.write(Paths.get("d:\\aa.txt"), str.getBytes());
		
		System.out.println(String.format("%n", "a"));
		
		System.out.println("aaaa".matches("a/g"));
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println(encoder.encode("admin"));
	}
}
