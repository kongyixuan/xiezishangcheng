<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"  %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>AddSpshoes.jsp</title>
    <style type="text/css">
		<!--
		body {
			background-color: #e6e6e6;
		}
		body,td,th {
	font-size: 16px;
		}
.STYLE1 {font-size: 16px}
		-->
	</style>
    <script type="text/javascript">
    	function blank(){ 
  			document.form1.spsseq.value = "";
			document.form1.spsname.value = "";
			document.form1.spsprice.value = "";
			document.form1.spspartnum.value = "";
			document.form1.spspartinfo.value = "";
			document.form1.spscss.value = "";
			document.form1.spscontent.value = "";
		}
		function checkForm(){
			if(form1.spsseq.value.length==0){
				alert("请输入该鞋的序列号！");
				form1.spsseq.focus();
				return false;
			}
			if(form1.spsname.value.length==0){
				alert("请输入该鞋的名称！");
				form1.spsname.focus();
				return false;
			}
			if(form1.spsprice.value.length==0){
				alert("请输入该鞋的价格！");
				form1.spsprice.focus();
				return false;
			}
			if(form1.spspartnum.value.length==0){
				alert("请输入该鞋的部件个数！");
				form1.spspartnum.focus();
				return false;
			}
			if(form1.spspartinfo.value.length==0){
				alert("请输入该鞋的部件信息！");
				form1.spspartinfo.focus();
				return false;
			}
			
			if(form1.spscss.value.length==0){
				alert("请输入该鞋的CSS信息！");
				form1.spscss.focus();
				return false;
			}			
		}
		// 复选框全选方法
	function SetChecked(boxname) {
		f = document.form1;
		for (i = 0; i < f.elements.length; i++) {
			if (f.elements[i].name == boxname) {
				f.elements[i].checked = true;
			}
		}
	}
	// 复选框反选方法
	function SetReverseChecked(boxname) {
		f = document.form1;
		for (i = 0; i < f.elements.length; i++) {
			if (f.elements[i].name == boxname) {
				if(f.elements[i].checked == true){
					f.elements[i].checked = false;
				}else{
					f.elements[i].checked = true;
				}
			}
		}
	}
	// 复选框全不选方法
	function SetUnChecked(boxname) {
		f = document.form1;
		for (i = 0; i < f.elements.length; i++) {
			if (f.elements[i].name == boxname) {
				f.elements[i].checked = false;
			}
		}
	}
	function submitspcify(){
	    var ss=document.getElementById("form1");
	    if(confirm("确定提交新增定制鞋?")){
	       ss.submit();
	    }	    
	}
	</script>
   </head>
  
