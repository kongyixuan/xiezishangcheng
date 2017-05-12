<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>addEnterpriseCertification.jsp</title>
    <style type="text/css">
		<!--
		body {
			background-color: #e6e6e6;
			font-size: 12px;
		}
		body,td,th {
	font-size: 16px;
		}
.STYLE1 {font-size: 16px}
		-->
	</style>
	
	<script type="text/javascript">
	function checkForm(){
			if(form1.busiName.value.length==0){
				alert("请输入公司名称！");
				form1.busiName.select();
				return false;
			}
			if(form1.busiNo.value.length==0){
				alert("请输入企业编号！");
				form1.busiNo.select();
				return false;
			}
			if(form1.acctName.value.length==0){
				alert("请输入对公账户名！");
				form1.acctName.select();
				return false;
			}
			if(form1.acctNO.value.length==0){
				alert("请输入对公账号！");
				form1.acctNO.select();
				return false;
			}
			if(form1.bankNo.value.length==0){
				alert("请输入清算联行号！");
				form1.bankNo.select();
				return false;
			}
			if(form1.uploadImage.value.length==0){
				alert("请上传营业执照！");
				form1.uploadImage.select();
				return false;
			}
		}
		</script>
		
		<!-- 图片上传预览 -->
<script type="text/JavaScript">
function setImageView(){
	var docObj=document.getElementById("uploadImage");
	var fileName=docObj.value;
	var position=fileName.lastIndexOf('.');
	var reg=fileName.substring(position+1); 
	if(reg=='jpg'||reg=='gif'||reg=='bmp'){
		var imgObjPreview=document.getElementById("preview");
		if (docObj.files && docObj.files[0]) {
			//火狐下，直接设img属性 
			imgObjPreview.style.display = 'block';
			imgObjPreview.style.width = '100px';
			imgObjPreview.style.height = '100px';
			//imgObjPreview.src = docObj.files[0].getAsDataURL(); 
			//火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式 
			imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]);
		} else {
			//IE下，使用滤镜 
			docObj.select();
			var imgSrc = document.selection.createRange().text;
			var localImagId = document.getElementById("localImag");
			//必须设置初始大小 
			localImagId.style.width = "100px";
			localImagId.style.height = "100px";
			//图片异常的捕捉，防止用户修改后缀来伪造图片 
			try {
				localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
				localImagId.filters
						.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
			} catch (e) {
				alert("您上传的图片格式不正确，请重新选择!");
				return false;
			}
			imgObjPreview.style.display = 'none';
			document.selection.empty();
		}
		return true;
	} else {
		alert('请选择正确的图片格式');
		return;
	}
}
</script>
	 <!-- function fillYear() {
		var year = document.getElementById("year");
		for ( var i = 1980; i <= 2020; i++)
			year.options.add(new Option(i, i));
		fillMonth();
	}
	function fillMonth() {
		var month = document.getElementById("month");
		for ( var i = 1; i <= 12; i++)
			month.options.add(new Option(i, i));
		fillDay();
	}
	function fillDay() {
		var year = document.getElementById("year"); //获取年份下拉列表
		var month = document.getElementById("month"); //获取月份下拉列表
		var day = document.getElementById("day"); //获取日期下拉列表
		day.options.length = 0; //清空下拉框
		var indexOfYear = year.selectedIndex + 1980;
		var indexOfMonth = month.selectedIndex + 1;
		if (indexOfMonth == 1 || indexOfMonth == 3 || indexOfMonth == 5
				|| indexOfMonth == 7 || indexOfMonth == 8 || indexOfMonth == 10
				|| indexOfMonth == 12) {
			for ( var i = 1; i <= 31; i++)
				day.options.add(new Option(i, i));

		} else if (indexOfMonth == 4 || indexOfMonth == 6 || indexOfMonth == 9
				|| indexOfMonth == 11) {
			for ( var i = 1; i <= 30; i++)
				day.options.add(new Option(i, i));
		} else if ((indexOfYear % 400 == 0)
				|| ((indexOfYear % 4 == 0) && (indexOfYear % 100 != 0))) {
			for ( var i = 1; i <= 29; i++)
				day.options.add(new Option(i, i));
		} else {
			for ( var i = 1; i <= 28; i++)
				day.options.add(new Option(i, i));
		}
	}
	window.onload = function() {
		//fillYear();
	};  -->
   </head>
  
  <body>
    
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="8" rowspan="3" background="/back/images/shadow.gif">
    </td>
    <td width="943" height="30" valign="middle" background="/back/images/table_bg001.gif"> &nbsp;&nbsp; <img src="/back/images/b_sing.gif" width="12" height="12" align="middle" />&nbsp;<font color="#999999">&nbsp;&nbsp; 提示信息及功能说明:添加一个新的管理员</font></td>
  </tr>
   <!----------------------表格标题部分------------------------------>
  <tr>
    <td height="580" valign="top" background="/back/images/table_bg001.gif" style="padding:5px;">
    <table width="100%" border="0" cellspacing="1" cellpadding="0" align="center">
      <tr>
        <td colspan="5"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="6%"><img src="/back/images/b_table_header_left.gif" width="58" height="31" /></td>
            <td width="92%" background="/back/images/b_table_title_bg.gif">
            	<div style="text-align:center; font:12px; color:#FFF; font-weight:bold;">
                	添加认证
                </div>
            </td>
            <td width="2%"><img src="/back/images/b_table_header_right.gif" width="58" height="31" /></td>
          </tr>
        </table>
        </td>
      </tr>
      <tr>
      	<td colspan="5" style="text-align:center; vertical-align:top">
        	<s:form id="form1" action="/back/enterpriseCertification!SaveEnterpriseCertification.action" method="post" enctype="multipart/form-data" name="form1" onSubmit="return checkForm();">
        		<s:textfield name="busiName" id="busiName" label="企业名称"/><br/>
        		<s:textfield name="busiNo" id="busiNo" label="企业编号"/><br/>
        		<s:textfield name="acctName" id="acctName" label="对公账户名"/><br/>
        		<s:textfield name="acctNo" id="acctNO" label="对公账号"/><br/>
        		<s:textfield name="bankNo" id="bankNo" label="清算联行号"/><br/>
        		<s:textfield name="busiremarks" id="busiremarks" label="备注"/><br/>
        		<%--  <tr>
              <td align="right" height="30" id="localImage" ><img id="preview" style="display:none"/></td>
              <td height="30" >
              	<select name="year" id="year" onchange="fillDay()"></select>
            	<select name="month" id="month" onchange="fillDay()"></select>
            	<select name="day" id="day"></select></td>
            </tr><br/> --%>
              
        		<s:file name="uploadImage" id="uploadImage" label="营业执照" onchange="javascript:setImageView();"/>
        		<br/>
        		<td align="left" height="100" id="localImage" >图片预览:<img id="preview" style="display:none"/></td>
        		<td align="left" height="30"><s:submit value="添 加"></s:submit></td>
        	</s:form>
     <td>
      </tr>
    </table>
    </td>
  </tr>
</table>
<!-- <div id="localImage" ><img id="preview" style="display:none"/></div> -->
</body>
</html>
