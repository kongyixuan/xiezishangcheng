<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>ShowAdmins.jsp</title>
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

function checkForm(num){
    var ss=document.getElementById("form2");
    if(num=="b"){
        ss.action="/back/comment.action";
        ss.submit();
    }else if(num=="a"){
        f=document.form1;
		for( i=0 ; i<f.elements.length ; i++) {
			if (f.elements[i].name=="chk_aid") {
				if(f.elements[i].checked==true){
					if(confirm("ȷ������ɾ����Щ������")){
						return true;
					}else{
						return false;
					}
				}
			}
		}
		alert("����ѡ��һ�����ݡ���");
		return false;
    }else{
        ss.action="/back/comment.action?currentPage="+num;
        ss.submit();
    }
	
}
	</script>
	</head>
  
  <body>
  	<c:if test="${empty sessionScope.ShowComments }">
      <script>location="/back/comment.action";</script>
    </c:if>
    <form id="form2" method="post" name="form1" action="/back/comment!BatchDeleteComments.action" onsubmit="return checkForm('a');">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="8" rowspan="3" background="/back/images/shadow.gif">
    </td>
    <td width="943" height="30" valign="middle" background="/back/images/table_bg001.gif"> &nbsp;&nbsp; <img src="/back/images/b_sing.gif" width="12" height="12" align="middle" />&nbsp;<font color="#999999">&nbsp;&nbsp; ��ʾ��Ϣ������˵�����ܹ��� ${sessionScope.ShowComments.totalRows } ������</font></td>
  </tr>
   <!----------------------�����ⲿ��------------------------------>
  <tr>
    <td height="580" valign="top" background="/back/images/table_bg001.gif" style="padding:5px;">
    <table width="100%" border="0" cellspacing="1" cellpadding="0" align="center">
      <tr>
        <td colspan="5" align="right">
           <input id="fuzzy" name="fuzzy" size="25" value="${requestScope.fuzzy}" />
           <input type="button" onclick="checkForm('b')" value="��ѯ" />           
        </td>
      </tr>
      <tr>
        <td colspan="5"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="6%"><img src="/back/images/b_table_header_left.gif" width="58" height="31" /></td>
            <td width="92%" background="/back/images/b_table_title_bg.gif">
            	<div style="text-align:center; font:12px; color:#FFF; font-weight:bold;">
                	ȫ����Ʒ�����б�
                </div>
            </td>
            <td width="2%"><img src="/back/images/b_table_header_right.gif" width="58" height="31" /></td>
          </tr>
        </table></td>
        </tr>
      <!----------------------����ֶβ���------------------------------>
      
      <tr>
        <td width="5%" background="/back/images/b_table_header.gif" height="34px">&nbsp;</td>
        <td width="10%" background="/back/images/b_table_header.gif" height="34px" align="center" valign="middle"><strong>�û�����</strong></td>
        <td width="20%" background="/back/images/b_table_header.gif" height="34px" align="center" valign="middle"><strong>Ь������</strong></td>
        <td width="60%" background="/back/images/b_table_header.gif" height="34px" align="center" valign="middle"><strong>��������</strong></td>
        <td width="5%" background="/back/images/bg_handler.gif" height="34px" align="center" valign="middle"><strong>����</strong></td>
      </tr>
      <!----------------------��̬������ʾ����------------------------------>
      <c:forEach items="${sessionScope.ShowComments.data }" var="comment">
      <tr height="30px" bgcolor="#F7F7F7" 
      		onmouseover="this.style.backgroundColor='lavender'; this.style.cursor='pointer';"
            onmouseout="this.style.backgroundColor='#F7F7F7'; this.style.cursor='pointer';">
        <td align="center" valign="middle"><label>
          <input type="checkbox" name="chk_aid" id="chk_aid" value="${comment.cid }"/>
        </label></td>
        <td height="35" align="center" valign="middle">${comment.users.uaccount}</td>
        <td align="center" valign="middle">${comment.shoes.sname }</td>
        <td align="center" valign="middle">${comment.sccomments}</td>
        <td align="center" valign="middle">
        		<a href="javascript:if(confirm('ȷ��ɾ����������Ϣ��?')==true){location='/back/comment!DeleteComments.action?cid=${comment.cid}';}">ɾ��</a>
        </td>
      </tr>
      </c:forEach>
      
      <!--------------------------��ҳ��ʾ����---------------------------------->
      <tr height="35px">
        <td height="40" colspan="3" align="left" valign="middle">
        	��ǰ�ڣ� ${sessionScope.ShowComments.currentPage } / ${sessionScope.ShowComments.totalPages } ҳ
        	<a href="javascript:SetChecked('chk_aid');">ȫѡ</a>&nbsp; 
        	<a href="javascript:SetResverseChecked('chk_aid');">��ѡ</a>&nbsp; 
        	<a href="javascript:SetUnChecked('chk_aid');">���</a>&nbsp;&nbsp;
        	<input type="submit" name="btnBatchDel" id="btnBatchDel" value="����ɾ��">
        </td>
        <td colspan="2" align="right" valign="middle">��ѡ�񣺵�
   		<c:forEach var="i" begin="1"
							end="${sessionScope.ShowComments.totalPages }">
							<c:if test="${sessionScope.ShowComments.currentPage == i  }"> ��${i}�� </c:if>
							<c:if test="${sessionScope.ShowComments.currentPage != i  }">
								<a href="javascript:checkForm('${i}');">${i}&nbsp;</a>
							</c:if>
						</c:forEach>    
                 ҳ</td>
        </tr>
    </table></td>
  </tr>
</table>
</form>
  </body>
</html>
