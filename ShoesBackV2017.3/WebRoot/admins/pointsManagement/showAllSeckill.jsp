<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
  <head> 
    <title>�鿴���л�����ɱ��Ʒ</title>  
  <style type="text/css">
  body
  {
	font-size: 13px;
}
  .left {
	font-family: "��Բ"; 
	color: #603F00;
	width:80px;
	text-align:left;
	padding-left:10px;
}
  .right {
	font-family: "��Բ";
	color: #111C00;
	text-decoration: none;
	width:120px;
}

table {
	cellspacing:0px;  
	cellpadding:0px;
	border-collapse:collapse;
}
td
{border-bottom:1px dotted;}
a
{text-decoration:none;
color:#f90;}

.title
{ 
	height:20px;
	font-size:16px; 
	padding-bottom:10px;
	font-family: "��Բ";
	text-align:center;
	
}
  </style>
</head>
  
  <body>
  
<c:if test="${empty sessionScope.ShowSecondKills}"> 
 	<script>location="/back/kill.action";</script>
</c:if> 


  <div>
    <span>�ܹ���${sessionScope.ShowSecondKills.totalRows } ����¼</span> ��ѡ�񣺵�
      <c:forEach var="i" begin="1" end="${sessionScope.ShowSecondKills.totalPages }">
        <c:if test="${sessionScope.ShowSecondKills.currentPage == i }"> [${i }]&nbsp; </c:if> 
        <c:if test="${sessionScope.ShowSecondKills.currentPage != i }"> <a href="/back/kill.action?currentPage=${i}">${i}</a>&nbsp; </c:if>
      </c:forEach>
      	ҳ     
  </div> 
  
    <table  border="0" style="margin:10px auto; border-bottom:0px;"  >   
      <tr >
        <td align="center"  class="title">����</td> 
        <td colspan="4"  class="title" >��ɱ��Ϣ</td> 
        <td colspan="5" class="title">Ь����ϸ����</td>  
      </tr>  
      
      
<c:forEach items="${sessionScope.ShowSecondKills.data}" var="seckill" varStatus="status" begin="0" step="1"> 
      <tr>
        <td  align="center" valign="top" width="80px; "  bgcolor="#F9FFF0">
<!-- ---------------------------����͵���޸����ӵ�ͬһ���ط� -------------->
        	<a href="/back/kill!ModifySecondKills.action?skid=${seckill.skid}" target="pointsManagement">���<br/>�޸�</a> 
        </td>
        <td  align="center">${seckill.shoes.sname}</td>
        <td class="left" rowspan="2" >��ʼʱ�䣺<br/><br/><br/>����ʱ�䣺</td>
        <td class="right"  rowspan="2" >${seckill.skstarttime}<br/><br/> ${seckill.skduration}</td> 
        <td  width="10px" rowspan="5" style="border-left:1px dotted;border-right:1px dotted;"></td>   
        <td class="left">���:</td>
        <td class="right">${seckill.shoes.snum} </td>
        <td class="left">������:</td>
        <td class="right"> ${seckill.shoes.sproducer}</td>
      </tr>
      <tr>
<!-- ---------------------------����͵���޸����ӵ�ͬһ���ط� -------------->
        <td  rowspan="4" align="center" width="30px;" style="border-right:1px dashed;">
        	<a href="/back/kill!ModifySecondKills.action?skid=${seckill.skid}" target="pointsManagement">${ status.count}</a> 
        </td>
        <td rowspan="4" style="width:200px;height:200px;"> 
			<c:if test="${requestScope.shoesimg[status.index]==null}"> 
				<img src="/back/images/empty.gif" style="max-width:200px; max-height:200px;"/>
			</c:if>
			<c:if test="${requestScope.shoesimg[status.index]!=null}"> 
        		<img src="/back/upload/img/${requestScope.shoesimg[status.index]}"  style="max-width:200px; max-height:200px;"/>
			</c:if>
        </td> 
        <td class="left">�Ա�:</td>
        <td class="right"><c:if test="${seckill.shoes.sgender eq '��'}">��Ь </c:if>
          <c:if test="${seckill.shoes.sgender eq 'Ů'}">ŮЬ </c:if></td>
        <td class="left">ԭ��/�ּ�</td>
        <td class="right"><fmt:formatNumber value="${seckill.shoes.sprices}" pattern="0.00"/>
          /
          <fmt:formatNumber value="${seckill.shoes.sprices*seckill.shoes.sdiscount}" pattern="0.00"/></td>
      </tr>
      <tr >
        <td class="left" >�ķѻ��֣�</td>
        <td class="right" >${seckill.skintegral}<br /></td>
        <td class="left">��ɫ:</td>
        <td class="right"> ${seckill.shoes.scolor}</td>
        <td rowspan="2" class="left">����ʱ��</td>
        <td rowspan="2" class="right"> ${seckill.shoes.spubtime}</td>
      </tr>
      <tr >
        <td class="left" >��ɱ������</td>
        <td class="right" >${seckill.skamount}</td>
        <td class="left">Ʒ �ƣ�</td>
        <td class="right"> ${seckill.shoes.brands.bname}</td>
      </tr>
      <tr >
        <td class="left" >��ɱ״̬��</td>
        <td class="right" >
	        <c:if test="${seckill.skisvalid==1}"> ��Ч </c:if> 
	        <c:if test="${seckill.skisvalid==0}"> ��Ч </c:if>  
        </td>
        <td class="left">�� �ͣ�</td>
        <td class="right">${seckill.shoes.types.tname}</td>
        <td class="left">�����ȶ�</td>
        <td class="right">${seckill.shoes.stimessold}(�˴�)</td>
      </tr>
      
      
      <tr  style="border:0px">
        <td colspan="9"  style="border:0px; height:10px;"><hr/></td>
      </tr>
</c:forEach>
  </table>
  </body>
</html>
