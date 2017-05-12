<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>AddAdmin.jsp</title>
    <style type="text/css">
		<!--
		body {
			background-color: #e6e6e6;
		}
		body,td,th {
	font-size: 16px;
		}
.STYLE1 {font-size: 16px}
		-->
	</style>
    <script type="text/javascript">
		function blank(){ 
  			document.form1.aacount.value = "";
			document.form1.apwd.value = "";
		}
		function submitForm(){
		    var ss=document.getElementById("form2");
		    if(confirm("确认添加权限?")){
		       ss.submit();
		    }
		}		
	</script>
   </head>
  
  <body>
    <form id="form2" method="post" name="form1" action="/back/permission!SavePermission.action" >
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="8" rowspan="3" background="/back/images/shadow.gif">
    </td>
    <td width="943" height="30" valign="middle" background="/back/images/table_bg001.gif"> &nbsp;&nbsp; <img src="/back/images/b_sing.gif" width="12" height="12" align="middle" />&nbsp;<font color="#999999">&nbsp;&nbsp; 提示信息及功能说明:添加一个新的权限</font></td>
  </tr>
   <!----------------------表格标题部分------------------------------>
  <tr>
    <td height="580" valign="top" background="/back/images/table_bg001.gif" style="padding:5px;">
    <table width="100%" border="0" cellspacing="1" cellpadding="0" align="center">
      <tr>
        <td colspan="5"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="6%"><img src="/back/images/b_table_header_left.gif" width="58" height="31" /></td>
            <td width="92%" background="/back/images/b_table_title_bg.gif">
            	<div style="text-align:center; font:12px; color:#FFF; font-weight:bold;">
                	添加一个新的权限
                </div>
            </td>
            <td width="2%"><img src="/back/images/b_table_header_right.gif" width="58" height="31" /></td>
          </tr>
        </table>
        </td>
      </tr>
      <tr>
      	<td colspan="5">
        	<p>&nbsp;</p>
        	<p>&nbsp;</p>
        	<p>&nbsp;</p>
        	<table width="65%" border="0" align="center" cellpadding="0" cellspacing="1" >
                <tr>
                	<td width="35%" height="35" align="right" background="/back/images/b_table_header.gif"><span class="STYLE1">权限名称：</span></td>
               	  <td width="65%" height="35" background="/back/images/b_table_header.gif"><input type="text" id="pername" name="pername" /></td>
                </tr>
                <tr>
                	<td width="35%" height="35" align="right" background="/back/images/b_table_header.gif">权限说明：</td>
               	  <td width="65%" height="35" background="/back/images/b_table_header.gif"><input type="text" id="percont" name="percont"/></td>
                </tr>
                <tr>
                	<td width="35%" height="35" align="right" background="/back/images/b_table_header.gif">权限备注：</td>
               	  <td width="65%" height="35" background="/back/images/b_table_header.gif"><input type="text" id="peremarks" name="peremarks"/></td>
                </tr>               
                 <tr>
                  <td width="35%" height="35" align="right" background="/back/images/b_table_header.gif">选择权限：</td>
               	  <td width="65%" height="35" background="/back/images/b_table_header.gif">
               	  	&nbsp;
               	  </td>
                </tr>
                <tr>
                  <td width="35%" height="35" align="right" background="/back/images/b_table_header.gif"></td>
               	  <td width="65%" height="35" background="/back/images/b_table_header.gif">
               	  <c:forEach items="${sessionScope.PercontentManager}" var="adm" varStatus="su">
               	  	<input type="checkbox" name="chk" id="chk" value="${adm.pcitems}"/>${adm.pcitems}&nbsp;&nbsp;
               	  	<c:if test="${su.count%3==0}">
                      <br/>
                     </c:if>     
               	  </c:forEach>          	  	
               	  </td>
                </tr>
               
                <tr>
                	<td height="35" colspan="2" align="center" background="/back/images/b_table_header.gif">
                    	<input type="button" onclick="submitForm()" id="btnModify" name="btnModify" value="添加">&nbsp;&nbsp;&nbsp;
                    	<input type="reset" id="btnReset" name="btnReset" value="重置" onClick="blank()">
                    </td>
                </tr>
            </table>
        <td>
      </tr>
    </table>
    </td>
  </tr>
</table>
</form>
  </body>
</html>
