<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>ShowUsers.jsp</title>
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
	<script language="javascript" >
//��������������ô�function��1.form������action,���������û�,������Ҫ���һ����־
//2.����ģ����ѯ��ť���ã��ü���ģ������������ʾ�б�,����Ҳ��Ҫ���һ����־
//3.���շ�ҳ���ң��ֱ��������ģ�������������з�ҳ��ѯ����,������Ҫ��Ӿ���Ҫ�鿴�ڼ�ҳ�����Ͳ���
	function checkForm(num){
	//��ȡҳ�������
	var form=document.getElementById("form2");
	//1.form������action,��������
	if(num=="a"){
	        f=document.form1;
			for( i=0 ; i<f.elements.length ; i++) {
				if (f.elements[i].name=="chk_uid") {
					if(f.elements[i].checked==true){
						if(confirm("ȷ������������Щ�û���?")){
							return true;
						}else{
							return false;
						}
					}
				}
			}
			alert("����ѡ��һ���û�����");
			return false;
	}
    // 2.����ģ����ѯ��ť����	
	else if(num=="b"){
	    //ģ����ѯ���жϱ��Ƿ�Ϊ�գ����Ϊ�գ�֤������ȫ���û���Ϣ������ģ��������ѯ
	    //����form������action����
	    form.action="/back/user.action";
	    //���ύ
	    form.submit(); 
	}
	//3.���շ�ҳ����,��ʱnum������ΪҪ��ѯҳ���ı���
	else{	   
	    //����form������action����
	    form.action="/back/user.action?currentPage="+num;
	    //���ύ
	    form.submit();
	}
	
}
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
	</script>
  </head>  
  <body>
  	<c:if test="${empty sessionScope.ShowUser}">
      <script>location="/back/user.action";</script>
    </c:if>
    <form id="form2" method="post" name="form1" action="/back/user!BatchDeleteUser.action" onSubmit="return checkForm('a');">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="8" rowspan="3" background="/back/images/shadow.gif">
    </td>
    <td width="943" height="30" valign="middle" background="/back/images/table_bg001.gif"> &nbsp;&nbsp; <img src="/back/images/b_sing.gif" width="12" height="12" align="middle" />&nbsp;<font color="#999999">&nbsp;&nbsp; ��ʾ��Ϣ������˵�����ܹ��� ${sessionScope.ShowUser.totalRows } ������</font></td>
  </tr>
   <!----------------------�����ⲿ��------------------------------>
  <tr>
    <td height="580" valign="top" background="/back/images/table_bg001.gif" style="padding:5px;">
    <table width="100%" border="0" cellspacing="1" cellpadding="0" align="center">
      <tr>
         <td colspan="13" align="right">
           <input name="fuzzy" id="fuzzy" size="25" value="${requestScope.fuzzy}" /> <input type="button" onclick="checkForm('b')" value="��ѯ"  />
         </td>
      </tr> 
      <tr>
        <td colspan="13">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="6%" background="/back/images/b_table_header_left.gif"><img src="/back/images/b_table_header_left.gif" width="58" height="31" /></td>
            <td width="92%" background="/back/images/b_table_title_bg.gif">
            	<div style="text-align:center; font:12px; color:#FFF; font-weight:bold;">
                	ȫ���û��б�                </div>            </td>
            <td width="2%" background="/back/images/b_table_header_right.gif"><img src="/back/images/b_table_header_right.gif" width="58" height="31" /></td>
          </tr>
        </table></td>
        </tr>
      <!----------------------����ֶβ���------------------------------>
      
      <tr>
        <td width="3%" background="/back/images/b_table_header.gif" height="34px">&nbsp;</td>
        <td width="9%" background="/back/images/b_table_header.gif" height="34px" align="center" valign="middle">
        <strong>���</strong></td>
        <td width="17%" background="/back/images/b_table_header.gif" height="34px" align="center" valign="middle">
        <strong>�û��˺�</strong></td>
        <td width="10%" background="/back/images/b_table_header.gif" height="34px" align="center" valign="middle">
        <strong>�û�����</strong></td>
        <td width="7%" height="34px" align="center" valign="middle" background="/back/images/b_table_header.gif">
          <p><strong>�û�</strong><strong>����</strong></p>          </td>
        <td width="8%" background="/back/images/b_table_header.gif" height="34px" align="center" valign="middle">
          <p><strong>��ϵ</strong><strong>�绰</strong></p>          </td>
        <td width="7%" background="/back/images/b_table_header.gif" height="34px" align="center" valign="middle">
          <p><strong>�û�</strong><strong>�Ա�</strong></p>          </td>
        <td width="7%" background="/back/images/b_table_header.gif" height="34px" align="center" valign="middle">
          <p><strong>�û�</strong><strong>����</strong></p>          </td>
        <td width="12%" background="/back/images/b_table_header.gif" height="34px" align="center" valign="middle">
          <p><strong>�û�</strong><strong>����</strong></p>          </td>
        <td width="10%" background="/back/images/b_table_header.gif" height="34px" align="center" valign="middle">
          <p><strong>�û�ע��</strong><strong>ʱ��</strong></p>          </td>
        <td width="10%" background="/back/images/b_table_header.gif" height="34px" align="center" valign="middle">
        <strong>����</strong></td>
      </tr>
      <!----------------------��̬������ʾ����------------------------------>
      <c:if test="${!empty sessionScope.ShowUser}">
         <c:forEach items="${sessionScope.ShowUser.data }" var="users">
         <c:if test="${users.udelete == 1}">
           <tr height="30px" bgcolor="#F7F7F7" style="background-color: #CC66FF;"
      		onmouseover="this.style.backgroundColor='lavender'; this.style.cursor='pointer';"
            onmouseout="this.style.backgroundColor='#CC66FF'; this.style.cursor='pointer';">
         </c:if>
         <c:if test="${users.udelete == 0}">
           <tr height="30px" bgcolor="#F7F7F7" 
      		onmouseover="this.style.backgroundColor='lavender'; this.style.cursor='pointer';"
            onmouseout="this.style.backgroundColor='#F7F7F7'; this.style.cursor='pointer';">
         </c:if>           
        <td align="center" valign="middle"><label>
          <input type="checkbox" name="chk_uid" id="chk_uid" value="${users.uid }"/>
        </label></td>
        <td height="49" align="center" valign="middle">${users.uid}</td>
        <td align="center" valign="middle">${users.uaccount }</td>
        <td align="center" valign="middle">*******</td>
        <td align="center" valign="middle">${users.uname }</td>
        <td align="center" valign="middle">${users.utel }</td>
        <td align="center" valign="middle">
            <c:if test="${users.ugender eq 'Ů'}">Ů</c:if>
            <c:if test="${users.ugender eq '��'}">��</c:if> </td>
        <td align="center" valign="middle">${users.uintegral }</td>
        <td align="center" valign="middle">${users.uemail }</td>
        <td align="center" valign="middle">
          <fmt:formatDate value="${users.uregtime}" pattern="yyyy-MM-dd"/>
        </td>
        <td colspan="3" align="center" valign="middle">
        <a href="/back/user!UserUpdate.action?uid=${users.uid }">����</a>&nbsp;&nbsp; 
        <c:if test="${users.udelete == 0}">
        <a href="javascript:if(confirm('ȷ�����ô��û���?')){location='/back/user!DeleteUser.action?uid=${users.uid}';}">����</a> 
        </c:if>
        <c:if test="${users.udelete == 1}">
        <a href="javascript:if(confirm('ȷ�������ô��û���?')){location='/back/user!UnDeleteUser.action?uid=${users.uid}';}">������</a>
       </c:if>
       </td>
        </tr>
      </c:forEach>
      </c:if>      
      <!--------------------------��ҳ��ʾ����---------------------------------->
      <tr height="35px">
        <td height="40" colspan="3" align="left" valign="middle">
        	��ǰ�ڣ� ${sessionScope.ShowUser.currentPage } / ${sessionScope.ShowUser.totalPages } ҳ&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;        </td>
        <td colspan="10" align="right" valign="middle">��ѡ�񣺵�
   <c:forEach var="i" begin="1"
							end="${sessionScope.ShowUser.totalPages }">
							<c:if test="${sessionScope.ShowUser.currentPage == i  }"> ��${i }�� </c:if>
							<c:if test="${sessionScope.ShowUser.currentPage != i  }">
								<a href="javascript:checkForm('${i}');">${i}&nbsp;</a>							</c:if>
						</c:forEach>    
       ҳ</td>
        </tr>
      <tr height="35px">
        <td height="40" colspan="3" align="left" valign="middle">&nbsp; <a href="javascript:SetChecked('chk_uid');">ȫѡ</a>&nbsp; <a href="javascript:SetResverseChecked('chk_uid');">��ѡ</a>&nbsp; <a href="javascript:SetUnChecked('chk_uid');">���</a>&nbsp;&nbsp;
          <input type="submit" name="btnBatchDel" id="btnBatchDel" value="������/����"></td>
        <td colspan="10" align="right" valign="middle">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
</table>
</form>
  </body>
</html>
