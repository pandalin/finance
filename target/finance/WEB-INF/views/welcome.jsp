<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Finance</title>
<script type="text/javascript" src="<c:url value="/common/plugins/highcharts/js/highcharts.js"/>"></script>
<script type="text/javascript" src="<c:url value="/common/plugins/highcharts/themes/gray.js"/>"></script>
<script type="text/javascript">
$(function () { 
	$.post('<%=path%>/consume/selectConsumeGroupByMonth',{},function(data){
		var con = [];
		
		var u = {};
		u.name = data.user_name;
		u.data = data.money;
		con.push(u);
		$('#container').highcharts({
			chart: {
	            type: 'line'
	        },
	        title: {
	            text: '',
	            x: -20 //center
	        },
	        subtitle: {
	            text: '',
	            x: -20
	        },
	        xAxis: {
	            categories: data.month
	        },
	        yAxis: {
	            title: {
	                text: '金额(RMB)'
	            },
	            plotLines: [{
	                value: 0,
	                width: 1,
	                color: '#808080'
	            }]
	        },
	        tooltip: {
	            valueSuffix: '元',
	            formatter: function() {
	                return '<b>'+ this.series.name +'</b><br>'+this.x +': '+ this.y +'元';
	            }
	        },
	        legend: {
	            layout: 'vertical',
	            align: 'right',
	            verticalAlign: 'middle',
	            borderWidth: 0
	        },
	        series: con
	    });
	},'json');
});
</script>
</head>

<body>
	<div class="row-fluid">
		<div class="span12">
			<div class="box chart gradient">
				<div class="title">
                    <h4>
                        <span class="icon16 icomoon-icon-bars"></span>
                        <span>您本月的平均消费情况</span>
                    </h4>
                    <a href="#" class="minimize">Minimize</a>
                </div>
                <div class="content" style="padding:0 0;">
					<div id="container" style="min-width:800px;height:300px"></div>
				</div>
			</div>
		</div>
	</div>
	<!-- End .row-fluid -->
</body>

</html>
