<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>"> 
    <title>...������</title>   
  </head>
  
  <body> 
  
	<c:if test="${!empty requestScope.SeckMsg }">  
		<c:choose>
	   		<c:when test="${requestScope.SeckMsg == 2 }">
	   			<script>alert("����ѡ�����Ʒ��Ϊ��ɱ�������ظ���ӡ��뷵�ء�");</script> 
	   		</c:when>  
	   		<c:when test="${requestScope.SeckMsg == 1 }">
	   		<!-- 	<script>alert("����ѡ���Ь�����¼ܣ��뷵��");</script> -->
	   			<script>alert("��������");</script>  
	   		</c:when>   
	   	</c:choose> 
	</c:if>    
	
	<div align="left"style="padding-left:50px; color:#f90;">
    	<span>������~���� == </span><br>
	 <h3>	
	 	<a style=" color:#f90; text-decoration:none;"
	 		href="/back/kill!FindShoesByPage.action?currentPage=1"><-����Ь���б�</a>
	 </h3>
   </div>
  </body>
</html>
