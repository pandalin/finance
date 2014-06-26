<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
<head>
<title>权限列表</title>
<script type="text/javascript">
$(function() {
	
	<sec:authorize ifAnyGranted="AUTH_ADMIN">
		var options={type:'GET',url:'<%=path%>/resources/getResources'};
		$.ajaxData(options,function(data){
			initResourceSelect(data);
		});
	</sec:authorize>
	
});
/**
 * 初始化资源下拉列表
 */
function initResourceSelect(resourceList) {
	$("#resource_id").select2({
		data:{results:resourceList,text:function (item) { return item.resource_name; }},
		formatSelection: format,
	    formatResult: format,
	    multiple:true
	}); 
}
function format(item) { 
	return item.resource_name; 
}

function addResources(auth_id) {
	
	$("#resource_modal").modal();
	//清空上一次的选择
	$("#resource_id").select2("data",null);
	
	$(".btn-success").click(function(){
		var nodes = treeObj.getCheckedNodes(true);
		alert(JSON.stringify(nodes));
		
		var data = $("#resource_id").select2("data");
		if (data.length === 0) {
			art.dialog.tips("请选择分配的资源");
			return;
		}
		var resArr = [];
		$.each(data,function(i,item){
			resArr.push(item.id);
		});
		var options={url:'<%=path%>/authorities/saveAuthRes',data:{authid:auth_id,resArr:resArr}};
		$.ajaxAction(options,function(){art.dialog.tips("分配成功");$("#resource_modal").modal("hide");});
	});
}
</script>
</head>
<body>
	<div class="heading">
		<ul class="breadcrumb">
            <li>您的位置<span class="divider">></span></li>
            <li>资源设置<span class="divider">></span></li>
            <li class="active"><a href="<c:url value="/authorities/listAuthorities/%25/%25"/>" class="tip">权限列表</a></li>
        </ul>

    </div><!-- End .heading-->
	<div class="row-fluid">
		<div class="span12">
			<div class="box gradient">
				<div class="title">
					<h4>
						<span class="icon16 icomoon-icon-shield "></span>
						<span>权限列表</span>
					</h4>
					<a href="#" class="minimize">Minimize</a>
				</div>
				
				<div class="content noPad ">
					<table id="authoritiesTable" cellpadding="0" cellspacing="0" border="0"
						class=" display table table-bordered"
						width="100%">
						<thead>
							<tr>
								<th>权限编码</th>
								<th>权限名称</th>
								<th>描述</th>
								<th>状态</th>
								<th>操作</th>
							</tr>
						</thead>
					<c:if test="${ !result.content.isEmpty()}">
						<tbody>
							<c:forEach items="${result.content}" var="auth" varStatus="st">
								<tr class="odd gradeX">
									<td>${auth.authority_code }</td>
									<td>${auth.authority_name }</td>
									<td>${auth.authority_desc }</td>
									<td><c:if test="${auth.authority_status==0 }">
											启用
										</c:if> <c:if test="${auth.authority_status==1 }">
											禁用
										</c:if>
									</td>
									<td>
										<div class="controls center">
											<sec:authorize ifAnyGranted="AUTH_ADMIN"> 
												<a href="javascript:void(0);" onclick="addResources('${auth.id }');" class="btip" title="分配资源"><span  class="icon12 icomoon-icon-pen-2"></span></a>
												<a href="<c:url value="/authorities/toEdit/${auth.id }"/>" class="btip" title="修改"><span  class="icon12 icomoon-icon-pencil"></span></a>
												<a href="<c:url value="/authorities/toDel/${auth.id }"/>"  class="btip" title="删除"><span class="icon12 icomoon-icon-remove"></span></a>
											</sec:authorize>
										</div>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</c:if>
				</table>
			</div>
			<!-- End .box -->

		</div>
		<!-- End .span12 -->
	</div>
    <div id="resource_modal" class="modal hide fade in" style="display: none; width:500px;">
		<div class="modal-header">
			<a class="close" data-dismiss="modal">×</a>
			<h3>分配资源</h3>
		</div>
		<div class="modal-body" style="height: auto;">
			<input style="width:515px"  id="resource_id" name="resource_id" type="hidden"/>
		</div>
		<div class="modal-footer">
			<a href="#" class="btn btn-success">Submit</a>
			<a href="#" class="btn" data-dismiss="modal">Close</a>
		</div>
	</div>
</body>
</html>