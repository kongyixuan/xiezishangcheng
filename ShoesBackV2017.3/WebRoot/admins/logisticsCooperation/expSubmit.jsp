<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<title>ExpSpot�ύ</title>
<style type="text/css">
<!--
div {
	height: 455px;
	width: 535px;
	font-family: "����";
	font-weight: normal;
	color: #900;
	font-size: medium;
	font-style: normal;
	font-variant: normal;
	text-decoration: blink;
	border: medium dotted #C90;
	cursor: auto;
	background-color: #FFC;
}
.inputUse {
	background-color: #FFC;
	border-bottom-width: 1px;
	border-bottom-style: solid;
	border-bottom-color: #900;
	color: #0CF;
	font-size:25px;
	font-weight:bold;
	font-family: "��Բ";
	border-top-style: none;
	border-right-style: none;
	border-left-style: none;
	text-align: center;
}
.buttonUse {
	font-family: "����";
	font-size: 16px;
	color: #F60;
	text-decoration: blink;
	font-weight: lighter;
	border-right-width: 2px;
	border-right-style: groove;
	border-right-color: #900;
	border-bottom-width: 2px;
	border-bottom-style: groove;
	border-bottom-color: #900;
	background-color: #FFC;
}
.titleUse {
	color: #F90;
	font-weight: bold;
	font-family: "΢���ź�";
	font-size: x-large;
}

-->
</style>
<script type="text/javascript">
function check(){
	var eleInfo=document.getElementById("info");
	var strInfo=eleInfo.value;
	strInfo=strInfo.replace(/\?/g,"-");
	strInfo=strInfo.replace(/ /g,"_");
	strInfo=strInfo.replace(/\&/g,"-");
	eleInfo.value=strInfo;
	
	//var stroid=document.getElementById("oid").value;
	//var strlat=document.getElementById("lat").value;
	//var strlng=document.getElementById("lng").value;
	
	//var uri="http://localhost:5311/b2c_shoes/servlet/ExpSpotServlet?oid="+stroid+"&lat="+strlat+"&lng="+strlng+			            "&info="+strInfo;
	//alert (uri);
	
	//window.location.href=uri;
	
	return true;
}
</script>
</head>

<body>
<div>
  <form id="form1" name="form1" method="get" action="/back/order!SaveExpSpot.action" onsubmit="return check()">
    <table width="535" height="455" border="0">
      <tr>
        <td colspan="3" align="center" class="titleUse">���������ڵ���Ϣ</td>
      </tr>
      <tr>
        <td width="28%" align="right">������ţ�</td>
        <td width="38%" align="center"><label>
          <input name="oid" type="text" class="inputUse" id="oid" readonly="readonly" value="${requestScope.nowId }"/>
        </label></td>
        <td width="34%">&nbsp;</td>
      </tr>
      <tr>
        <td align="right">��γγ�ȣ�</td>
        <td align="center"><label>
          <input name="info" type="text" class="inputUse" id="lat" />
        </label></td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td align="right">�������ȣ�</td>
        <td align="center"><label>
          <input name="info" type="text" class="inputUse" id="lng" />
        </label></td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td align="right">����˵����</td>
        <td align="center"><input name="info" type="text" class="inputUse" id="info" /></td>
        <td valign="bottom"><input name="submit" type="submit" class="buttonUse" id="submit" onclick="" value="�ύ�����ڵ�" /></td>
      </tr>
      <tr>
        <td colspan="3" align="right">&nbsp;</td>
      </tr>
    </table>
  </form>
</div>
</body>
</html>

