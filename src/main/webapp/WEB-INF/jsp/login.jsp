<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录页面</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$("input[type='button']").bind("click",function(){
		if($.trim($("input[name='username']").val()).length==0)
		{
			$("input[name='username']").select();
			$("input[name='username']").focus();
			alert("用户名不能为空！");
			return false;
		}
		if($.trim($("input[name='password']").val()).length==0)
		{
			$("input[name='password']").select();
			$("input[name='password']").focus();
			alert("密码不能为空！");
			return false;
		}
		$.post("${pageContext.request.contextPath}/user/login",{username:$("input[name='username']").val(),password:$("input[name='password']").val()},function(data){
			if(data.status==200){
				alert("登录成功，欢迎！");
				location.href="${pageContext.request.contextPath}/";
			}else{
				alert(data.msg);
				return false;
			}
		})
		//	$("#f1").submit();	
	});
});
</script>
</head>
<body>
<form id="f1" action="${pageContext.request.contextPath}/user/login" method="post">
	<table>
	<tr>
		<td>用户名</td><td><input type="text" name="username"/></td>
	</tr>
	<tr>
		<td>密码</td><td><input type="password" name="password"/></td>
	</tr>
	<tr >
		<td colspan="1"><input type="button" value="登录"/></td>
	</tr>
	</table>
</form>
</body>
</html>