<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
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
  </head>
  
  <body>
  	<c:if test="${empty sessionScope.ShowInquirys }">
      <script>location="/back/inquiry.action";</script>
    </c:if>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="8" rowspan="3" background="/back/images/shadow.gif">
    </td>
    <td width="943" height="30" valign="middle" background="/back/images/table_bg001.gif"> &nbsp;&nbsp; <img src="/back/images/b_sing.gif" width="12" height="12" align="middle" />&nbsp;<font color="#999999">&nbsp;&nbsp; ��ʾ��Ϣ������˵�����ܹ��� ${sessionScope.ShowInquirys.totalRows } ������</font></td>
  </tr>
   <!----------------------�����ⲿ��------------------------------>
  <tr>
    <td height="580" valign="top" background="/back/images/table_bg001.gif" style="padding:5px;">
    <table width="100%" border="0" cellspacing="1" cellpadding="0" align="center">
      <tr>
         <td colspan="5" align="right">
           <form action="/back/inquiry.action" method="post">
              <input id="fuzzy" name="fuzzy" size="25" value="${requestScope.fuzzy}" />
              <input type="submit" value="��ѯ" />
           </form>
         </td>
      </tr>
      <tr>
        <td colspan="5"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="6%"><img src="/back/images/b_table_header_left.gif" width="58" height="31" /></td>
            <td width="92%" background="/back/images/b_table_title_bg.gif">
            	<div style="text-align:center; font:12px; color:#FFF; font-weight:bold;">
                	ȫ���ͻ���ѯ�б�
                </div>
            </td>
            <td width="2%"><img src="/back/images/b_table_header_right.gif" width="58" height="31" /></td>
          </tr>
        </table></td>
        </tr>
      <!----------------------����ֶβ���------------------------------>
      
      <tr>
        <td width="5%" background="/back/images/b_table_header.gif" height="34px">���</td>
        <td width="10%" background="/back/images/b_table_header.gif" height="34px" align="center" valign="middle"><strong>Ь������</strong></td>
        <td width="70%" background="/back/images/b_table_header.gif" height="34px" align="center" valign="middle"><strong>������ϸ</strong></td>
        <td width="10%" background="/back/images/b_table_header.gif" height="34px" align="center" valign="middle"><strong>����ʱ��</strong></td>
        <td width="5%" background="/back/images/bg_handler.gif" height="34px" align="center" valign="middle"><strong>����</strong></td>
      </tr>
      <!----------------------��̬������ʾ����------------------------------>
      <c:forEach items="${sessionScope.ShowInquirys.data }" var="inquiry">
      <tr height="30px" bgcolor="#F7F7F7" 
      		onmouseover="this.style.backgroundColor='lavender'; this.style.cursor='pointer';"
            onmouseout="this.style.backgroundColor='#F7F7F7'; this.style.cursor='pointer';">
        <td align="center" valign="middle">${inquiry.sqid }</td>
        <td height="35" align="center" valign="middle">${inquiry.shoes.sname }</td>
        <td align="center" valign="middle">${inquiry.sqquestion }</td>
        <td align="center" valign="middle">
          <fmt:formatDate value="${inquiry.sqquestiontime}" pattern="yyyy-MM-dd"/>
        </td>
        <td align="center" valign="middle"><a href="/back/inquiry!AnswerResponse.action?sqid=${inquiry.sqid}">�ظ�</a>&nbsp;&nbsp; 
        </td>
      </tr>
      </c:forEach>
      
      <!--------------------------��ҳ��ʾ����---------------------------------->
      <tr height="35px">
        <td height="40" colspan="2" align="left" valign="middle">
        	��ǰ�ڣ� ${sessionScope.ShowInquirys.currentPage } / ${sessionScope.ShowInquirys.totalPages } ҳ
        </td>
        <td colspan="3" align="right" valign="middle">��ѡ�񣺵�
   <c:forEach var="i" begin="1"
							end="${sessionScope.ShowInquirys.totalPages }">
							<c:if test="${sessionScope.ShowInquirys.currentPage == i  }"> ��${i }�� </c:if>
							<c:if test="${sessionScope.ShowInquirys.currentPage != i  }">
								<a href="/back/inquiry.action?currentPage=${i }">${i}&nbsp;</a>
							</c:if>
						</c:forEach>    
       ҳ</td>
        </tr>
    </table></td>
  </tr>
</table>

  </body>
</html>
