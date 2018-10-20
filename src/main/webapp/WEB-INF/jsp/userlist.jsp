<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户列表展示</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/jquery.js"></script>
<script type="text/javascript">
$(function(){
	loadList(1,10);
})
function loadList(cP,pS){
	$.post("${pageContext.request.contextPath}/user/getUsers",{currentPage:cP,pageSize:pS},function(data){
		var str;
		$.each(data.data.list,function(index,user){
			if(user.updatetime==null){
				str="尚未登录";
			}else{
				str=fmtDate(user.updatetime);
			}
			$("#show").append("<tr><td>"+(index+1)+"</td><td>"+user.username+"</td><td>"+fmtDate(user.creattime)+"</td><td>"+str+"</td></tr>");
		});
//		$.each(data.data.navigatepageNums,function(index,page){
//		$("#mark").append(+"共"+data.data.pages+"页")
//			<a href="">page</a>
//		})
		$("#mark").append("第"+data.data.pageNum+"页"+"共"+data.data.pages+"页")
	});
	
}
function fmtDate(inputTime) {
       var date = new Date(inputTime);
       var y = date.getFullYear();
       var m = date.getMonth() + 1;
       m = m < 10 ? ('0' + m) : m;
       var d = date.getDate();
       d = d < 10 ? ('0' + d) : d;
       var h = date.getHours();
       h = h < 10 ? ('0' + h) : h;
       var minute = date.getMinutes();
       var second = date.getSeconds();
       minute = minute < 10 ? ('0' + minute) : minute;
       second = second < 10 ? ('0' + second) : second;
       return y + '-' + m + '-' + d + ' ' + h + ':' + minute + ':' + second;
   }
</script>
</head>
<body>
<form action="${pageContext.request.contextPath}/user/getUsers" method="post">

<table id="show">
	<tr>
		<th>序号</th><th>用户名</th><th>注册时间</th><th>上次登录</th>
	</tr>
</table>
<div id="mark"></div>
</form>
</body>
</html>