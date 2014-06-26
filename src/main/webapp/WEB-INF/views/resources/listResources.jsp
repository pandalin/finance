<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>资源列表</title>
<script type="text/javascript">
	
</script>
</head>
<body>
<div class="heading">
        <ul class="breadcrumb">
            <li>您的位置<span class="divider">></span></li>
            <li>资源设置<span class="divider">></span></li>
            <li class="active"><a href="<c:url value="/resources/toList"/>" class="tip">资源列表</a></li>
        </ul>

    </div><!-- End .heading-->
	<div class="row-fluid">

		<div class="span12">

			<div class="box gradient">

				<div class="title">
					<h4>
						<span class="icon16 icomoon-icon-tree"></span>
						<span>资源列表</span>
					</h4>
					<a href="#" class="minimize">Minimize</a>
				</div>
				<div class="content noPad clearfix">
					<table id="resourceTable" cellpadding="0" cellspacing="0" border="0"
						class="responsive display table table-bordered"
						width="100%">
						<thead>
							<tr>
								<th>资源名称</th>
								<th>资源类型</th>
								<th>资源URL</th>
								<th>父级资源</th>
								<th>资源状态</th>
								<th>资源描述</th>
								<th>资源序号</th>
								<sec:authorize ifAnyGranted="AUTH_ADMIN">
								<th>操作</th>
								</sec:authorize>
							</tr>
						</thead>
						<c:if test="${ !result.content.isEmpty()}">
						<tbody>
							<c:forEach items="${result.content}" var="r">
								<tr class="odd gradeX">
									<td>${r.resource_name }</td>
									<td>
										<c:if test="${r.resource_type eq 'MENU_1' }">一级菜单</c:if>
										<c:if test="${r.resource_type eq 'MENU_2' }">二级菜单</c:if>
										<c:if test="${r.resource_type eq 'BUTTON' }">菜单按钮</c:if>
									</td>
									<td>${r.resource_url }</td>
									<td>${r.parent_name }</td>
									<td>
										<c:if test="${r.resource_status==0 }">有效</c:if> 
										<c:if test="${r.resource_status==1 }">无效</c:if>
									</td>
									<td>${r.resource_desc }</td>
									<td>${r.resource_priority }</td>
									<sec:authorize ifAnyGranted="AUTH_ADMIN">
										<td>
											<div class="controls center">
												<a href="<c:url value="/resources/toEdit/${r.id }"/>" class="tip" title="修改"><span  class="icon12 icomoon-icon-pencil"></span></a>
												<a href="<c:url value="/resources/toDel/${r.id }"/>"  class="tip" title="删除"><span class="icon12 icomoon-icon-remove"></span></a>
											</div>
										</td>
									</sec:authorize>
								</tr>
							</c:forEach>
						</tbody>
						</c:if>
					</table>
					<%@include file="../common/pagination.jsp" %>
				</div>
			</div>
			<!-- End .box -->

		</div>
		<!-- End .span12 -->
	</div>

</body>
</html>