<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>无标题文档</title>
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
</style></head>

<body>
<table width="200" border="0" cellspacing="0" cellpadding="0" style="margin-bottom:5px;">
  <tr>
    <td style="background: url('/back/images/menu_title.gif');" height="40px"> 
    	<div style="font-size:12px; font-weight:bold; color:#FFF; margin-left:30px;">商品管理</div>
    </td>
  </tr>
  <tr>
    <td style="background: url('/back/images/menu_blank.gif');" height="35px">
    	<div style="font-size:12px; margin-left:30px;"><a href="ShowShoes.jsp" target="productManagement">商品管理-商品列表</a></div>
    </td>
  </tr>
  <tr>
    <td style="background: url('/back/images/menu_blank.gif');" height="35px"><!-- AddShoe.jsp -->
    	<div style="font-size:12px; margin-left:30px;"><a href="/back/shoe!AddShoes.action" target="productManagement">商品管理-新增商品</a></div>
    </td>
  </tr>
  <tr>
    <td style="background: url('/back/images/menu_bottom.gif');" height="13px"></td>
  </tr>
</table>

<table width="200" border="0" cellspacing="0" cellpadding="0" style="margin-bottom:5px;">
  <tr>
    <td style="background: url('/back/images/menu_title.gif');" height="40px"> 
    	<div style="font-size:12px; font-weight:bold; color:#FFF; margin-left:30px;">品牌管理</div>
    </td>
  </tr>
  <tr>
    <td style="background: url('/back/images/menu_blank.gif');" height="35px">
    	<div style="font-size:12px; margin-left:30px;"><a href="ShowBrands.jsp" target="productManagement">品牌管理-品牌列表</a></div>
    </td>
  </tr>
  <tr>
    <td style="background: url('/back/images/menu_blank.gif');" height="35px">
    	<div style="font-size:12px; margin-left:30px;"><a href="AddBrand.jsp" target="productManagement">品牌管理-新增品牌</a></div>
    </td>
  </tr>
  <tr>
    <td style="background: url('/back/images/menu_bottom.gif');" height="13px"></td>
  </tr>
</table>

<table width="200" border="0" cellspacing="0" cellpadding="0" style="margin-bottom:5px;">
  <tr>
    <td style="background: url('/back/images/menu_title.gif');" height="40px"> 
    	<div style="font-size:12px; font-weight:bold; color:#FFF; margin-left:30px;">类型管理</div>
    </td>
  </tr>
  <tr>
    <td style="background: url('/back/images/menu_blank.gif');" height="35px">
    	<div style="font-size:12px; margin-left:30px;"><a href="ShowTypes.jsp" target="productManagement">类型管理-类型列表</a></div>
    </td>
  </tr>
  <tr>
    <td style="background: url('/back/images/menu_blank.gif');" height="35px">
    	<div style="font-size:12px; margin-left:30px;"><a href="AddType.jsp" target="productManagement">类型管理-新增类型</a></div>
    </td>
  </tr>
  
   <tr>
    <td style="background: url('/back/images/menu_title.gif');" height="40px"> 
    	<div style="font-size:12px; font-weight:bold; color:#FFF; margin-left:30px;">定制鞋管理</div>
    </td>
  </tr>
  <tr>
    <td style="background: url('/back/images/menu_blank.gif');" height="35px">
    	<div style="font-size:12px; margin-left:30px;"><a href="showSpshoes.jsp" target="productManagement">定制鞋查询修改</a></div>
    </td>
  </tr>
  <tr>
    <td style="background: url('/back/images/menu_blank.gif');" height="35px"><!-- AddSpshoes.jsp -->
    	<div style="font-size:12px; margin-left:30px;"><a href="/back/spcify!AddSpcifyShoes.action" target="productManagement">新增定制鞋款</a></div>
    </td>
  </tr> 
  <tr>
    <td style="background: url('/back/images/menu_bottom.gif');" height="13px"></td>
  </tr>
</table></body>
</html>
