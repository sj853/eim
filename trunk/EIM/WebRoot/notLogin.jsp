<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
   对不起，你还没有登录！<br/>
   
  还有<span id="timer" style="color:#ff0000;">5</span>秒将自动跳转到登录界面<br>
  (<a href="login.jsp">立刻跳转到登录界面</a>)

<script type=text/javascript>
var t=6;
function f(){
t--;
if (t==0) location.href='login.jsp';
timer.innerHTML=t;
setTimeout("f();",1000);
}
f();
</script>

  </body>
</html>
