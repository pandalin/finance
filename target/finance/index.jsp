<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Finance</title>
</head>
<body>
	<!-- 不要cas时开启springsecurity login -->
	<%-- <jsp:forward page="/login" /> --%>
	<!-- 用cas的时候,访问项目，验证通过后直接进入主页 -->
	<jsp:forward page="/welcome" />
</body>
</html>