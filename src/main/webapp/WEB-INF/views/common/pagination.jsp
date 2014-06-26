<%@page import="org.springframework.data.domain.Page"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <div class="pagination pagination-left" style="margin: 0 0">
	<ul>
		<li><a href="javascript:void(0);">共有${result.totalElements }条数据|当前为第${result.number+1 }页|总共${result.totalPages }页</a></li>
	</ul>
</div> --%>
<div class="pagination pagination-right" style="margin: 0 0">
	<ul id="pagination"></ul>
</div>
<script type="text/javascript">
$(function(){
	//分页
	//var url = $("#search-form").prop("action");
	var url = $("#search-form").prop("action") || $(".breadcrumb").find("a").prop("href");
	var options = {
	        currentPage: '${result.number+1}',
	        totalPages: '${result.totalPages }',
	        numberOfPages:5,
	        pageUrl: function(type, page, current){  
	        	var keyword = $.trim($("#keyword").val());
	        	if (keyword ===""){
	        		keyword = "%25";
	    		} else {
	    			keyword = encodeURI(keyword);
	    		}
	        	return url+"/"+(page-1)+"/"+'${result.size }'+"/"+keyword;
	        }
	}
	if ('${result.totalPages }' > 0 ) {
		$('#pagination').bootstrapPaginator(options);
	};
	//查询
	$("#tipue_search_button").click(function(){
		var keyword = $.trim($("#keyword").val());
		if (keyword ===""){
			url += "/%25/%25/%25";
		} else {
			url += "/%25/%25/"+encodeURI(keyword);
		}
		$("#search-form").prop("action",url);
		$("#search-form").submit();
	});
	
	
});
function getKeyword(){
	var keyword = $.trim($("#keyword").val());
	if (keyword ===""){
		url += "/%25/%25/%25";
	} else {
		url += "/%25/%25/"+encodeURI(keyword);
	}
	return keyword;
}
</script>