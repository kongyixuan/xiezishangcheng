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
		function submitadmin(){
		    var ss=document.getElementById("form2");
		    if(confirm("ȷ����Ӻ�̨�û�?")){
		        ss.submit();
		    }
		}
		function checkForm(){
			if(form1.aacount.value.length==0){
				alert("���������Ա�˺ţ�");
				form1.aacount.focus();
				return false;
			}
			if(form1.apwd.value.length==0){
				alert("���������Ա���룡");
				form1.apwd.focus();
				return false;
			}
			if(form1.apwd1.value.length==0){
				alert("���ظ��������Ա���룡");
				form1.apwd1.focus();
				return false;
			}
			
			if(form1.apwd.value != form1.apwd1.value){
				alert("������������벻һ�£�");
				form1.apwd.focus();
				return false;
			}
		
		}
	</script>
   </head>
  <body>
    <form id="form2" method="post" name="form1" action="/back/admin!SaveAdmins.action" onSubmit="return checkForm()">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="8" rowspan="3" background="/back/images/shadow.gif">
    </td>
    <td width="943" height="30" valign="middle" background="/back/images/table_bg001.gif"> &nbsp;&nbsp; <img src="/back/images/b_sing.gif" width="12" height="12" align="middle" />&nbsp;<font color="#999999">&nbsp;&nbsp; ��ʾ��Ϣ������˵��:���һ���µĹ���Ա</font></td>
  </tr>
   <!----------------------�����ⲿ��------------------------------>
  <tr>
    <td height="580" valign="top" background="/back/images/table_bg001.gif" style="padding:5px;">
    <table width="100%" border="0" cellspacing="1" cellpadding="0" align="center">
      <tr>
        <td colspan="5"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="6%"><img src="/back/images/b_table_header_left.gif" width="58" height="31" /></td>
            <td width="92%" background="/back/images/b_table_title_bg.gif">
            	<div style="text-align:center; font:12px; color:#FFF; font-weight:bold;">
                	���һ���µĹ���Ա
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
                	<td width="35%" height="35" align="right" background="/back/images/b_table_header.gif"><span class="STYLE1">����Ա�˺ţ�</span></td>
               	  <td width="65%" height="35" background="/back/images/b_table_header.gif"><input type="text" id="aacount" name="acount"/></td>
                </tr>
                <tr>
                	<td width="35%" height="35" align="right" background="/back/images/b_table_header.gif">����Ա���룺</td>
               	  <td width="65%" height="35" background="/back/images/b_table_header.gif"><input type="password" id="apwd" name="apwd"/></td>
                </tr>
                <tr>
                	<td width="35%" height="35" align="right" background="/back/images/b_table_header.gif">�ظ����룺</td>
               	  <td width="65%" height="35" background="/back/images/b_table_header.gif"><input type="password" id="apwd1" name="apwd1"/></td>
                </tr>
                <tr>
                	<td width="35%" height="35" align="right" background="/back/images/b_table_header.gif"><span class="STYLE1">��ע��</span></td>
               	  <td width="65%" height="35" background="/back/images/b_table_header.gif"><input type="text" id="aacount" name="aremarks"/></td>
                </tr>
                 <tr>
                  <td width="35%" height="35" align="right" background="/back/images/b_table_header.gif">ѡ��Ȩ�ޣ�</td>
               	  <td width="65%" height="35" background="/back/images/b_table_header.gif">
               	  	&nbsp;
               	  </td>
                </tr>
                <tr>
                  <td width="35%" height="35" align="right" background="/back/images/b_table_header.gif"></td>
               	  <td width="65%" height="35" background="/back/images/b_table_header.gif">
               	  	<select name="pcid" >               	  	  
               	  	   <c:forEach items="${sessionScope.PermissionList}" var="adm" varStatus="su">
               	  	      <option value="${adm.perid}">${adm.pername}</option>
               	  	   </c:forEach>
               	  	</select>               	  	
               	  </td>
                </tr>
               
                <tr>
                	<td height="35" colspan="2" align="center" background="/back/images/b_table_header.gif">
                    	<input type="button" onclick="submitadmin()" id="btnModify" name="btnModify" value="���">&nbsp;&nbsp;&nbsp;
                    	<input type="reset" id="btnReset" name="btnReset" value="����" onClick="blank()">
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
