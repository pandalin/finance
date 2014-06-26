<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎界面</title>
</head>
<body>
	<center>
		<h1>Spring Security example</h1>
		<h3>Message : ${message}</h3>
		<h3>欢迎光临 : ${username}</h3>
	
		<a href="<c:url value="/j_spring_security_logout" />"> Logout</a>
		
		<a href="<c:url value="/admin"/> ">访问admin的界面</a>
		<a href="<c:url value="/user"/> ">访问user的界面</a>
	</center>

	<hr/>
	
</body>
</html>