<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="<c:url value="/common/css/icons.css"/>" type="text/css" rel="stylesheet" />
    <link href="<c:url value="/common/plugins/validate/validate.css"/>" type="text/css" rel="stylesheet" />
    <link href="<c:url value="/common/plugins/uniform/uniform.default.css"/>" type="text/css" rel="stylesheet" />
   	<link href="<c:url value="/common/plugins/select/select2.css"/>" type="text/css" rel="stylesheet" />
	<link href="<c:url value="/common/plugins/chosen/chosen.css"/>" type="text/css" rel="stylesheet" />
    <link href="<c:url value="/common/css/bootstrap/bootstrap.min.css"/>" type="text/css" rel="stylesheet" />
    <link href="<c:url value="/common/css/bootstrap/bootstrap-responsive.min.css"/>" type="text/css" rel="stylesheet" />
    
    <!-- Main stylesheets -->
    <link href="<c:url value="/common/css/main.css"/>" rel="stylesheet" type="text/css" /> 
    <!-- Right to left version 
    <link href="<c:url value="/common/css/rtl.css"/>" rel="stylesheet" type="text/css" /> -->

    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <!-- Le fav and touch icons -->
    <link rel="shortcut icon" href="<c:url value="/common/images/favicon.ico"/>" />
    
    <script type="text/javascript">
        //adding load class to body and hide page
        document.documentElement.className += 'loadstate';
    </script>

    <!-- <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script> -->
	<script type="text/javascript" src="<c:url value="/common/js/jquery-1.7.1.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/common/js/jquery.cookie.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/common/plugins/totop/jquery.ui.totop.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/common/plugins/artdialog/jquery.artDialog.js?skin=idialog"/>"></script>
	<script type="text/javascript" src="<c:url value="/common/plugins/artdialog/plugins/iframeTools.js"/>"></script>

	<!-- Load plugins -->
	<script type="text/javascript" src="<c:url value="/common/plugins/validate/jquery.validate.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/common/plugins/uniform/jquery.uniform.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/common/plugins/select/select2.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/common/plugins/chosen/jquery.chosen.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/common/js/bootstrap/bootstrap.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/common/plugins/bootstrap-paginator/0.5/bootstrap-paginator.min.js"/>"></script>
	
    <!-- Important Place before main.js  -->
    <!-- <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.21/jquery-ui.min.js"></script> -->
    <script type="text/javascript" src="<c:url value="/common/js/supr-theme/jquery-ui-1.8.21.custom.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/common/js/main.js"/>"></script>
    <title><decorator:title>Finance</decorator:title></title> 
    <decorator:head />
