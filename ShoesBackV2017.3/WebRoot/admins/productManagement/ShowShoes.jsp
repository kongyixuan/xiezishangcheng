<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"  %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>ShowShoes.jsp</title>
 <style type="text/css">
.modal-footer span {
	font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
	float: left;
	font-size: 16px;
}
.table th, .table td { 
	text-align: center; 
	height:25px;
}      
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
   //��ȡ�ύ������
    var ss=document.getElementById("form2");
    //�ύģ������
    if(num=="b"){
       ss.action="/back/shoe.action";
       ss.submit();
    }
    //excel����c
    else if(num=="c"){              
       ss.action="/back/filedownload/download.action";
       ss.submit();
    }
    //��������
    else if(num=="a"){
        f=document.form1;
		for( i=0 ; i<f.elements.length ; i++) {
			if (f.elements[i].name=="chk_aid") {
				if(f.elements[i].checked==true){
					if(confirm("ȷ������������ЩЬ����?")){
						return true;
					}else{
						return false;
					}
				}
			}
		}
		alert("����ѡ��һ�����ݡ���");
		return false;
    }
    //��ҳ�ύ����
    else{
         ss.action="/back/shoe.action?currentPage="+num;
	    //���ύ
	     ss.submit();
    }
	
}
	</script>
  </head>
  
  <body>
  	<c:if test="${empty sessionScope.ShowShoes }">
      <script>location="/back/shoe.action";</script>
    </c:if>
    <form id="form2" method="post" name="form1" action="/back/shoe!BatchDelete.action" onSubmit="return checkForm('a');">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="8" rowspan="3" background="/back/images/shadow.gif">
    </td>
    <td width="943" height="30" valign="middle" background="/back/images/table_bg001.gif"> &nbsp;&nbsp; <img src="/back/images/b_sing.gif" width="12" height="12" align="middle" />&nbsp;<font color="#999999">&nbsp;&nbsp; ��ʾ��Ϣ������˵�����ܹ��� ${sessionScope.ShowShoes.totalRows } ������</font></td>
  </tr>
   <!----------------------�����ⲿ��------------------------------>
  <tr>
    <td height="580" valign="top" background="/back/images/table_bg001.gif" style="padding:5px;">
    <table width="100%" border="0" cellspacing="1" cellpadding="0" align="center">
      <tr>
         <td colspan="10" align="right">
           <input name="fuzzy" id="fuzzy" size="25" value="${requestScope.fuzzy}" /> <input type="button" onclick="checkForm('b')" value="��ѯ"  />
         </td>
      </tr> 
      <tr>
        <td colspan="10"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="6%"><img src="/back/images/b_table_header_left.gif" width="58" height="31" /></td>
            <td width="92%" background="/back/images/b_table_title_bg.gif">
            	<div style="text-align:center; font:12px; color:#FFF; font-weight:bold;">
                	ȫ��Ь���б�
                </div>
            </td>
            <td width="2%"><img src="/back/images/b_table_header_right.gif" width="58" height="31" /></td>
          </tr>
        </table></td>
        </tr>
      <!----------------------����ֶβ���------------------------------>
      
      <tr>
        <td width="5%" background="/back/images/b_table_header.gif" height="34px">&nbsp;</td>
        <td width="10%" background="/back/images/b_table_header.gif" height="34px" align="center" valign="middle"><strong>���</strong></td>
        <td width="10%" background="/back/images/b_table_header.gif" height="34px" align="center" valign="middle"><strong>����</strong></td>
        <td width="10%" background="/back/images/b_table_header.gif" height="34px" align="center" valign="middle"><strong>Ʒ��</strong></td>
        <td width="10%" background="/back/images/b_table_header.gif" height="34px" align="center" valign="middle"><strong>����</strong></td>
        <td width="10%" background="/back/images/b_table_header.gif" height="34px" align="center" valign="middle"><strong>�۸�</strong></td>
         <td width="10%" background="/back/images/b_table_header.gif" height="34px" align="center" valign="middle"><strong>������</strong></td>
        <td width="10%" background="/back/images/b_table_header.gif" height="34px" align="center" valign="middle"><strong>�Ա�</strong></td>
        <td width="10%" background="/back/images/b_table_header.gif" height="34px" align="center" valign="middle"><strong>��ɫ</strong></td>
        <td width="15%" background="/back/images/bg_handler.gif" height="34px" align="center" valign="middle"><strong>����</strong></td>
      </tr>
      <!----------------------��̬������ʾ����------------------------------>
      <c:if test="${!empty sessionScope.ShowShoes}">
         <c:forEach items="${sessionScope.ShowShoes.data}" var="shoe">
         <c:if test="${shoe.sdelete == 0}">
            <tr height="30px" bgcolor="#F7F7F7" 
      		onmouseover="this.style.backgroundColor='lavender'; this.style.cursor='pointer';"
            onmouseout="this.style.backgroundColor='#F7F7F7'; this.style.cursor='pointer';">  
         </c:if>
         <c:if test="${shoe.sdelete == 1}">
            <tr height="30px" bgcolor="#F7F7F7" style="background-color: #CC66FF;" 
      		onmouseover="this.style.backgroundColor='lavender'; this.style.cursor='pointer';"
            onmouseout="this.style.backgroundColor='#CC66FF'; this.style.cursor='pointer';">
         </c:if>      
        <td align="center" valign="middle"><label>
          <input type="checkbox" name="chk_aid" id="chk_aid" value="${shoe.sid }"/>
        </label></td>
        <td height="35" align="center" valign="middle">${shoe.sid}</td>
        <td align="center" valign="middle">${shoe.types.tname}</td>
        <td align="center" valign="middle">${shoe.brands.bname }</td>
        <td align="center" valign="middle">${shoe.sname }</td>
        <td align="center" valign="middle"><fmt:formatNumber value="${shoe.sprices}" pattern="��0.00"/></td>
        <td align="center" valign="middle">${shoe.sproducer}</td>
        <td align="center" valign="middle">
        <c:if test="${shoe.sgender eq 'Ů'}">Ů��</c:if>
        <c:if test="${shoe.sgender eq '��'}">�п�</c:if>
        </td>
        <td align="center" valign="middle">${shoe.scolor}</td>
        <td align="center" valign="middle"><a href="/back/shoe!ShoeUpdate.action?sid=${shoe.sid}">�޸�</a>&nbsp;&nbsp;
        <c:if test="${shoe.sdelete == 0}">
          <a href="javascript:if(confirm('ȷ�����ø�Ь����?')==true){location='/back/shoe!DeleteShoes.action?sid=${shoe.sid }';}">����</a>
        </c:if>
        <c:if test="${shoe.sdelete == 1}">
          <a href="javascript:if(confirm('ȷ��������Ь����?')==true){location='/back/shoe!UnDeleteShoes.action?sid=${shoe.sid }';}">������</a>
        </c:if> 
        </td>
      </tr>
      </c:forEach>
      </c:if>
      
      
      <!--------------------------��ҳ��ʾ����---------------------------------->
      <tr height="35px">
        <td height="40" colspan="8" align="left" valign="middle">
        	��ǰ�ڣ� ${sessionScope.ShowShoes.currentPage } / ${sessionScope.ShowShoes.totalPages } ҳ&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        	<a href="javascript:SetChecked('chk_aid');">ȫѡ</a>&nbsp; 
        	<a href="javascript:SetResverseChecked('chk_aid');">��ѡ</a>&nbsp; 
        	<a href="javascript:SetUnChecked('chk_aid');">���</a>&nbsp;&nbsp;
        	<input type="submit" name="btnBatchDel" id="btnBatchDel" value="������/����">&nbsp;&nbsp;
        	<s:a href="javascript:checkForm('c');">excel����</s:a>&nbsp;
        	<a href="/back/admins/productManagement/ImportShoes.jsp">excel����</a>
        </td>
        <td colspan="2" align="right" valign="middle">��ѡ�񣺵�
   <c:forEach var="i" begin="1"
							end="${sessionScope.ShowShoes.totalPages }">
							<c:if test="${sessionScope.ShowShoes.currentPage == i}"> ��${i}�� </c:if>
							<c:if test="${sessionScope.ShowShoes.currentPage != i}">
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
