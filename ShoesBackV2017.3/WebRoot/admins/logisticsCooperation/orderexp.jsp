<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<title>�ޱ����ĵ�</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="/back/fancybox/jquery.mousewheel-3.0.2.pack.js"></script>
	<script type="text/javascript" src="/back/fancybox/jquery.fancybox-1.3.1.js"></script>
	<link rel="stylesheet" type="text/css" href="/back/fancybox/jquery.fancybox-1.3.1.css" media="screen" />
    <link rel="stylesheet" type="text/css" href="/back/fancybox/style.css" />
	<script type="text/javascript">
		
		function fancy(id){
			$("#various"+id).fancybox({
				'width'				: '60%',
				'height'			: '80%',
				'autoScale'			: false,
				'transitionIn'		: 'none',
				'transitionOut'		: 'none',
				'type'				: 'iframe'
			});
		}
		function fancy2(id){
			$("#various2"+id).fancybox({
				'width'				: '60%',
				'height'			: '80%',
				'autoScale'			: false,
				'transitionIn'		: 'none',
				'transitionOut'		: 'none',
				'type'				: 'iframe'
			});
		}
	</script>
</head>

<body>
<c:if test="${empty requestScope.OrderExp}">
	<script>location="/back/order!GetExpOrders.action";</script>
</c:if>
<form method="post" name="form1" action="">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  
  <tr>
    <td width="8" rowspan="3" style="background: url('/back/images/shadow.gif');">
    </td>
    <td width="943" height="30" valign="middle" style="background: url('/back/images/table_bg001.gif');"> &nbsp;&nbsp; <img src="/back/images/b_sing.gif" width="12" height="12" align="middle" />&nbsp;<font color="#999999">&nbsp;&nbsp; �ܹ���${requestScope.OrderExp.totalRows }  ������</font></td>
  </tr> 
   <!-------------------�����ⲿ��------------------------------>
  <tr>
    <td height="580" valign="top" style="background: url('/back/images/table_bg001.gif'); padding: 5px;" >
   <table width="100%" border="0" cellspacing="1" cellpadding="0">
      <tr>
        <td colspan="12"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="6%" style="background-image: url('/back/images/b_table_header_left.gif');"><img src="/back/images/b_table_header_left.gif" width="58" height="31" /></td>
            <td width="92%" style="background-image: url('/back/images/b_table_title_bg.gif');">
            	<div style="text-align:center; font:12px; color:#FFF; font-weight:bold;">
                	 �� �� �� ��
                </div>
            </td>
            <td width="2%"><img src="/back/images/b_table_header_right.gif" width="58" height="31" /></td>
          </tr>
        </table></td>
        </tr>
      <!----------------------����ֶβ���------------------------------>
      <tr>
        <td width="3%" background="/back/images/b_table_header.gif" height="34" align="center" valign="middle"><strong>ID</strong></td>
        <td width="9%" background="/back/images/b_table_header.gif" height="34px" align="center" valign="middle"><strong>��������</strong></td>
        <td width="9%" background="/back/images/b_table_header.gif" height="34px" align="center" valign="middle"><strong>�µ��ͻ�</strong></td>
        <td width="20%" background="/back/images/b_table_header.gif" height="34px" align="center" valign="middle"><strong>�ջ���ַ</strong></td>
        <td width="10%" background="/back/images/b_table_header.gif" height="34px" align="center" valign="middle"><strong>�������</strong></td>
        <td width="12%" background="/back/images/b_table_header.gif" height="34px" align="center" valign="middle"><strong>�µ�ʱ��</strong></td>
        <td width="7%" background="/back/images/b_table_header.gif" height="34px" align="center" valign="middle"><strong>����״̬</strong></td>
        <td width="12%" background="/back/images/b_table_header.gif" height="34px" align="center" valign="middle"><strong>��ע��Ϣ</strong></td>
        <td width="25%" background="/back/images/bg_handler.gif" height="34px" align="center" valign="middle"><strong>����</strong></td>
      </tr>
      <!----------------------��̬������ʾ����------------------------------>
      <c:forEach items="${requestScope.OrderExp.data }" var="o">
      <tr height="30px" bgcolor="#F7F7F7" 
      		onmouseover="this.style.backgroundColor='lavender'; this.style.cursor='pointer';"
            onmouseout="this.style.backgroundColor='#F7F7F7'; this.style.cursor='pointer';">
        <td height="35" align="center" valign="middle">${o.oid }</td>
        <td align="center" valign="middle">${o.onum }</a></td>
        <td align="center" valign="middle">${o.users.uaccount }</td>
        <td align="center" valign="middle">${o.receives.recprovince } ${o.receives.reccity } ${o.receives.recdistrict } ${o.receives.recstreet } </td>
        <td align="center" valign="middle"><font style="color:#F00; font-weight:bold;"><fmt:formatNumber value="${o.ototal }" pattern="��0.00"/></font></td>
        <td align="center" valign="middle"><fmt:formatDate value="${o.ordertime}" type="both" /></td>
        <c:if test="${o.ostate==1 }">
        <td align="center" valign="middle">��ͨ����</td>
        </c:if>
        <c:if test="${o.ostate==7 }">
        <td align="center" valign="middle">��ɱ����</td>
        </c:if>
        <c:if test="${o.ostate ne ''}">
        <td align="center" valign="middle">${o.onote}</td>
        </c:if>
        <c:if test="${o.ostate eq ''}">
        <td align="center" valign="middle">&nbsp;</td>
        </c:if>
        <td align="center" valign="middle"><a id="various${o.oid }" href="/back/order!AddExp.action?oid=${o.oid }" onclick="fancy(${o.oid })">�����ڵ�</a>&nbsp;&nbsp;
        								   <a id="various2${o.oid }" href="/back/order!GetExpInfo.action?oid=${o.oid }" onclick="fancy2(${o.oid })">�鿴����</a></td>
      </tr>
      </c:forEach>
      
      <!--------------------------��ҳ��ʾ����---------------------------------->
      <tr height="35px">
        <td height="40" colspan="6" align="left" valign="middle">��ǰ�ڣ� ${requestScope.OrderExp.currentPage } / ${requestScope.orderPage.totalPages } ҳ</td>
        <td>&nbsp;</td>
        <td colspan="5" align="right" valign="middle">��ѡ�񣺵�
        <c:forEach begin="1" end="${requestScope.OrderExp.totalPages }" var="i">
            <c:if test="${requestScope.OrderExp.currentPage == i}">��${i }��</c:if>
            <c:if test="${requestScope.OrderExp.currentPage != i}">
        	  <a href="/back/order!GetExpOrders.action?currentPage=${i}">${i}</a>
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
