<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%String path = request.getContextPath(); %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>消费记录列表</title>
</head>
<body>
	<div class="heading">
        <ul class="breadcrumb">
            <li>您的位置<span class="divider">></span></li>
            <li>消费管理<span class="divider">></span></li>
            <li class="active"><a href="#" class="tip">消费列表</a></li>
        </ul>

    </div><!-- End .heading-->
	<div class="row-fluid">

		<div class="span12">

			<div class="box gradient">

				<div class="title">
					<h4>
						<span class="icon16 cut-icon-arrow-down blue"></span>
						<span>消费记录列表</span>
					</h4>
					<a href="#" class="minimize">Minimize</a>
				</div>
				<div class="content noPad ">
					<table id="consumeTable" cellpadding="0" cellspacing="0" border="0"
						class=" display table table-bordered"
						width="100%">
						<thead>
							<tr>
								<th>消费类型</th>
								<th>消费人</th>
								<th>消费金额</th>
								<th>消费日期</th>
								<th>描述</th>
								<th>操作</th>
							</tr>
						</thead>
						<c:if test="${ !result.content.isEmpty()}">
						<tbody>
							<c:forEach items="${result.content}" var="con" varStatus="st">
								<tr class="odd gradeX">
									<td>${con.consume_type }</td>
									<td>${con.consume_user.user_name }</td>
									<td>${con.consume_money }</td>
									<td><fmt:formatDate value="${con.consume_date }" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
									<td>${con.consume_desc }</td>
									<td>
										<div class="controls center">
											<sec:authorize ifAnyGranted="AUTH_ADMIN"> 
												<a href="<c:url value="/consume/toEdit/${con.consume_id }"/>" class="btip" title="修改"><span  class="icon12 icomoon-icon-pencil"></span></a>
												<a href="<c:url value="/consume/toDel/${con.consume_id }"/>"  class="btip" title="删除"><span class="icon12 icomoon-icon-remove"></span></a>
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
</body>
</html>