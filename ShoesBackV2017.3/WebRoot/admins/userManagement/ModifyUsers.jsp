<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>ModifyUsers.jsp</title>
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
  			document.form1.uaccount.value = "";
			document.form1.upwd.value = "";
			document.form1.uname.value = "";
			document.form1.utel.value = "";
			document.form1.uemail.value = "";
			document.form1.uintegral.value = "";
			document.form1.upwdask.value = "";
			document.form1.upwdans.value = "";
			
		}
		function checkForm(){
			if(form1.uaccount.value.length==0){
				alert("�������û��˺ţ�");
				form1.uaccount.select();
				return false;
			}
			if(form1.upwd.value.length==0){
				alert("�������û����룡");
				form1.upwd.select();
				return false;
			}
			if(form1.uname.value.length==0){
				alert("�������û�������");
				form1.uname.select();
				return false;
			}
			if(form1.utel.value.length==0){
				alert("�������û��绰��");
				form1.utel.select();
				return false;
			}
			if(form1.uemail.value.length==0){
				alert("�������û������ʼ���");
				form1.uemail.select();
				return false;
			}
			if(form1.uintegral.value.length==0){
				alert("�������û����֣�");
				form1.uintegral.select();
				return false;
			}		
		}
		function submitmodify(name){
		  var ss=document.getElementById("form1");
		  if(confirm("ȷ���޸��û�"+name+"����Ϣ?")){
		     ss.submit();
		  }
		}
	</script>
  </head>
  
  <body>
    <form id="form1" method="post" name="form1" action="/back/user!ModifyUser.action" onSubmit="return checkForm()">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="8" rowspan="3" background="/back/images/shadow.gif">
    </td>
    <td width="943" height="30" valign="middle" background="/back/images/table_bg001.gif"> &nbsp;&nbsp; <img src="/back/images/b_sing.gif" width="12" height="12" align="absmiddle" />&nbsp;<font color="#999999">&nbsp;&nbsp; ��ʾ��Ϣ������˵��:����һ������Ա����Ϣ</font></td>
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
                	�޸�һ���û�����Ϣ
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

        	<table width="60%" border="0" align="center" cellpadding="0" cellspacing="1" >
  <tr>
                	<td width="43%" height="35" align="right" background="/back/images/b_table_header.gif"><span class="STYLE1">�û���ţ�</span></td>
               	  <td width="57%" height="35" background="/back/images/b_table_header.gif">
               	    <input type="hidden" name="uid" value="${user.uid }">
               	  ${user.uid }</td>
                </tr>
                <tr>
                	<td width="43%" height="35" align="right" background="/back/images/b_table_header.gif"><span class="STYLE1">�û��˺ţ�</span></td>
               	  <td width="57%" height="35" background="/back/images/b_table_header.gif"><input type="text" id="uaccount" name="uaccount" value="${user.uaccount}"/></td>
                </tr>
                <tr>
                	<td width="43%" height="35" align="right" background="/back/images/b_table_header.gif">�û����룺</td>
               	  <td width="57%" height="35" background="/back/images/b_table_header.gif"><input type="password" id="upwd" name="upwd" value="${user.upwd}"/></td>
                </tr>
                <td width="43%" height="35" align="right" background="/back/images/b_table_header.gif">�û�������</td>
               	  <td width="57%" height="35" background="/back/images/b_table_header.gif"><input type="text" id="uname" name="uname" value="${user.uname}"/></td>
                </tr>
                <td width="43%" height="35" align="right" background="/back/images/b_table_header.gif">�û��绰��</td>
               	  <td width="57%" height="35" background="/back/images/b_table_header.gif"><input type="text" id="utel" name="utel" value="${user.utel}"/></td>
                </tr>
                <td width="43%" height="35" align="right" background="/back/images/b_table_header.gif">�û��Ա�</td>
               	  <td width="57%" height="35" background="/back/images/b_table_header.gif">
               	<c:if test="${requestScope.user.ugender eq 'Ů'}">
              	<input type="radio" name="ugender" id="gender1" value="Ů" checked="checked">Ů
				<input type="radio" name="ugender" id="gender2" value="��">��    
				</c:if>      
				 <c:if test="${requestScope.user.ugender eq '��'}">
              	<input type="radio" name="ugender" id="gender1" value="Ů">Ů
				<input type="radio" name="ugender" id="gender2" value="��" checked="checked">��    
				</c:if>     			 
					</td>
                </tr>
                <td width="43%" height="35" align="right" background="/back/images/b_table_header.gif">�û����䣺</td>
               	  <td width="57%" height="35" background="/back/images/b_table_header.gif"><input type="text" id="uemail" name="uemail" value="${user.uemail}"/></td>
                </tr>
                
               	 <input type="hidden" id="uregtime" name="uregtime" value="${user.uregtime }"/>
               
                <td width="43%" height="35" align="right" background="/back/images/b_table_header.gif">�û����֣�</td>
               	  <td width="57%" height="35" background="/back/images/b_table_header.gif"><input type="text" id="uintegral" name="uintegral" value="${user.uintegral}"/></td>
                </tr>
                <td width="43%" height="35" align="right" background="/back/images/b_table_header.gif">�û���ʾ���⣺</td>
               	  <td width="57%" height="35" background="/back/images/b_table_header.gif"><input type="text" id="upwdask" name="upwdask" value="${user.upwdask}"/></td>
                </tr>
                <td width="43%" height="35" align="right" background="/back/images/b_table_header.gif">�û���ʾ�𰸣�</td>
               	  <td width="57%" height="35" background="/back/images/b_table_header.gif"><input type="text" id="upwdans" name="upwdans" value="${user.upwdans}"/></td>
                </tr>
                <tr>
                	<td height="35" colspan="2" align="center" background="/back/images/b_table_header.gif">
                    	<input type="submit" id="btnModify" onclick="submitmodify('${user.uaccount}')" name="btnModify" value="ȷ���޸�">&nbsp;&nbsp;&nbsp;
                    	<input type="reset"" id="btnReset" name="btnReset" value="���" onClick="blank()">
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
