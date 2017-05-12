<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"  %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
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
  			document.form1.bname.value = "";
		}
		function checkForm(){
			if(form1.bname.value.length==0){
				alert("请输入品牌名称！");
				form1.bname.focus();
				return false;
			}else{
			    if(confirm("确认更新品牌?")){
			       return true;
			    }else{
			       return false;
			    }
			}
		}
	</script>
  </head>
  
  <body>
    <form method="post" name="form1" action="/back/brand!ModifyBrands.action?bid=${brand.bid }" onSubmit="return checkForm()">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="8" rowspan="3" background="/back/images/shadow.gif">
    </td>
    <td width="943" height="30" valign="middle" background="/back/images/table_bg001.gif"> &nbsp;&nbsp; <img src="/back/images/b_sing.gif" width="12" height="12" align="middle" />&nbsp;<font color="#999999">&nbsp;&nbsp; 提示信息及功能说明:更新一个类型的信息</font></td>
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
                	修改一个品牌
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
                	<td width="43%" height="35" align="right" background="/back/images/b_table_header.gif"><span class="STYLE1">品牌编号：</span></td>
               	  <td width="57%" height="35" background="/back/images/b_table_header.gif">${brand.bid }</td>
                </tr>
                <tr>
                	<td width="43%" height="35" align="right" background="/back/images/b_table_header.gif"><span class="STYLE1">品牌名称：</span></td>
               	  <td width="57%" height="35" background="/back/images/b_table_header.gif"><input type="text" id="bname" name="bname" value="${brand.bname}"/></td>
                </tr>
                <tr>
                	<td width="43%" height="35" align="right" background="/back/images/b_table_header.gif"><span class="STYLE1">品牌性别：</span></td>
               	  <td width="57%" height="35" background="/back/images/b_table_header.gif">
               	  <select name="bsex" id="bsex">               	  
               	    <c:if test="${brand.bsex eq ''}">
               	      <option value="" selected="selected">男女</option>
               	      <option value="男" >男</option>
               	      <option value="女" >女</option>
               	    </c:if>
               	    <c:if test="${brand.bsex eq '男'}">
               	      <option value="">男女</option>
               	      <option value="男" selected="selected" >男</option>
               	      <option value="女" >女</option>
               	    </c:if>
               	    <c:if test="${brand.bsex eq '女'}">
               	      <option value="">男女</option>
               	      <option value="男" >男</option>
               	      <option value="女" selected="selected" >女</option>
               	    </c:if>               	    
               	  </select>               	  
               	  </td>
                </tr>
                <tr>
                	<td width="43%" height="35" align="right" background="/back/images/b_table_header.gif"><span class="STYLE1">品牌状态：</span></td>
               	  <td width="57%" height="35" background="/back/images/b_table_header.gif">
               	    <input type="checkbox" id="bstate" name="bstate"  
               	    <c:if test="${brand.bstate==0}" >
               	      checked="checked"
               	    </c:if>       	    value="0" />
               	  </td>
                </tr>
                 <tr>
                	<td width="43%" height="35" align="right" background="/back/images/b_table_header.gif"><span class="STYLE1">品牌备注：</span></td>
               	  <td width="57%" height="35" background="/back/images/b_table_header.gif">
               	  <textarea name="bremarks" id="bremarks" rows="5" cols="20">
               	  ${brand.bremarks}
               	  </textarea>
               	  </td>
                </tr>
                <tr>
                	<td height="35" colspan="2" align="center" background="/back/images/b_table_header.gif">
                    	<input type="submit" id="btnModify" name="btnModify" value="确认修改">&nbsp;&nbsp;&nbsp;
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
