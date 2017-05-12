<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"  %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>ModifyAdmin.jsp</title>
		<style type="text/css">
<!--
body {
	background-color: #e6e6e6;
}

body,td,th {
	font-size: 16px;
}

.STYLE1 {
	font-size: 16px
}
-->
</style>
		<script type="text/javascript">
		function blank(){ 
  			document.form1.sqanswer.value = "";
		}
		function checkForm(){
			if(form1.sqanswer.value.length==0){
				alert("请勿输入空的回复内容！");
				form1.sqanswer.focus();
				return false;
			}
		}
	</script>
	</head>

	<body>
		<form method="post" name="form1" action="/back/inquiry!UpdateResponse.action" onSubmit="return checkForm()">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="8" rowspan="3" background="/back/images/shadow.gif">
					</td>
					<td width="943" height="30" valign="middle"	background="/back/images/table_bg001.gif">						&nbsp;&nbsp;
						<img src="/back/images/b_sing.gif" width="12" height="12" align="middle" />						&nbsp;
						<font color="#999999">&nbsp;&nbsp; 提示信息及功能说明:回复客户提出的问题</font>
					</td>
				</tr>
				<!----------------------表格标题部分------------------------------>
				<tr>
					<td height="580" valign="top"	background="/back/images/table_bg001.gif"	style="padding: 5px;">
						<table width="100%" border="0" cellspacing="1" cellpadding="0"	align="center">
							<tr>
								<td colspan="5">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="6%">
												<img src="/back/images/b_table_header_left.gif"	width="58" height="31" />
											</td>
											<td width="92%"	background="/back/images/b_table_title_bg.gif">
												<div style="text-align: center; font: 12px; color: #FFF; font-weight: bold;">
													回复客户提出的问题
												</div>
											</td>
											<td width="2%">
												<img src="/back/images/b_table_header_right.gif" width="58" height="31" />
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td colspan="5">
									<p>				&nbsp;
									</p>
									<p>		&nbsp;
									</p>
									<p>		&nbsp;
									</p>
									<input type="hidden" id="sqid" name="sqid"	value="${inquiry.sqid }" />
									<table width="80%" border="0" align="center" cellpadding="0"	cellspacing="1">
										<tr>
											<td width="25%" height="35" align="right"	background="/back/images/b_table_header.gif">
												<span class="STYLE1">鞋子名称:</span>
											</td>
											<td width="75%" height="35"	background="/back/images/b_table_header.gif">
												${inquiry.shoes.sname }
											</td>
										</tr>
										<tr>
											<td width="25%" height="35" align="right"	background="/back/images/b_table_header.gif">
												<span class="STYLE1">问题详情：</span>
											</td>
											<td width="75%" height="35"	background="/back/images/b_table_header.gif">
												${inquiry.sqquestion }
											</td>
										</tr>
										<tr>
											<td width="25%" height="35" align="right" background="/back/images/b_table_header.gif">
												提问时间：
											</td>
											<td width="75%" height="35" background="/back/images/b_table_header.gif">
												${inquiry.sqquestiontime}
											</td>
										</tr>
										<tr>
											<td width="25%" height="35" align="right" background="/back/images/b_table_header.gif">
												回复内容：
											</td>
											<td width="75%" height="35"	background="/back/images/b_table_header.gif">
												<input type="text" name="sqanswer" id="sqanswer" value="${inquiry.sqanswer}" />
											</td>
										</tr>										
										<tr>
											<td height="35" colspan="2" align="center" background="/back/images/b_table_header.gif">
												<input type="submit" id="btnModify" name="btnModify" value="回复">	&nbsp;&nbsp;&nbsp;
												<input type="button" id="btnReset" name="btnReset"	value="清空" onClick="blank()">
											</td>
										</tr>
									</table>
								<td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>