<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
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
  			document.form1.bname.value = "";
		}
		function checkForm(){
			if(form1.bname.value.length==0){
				alert("������Ʒ�����ƣ�");
				form1.bname.focus();
				return false;
			}		
		}
		function submitbrands(){
		   var form1=document.getElementById("form1");
		   if(confirm("ȷ�������Ʒ��?")){
		      form1.submit();
		   }
		}
	</script>
   </head>
  
  <body>
    <form id="form1" method="post" name="form1" action="/back/brand!AddBrands.action" onSubmit="return checkForm()">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="8" rowspan="3" background="/back/images/shadow.gif">
    </td>
    <td width="943" height="30" valign="middle" background="/back/images/table_bg001.gif"> &nbsp;&nbsp; <img src="/back/images/b_sing.gif" width="12" height="12" align="absmiddle" />&nbsp;<font color="#999999">&nbsp;&nbsp; ��ʾ��Ϣ������˵��:���һ���µ�Ʒ��</font></td>
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
                	���һ���µ�Ʒ��
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
        	<table width="60%" border="0" align="center" cellpadding="0" cellspacing="1" >
               
                <tr>
                	<td width="43%" height="35" align="right" background="/back/images/b_table_header.gif">Ʒ�����ƣ�</td>
               	  <td width="57%" height="35" background="/back/images/b_table_header.gif"><input type="text" id="bname" name="bname"/></td>
                </tr>
                <tr>
                	<td width="43%" height="35" align="right" background="/back/images/b_table_header.gif">Ʒ���Ա�</td>
               	  <td width="57%" height="35" background="/back/images/b_table_header.gif">
               	  ��<input type="radio" id="bsex" name="bsex" value="��" checked="checked" />&nbsp;
               	  Ů<input type="radio" id="bsex" name="bsex" value="Ů"  />&nbsp;
               	  ��Ů<input type="radio" id="bsex" name="bsex" value=""  />&nbsp;
               	  </td>
                </tr>
                <tr>
                	<td width="43%" height="35" align="right" background="/back/images/b_table_header.gif">Ʒ��״̬��</td>
               	  <td width="57%" height="35" background="/back/images/b_table_header.gif">
               	  <input type="checkbox" id="bstate" name="bstate" value="0" />Ĭ������״̬
               	  </td>
                </tr>
                 <tr>
                	<td width="43%" height="35" align="right" background="/back/images/b_table_header.gif">Ʒ�Ʊ�ע��</td>
               	  <td width="57%" height="35" background="/back/images/b_table_header.gif">
               	  <textarea rows="5" cols="20" name="bremarks" id="bremarks"></textarea>               	 
               	  </td>
                </tr>
                <tr>
                	<td height="35" colspan="2" align="center" background="/back/images/b_table_header.gif">
                    	<input type="button" onclick="submitbrands()" id="btnModify" name="btnModify" value="���">&nbsp;&nbsp;&nbsp;
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
