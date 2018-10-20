<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>欢迎！</title>
<script type="text/javascript" src="jslib/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$("#register").click(function(){
		//location.href="";打开新页面时本页面会被覆盖
		window.open("register.jsp");//在新窗口中打开新页面
	});

});
</script>
</head>
<body>

<a href="${pageContext.request.contextPath}/user/page/register">注册</a>
<a href="${pageContext.request.contextPath}/user/page/login">登录</a>
<a href="${pageContext.request.contextPath}/user/page/userlist">用户列表</a>
</body>
</html>