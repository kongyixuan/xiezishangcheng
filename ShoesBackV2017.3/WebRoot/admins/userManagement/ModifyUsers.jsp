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
				alert("请输入用户账号！");
				form1.uaccount.select();
				return false;
			}
			if(form1.upwd.value.length==0){
				alert("请输入用户密码！");
				form1.upwd.select();
				return false;
			}
			if(form1.uname.value.length==0){
				alert("请输入用户姓名！");
				form1.uname.select();
				return false;
			}
			if(form1.utel.value.length==0){
				alert("请输入用户电话！");
				form1.utel.select();
				return false;
			}
			if(form1.uemail.value.length==0){
				alert("请输入用户电子邮件！");
				form1.uemail.select();
				return false;
			}
			if(form1.uintegral.value.length==0){
				alert("请输入用户积分！");
				form1.uintegral.select();
				return false;
			}		
		}
		function submitmodify(name){
		  var ss=document.getElementById("form1");
		  if(confirm("确认修改用户"+name+"的信息?")){
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
    <td width="943" height="30" valign="middle" background="/back/images/table_bg001.gif"> &nbsp;&nbsp; <img src="/back/images/b_sing.gif" width="12" height="12" align="absmiddle" />&nbsp;<font color="#999999">&nbsp;&nbsp; 提示信息及功能说明:更新一个管理员的信息</font></td>
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
                	修改一个用户的信息
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
                	<td width="43%" height="35" align="right" background="/back/images/b_table_header.gif"><span class="STYLE1">用户编号：</span></td>
               	  <td width="57%" height="35" background="/back/images/b_table_header.gif">
               	    <input type="hidden" name="uid" value="${user.uid }">
               	  ${user.uid }</td>
                </tr>
                <tr>
                	<td width="43%" height="35" align="right" background="/back/images/b_table_header.gif"><span class="STYLE1">用户账号：</span></td>
               	  <td width="57%" height="35" background="/back/images/b_table_header.gif"><input type="text" id="uaccount" name="uaccount" value="${user.uaccount}"/></td>
                </tr>
                <tr>
                	<td width="43%" height="35" align="right" background="/back/images/b_table_header.gif">用户密码：</td>
               	  <td width="57%" height="35" background="/back/images/b_table_header.gif"><input type="password" id="upwd" name="upwd" value="${user.upwd}"/></td>
                </tr>
                <td width="43%" height="35" align="right" background="/back/images/b_table_header.gif">用户姓名：</td>
               	  <td width="57%" height="35" background="/back/images/b_table_header.gif"><input type="text" id="uname" name="uname" value="${user.uname}"/></td>
                </tr>
                <td width="43%" height="35" align="right" background="/back/images/b_table_header.gif">用户电话：</td>
               	  <td width="57%" height="35" background="/back/images/b_table_header.gif"><input type="text" id="utel" name="utel" value="${user.utel}"/></td>
                </tr>
                <td width="43%" height="35" align="right" background="/back/images/b_table_header.gif">用户性别：</td>
               	  <td width="57%" height="35" background="/back/images/b_table_header.gif">
               	<c:if test="${requestScope.user.ugender eq '女'}">
              	<input type="radio" name="ugender" id="gender1" value="女" checked="checked">女
				<input type="radio" name="ugender" id="gender2" value="男">男    
				</c:if>      
				 <c:if test="${requestScope.user.ugender eq '男'}">
              	<input type="radio" name="ugender" id="gender1" value="女">女
				<input type="radio" name="ugender" id="gender2" value="男" checked="checked">男    
				</c:if>     			 
					</td>
                </tr>
                <td width="43%" height="35" align="right" background="/back/images/b_table_header.gif">用户邮箱：</td>
               	  <td width="57%" height="35" background="/back/images/b_table_header.gif"><input type="text" id="uemail" name="uemail" value="${user.uemail}"/></td>
                </tr>
                
               	 <input type="hidden" id="uregtime" name="uregtime" value="${user.uregtime }"/>
               
                <td width="43%" height="35" align="right" background="/back/images/b_table_header.gif">用户积分：</td>
               	  <td width="57%" height="35" background="/back/images/b_table_header.gif"><input type="text" id="uintegral" name="uintegral" value="${user.uintegral}"/></td>
                </tr>
                <td width="43%" height="35" align="right" background="/back/images/b_table_header.gif">用户提示问题：</td>
               	  <td width="57%" height="35" background="/back/images/b_table_header.gif"><input type="text" id="upwdask" name="upwdask" value="${user.upwdask}"/></td>
                </tr>
                <td width="43%" height="35" align="right" background="/back/images/b_table_header.gif">用户提示答案：</td>
               	  <td width="57%" height="35" background="/back/images/b_table_header.gif"><input type="text" id="upwdans" name="upwdans" value="${user.upwdans}"/></td>
                </tr>
                <tr>
                	<td height="35" colspan="2" align="center" background="/back/images/b_table_header.gif">
                    	<input type="submit" id="btnModify" onclick="submitmodify('${user.uaccount}')" name="btnModify" value="确认修改">&nbsp;&nbsp;&nbsp;
                    	<input type="reset"" id="btnReset" name="btnReset" value="清空" onClick="blank()">
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
