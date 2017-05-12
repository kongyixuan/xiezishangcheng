<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"  %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>ShowEnterpriseCertification.jsp</title>
    <style type="text/css">
		<!--
		body {
			background-color: #e6e6e6;
		}
		body,td,th {
			font-size: 12px;
		}
		-->
	</style>
	<script language="javascript">	


function SetChecked(boxname) {
	f=document.form1;
	for( i=0 ; i<f.elements.length ; i++) {
		if (f.elements[i].name==boxname) {
		f.elements[i].checked=true;
		}
	}
}
function SetResverseChecked(boxname) {
	f=document.form1;
	for( i=0 ; i<f.elements.length ; i++) {
		if (f.elements[i].name==boxname) {
			if(f.elements[i].checked==true){
				f.elements[i].checked=false;
			}else{
				f.elements[i].checked=true;
			}
		}
	}
}
function SetUnChecked(boxname) {
	f=document.form1;
	for( i=0 ; i<f.elements.length ; i++) {
		if (f.elements[i].name==boxname) {
		f.elements[i].checked=false;
		}
	}
}

function checkForm(){
	f=document.form1;
	for( i=0 ; i<f.elements.length ; i++) {
		if (f.elements[i].name=="chk_adid") {
			if(f.elements[i].checked==true){
				if(confirm("确定批量删除这些数据吗？")){
					return true;
				}else{
					return false;
				}
			}
		}
	}
	alert("至少选中一个部门……");
	return false;
}</script>
  </head>
  
  <body>
  	<c:if test="${empty sessionScope.ShowEnterpriseCertifications }">
      <script>location="/back/enterpriseCertification.action";</script>
    </c:if>
    <form method="post" name="form1" action="/back/enterpriseCertification!BatchDeleteEnterpriseCertification.action" onSubmit="return checkForm();">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="8" rowspan="3" background="/back/images/shadow.gif">
    </td>
    <td width="943" height="30" valign="middle" background="/back/images/table_bg001.gif"> &nbsp;&nbsp; <img src="/back/images/b_sing.gif" width="12" height="12" align="middle" />&nbsp;<font color="#999999">&nbsp;&nbsp; 提示信息及功能说明，总共： ${sessionScope.ShowEnterpriseCertifications.totalRows } 条数据</font></td>
  </tr>
   <!----------------------表格标题部分------------------------------>
   <tr>
    <td height="580" valign="top" background="/back/images/table_bg001.gif" style="padding:5px;">
    <table width="100%" border="0" cellspacing="1" cellpadding="0" align="center">
      <tr>
        <td colspan="8"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="6%"><img src="/back/images/b_table_header_left.gif" width="58" height="31" /></td>
            <td width="95%" background="/back/images/b_table_title_bg.gif">
            	<div style="text-align:center; font:12px; color:#FFC0CB; font-weight:bold;">
                	全部认证企业列表                </div>            </td>
            <td width="2%"><img src="/back/images/b_table_header_right.gif" width="58" height="31" /></td>
          </tr>
        </table></td>
        </tr>
      <!----------------------表格字段部分------------------------------>
      
     
      <tr>
        <td width="2%" background="/back/images/b_table_header.gif" height="34px">&nbsp;</td>
        <td width="10%" background="/back/images/b_table_header.gif" height="34px" align="center" valign="middle"><strong>认证编号</strong></td>
        <td width="12%" background="/back/images/b_table_header.gif" align="center" valign="middle"><strong>企业名称</strong></td>
        <td width="10%" background="/back/images/b_table_header.gif" align="center" valign="middle"><strong>企业编号</strong></td>
        <td width="13%" background="/back/images/b_table_header.gif" align="center" valign="middle"><strong>对公账户名</strong></td>
         <td width="16%" background="/back/images/b_table_header.gif" height="34px" align="center" valign="middle"><strong>对公账号</strong></td>
        <td width="19%" background="/back/images/b_table_header.gif" height="34px" align="center" valign="middle"><strong>营业执照</strong></td>
        
       
        <td width="18%" background="/back/images/bg_handler.gif" height="34px" align="center" valign="middle"><strong>处理</strong></td>
      </tr>
      <!----------------------动态数据显示部分------------------------------>
      <c:forEach items="${sessionScope.ShowEnterpriseCertifications.data }" var="EnterpriseCertifications">
      <tr height="30px" bgcolor="#F7F7F7" 
      		onmouseover="this.style.backgroundColor='lavender'; this.style.cursor='pointer';"
            onmouseout="this.style.backgroundColor='#F7F7F7'; this.style.cursor='pointer';">
        <td align="center" valign="middle"><label>
          <input type="checkbox" name="chk_adid" id="chk_adid" value="${EnterpriseCertifications.busiid }"/>
        </label></td>
        <td height="35" align="center" valign="middle">${EnterpriseCertifications.busiid}</td>
        <td align="center" valign="middle">${EnterpriseCertifications.busiName}</td>
        <td align="center" valign="middle">${EnterpriseCertifications.busiNo}</td>
        <td align="center" valign="middle">${EnterpriseCertifications.acctName}</td>
        <td align="center" valign="middle">${EnterpriseCertifications.acctNo}</td>
<%--         <td align="center" valign="middle">${EnterpriseCertifications.busiid}</td> --%>
        <td align="center" valign="middle">
        <img width="50px;" height="40px;" src="/back/upload/img/${EnterpriseCertifications.busiimage}" />
        </td>
        <td align="center" valign="middle">&nbsp;&nbsp; 
        		<a href="javascript:if(confirm('确定删除吗？')){location='/back/enterpriseCertification!DeleteEnterpriseCertification.action?busiid=${EnterpriseCertifications.busiid}';}">删除</a></td>
      </tr>
      </c:forEach>
      
      <!--------------------------分页显示部分---------------------------------->
      <tr height="35px">
        <td height="40" colspan="6" align="left" valign="middle">
        	当前第： ${sessionScope.ShowEnterpriseCertifications.currentPage } / ${sessionScope.ShowEnterpriseCertifications.totalPages } 页&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
        	<a href="javascript:SetChecked('chk_adid');">全选</a>&nbsp; 
        	<a href="javascript:SetResverseChecked('chk_adid');">反选</a>&nbsp; 
        	<a href="javascript:SetUnChecked('chk_adid');">清空</a>&nbsp;&nbsp;
        	<input type="submit" name="btnBatchDel" id="btnBatchDel" value="批量删除">        </td>
        <td colspan="2" align="right" valign="middle">请选择：第
   <c:forEach var="i" begin="1"
							end="${sessionScope.ShowEnterpriseCertifications.totalPages }">
							<c:if test="${sessionScope.ShowEnterpriseCertifications.currentPage == i  }"> 【${i }】 </c:if>
							<c:if test="${sessionScope.ShowEnterpriseCertifications.currentPage != i  }">
								<a href="/back/enterpriseCertification.action?currentPage=${i }">${i}&nbsp;</a>							</c:if>
						</c:forEach>    
       页</td>
        </tr>
    </table></td>
  </tr>
</table>
</form>
  </body>
</html>
