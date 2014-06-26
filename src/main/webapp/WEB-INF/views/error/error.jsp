<%@page import="java.io.PrintWriter"%>
<%@page import="java.io.StringWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.lang.Exception" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	Throwable exception = (Throwable) request.getAttribute("exception");
	request.setAttribute("exception", exception);
	
	 Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
     pageContext.setAttribute("statusCode", statusCode);
	
	String uri = (String) request.getAttribute("javax.servlet.error.request_uri");
    String queryString = request.getQueryString();
    String url = uri + (queryString == null || queryString.length() == 0 ? "" : "?" + queryString);
    pageContext.setAttribute("url", url);
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>出错啦</title>
</head>
<body>
	<div class="alert alert-error">
		<a class="close" data-dismiss="alert">×</a> <strong> <c:choose>
				<c:when test="${statusCode eq 404}">
					<h3 style='display: inline;'>页面没有找到！</h3>
					<br />对不起，暂时没有找到您所访问的页面地址,请联系管理员解决此问题！&nbsp;&nbsp;&nbsp;&nbsp;
					<refresh><a href='${url}' class='btn btn-danger'>刷新,看看是否能访问了</a></refresh>
				</c:when>
				<c:when test="${statusCode eq 403}">
					<h3 style='display: inline;'>对不起，您无权访问此资源！</h3>
				</c:when>
				<c:otherwise>
					<h3 style='display: inline;'>服务器程序出问题了！</h3>
					<br />对不起,您访问的页面出了一点内部小问题，刷新重新访问或先去别的页面转转,过会再来吧~！&nbsp;&nbsp;&nbsp;&nbsp;<refresh>
					<a href='${url}' class='btn btn-danger'>刷新,看看是否能访问了</a></refresh>
					<c:if test="${not empty exception}">
						<%
							StringWriter stringWriter = new StringWriter();
							PrintWriter printWriter = new PrintWriter(stringWriter);
							exception.printStackTrace(printWriter);
							pageContext.setAttribute("stackTrace", stringWriter.toString());
						%>
					<br/>
					详细信息:${ stackTrace}
					</c:if>
				</c:otherwise>
			</c:choose>
		</strong>
	</div>
</body>
</html>