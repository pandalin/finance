<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="org.springframework.security.web.WebAttributes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%String path=request.getContextPath(); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Finance</title>
   
    <!-- Le styles -->
    <link href="<%=path %>/common/css/bootstrap/bootstrap.css" rel="stylesheet" />
    <link href="<%=path %>/common/css/bootstrap/bootstrap-responsive.css" rel="stylesheet" />
    <link href="<%=path %>/common/css/icons.css" rel="stylesheet" type="text/css" />
    <link href="<%=path %>/common/plugins/uniform/uniform.default.css" type="text/css" rel="stylesheet" />
	<link href="<c:url value="/common/plugins/validate/validate.css"/>" type="text/css" rel="stylesheet" />
    <!-- Main stylesheets -->
    <link href="<%=path %>/common/css/main.css" rel="stylesheet" type="text/css" /> 

    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <!-- Le fav and touch icons -->
    <link rel="shortcut icon" href="<%=path %>/common/images/favicon.ico" />
<style>
.errorblock {
    color: #ff0000;
    background-color: #ffEEEE;
    border: 3px solid #ff0000;
    padding: 8px;
    margin: 16px;
}
</style>
</head>
<body class="loginPage">

   <div class="container-fluid">

        <div id="header">

            <div class="row-fluid">

                <div class="navbar">
                    <div class="navbar-inner">
                      <div class="container">
                            <a class="brand" href="dashboard.html">Finance.<span class="slogan">system</span></a>
                            <!-- <a class="brand" href="javascript:void(0);">欢迎光临.<span class="slogan">个人消费系统</span></a> -->
                      </div>
                    </div><!-- /navbar-inner -->
                  </div><!-- /navbar -->
                

            </div><!-- End .row-fluid -->

        </div><!-- End #header -->

    </div><!-- End .container-fluid -->    
    <div class="container-fluid">
        <div class="loginContainer">
        	<div class="alert alert-error" style="display: ${error eq 'true'?'block':'none'}">
			  <a class="close" data-dismiss="alert">×</a>
			  <strong>${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message }</strong>
			</div>
            <form class="form-horizontal" action="<c:url value='login_check' />" id="loginForm" method="post">
                <div class="form-row row-fluid">
                    <div class="span12">
                        <div class="row-fluid">
                            <label class="form-label span12" for="username">
                                Username:
                                <span class="icon16 icomoon-icon-user-3 right gray marginR10"></span>
                            </label>
                            <input class="span12" id="username" type="text" name="j_username" value="" placeholder="Please enter your username"/>
                        </div>
                    </div>
                </div>

                <div class="form-row row-fluid">
                    <div class="span12">
                        <div class="row-fluid">
                            <label class="form-label span12" for="password">
                                Password:
                                <span class="icon16 icomoon-icon-locked right gray marginR10"></span>
                                <!-- <span class="forgot"><a href="#">Forgot your password?</a></span> -->
                            </label>
                            <input class="span12" id="password" type="password" name="j_password" value="" placeholder="Please enter your password"/>
                        </div>
                    </div>
                </div>
                <div class="form-row row-fluid">                       
                    <div class="span12">
                        <div class="row-fluid">
                            <div class="form-actions">
                            <div class="span12 controls">
                                <input type="checkbox" id="_spring_security_remember_me" value="" class="styled" name="_spring_security_remember_me" /> remember me
                                <button type="submit" class="btn btn-info right" id="loginBtn"><span class="icon16 icomoon-icon-enter white"></span> 登录</button>
                            </div>
                            </div>
                        </div>
                    </div> 
                </div>

            </form>
        </div>

    </div><!-- End .container-fluid -->
    <!-- Le javascript
    ================================================== -->
    <!-- <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script> -->
    <script type="text/javascript" src="<%=path %>/common/js/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="<%=path %>/common/js/bootstrap/bootstrap.js"></script>  
    <script type="text/javascript" src="<%=path %>/common/plugins/validate/jquery.validate.min.js"></script>
    <script type="text/javascript" src="<%=path %>/common/plugins/uniform/jquery.uniform.min.js"></script>

     <script type="text/javascript">
        // document ready function
        $(document).ready(function() {
            $("input, textarea, select").not('.nostyle').uniform();
            $("#loginForm").validate({
                rules: {
                	j_username: {
                        required: true,
                        maxlength: 10
                    },
                    j_password: {
                        required: true,
                        maxlength: 10
                    }  
                },
                messages: {
                	j_username: {
                        required: "Please enter your username",
                        maxlength: "My name is bigger"
                    },
                    j_password: {
                        required: "Please provide a password",
                        maxlength: "My password is bigger"
                    }
                }   
            });
        });
    </script>
</body>
</html>
