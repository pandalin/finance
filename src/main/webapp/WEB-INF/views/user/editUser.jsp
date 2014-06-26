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
<title>用户管理</title>
<script type="text/javascript" src="<c:url value="/common/plugins/My97DatePicker/WdatePicker.js"/>"></script>
<script type="text/javascript">
$(document).ready(function() {
	 $("#form-validate").validate({
	    	ignore: null,
	    	ignore: 'input[type="hidden"]',
	    	rules: {
				requiredArea: "required",
				user_code: {
					required: true,
					minlength: 4
				},
				user_name: {
					required: true,
					minlength: 2
				},
				user_password: {
					required: true,
					minlength: 5
				},
				confirm_password: {
					required: true,
					minlength: 5,
					equalTo: "#password"
				},
				email: {
					required: true,
					email: true
				},
				birthday: {
			      required: true,
			      date: true
			    }
			},
			messages: {
				required: "Please enter a something",
				user_code: {
					required: "Please enter your login name",
					minlength: "This field must consist of at least 4 characters"
				},
				user_name: {
					required: "Please enter your real name",
					minlength: "This field must consist of at least 4 characters"
				},
				user_password: {
					required: "Please provide a password",
					minlength: "Your password must be at least 5 characters long"
				},
				confirm_password: {
					required: "Please provide a password",
					minlength: "Your password must be at least 5 characters long",
					equalTo: "Please enter the same password as above"
				}
			}
	    });
});
</script>
</head>
<body>
	<div class="heading">
		<ul class="breadcrumb">
            <li>您的位置<span class="divider">></span></li>
            <li>用户设置<span class="divider">></span></li>
            <li class="active"><a href="#" class="tip">用户管理</a></li>
        </ul>
    </div><!-- End .heading-->
	<div class="row-fluid">

		<div class="span12">

			<div class="box">

				<div class="title">

					<h4>
						<span>编辑界面</span>
					</h4>

				</div>
				<div class="content">

					<form class="form-horizontal" id="form-validate" action="<c:url value="/user/addUser" />" method="post">
						<input type="hidden" name="user_id" value="${user.user_id }" />
						<div class="form-row row-fluid">
							<div class="span12">
								<div class="row-fluid">
									<label class="form-label span3 left" for="required">用户名</label> 
									<input class="span4" id="user_code" name="user_code" type="text" value="${user.user_code }"/>
								</div>
							</div>
						</div>
						<div class="form-row row-fluid">
							<div class="span12">
								<div class="row-fluid">
									<label class="form-label span3 left" for="required">姓名</label> 
									<input class="span4" id="user_name" name="user_name" type="text" value="${user.user_name }"/>
								</div>
							</div>
						</div>
						<c:if test="${empty user.user_id}">
							<div class="form-row row-fluid">
								<div class="span12">
									<div class="row-fluid">
										<label class="form-label span3 left" for="required">密码</label>
										<input class="span4" id="password" name="user_password" type="password" placeholder="Enter your password" /> 
									</div>
								</div>
							</div>
							<div class="form-row row-fluid">
								<div class="span12">
									<div class="row-fluid">
										<label class="form-label span3 left" for="required">确认密码</label>
										<input class="span4" id="passwordConfirm" name="confirm_password" type="password" placeholder="Enter your password again" />
									</div>
								</div>
							</div>
						</c:if>
						<div class="form-row row-fluid">
							<div class="span12">
								<div class="row-fluid">
									<label class="form-label span3 left" for="normal">生日</label>
									<input class="span4 Wdate" id="birthday" name="birthday" type="text" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})"/> 
								</div>
							</div>
						</div>
						<div class="form-row row-fluid">
							<div class="span12">
								<div class="row-fluid">
									<label class="form-label span3 left" for="checkboxes">状态</label>
									<div class="left marginT5 marginR10">
										<input type="radio" name="user_status" value="0" checked="checked" />启用
									</div>
									<div class="left marginT5 marginR10">
										<input type="radio" name="user_status" value="1" />禁用
									</div>
								</div>
							</div>
						</div>
						<div class="form-row row-fluid">
							<div class="span12">
								<div class="row-fluid">
									<label class="form-label span3 left" for="normal">描述</label>
									<div class="controls-textarea span9">
										<textarea id="requiredArea" name="requiredArea"></textarea>
									</div>
								</div>
							</div>
						</div>
						<div class="form-row row-fluid">
							<div class="span12">
								<div class="row-fluid">
									<div class="form-actions">
										<div class="span3"></div>
										<div class="span9 controls">
											<button type="submit" class="btn btn-success">保存</button>
											<button type="button" class="btn btn-danger">取消</button>
										</div>
									</div>
								</div>
							</div>
						</div>
					</form>

				</div>

			</div>
			<!-- End .box -->

		</div>
		<!-- End .span12 -->

	</div>
	<!-- End .row-fluid -->
</body>
</html>