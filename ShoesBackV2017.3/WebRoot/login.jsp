<%@ page language="java" import="java.util.*" pageEncoding="Utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>后台登录界面</title>

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/signin.css" rel="stylesheet">
<script type="text/javascript">
var count = 0;
function changeCode(){    	    
    var code = document.getElementById("from_validate_img");
    code.src="ValidateCodeServlet?msg="+(count++);
}</script>
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
</head>

<body>

	<div class="signin">
		<div class="signin-head">
			<img src="images/test/head_120.png" alt="" class="img-circle">
		</div>
		<form class="form-signin" role="form" action="admin.action"
			method="post" onSubmit="return checkForm()">
			<div>
				<INPUT id=__EVENTTARGET type=hidden name=__EVENTTARGET> <INPUT
					id=__EVENTARGUMENT type=hidden name=__EVENTARGUMENT> <INPUT
					id=__VIEWSTATE type=hidden
					value=/wEPDwUKLTMxMzU2OTkzM2QYAQUeX19Db250cm9sc1JlcXVpcmVQb3N0QmFja0tleV9fFgEFA2J0bmF2stSMyj1cbWFEH2tzan/b7XAS
					name=__VIEWSTATE>
			</div>
			<input type="text" class="form-control" id="aacount" name="acount"
				placeholder="用户名" required autofocus /> <input type="password"
				class="form-control" name="apwd" placeholder="密码" required /> <input
				type="text" id="from_validate" name="validate" placeholder="验证码" required />&nbsp;<img
				id="from_validate_img" src="ValidateCodeServlet" align="middle" />&nbsp;<a
				href="javascript:changeCode();"><font id="font_felsh">刷新</font></a>
			<button class="btn btn-lg btn-warning btn-block" type="submit">登录</button>

		</form>
	</div>

	<div style="text-align: center;"></div>
</body>
</html>
