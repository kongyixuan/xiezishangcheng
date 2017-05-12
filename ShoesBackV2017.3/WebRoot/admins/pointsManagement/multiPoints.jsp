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
  			document.form1.multiple.value = "";
		}
		function checkForm(){
			if(form1.multiple.value.length==0){
				alert("请输入倍数！");
				form1.multiple.focus();
				return false;
			}		
		}
	</script>
   </head>
  
  <body>
    <form method="post" name="form1" action="/back/kill!SetSecondKills.action" onSubmit="return checkForm();">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="8" rowspan="3" background="../../images/shadow.gif">
    </td>
    <td width="943" height="30" valign="middle" background="../../images/table_bg001.gif"> &nbsp;&nbsp; <img src="../../images/b_sing.gif" width="12" height="12" align="middle" />&nbsp;<font color="#999999">&nbsp;&nbsp; 提示信息及功能说明:设置每日积分倍数</font></td>
  </tr>
   <!----------------------表格标题部分------------------------------>
  <tr>
    <td height="580" valign="top" background="../../images/table_bg001.gif" style="padding:5px;">
    <table width="100%" border="0" cellspacing="1" cellpadding="0" align="center">
      <tr>
        <td colspan="5"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="6%"><img src="../../images/b_table_header_left.gif" width="58" height="31" /></td>
            <td width="92%" background="../../images/b_table_title_bg.gif">
            	<div style="text-align:center; font:12px; color:#FFF; font-weight:bold;">
                	设置每日积分倍数
                </div>
            </td>
            <td width="2%"><img src="../../images/b_table_header_right.gif" width="58" height="31" /></td>
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
                	<td width="43%" height="35" align="right" background="../../images/b_table_header.gif">今日积分倍数：</td>
               	  <td width="57%" height="35" background="../../images/b_table_header.gif">
               	  <input type="text" id="multiple" name="multiple"/></td>
                </tr>
                <tr>
                	<td height="35" colspan="2" align="center" background="../../images/b_table_header.gif">
                    	<input type="submit" id="btnModify" name="btnModify" value="确认">&nbsp;&nbsp;&nbsp;
                    	<input type="button" id="btnReset" name="btnReset" value="重置" onClick="blank()">
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
