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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色列表</title>
<script type="text/javascript">
	function addRoleAuth(roleid) {
		
		getAuthorities(roleid);
		$(".btn-success").click(function(){
			 var authArr = [];
    		 $("input[name='authId']:checked").each(function(){
    			authArr.push($(this).val());
    		 });
    		 if (authArr.length===0) {
    			art.dialog.tips("请选择需要分配的权限"); 
    			return;
    		 }
    		  
    		 var options={url:'<%=path%>/authorities/saveRoleAuth',data:{authArr:authArr,roleid:roleid}};
    		 $.ajaxAction(options,function(){art.dialog.tips("分配成功");$("#dialog").modal("hide");});
		});
	}
	function getAuthorities(roleid) {
		var options={type:'GET',url:'<%=path%>/authorities/getRoleAuth/'+roleid};
		$.ajaxAction(options,function(data){
			var strHTML="";
			$.each(data.allAuthList,function(i,auth){
				strHTML += "<tr>";  
				strHTML += "<td class='chChildren'><input type='checkbox' name='authId' value='"+auth.id+"' class='styled' ";
				$.each(data.roleAuthList,function(i,roleauth) {
					if(auth.id===roleauth.id) {
						strHTML += " checked='checked' ";
						return;
					}
				});
				strHTML += " /></td>";
                strHTML += "<td>"+auth.authority_code+"</td>";  
                strHTML += "<td>"+auth.authority_name+"</td>";  
                strHTML += "</tr>";  
			});
			$("#Data").html(strHTML);
			$("input[type='checkbox']").uniform();
			$("#dialog").modal();
		});
	}
</script>
</head>
<body>
<div class="heading">
        <ul class="breadcrumb">
        	<li>您的位置<span class="divider">></span></li>
            <li>角色设置<span class="divider">></span></li>
            <li class="active"><a href="#" class="tip">角色列表</a></li>
        </ul>

    </div><!-- End .heading-->
	<div class="row-fluid">
		<div class="span12">

			<div class="box gradient">

				<div class="title">
					<h4>
						<span class="icon16 icomoon-icon-user-4"></span>
						<span>角色列表</span>
					</h4>
					<a href="#" class="minimize">Minimize</a>
				</div>
				<div class="content noPad clearfix">
				<form id="search-form" class="form form-horizontal" action="<c:url value="/role/listRole"/>" method="post">
                     <div class="form-row row-fluid">
                         <div class="span12">
                             <div class="row-fluid">
                                 <div class="span10">
                                 	<label class="form-label span2 left">关键字</label> 
                                    <input type="text" class="span2" id="keyword" name="keyword" placeholder="Keywords" value="${keyword }"/>
                                    <button class="btn" type="button" id="tipue_search_button"><span class="icon16 icomoon-icon-search-3"></span> Search</button>
                                 </div>
                             </div>
                         </div>
                     </div>
                </form>
				<table id="roleTable" cellpadding="0" cellspacing="0" border="0"
					class="responsive display table table-bordered"
					width="100%">
					<thead>
						<tr>
							<th>角色编码</th>
							<th>角色名称</th>
							<th>角色状态</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${result.content}" var="r">
							<tr class="odd gradeX">
								<td>${r.role_code }</td>
								<td>${r.role_name }</td>
								<td>
									<c:if test="${r.role_status==0 }">启用 </c:if> 
									<c:if test="${r.role_status==1 }">禁用</c:if>
								</td>
								<td>
									<div class="controls center">
										<sec:authorize ifAnyGranted="AUTH_ADMIN,AUTH_ASSIGN_AUTH">
											<a href="javascript:void(0);" onclick="addRoleAuth('${r.id }');" class="btip" title="分配权限"><span  class="icon12 icomoon-icon-pen-2"></span></a>
										</sec:authorize>
										<sec:authorize ifAnyGranted="AUTH_ADMIN">
											<a href="<c:url value="/role/toEdit/${r.id }"/>" class="btip" title="修改"><span  class="icon12 icomoon-icon-pencil"></span></a>
											<a href="<c:url value="/role/toDel/${r.id }"/>"  class="btip" title="删除"><span class="icon12 icomoon-icon-remove"></span></a>
										</sec:authorize>
									</div>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<%@include file="../common/pagination.jsp" %>
				</div>
			</div>
			<!-- End .box -->

		</div>
		<!-- End .span12 -->
	</div>
    <div id="dialog" class="modal hide fade in" style="display: none; ">
		<div class="modal-header">
			<a class="close" data-dismiss="modal">×</a>
			<h3>分配权限</h3>
		</div>
		<div class="modal-body">
			<table class="responsive table table-bordered" id="checkAll">  
		    	<thead>
		    		<tr>
		    			<th id="masterCh" class="ch"><input type="checkbox" name="checkbox" value="all" class="styled" /></th>
		    			<th>权限编码</th>
		    			<th>权限名称</th>
		    		</tr>
		    	</thead>  
		    	<tbody id="Data"></tbody>  
	    	</table>  		        
		</div>
		<div class="modal-footer">
			<a href="#" class="btn btn-success">Submit</a>
			<a href="#" class="btn" data-dismiss="modal">Close</a>
		</div>
	</div>
</body>
</html>