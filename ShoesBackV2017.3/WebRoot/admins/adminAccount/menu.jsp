<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<title>My JSP 'menu.jsp' starting page</title>
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
    <td background="/back/images/menu_title.gif" height="40px"> 
    	<div style="font-size:12px; font-weight:bold; color:#FFF; margin-left:30px;">����Ա�˺�</div>
    </td>
  </tr>
  <tr>
    <td background="/back/images/menu_blank.gif" height="35px">
    	<div style="font-size:12px; margin-left:30px;"><a href="ShowAdmins.jsp" target="adminAccount">����Ա�б�</a></div>
    </td>
  </tr>
  <tr>
    <td background="/back/images/menu_blank.gif" height="35px">
    	<div style="font-size:12px; margin-left:30px;"><a href="/back/admin!AddAdmins.action" target="adminAccount">��ӹ���Ա</a></div>
    </td>
  </tr>
   
  <tr>
    <td background="/back/images/menu_bottom.gif" height="13px"></td>
  </tr>
</table>
<table width="200" border="0" cellspacing="0" cellpadding="0" style="margin-bottom:5px;">
  <tr>
    <td style="background: url('/back/images/menu_title.gif');" height="40px"> 
    	<div style="font-size:12px; font-weight:bold; color:#FFF; margin-left:30px;">����Ȩ��</div>
    </td>
  </tr>
  <tr>
    <td style="background: url('/back/images/menu_blank.gif');" height="35px">
    	<div style="font-size:12px; margin-left:30px;"><a href="ShowPermission.jsp" target="adminAccount">����Ȩ��-Ȩ���б�</a></div>
    </td>
  </tr>
  <tr>
    <td style="background: url('/back/images/menu_blank.gif');" height="35px">
    	<div style="font-size:12px; margin-left:30px;"><a href="/back/permission!AddPermission.action" target="adminAccount">����Ȩ��-����Ȩ��</a></div>
    </td>
  </tr>
  <tr>
    <td style="background: url('/back/images/menu_bottom.gif');" height="13px"></td>
  </tr>
</table>
  </body>
</html>
