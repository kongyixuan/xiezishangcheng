<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"  %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<!-- saved from url=(0036)http://localhost:10868/hr/login.aspx -->
<!-- saved from url=(0041)http://www.youhao.com/manage/YHLogin.aspx -->
<html>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<STYLE type=text/css>BODY {
	FONT-SIZE: 12px; COLOR: #ffffff; FONT-FAMILY: 宋体
}
TD {
	FONT-SIZE: 12px; COLOR: #ffffff; FONT-FAMILY: 宋体
}
</STYLE>

<META content="MSHTML 6.00.6000.16809" name=GENERATOR>
<script type="text/javascript">
function checkForm(){
	if(form1.aacount.value.length==0){
		alert("请输入账号！");
		form1.aacount.focus();
		return false;
	}
	if(form1.apwd.value.length==0){
		alert("请输入密码！");
		form1.apwd.focus();
		return false;
	}
	if(form1.validate.value.length==0){
		alert("请输入验证码！");
		form1.validate.focus();
		return false;
	}
}
</script>
</HEAD>
<BODY>
<FORM id="form1" name="form1" action="admin.action" method="post" onSubmit="return checkForm()">
<DIV><INPUT id=__EVENTTARGET type=hidden name=__EVENTTARGET> <INPUT 
id=__EVENTARGUMENT type=hidden name=__EVENTARGUMENT> <INPUT id=__VIEWSTATE 
type=hidden 
value=/wEPDwUKLTMxMzU2OTkzM2QYAQUeX19Db250cm9sc1JlcXVpcmVQb3N0QmFja0tleV9fFgEFA2J0bmF2stSMyj1cbWFEH2tzan/b7XAS 
name=__VIEWSTATE> </DIV>

<SCRIPT src="login_files/WebResource.axd" type=text/javascript></SCRIPT>

<SCRIPT src="login_files/WebResource(1).axd" type=text/javascript></SCRIPT>

<SCRIPT src="login_files/ScriptResource.axd" type=text/javascript></SCRIPT>

<SCRIPT src="login_files/ScriptResource(1).axd" type=text/javascript></SCRIPT>

<DIV id=UpdatePanel1>
<DIV id=div1 
style="LEFT: 0px; POSITION: absolute; TOP: 0px; BACKGROUND-COLOR: #0066ff"></DIV>
<DIV id=div2 
style="LEFT: 0px; POSITION: absolute; TOP: 0px; BACKGROUND-COLOR: #0066ff"></DIV>
<!-- <SCRIPT language=JavaScript> 
var speed=20;
var temp=new Array(); 
var clipright=document.body.clientWidth/2,clipleft=0;
for (i=1;i<=2;i++){ 
	temp[i]=eval("document.all.div"+i+".style");
	temp[i].width=document.body.clientWidth/2;
	temp[i].height=document.body.clientHeight;
	temp[i].left=(i-1)*parseInt(temp[i].width);
} 
function openit(){ 
	clipright-=speed;
	temp[1].clip="rect(0 "+clipright+" auto 0)";
	clipleft+=speed;
	temp[2].clip="rect(0 auto auto "+clipleft+")";
	if (clipright<=0)
		clearInterval(tim);
} 
tim=setInterval("openit()",100);
var count = 0;
function changeCode(){    	    
    var code = document.getElementById("code");
    code.src="ValidateCodeServlet?msg="+(count++);
}
</SCRIPT> -->

<DIV>&nbsp;&nbsp; </DIV>
<DIV>
<TABLE cellSpacing=0 cellPadding=0 width=900 align=center border=0>
  <TBODY>
  <TR>                                                           <!-- w -->
    <TD style="HEIGHT: 105px"><IMG src="login_files/website_logo.gif"
  border=0></TD></TR>
  <TR>
    <TD background="login_files/login_2.jpg" height=300>
      <TABLE height=300 cellPadding=0 width=900 border=0>
        <TBODY>
        <TR>
          <TD colSpan=2 height=35></TD></TR>
        <TR>
          <TD width=360></TD>
          <TD>
            <TABLE cellSpacing=0 cellPadding=2 border=0>
              <TBODY>
              <TR>
                <TD style="HEIGHT: 28px" width=80>登 录 名：</TD>
                <TD style="HEIGHT: 28px" width=150><INPUT id="aacount" name="acount" 
                  style="WIDTH: 130px"></TD>
                <TD style="HEIGHT: 28px" width=370></TD>
              </TR>
              <TR>
                <TD style="HEIGHT: 28px">登录密码：</TD>
                <TD style="HEIGHT: 28px"><INPUT id="apwd" style="WIDTH: 130px" 
                  type=password name="apwd"></TD>
                <TD style="HEIGHT: 28px"></TD>
              </TR>
              <TR>
                <TD style="HEIGHT: 28px">验证码：</TD>
                <TD style="HEIGHT: 28px;width: 200px"><input type="text" name="validate" id="validate" size="5"/>&nbsp;<img id="code" src="ValidateCodeServlet" align="middle"/>&nbsp;<a href="javascript:changeCode();"><font color="black">换一张</font></a></TD>
                <TD style="HEIGHT: 28px">&nbsp;</TD>
              </TR>
              <TR>
                <TD style="HEIGHT: 18px"></TD>
                <TD style="HEIGHT: 18px"><span style="HEIGHT: 28px">
                  <input id="btnLogin" style="BORDER-TOP-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-RIGHT-WIDTH: 0px" type="submit" value="登录">
                </span></TD>
                <TD style="HEIGHT: 18px"></TD>
              </TR>
              <TR>
                <TD></TD>
                <TD>&nbsp;</TD>
              </TR>
             </TBODY>
            </TABLE></TD></TR></TBODY></TABLE></TD></TR>
  <TR>
    <TD><IMG src="login_files/login_3.jpg" border=0></TD></TR></TBODY></TABLE></DIV></DIV>
</FORM>

</BODY></HTML>
