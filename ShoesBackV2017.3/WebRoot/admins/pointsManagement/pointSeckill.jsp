<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<title>���ֳ齱</title>
<style type="text/css">
<!--
body {
	background-color: #e6e6e6;
}
body, td, th {
	font-size: 12px;
}
table {
	cellspacing="0" ;
	cellpadding="0";
	border-collapse:collapse;
	border:0px;
}
td {
	word-break:break-all;
	text-align:left;
	border:0px;
}
-->
</style>
<script>
function SetChecked(boxname) {
	f=document.form1;
	for( i=0 ; i<f.elements.length ; i++) {
		if (f.elements[i].id==boxname) {
		f.elements[i].checked=true;
		}
	}
}
function SetResverseChecked(boxname) {
	f=document.form1;
	for( i=0 ; i<f.elements.length ; i++) {
		if (f.elements[i].id==boxname) {
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
		if (f.elements[i].id==boxname) {
		f.elements[i].checked=false;
		}
	}
}

function checkForm(){
	f=document.form1;
	for( i=0 ; i<f.elements.length ; i++) {
		if (f.elements[i].name=="chk") {
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
<c:if test="${empty sessionScope.ShowShoes }"> 
 	<script>location="/back/kill!FindShoesByPage.action";</script>
</c:if> 


  <div>
    <span>�ܹ���${sessionScope.ShowShoes.totalRows } ����¼</span> ��ѡ�񣺵�
      <c:forEach var="i" begin="1" end="${sessionScope.ShowShoes.totalPages }">
        <c:if test="${sessionScope.ShowShoes.currentPage == i }"> [${i }]&nbsp; </c:if> 
        <c:if test="${sessionScope.ShowShoes.currentPage != i }"> <a href="/back/kill!FindShoesByPage.action?currentPage=${i }">[${i }]</a>&nbsp; </c:if>
      </c:forEach>
      	ҳ
      
  </div> 
  
<table width="100%"  >
<tr>
  <td width="8" rowspan="2" background="images/shadow.gif"></td>
  <td width="953" height="26" valign="middle" background="/back/images/table_bg001.gif">&nbsp;&nbsp; <img src="/back/images/b_sing.gif" width="12" height="12" align="absmiddle" />&nbsp;<font color="#999999">&nbsp;&nbsp;��ѡ������Ϊ���ֳ齱����Ʒ</font></td>
</tr>
<tr>
  <td height="640" valign="top" background="/back/images/table_bg001.gif"><div align="center" >
    <div  width="100%">
    <tabl border="0" cellspacing="0" cellpadding="0">
    <div align="center" >
  <div align="left">
    <input name="selectAll" type="button" value="��������" />
    <a href="javascript:SetChecked('chk');">ȫѡ</a> 
    <a href="javascript:SetResverseChecked('chk');">��ѡ</a>
    <a href="javascript:SetUnChecked('chk');">ȫ��ѡ</a>
    <hr color="#CCCCCC" />
  </div>
  
  <form  name="form1" method="post" action="/back/kill!BatchAddSecondKills.action?shoesid=${shoes.sid}"  onSubmit="" >
  <table width="90%" border="1" style="margin:10px auto; border-bottom:0px;"  > 
    <c:forEach items="${sessionScope.ShowShoes.data}" var="shoes" varStatus="status" begin="0" step="1"> 	
      <tr>
        <td></td>
        <td>����һ��</td>
        <td>���:${shoes.snum} </td>  
        <td>Ь��:</td>
        <td> ${shoes.sname}</td>
        <td>������:</td>
        <td> ${shoes.sproducer}</td>
        <td>ԭ��/�ּ�</td>
        <td><fmt:formatNumber value="${shoes.sprices}" pattern="0.00"/>
          /
          <fmt:formatNumber value="${shoes.sprices*shoes.sdiscount}" pattern="0.00"/></td>
      </tr>
      <tr>
        <td rowspan="2" align="center" valign="middle">
        	<input name="choseLottery"  id="chk" type="checkbox" />
          &nbsp;</td>
        <td rowspan="2">
        	<c:if test="${requestScope.shoesimg[status.index]==null}"> 
				<img src="/back/images/empty.gif" style="max-width:50px; max-height:50px;"/>
			</c:if>
			<c:if test="${requestScope.shoesimg[status.index]!=null}"> 
        		<img src="/back/upload/img/${requestScope.shoesimg[status.index]}"  style="max-width:50px; max-height:50px;"/>
        	</c:if> 
        </td>
        <td><br /></td> 
        <td>�Ա�:</td>
        <td>
        	<c:if test="${shoes.sgender eq '��'}">��Ь </c:if>
          	<c:if test="${shoes.sgender eq 'Ů'}">ŮЬ </c:if></td>
        <td>Ʒ �ƣ�</td>
        <td> ${shoes.brands.bname}</td>
        <td>����ʱ��</td>
        <td> ${shoes.spubtime}</td>
      </tr>
      <tr >
        <td > 
          <a href="/back/kill!AddSecondKills.action?shoesid=${shoes.sid}">���Ϊ��ɱ��Ʒ</a>
        </td>
        <td>��ɫ:</td>
        <td> ${shoes.scolor}</td>
        <td>�� �ͣ�</td>
        <td>${shoes.types.tname}</td>
        <td>�����ȶ�</td>
        <td>${shoes.stimessold}(�˴�)</td>
      </tr> 
      <tr> <td colspan="3"><br/> <br/> </td> </tr>
	
    </c:forEach>
  </table>
</form>
  <!----------------------�����ⲿ��------------------------------>
</body>
</html>
