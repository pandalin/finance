<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% String path = request.getContextPath(); %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form"  uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件上传</title>
<script type="text/javascript">
$(document).ready(function() {
});
</script>
</head>
<body>
	<div class="heading">
		<ul class="breadcrumb">
            <li>您的位置<span class="divider">></span></li>
            <li>文件管理<span class="divider">></span></li>
            <li class="active"><a href="#" class="tip">文件上传</a></li>
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

					<form:form class="form-horizontal" id="form-validate" action="ajaxUpload" method="post" enctype="multipart/form-data">
						<div class="form-row row-fluid">
							<div class="span12">
								<div class="row-fluid">
									<label class="form-label span3 left" for="required">文件</label>
									<input class="span4" type="file" name="file">
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
					</form:form>

				</div>

			</div>
			<!-- End .box -->

		</div>
		<!-- End .span12 -->

	</div>
	<!-- End .row-fluid -->
</body>
</html>