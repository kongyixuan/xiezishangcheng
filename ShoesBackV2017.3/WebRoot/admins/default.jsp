<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
   <!-- 后台主界面 -->
<title>鞋城网站后台</title>
<script src="scripts/SpryTabbedPanels.js" type="text/javascript"></script>
<link href="css/SpryTabbedPanels.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="css/index_back.css"/>
</head>

<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td style="height:45px; background:url(images/b_top.gif);">  
    <div style="width:326px; height:49px; position:absolute; margin-top:-18px; margin-left:10px;">
   			<!-- <img width="326px;" height="49px;" src="login_files/website_logo.gif"/> -->
    </div>	
    </td>
  </tr>
  <tr>
    <td>    
    <div style="width:100%; height:17px; position:absolute; padding-top:12px; font-size:12px;">
   		<span style="margin-left:10px">
   		${sessionScope.admininfo.permission.pername}:  		  
        ${sessionScope.admininfo.acount}欢迎登录，&nbsp;|&nbsp;<a href="admin!Logout.action"><font style="color: #F00;font-weight: bold;">安全退出</font></a>
        </span>
    </div>
    <!-- 后台主界面卡片选项 -->
	<div id="TabbedPanels1" class="TabbedPanels">
  		<ul class="TabbedPanelsTabGroup">
  		<c:forEach items="${sessionScope.admininfo.permission.percontents}" var="adm">
               <li class="TabbedPanelsTab" tabindex="0">${adm.pcitems}</li>
  		</c:forEach>
  		</ul>
  		<div class="TabbedPanelsContentGroup">
		 	<c:forEach items="${sessionScope.admininfo.permission.percontents}" var="adm">
		   <div class="TabbedPanelsContent">
           	   <table width="100%" border="0" cellspacing="0" cellpadding="0">
            	  <tr>
            	    <td width="15%" valign="top"><iframe src="/back/admins/${adm.pcmenu}" frameborder="0" width="210px" height="700px"></iframe></td>
            	    <td width="85%" valign="top">
            	       <!-- userManagement -->
            	       <iframe name="${fn:substringBefore(adm.pcmenu,'/')}" src="/back/admins/${adm.pcurl}" frameborder="0" width="99%" height="700px"></iframe>
                    </td>
          	    </tr>
          	  </table>
            </div>
	  </c:forEach>
  		</div>
	</div>
	
	<script type="text/javascript">
	  var TabbedPanels1=new Spry.Widget.TabbedPanels("TabbedPanels1");
	</script>
    </td>
  </tr>
 <tr>
   <td style="background-image: url('images/b_bottom.gif');height: 40px;color: #FFF;font-size: 12px;font-weight: bold;text-align: right;">
     技术支持：鞋子商城&nbsp;|&nbsp;地址：河南郑州--郑州轻工业学院 &nbsp;|&nbsp;24小时服务热线：xxx-88888888&nbsp;|&nbsp;电子邮件：xxxx@xxx.com&nbsp;|&nbsp;       
   </td>
 </tr>
</table>
</body>
</html>
