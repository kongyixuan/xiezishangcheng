<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"  %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>ShowSpshoes.jsp</title>
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
	<script type="text/javascript">
	// ��ѡ��ȫѡ����
	function SetChecked(boxname) {
		f = document.form1;
		for (i = 0; i < f.elements.length; i++) {
			if (f.elements[i].name == boxname) {
				f.elements[i].checked = true;
			}
		}
	}
	// ��ѡ��ѡ����
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
	// ��ѡ��ȫ��ѡ����
	function SetUnChecked(boxname) {
		f = document.form1;
		for (i = 0; i < f.elements.length; i++) {
			if (f.elements[i].name == boxname) {
				f.elements[i].checked = false;
			}
		}
	}
	function checkForm(){
	f=document.form1;
	for( i=0 ; i<f.elements.length ; i++) {
		if (f.elements[i].name=="chk_spsid") {
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
}
	</script>
  </head>
  
  <body>
  	<c:if test="${empty sessionScope.ShowSpcifyShoes}">
      <script>location="/back/spcify.action";</script>
    </c:if>
    <form method="post" name="form1" action="/back/spcify!BatchDeleteSpcifyShoes.action" onSubmit="return checkForm();">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="8" rowspan="3" background="/back/images/shadow.gif">
    </td>
    <td width="943" height="30" valign="middle" background="/back/images/table_bg001.gif"> &nbsp;&nbsp; <img src="/back/images/b_sing.gif" width="12" height="12" align="middle" />&nbsp;<font color="#999999">&nbsp;&nbsp; ��ʾ��Ϣ������˵�����ܹ��� ${sessionScope.ShowSpcifyShoes.totalRows } ������</font></td>
  </tr>
   <!----------------------�����ⲿ��------------------------------>
  <tr>
    <td height="580" valign="top" background="/back/images/table_bg001.gif" style="padding:5px;">
    <table width="100%" border="0" cellspacing="1" cellpadding="0" align="center">
      <tr>
        <td colspan="11"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="6%"><img src="/back/images/b_table_header_left.gif" width="58" height="31" /></td>
            <td width="92%" background="/back/images/b_table_title_bg.gif">
            	<div style="text-align:center; font:12px; color:#FFF; font-weight:bold;">
                	ȫ������Ь���б�                </div>            </td>
            <td width="2%"><img src="/back/images/b_table_header_right.gif" width="58" height="31" /></td>
          </tr>
        </table></td>
        </tr>
      <!----------------------����ֶβ���------------------------------>
      
      <tr>
        <td width="5%" background="/back/images/b_table_header.gif" height="34px">&nbsp;</td>
        <td width="5%" background="/back/images/b_table_header.gif" height="34px" align="center" valign="middle"><strong>���</strong></td>
        <td width="10%" background="/back/images/b_table_header.gif" height="34px" align="center" valign="middle"><strong>����</strong></td>
        <td width="10%" background="/back/images/b_table_header.gif" height="34px" align="center" valign="middle"><strong>Ʒ��</strong></td>
        <td width="5%" background="/back/images/b_table_header.gif" height="34px" align="center" valign="middle"><strong>�۸�</strong></td>
        <td width="10%" background="/back/images/b_table_header.gif" height="34px" align="center" valign="middle"><strong>���к�</strong></td>
         <td width="10%" background="/back/images/b_table_header.gif" height="34px" align="center" valign="middle"><strong>����</strong></td>
        <td width="5%" background="/back/images/b_table_header.gif" height="34px" align="center" valign="middle"><strong>�Ա�</strong></td>
        <td width="10%" background="/back/images/b_table_header.gif" height="34px" align="center" valign="middle"><strong>��������</strong></td>
        <td width="20%" background="/back/images/bg_handler.gif" align="center" valign="middle"><strong>������Ϣ</strong></td>
        <td width="20%" background="/back/images/bg_handler.gif" height="34px" align="center" valign="middle"><strong>����</strong></td>
      </tr>
      <!----------------------��̬������ʾ����------------------------------>
      <c:forEach items="${sessionScope.ShowSpcifyShoes.data }" var="spcifyShoes">
      <tr height="30px" bgcolor="#F7F7F7" 
      		onmouseover="this.style.backgroundColor='lavender'; this.style.cursor='pointer';"
            onmouseout="this.style.backgroundColor='#F7F7F7'; this.style.cursor='pointer';">
        <td align="center" valign="middle"><label>
          <input type="checkbox" name="chk_spsid" id="chk_spsid" value="${spcifyShoes.spsid }"/>
        </label></td>
        <td height="35" align="center" valign="middle">${spcifyShoes.spsid }</td>
        <td align="center" valign="middle">${spcifyShoes.types.tname }</td>
        <td align="center" valign="middle">${spcifyShoes.brands.bname }</td>
        <td align="center" valign="middle"><fmt:formatNumber value="${spcifyShoes.spsprices}" pattern="��0.00"/></td>
        <td align="center" valign="middle">${spcifyShoes.spsseq}</td>
        <td align="center" valign="middle">${spcifyShoes.spsname}</td>
        <td align="center" valign="middle">
        <c:if test="${spcifyShoes.spsgender eq '��'}">�п�</c:if>
        <c:if test="${spcifyShoes.spsgender eq 'Ů'}">Ů��</c:if>        </td>
        <td align="center" valign="middle">${spcifyShoes.spspartnum }</td>
        <td align="center" valign="middle">${spcifyShoes.spspartinfo }</td>
        <td align="center" valign="middle">
          <a href="/back/spcify!UpdateSpcifyShoes.action?spsid=${spcifyShoes.spsid }">�޸�</a>&nbsp;&nbsp; 
          <a href="javascript:if(confirm('ȷ��ɾ��������¼��?')==true){location='/back/spcify!DeleteSpcifyShoes.action?spsid=${spcifyShoes.spsid }';}">ɾ��</a></td>
      </tr>
      </c:forEach>
      <!--------------------------��ҳ��ʾ����---------------------------------->
      <tr height="35px">
        <td height="40" colspan="8" align="left" valign="middle">
        	��ǰ�ڣ� ${sessionScope.ShowSpcifyShoes.currentPage } / ${sessionScope.ShowSpcifyShoes.totalPages } ҳ&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
        	<a href="javascript:SetChecked('chk_spsid');">ȫѡ</a>&nbsp; 
        	<a href="javascript:SetResverseChecked('chk_spsid');">��ѡ</a>&nbsp; 
        	<a href="javascript:SetUnChecked('chk_spsid');">���</a>&nbsp;&nbsp;
        	<input type="submit" name="btnBatchDel" id="btnBatchDel" value="����ɾ��">        </td>
        <td colspan="3" align="right" valign="middle">��ѡ�񣺵�
   <c:forEach var="i" begin="1"
							end="${sessionScope.ShowSpcifyShoes.totalPages }">
							<c:if test="${sessionScope.ShowSpcifyShoes.currentPage == i}"> ��${i}�� </c:if>
							<c:if test="${sessionScope.ShowSpcifyShoes.currentPage != i}">
								<a href="/back/spcify.action?currentPage=${i}">${i}&nbsp;</a>							</c:if>
						</c:forEach>    
       ҳ</td>
        </tr>
    </table></td>
  </tr>
</table>
</form>
  </body>
</html>
