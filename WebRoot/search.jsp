<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'search.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
  </head>
  
  <body>
    <input id="i1" oninput="search()" width="100px"/>
    <div width="100px" id="d1"></div>
  </body>
  <script type="text/javascript">
  	function search(){
  		var content = $("#i1").val();
  		$("#d1").html("");
  		$.ajax({
  			url:"<%=path%>/test/search.do",
  			data:{"content":content},
  			success:function(data){
  				var json=eval("("+data+")");
  				for(var i = 0;i<(json.res).length;i++){
  					$("#d1").append((json.res)[i]+"<br/>");
  				}
  			}
  		})
  	}
  </script>
</html>
