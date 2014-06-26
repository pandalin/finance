<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>资源管理</title>
<script type="text/javascript">
function format(item) { return item.resource_name; }
$(document).ready(function() {
	
	$('#resource_type').chosen();
	
	var options={type:'GET',url:'<%=path%>/resources/getResources'};
	$.ajaxData(options,function(data){
		$("#resource_parent").select2({
			data:{results:data,text:function (item) { return item.resource_name; }},
			formatSelection: format,
		    formatResult: format,
		    closeOnSelect:false,
		    initSelection:function(element, callback) {
		        callback(data);
		    }
		}); 
	});
	$("#resource_parent").select2("val","${resource.resource_parent}");
  	
	$("#form-validate").validate({
	    	ignore: null,
	    	ignore: 'input[type="hidden"]',
	    	rules: {
	    		resource_desc: "required",
	    		resource_name: {
					required: true,
					minlength: 4
				},
				resource_type: {
					required: true
				},
				resource_parent: {
					required: true
				},
				resource_url: {
					required: true
				},
				resource_priority:{
					required: true,
					number: true
				}
			},
			messages: {
				required: "Please enter something",
				resource_name: {
					required: "Please enter something",
					minlength: "This field must consist of at least 4 characters"
				},
				resource_type: {
					required: "Please select a type"
				},
				resource_parent: {
					required: "Please select a parent"
				},
				resource_url: {
					required: "Please enter something"
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
            <li>资源设置<span class="divider">></span></li>
            <li class="active"><a href="#" class="tip">资源管理</a></li>
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

					<form class="form-horizontal" id="form-validate" action="<c:url value="/resources/save" />" method="post">
						<input type="hidden" name="id" value="${resource.id }">
						<div class="form-row row-fluid">
							<div class="span12">
								<div class="row-fluid">
									<label class="form-label span3 left" for="required">资源名称</label> 
									<input class="span4" id="resource_name" name="resource_name" type="text" value="${resource.resource_name }"/>
								</div>
							</div>
						</div>
						<div class="form-row row-fluid">
							<div class="span12">
								<div class="row-fluid">
									<label class="form-label span3 left" for="required">资源类型</label> 
									<div class="span4 controls sel">
									<select id="resource_type" name="resource_type" class="nostyle" style="width:365px;">
										<option value="URL" <c:if test="${resource_type eq 'URL' }">selected="selected"</c:if>>URL</option>
										<option value="MENU" <c:if test="${resource_type eq 'MENU' }">selected="selected"</c:if>>菜单</option>
										<option value="BUTTON" <c:if test="${resource_type eq 'BUTTON' }">selected="selected"</c:if>>按钮</option>
									</select>
									</div>
								</div>
							</div>
						</div>
						<div class="form-row row-fluid">
							<div class="span12">
								<div class="row-fluid">
									<label class="form-label span3 left" for="required">上级节点</label> 
									<div class="span4 controls">
										<input style="width:365px"  id="resource_parent" name="resource_parent" type="hidden"/>
									</div>
								</div>
							</div>
						</div>
						<div class="form-row row-fluid">
							<div class="span12">
								<div class="row-fluid">
									<label class="form-label span3 left" for="required">资源URL</label> 
									<input class="span4" id="resource_url" name="resource_url" type="text" value="${resource.resource_url }"/>
								</div>
							</div>
						</div>
						<div class="form-row row-fluid">
							<div class="span12">
								<div class="row-fluid">
									<label class="form-label span3 left" for="checkboxes">状态</label>
									<div class="left marginT5 marginR10">
										<input type="radio" name="resource_status" value="0" checked="checked" />启用
									</div>
									<div class="left marginT5 marginR10">
										<input type="radio" name="resource_status" value="1" />禁用
									</div>
								</div>
							</div>
						</div>
						<div class="form-row row-fluid">
							<div class="span12">
								<div class="row-fluid">
									<label class="form-label span3 left" for="normal">描述</label>
									<div class="controls-textarea span9">
										<textarea id="resource_desc" name="resource_desc">${resource.resource_desc }</textarea>
									</div>
								</div>
							</div>
						</div>
						<div class="form-row row-fluid">
							<div class="span12">
								<div class="row-fluid">
									<label class="form-label span3 left" for="required">序号</label> 
									<input class="span4" id="resource_priority" name="resource_priority" type="text" value="${resource.resource_priority }"/>
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