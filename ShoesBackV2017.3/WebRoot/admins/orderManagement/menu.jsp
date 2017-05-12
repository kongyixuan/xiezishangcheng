<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<title>管理订单</title>
  	<style type="text/css">
<!--
body {
	background-color: #e6e6e6;
}
a:link {
	text-decoration: none;
}
a:visited {
	text-decoration: none;
}
a:hover {
	text-decoration: underline;
}
a:active {
	text-decoration: none;
}
-->
</style>
  </head>
  
  <body>
    <table width="200" border="0" cellspacing="0" cellpadding="0" style="margin-bottom:5px;">
  <tr>
    <td style="background: url('/back/images/menu_title.gif');" height="40px"> 
    	<div style="font-size:12px; font-weight:bold; color:#FFF; margin-left:30px;">订单处理</div>
    </td>
  </tr>
  <tr>
    <td style="background: url('/back/images/menu_blank.gif');"  height="35px">
    	<div style="font-size:12px; margin-left:30px;"><a href="showOrders.jsp" target="orderManagement">查看订单列表</a></div>
    </td>
  </tr>
 
  <tr>
    <td style="background: url('/back/images/menu_bottom.gif');" height="13px"></td>
  </tr>
</table>

  <table width="200" border="0" cellspacing="0" cellpadding="0" style="margin-bottom:5px;">
  <tr>
    <td style="background: url('/back/images/menu_title.gif');" height="40px"> 
    	<div style="font-size:12px; font-weight:bold; color:#FFF; margin-left:30px;">定制商品处理</div>
    </td>
  </tr>
  <tr>
    <td style="background: url('/back/images/menu_blank.gif');" height="35px">
    	<div style="font-size:12px; margin-left:30px;"><a href="/back/spcifyResults.action" target="orderManagement">定制商品订单列表</a></div>
    </td>
  </tr>
 
  <tr>
    <td style="background: url('/back/images/menu_bottom.gif');" height="13px"></td>
  </tr>
</table>
  </body>
</html>
