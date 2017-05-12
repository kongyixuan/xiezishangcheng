<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>"> 
    <title>...出错了</title>   
  </head>
  
  <body> 
  
	<c:if test="${!empty requestScope.SeckMsg }">  
		<c:choose>
	   		<c:when test="${requestScope.SeckMsg == 2 }">
	   			<script>alert("您所选择的商品已为秒杀，请勿重复添加。请返回。");</script> 
	   		</c:when>  
	   		<c:when test="${requestScope.SeckMsg == 1 }">
	   		<!-- 	<script>alert("您所选择的鞋款已下架，请返回");</script> -->
	   			<script>alert("操作错误");</script>  
	   		</c:when>   
	   	</c:choose> 
	</c:if>    
	
	<div align="left"style="padding-left:50px; color:#f90;">
    	<span>出错了~！！ == </span><br>
	 <h3>	
	 	<a style=" color:#f90; text-decoration:none;"
	 		href="/back/kill!FindShoesByPage.action?currentPage=1"><-返回鞋款列表</a>
	 </h3>
   </div>
  </body>
</html>
