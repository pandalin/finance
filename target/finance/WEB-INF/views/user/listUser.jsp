<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户列表</title>
<script type="text/javascript">
	function addRole(userid) {
		getRoles(userid);
		$(".btn-success").click(function(){
			var roleArr = [];
			$("input[name='roleId']:checked").each(function(){
				roleArr.push($(this).val());
			});
			if (roleArr.length===0) {
				art.dialog.tips("请选择需要分配的角色"); 
				return;
			}
			var options={url:'<%=path%>/role/addUserRole',data:{roleArr:roleArr,userid:userid}};
		    $.ajaxAction(options,function(){
		    	art.dialog.tips("分配成功");
		    	$("#role_modal").modal("hide");
		    });
		});
	}
	function getRoles(userid) {
		
		var options={url:'<%=path%>/role/userRoleList/'+userid};
	    $.ajaxAction(options,function(data){
	    	var strHTML="";
			$.each(data.allRoleList,function(i,role){
				strHTML += "<tr>";  
               
				strHTML += "<td class='chChildren'><input type='checkbox' name='roleId' value='"+role.id+"' class='styled' ";
				$.each(data.userRoleList,function(i,userrole) {
					if(role.id===userrole.id) {
						strHTML += " checked='checked' ";
						return false;
					}
				});
				strHTML += " /></td>";
				
				strHTML += "<td>"+role.role_code+"</td>";  
                strHTML += "<td>"+role.role_name+"</td>";  
                strHTML += "</tr>";  
			});
			$("#Data").html(strHTML);
			$("input[type='checkbox']").uniform();
			$("#role_modal").modal({backdrop:true});
	    });
		
	}
</script>
</head>
<body>
	<div class="heading">
		<ul class="breadcrumb">
			<li>You are here:<span class="divider">></span></li>
            <li>用户设置<span class="divider">></span></li>
            <li class="active"><a href="#" class="tip">用户列表</a></li>
        </ul>

    </div><!-- End .heading-->
	<div class="row-fluid">
		<div class="span12">
			<div class="box gradient">
				<div class="title">
					<h4>
						<span class="icon16 icomoon-icon-people "></span>
						<span>用户列表</span>
					</h4>
					<a href="#" class="minimize">Minimize</a>
				</div>
				
				<div class="content noPad ">
				<form id="search-form" class="form form-horizontal" action="<c:url value="/user/listUser"/>" method="post">
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
				<table id="userTable" cellpadding="0" cellspacing="0" border="0"
					class=" display table table-bordered"
					width="100%">
					<thead>
						<tr>
							<th>用户编码</th>
							<th>用户名</th>
							<th>状态</th>
							<th>创建日期</th>
							<th>操作</th>
						</tr>
					</thead>
					<c:if test="${ !result.content.isEmpty()}">
					<tbody>
						<c:forEach items="${result.content}" var="u" varStatus="st">
							<tr class="odd gradeX">
								<td>${u.user_code }</td>
								<td>${u.user_name }</td>
								<td><c:if test="${u.user_status==0 }">
										启用
									</c:if> <c:if test="${u.user_status==1 }">
										禁用
									</c:if>
								</td>
								<td><fmt:formatDate value="${u.user_date }" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
								<td>
									<div class="controls center">
										<sec:authorize ifAnyGranted="AUTH_ADMIN"> 
											<a href="javascript:void(0);" onclick="addRole('${u.user_id }');" class="btip" title="分配角色"><span  class="icon12 icomoon-icon-pen-2"></span></a>
											<a href="<c:url value="/user/toEdit/${u.user_id }"/>" class="btip" title="修改"><span  class="icon12 icomoon-icon-pencil"></span></a>
											<a href="<c:url value="/user/toDel/${u.user_id }"/>"  class="btip" title="删除"><span class="icon12 icomoon-icon-remove"></span></a>
										</sec:authorize>
									</div>
								</td>
							</tr>
						</c:forEach>
					</tbody>
					</c:if>
				</table>
				<%@include file="../common/pagination.jsp" %>
			</div>
			<!-- End .box -->

		</div>
		<!-- End .span12 -->
	</div>
    <div id="role_modal" class="modal hide fade in" style="display: none; ">
		<div class="modal-header">
			<a class="close" data-dismiss="modal">×</a>
			<h3>分配角色</h3>
		</div>
		<div class="modal-body">
			<table class="responsive table table-bordered" id="checkAll">  
		    	<thead>
		    		<tr>
		    			<th id="masterCh" class="ch"><input type="checkbox" name="checkbox" value="all" class="styled" /></th>
		    			<th>角色编码</th>
		    			<th>角色名称</th>
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