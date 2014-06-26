<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="header.jsp"%>
</head>
<body>
	<div id="qLoverlay"></div>
    <div id="qLbar"></div>
    <div id="header">
        <div class="navbar">
            <div class="navbar-inner">
              <div class="container-fluid">
                <a class="brand" href="#">Finance</a>
                <div class="nav-no-collapse">
                  
                    <ul class="nav pull-right usernav">
                    	<li class="dropdown">
                            <a href="#" class="dropdown-toggle avatar">
                    		<span class="icon16 icomoon-icon-user-2"></span>
                                <span class="txt">${sessionScope.CURRENT_USER.user_name }</span>
                                <span class="txt"><sec:authentication property="principal.user.user_name"/> </span>
                            </a>
                        </li>
                        <li><a href="<c:url value="/j_spring_security_logout" />"><span class="icon16 icomoon-icon-exit"></span> 退出</a></li>
                    </ul>
                </div><!-- /.nav-collapse -->
              </div>
            </div><!-- /navbar-inner -->
          </div><!-- /navbar --> 

    </div><!-- End #header -->
	<div id="wrapper">
		<%@include file="left.jsp"%>
		 <!--Body content-->
        <div id="content" class="clearfix">
            <div class="contentwrapper"><!--Content wrapper-->
                
                <!-- Build page from here: -->
				<decorator:body />
                <!-- Page end here -->
            </div><!-- End contentwrapper -->
        </div><!-- End #content -->
	</div>
</body>
</html>