<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	 <base href="<%=basePath%>">
    <title>管理界面</title>
	
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="themes/default/easyui.css">
        <link rel="stylesheet" type="text/css" href="themes/icon.css">
      <script type="text/javascript" src="js_lib/jquery-1.4.4.min.js">
        </script>
        <script type="text/javascript" src="js_lib/jquery.easyui.min.js">
        </script>
		   <script type="text/javascript" src="js_lib/main.js">
        </script>
		
	
  </head>
  
  <body>
  <div id="cc" style="width:auto;height:600px;">
    <div region="north" split="true" title="标题栏" style="height:100px;width:auto;">
	<div id="p" title="欢迎光临" collapsible="true" style="padding:10px;width:auto;">
 欢迎您，<%String name = (String)session.getAttribute("user");
 out.print(name); %>
</div>

	</div>
    <div region="west" split="true" title="菜单栏" style="width:200px;height:auto;">
	<ul id="menutree" class="easyui-tree" animate="false" url="menutree.json"></ul>
</div>
    <div  region="center" title="操作窗口" style="5px;height:auto;width:auto;"><div id="content"></div></div>
</div>

  </body>
</html>