<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="8" rowspan="3" background="/back/images/shadow.gif">
    <br></td>
    <td width="943" height="30" valign="middle" background="/back/images/table_bg001.gif"> &nbsp;&nbsp; <img src="/back/images/b_sing.gif" width="12" height="12" align="absmiddle" />&nbsp;<font color="#999999">&nbsp;&nbsp; 提示信息及功能说明:添加一种新的鞋</font></td>
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
                	添加一种新的鞋
                </div>
            </td>
            <td width="2%"><img src="/back/images/b_table_header_right.gif" width="58" height="31" /></td>
          </tr>
        </table>
        </td>
      </tr>
      <tr><td align="center">
      <br/>
      <br/>
      <br/>	
      	<form id="form1" name="form1" method="post" action="/back/spcify!SaveSpcifyShoes.action" enctype="multipart/form-data" onSubmit="return checkForm();">
    	<table width="500" border="0" align="center" cellpadding="0" cellspacing="1">
            <tr>
              <td align="right" height="30" background="/back/images/b_table_header.gif">鞋子类型：</td>
              <td height="30" background="/back/images/b_table_header.gif">
              <select name="tid" id="tid">             
    	      <c:forEach items="${sessionScope.lstTypes }" var="type">
    	        <option value="${type.tid }">${type.tname }</option>
    	        </c:forEach>
  	        </select></td>
            </tr>
            <tr>
              <td align="right" height="30" background="/back/images/b_table_header.gif">鞋子品牌：</td>
              <td height="30" background="/back/images/b_table_header.gif">
              <select name="bid" id="bid">              
    	      <c:forEach items="${sessionScope.lstBrands }" var="brand">
    	        <option value="${brand.bid }">${brand.bname }</option>
    	        </c:forEach>
  	        </select></td>
            </tr>
            <tr>
              <td align="right" height="30" background="/back/images/b_table_header.gif">序列号：</td>
              <td height="30" background="/back/images/b_table_header.gif"><input type="text" name="spsseq" id="spsseq"/></td>
            </tr>
            <tr>
              <td align="right" height="30" background="/back/images/b_table_header.gif">鞋子名称：</td>
              <td height="30" background="/back/images/b_table_header.gif"><input type="text" name="spsname" id="spsname"/></td>
            </tr>
            <tr>
              <td align="right" height="30" background="/back/images/b_table_header.gif">鞋子价格：</td>
              <td height="30" background="/back/images/b_table_header.gif"><input type="text" name="spsprices" id="spsprices"/></td>
            </tr>
            <tr>
              <td align="right" height="30" height="30" background="/back/images/b_table_header.gif">部件个数：</td>
              <td height="30" background="/back/images/b_table_header.gif"><input type="text" name="spspartnum" id="spspartnum"/></td>
            </tr>
            <tr>
              <td align="right" height="30" background="/back/images/b_table_header.gif">部件信息：</td>
              <td height="30" background="/back/images/b_table_header.gif"><input type="text" name="spspartinfo" id="spspartinfo"/></td>
            </tr>
            <tr>
              <td align="right" height="30" background="/back/images/b_table_header.gif">男女款式：</td>
              <td height="30" background="/back/images/b_table_header.gif">
              	<input type="radio" name="spsgender" id="sgender1" value="男" checked="checked">男
				<input type="radio" name="spsgender" id="sgender2" value="女">女              
              </td>
            </tr>
            
             <tr>
              <td align="right" height="30" background="/back/images/b_table_header.gif">鞋子CSS：</td>
              <td height="30" background="/back/images/b_table_header.gif"><input type="text" name="spscss" id="spscss"/></td>
            </tr>
            <tr>
              <td align="right" height="30" background="/back/images/b_table_header.gif">详细信息：</td>
              <td height="30" background="/back/images/b_table_header.gif"><textarea name="spscontent" rows="5" class="linput" id="spscontent"></textarea></td>
            </tr>
		 <tr>
            	<td align="right" height="30" background="/back/images/b_table_header.gif">
            		选择尺寸:<br/>
            		<p>&nbsp;</p>
            		<a href="javascript:;" onclick="SetChecked('chk');return false;"><font color="black">全选</font></a>&nbsp;&nbsp;<br/>
            		<p>&nbsp;</p>
            		<a href="javascript:;" onclick="SetReverseChecked('chk');return false;"><font color="black">反选</font></a>&nbsp;&nbsp;<br/>
            		<p>&nbsp;</p>
            		<a href="javascript:;" onclick="SetUnChecked('chk');return false;"><font color="black">清空</font></a>&nbsp;&nbsp;
            	</td>
            	<td  height="30" background="/back/images/b_table_header.gif">
            		<c:forEach items="${sessionScope.lstSizes }" var="size">
            			 <input type="checkbox" name="chk" id="chk" value="${size.sizeid}"/>&nbsp;${size.sizenum}<br/>
            		</c:forEach>
           		</td>
            </tr>
            <tr>           
            <td height="35" colspan="2" align="center" style="background: url('/back/images/b_table_header.gif');" >
                    	<input type="button" onclick="submitspcify()" id="btnModify" name="btnModify" value="添加">&nbsp;&nbsp;&nbsp;
                    	<input type="reset" id="btnReset" name="btnReset" value="重置" onClick="blank()">
                    </td>
            </tr>
          </table>
    	</form>
    	</td>
      </tr>
    </table>
    </td>
  </tr>
</table>
  </body>
</html>
