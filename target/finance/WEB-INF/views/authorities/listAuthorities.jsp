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
<link href="<c:url value="/common/plugins/ztree/css/zTreeStyle/zTreeStyle.css"/>" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="<c:url value="/common/plugins/ztree/jquery.ztree.core-3.5.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/common/plugins/ztree/jquery.ztree.excheck-3.5.min.js"/>"></script>
<title>权限列表</title>
<script type="text/javascript">
var authid="";
function addResources(auth_id) {
	authid=auth_id;
	$("#resource_modal").modal();
	$('body').on('hidden', '.modal', function () {$(this).removeData('modal');}); 
	var options={type:'GET',url:'<%=path%>/resources/getAuthResources/'+auth_id};
	$.ajaxData(options,function(data){
		if (data && data.length !== 0) {
			//初始化权限对应的资源
			initResourceTree(data);
		} else {
			initResourceTree();
		}
	});
	
}
</script>
<script type="text/javascript">
$(function(){
	initResourceTree();
	
	$(".btn-success").click(function(){
		var checkedNodes = treeObj.getCheckedNodes(true);
		
		if (checkedNodes.length!==0){
			
			var resArr = [];
			$.each(checkedNodes,function(i,item){
				resArr.push(item.id);
			});
			var options={url:'<%=path%>/authorities/saveAuthRes',data:{authid:authid,resArr:resArr}};
			$.ajaxAction(options,function(){art.dialog.tips("分配成功");$("#resource_modal").modal("hide");});
		}
		
	});
	
});
var treeObj;
var setting={
		data:{
			simpleData:{
				enable:true,
				idKey:"id",
				pIdKey:"resource_parent",
				rootPId:"0"
			},
			key:{
				name:"resource_name"
			}
		},
		check:{
			enable:true,
			chkStyle:"checkbox",
			chkboxType:{ "Y": "ps", "N": "s"}
		},
		view: {
			fontCss: {color:"#50C4D3","font-family": "Microsoft YaHei","font-size": "13px","font-weight": "400"}
		}

}
function initResourceTree(selectNodes) {
	if (selectNodes) {
		var nodes = treeObj.getCheckedNodes();//这里要清空上一次的选择才行,否则上一次的选择还在
		for (var i=0, l=nodes.length; i < l; i++) {
			treeObj.checkNode(nodes[i], false, false);
		}
		
		$.each(selectNodes,function(i,item) {
			treeObj.checkNode(treeObj.getNodeByParam("id",item),true,false,false);
		});
	} else {
		var options={type:'GET',url:'<%=path%>/resources/getResources'};
		$.ajaxData(options,function(data){
			var t = $("#tree");
			t = $.fn.zTree.init(t, setting, data).expandAll(true);
			treeObj = $.fn.zTree.getZTreeObj("tree");
			treeObj.expandAll(false);
			var nodes = treeObj.getNodes();
			treeObj.setChkDisabled(nodes[0],true);
			treeObj.expandNode(nodes[0],true);
		});
	}
}
</script>
</head>
<body>
	<div class="heading">
		<ul class="breadcrumb">
            <li>您的位置<span class="divider">></span></li>
            <li>资源设置<span class="divider">></span></li>
            <li class="active"><a href="<c:url value="/authorities/toList"/>" class="tip">权限列表</a></li>
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
									<td>
										<c:if test="${auth.authority_status==0 }">启用 </c:if> 
										<c:if test="${auth.authority_status==1 }">禁用</c:if>
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
			<!-- <input style="width:515px"  id="resource_id" name="resource_id" type="hidden"/> -->
			<ul id="tree" class="ztree" style="overflow:auto;"></ul>
		</div>
		<div class="modal-footer">
			<a href="#" class="btn btn-success">Submit</a>
			<a href="#" class="btn" data-dismiss="modal">Close</a>
		</div>
	</div>
</body>
</html>