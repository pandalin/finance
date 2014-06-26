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
<title>新增消费记录</title>
<script type="text/javascript" src="<c:url value="/common/plugins/My97DatePicker/WdatePicker.js"/>"></script>
<script type="text/javascript">
$(document).ready(function() {
	 $("#form-validate").validate({
	    	ignore: null,
	    	ignore: 'input[type="hidden"]',
	    	rules: {
	    		consume_desc: "required",
				consume_date: {
					required: true,
					date: true
				},
				consume_money: {
					required: true,
					number: true
				}
			},
			messages: {
				required: "Please enter a something"
			}
	    });
});
</script>
</head>
<body>
	<div class="heading">
        <ul class="breadcrumb">
            <li>您的位置<span class="divider">></span></li>
            <li>消费管理<span class="divider">></span></li>
            <li class="active"><a href="#" class="tip">新增消费</a></li>
        </ul>

    </div><!-- End .heading-->
	<div class="row-fluid">

		<div class="span12">

			<div class="box">

				<div class="title">

					<h4>
						<span>新增消费</span>
					</h4>

				</div>
				<div class="content">

					<form class="form-horizontal" id="form-validate" action="<c:url value="/consume/add" />" method="post">
						<div class="form-row row-fluid">
							<div class="span12">
								<div class="row-fluid">
									<label class="form-label span3 left" for="normal">消费日期</label>
									<div class="span9 controls">
										<input class="span4 Wdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})" id="consume_date" name="consume_date" type="text"  /> 
									</div>
								</div>
							</div>
						</div>
						<div class="form-row row-fluid">
							<div class="span12">
								<div class="row-fluid">
									<label class="form-label span3 left" for="normal">消费金额</label>
									<div class="span9 controls">
										<input class="span4"  id="consume_money" name="consume_money" type="text"  /> 
									</div>
								</div>
							</div>
						</div>
						<div class="form-row row-fluid">
							<div class="span12">
								<div class="row-fluid">
									<label class="form-label span3 left" for="normal">描述</label>
									<div class="controls-textarea span9">
										<textarea id="consume_desc" name="consume_desc"></textarea>
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