<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>ModifyAdmin.jsp</title>
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
		function checkForm(){
			if(form1.aacount.value.length==0){
				alert("请输入管理员账号！");
				form1.aacount.focus();
				return false;
			}
			if(form1.apwd.value.length==0){
				alert("请输入管理员密码！");
				form1.apwd.focus();
				return false;
			}
		
		}
	</script>
  </head>
  
  <body>
  
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="8" rowspan="3" background="/back/images/shadow.gif">    </td>
    <td width="943" height="30" valign="middle" background="/back/images/table_bg001.gif"> &nbsp;&nbsp; <img src="/back/images/b_sing.gif" width="12" height="12" align="middle" />&nbsp;<font color="#999999">&nbsp;&nbsp; 提示信息及功能说明:查看一个订单的详细信息</font></td>
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
                	查看一个订单的详细信息                </div>            </td>
            <td width="2%"><img src="/back/images/b_table_header_right.gif" width="58" height="31" /></td>
          </tr>
        </table>        </td>
      </tr>
      <tr>
      	<td colspan="5">

        	<p>&nbsp;</p>
        	<table width="60%" border="0" align="center" cellpadding="0" cellspacing="1" >
  <tr>
               	  <td width="43%" height="35" align="right" background="/back/images/b_table_header.gif"><span class="STYLE1">订单编号：</span></td>
           	    <td width="57%" height="35" background="/back/images/b_table_header.gif">${order.oid }</td>
              </tr>
              <tr>
               	  <td width="43%" height="35" align="right" background="/back/images/b_table_header.gif"><span class="STYLE1">用户账号：</span></td>
           	    <td width="57%" height="35" background="/back/images/b_table_header.gif">${order.users.uaccount }</td>
              </tr>
              <tr>
                <td height="35" align="right" background="/back/images/b_table_header.gif">收货地址编号：</td>
                <td height="35" background="/back/images/b_table_header.gif">${order.receives.recid }</td>
              </tr>
              <tr>
                <td height="35" align="right" background="/back/images/b_table_header.gif">订单状态：</td>
                <td height="35" background="/back/images/b_table_header.gif">${order.ostate }</td>
              </tr>
              <tr>
                <td height="35" align="right" background="/back/images/b_table_header.gif">下单时间：</td>
                <td height="35" background="/back/images/b_table_header.gif">${order.ordertime}</td>
              </tr>
              <tr>
                <td height="35" align="right" background="/back/images/b_table_header.gif">订单总金额：</td>
                <td height="35" background="/back/images/b_table_header.gif">${order.ototal }</td>
              </tr>
              <tr>
               	  <td width="43%" height="35" align="right" background="/back/images/b_table_header.gif">订单物流信息：</td>
           	    <td width="57%" height="35" background="/back/images/b_table_header.gif">${order.oexpinfo }</td>
              </tr>
              <tr>
                <td height="35" align="right" background="/back/images/b_table_header.gif">订单备忘：</td>
                <td height="35" background="/back/images/b_table_header.gif">${order.onote }</td>
              </tr>
              <tr>
                <td height="35" align="right" background="/back/images/b_table_header.gif">所获积分：</td>
                <td height="35" background="/back/images/b_table_header.gif">${order.ointegral }</td>
              </tr>
              <tr>
               	  <td height="35" colspan="2" align="center" background="/back/images/b_table_header.gif">&nbsp;&nbsp;&nbsp;
               	 <a href="/back/admins/orderManagement/showOrders.jsp" >返回</a>                 </td>
              </tr>
            </table>
        <td>      </tr>
    </table>    </td>
  </tr>
  </table>
</body>
</html>
