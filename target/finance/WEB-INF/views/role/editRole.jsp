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
<title>角色管理</title>
<script type="text/javascript">
$(document).ready(function() {
	 $("#form-validate").validate({
	    	ignore: null,
	    	ignore: 'input[type="hidden"]',
	    	rules: {
	    		role_desc: "required",
	    		role_code: {
					required: true,
					minlength: 4
				},
				role_name: {
					required: true,
					minlength: 4
				}
			},
			messages: {
				required: "Please enter a something",
				role_code: {
					required: "Please enter a code",
					minlength: "This field must consist of at least 4 characters"
				},
				role_name: {
					required: "Please enter a name",
					minlength: "This field must consist of at least 4 characters"
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
            <li>角色设置<span class="divider">></span></li>
            <li class="active"><a href="#" class="tip">角色管理</a></li>
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

					<form class="form-horizontal" id="form-validate" action="<c:url value="/role/addRole" />" method="post">
						<div class="form-row row-fluid">
							<div class="span12">
								<div class="row-fluid">
									<label class="form-label span3 left" for="required">角色编码</label> 
									<input class="span4" id="role_code" name="role_code" type="text" value="${role.role_code }"/>
								</div>
							</div>
						</div>
						<div class="form-row row-fluid">
							<div class="span12">
								<div class="row-fluid">
									<label class="form-label span3 left" for="required">角色名称</label> 
									<input class="span4" id="role_name" name="role_name" type="text" value="${role.role_name }"/>
								</div>
							</div>
						</div>
						<div class="form-row row-fluid">
							<div class="span12">
								<div class="row-fluid">
									<label class="form-label span3 left" for="checkboxes">状态</label>
									<div class="left marginT5 marginR10">
										<input type="radio" name="role_status" value="0" checked="checked" />启用
									</div>
									<div class="left marginT5 marginR10">
										<input type="radio" name="role_status" value="1" />禁用
									</div>
								</div>
							</div>
						</div>
						<div class="form-row row-fluid">
							<div class="span12">
								<div class="row-fluid">
									<label class="form-label span3 left" for="normal">描述</label>
									<div class="controls-textarea span9">
										<textarea id="role_desc" name="role_desc"></textarea>
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