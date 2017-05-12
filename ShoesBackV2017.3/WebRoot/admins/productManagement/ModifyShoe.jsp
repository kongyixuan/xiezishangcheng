<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"  %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>AddAdmin.jsp</title>
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
   
	function fillYear() {
		var year = document.getElementById("year");
		for ( var i = 1980; i <= 2015; i++)
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
	function checkForm() {
		if (form1.snum.value.length == 0) {
			alert("请输入该鞋的序列号！");
			form1.snum.focus();
			return false;
		}
		if (form1.sname.value.length == 0) {
			alert("请输入该鞋的名称！");
			form1.sname.focus();
			return false;
		}
		if (form1.sprice.value.length == 0) {
			alert("请输入该鞋的价格！");
			form1.sprice.focus();
			return false;
		}
		if (form1.sdiscount.value.length == 0) {
			alert("请输入该鞋的折扣！");
			form1.sdiscount.focus();
			return false;
		}
		if (form1.sproducer.value.length == 0) {
			alert("请输入该鞋的生产商！");
			form1.sproducer.focus();
			return false;
		}
		if (form1.gender1.checked == false && form1.gender2.checked == false) {
			alert("请选择性别款式！");
			return false;
		}
		if (form1.scolor.value.length == 0) {
			alert("请输入该鞋的颜色！");
			form1.scolor.focus();
			return false;
		}
		if (form1.stimessold.value.length == 0) {
			alert("请输入该鞋所卖出的次数！");
			form1.stimessold.focus();
			return false;
		}
		if (form1.sintegral.value.length == 0) {
			alert("请输入该鞋的积分倍数！");
			form1.sintegral.focus();
			return false;
		}

	}
	function checkCount(sc){
	    var sv=document.getElementById(sc.id+"inp");
	    var s=document.getElementById(sc.id);
	    if(s.checked==true){
	        sv.style.display="block";
	    }else{
	        sv.style.display="none";
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
	function checkModify(){
	    var form =document.getElementById("form1");
	    if(confirm("确认更新鞋子信息?")){
	        form.submit();
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
	
	window.onload = function() {
		fillYear();
		
		var old_tno = document.getElementById("old_tno").value;
		var old_bno = document.getElementById("old_bno").value;
		var yyyy = document.getElementById("yyyy").value;
		var mm = document.getElementById("mm").value;
		var dd = document.getElementById("dd").value;
		var old_sgender = document.getElementById("old_sgender").value;
		var tid = document.getElementById("tid");
		var bid = document.getElementById("bid");
		var year = document.getElementById("year");
		var month = document.getElementById("month");
		var day = document.getElementById("day");
		var gender1 = document.getElementById("gender1");
		var gender2 = document.getElementById("gender2");

		for ( var i = 0; i < tid.options.length; i++) {
			if (old_tno == tid.options[i].value) {
				tid.options[i].selected = "selected";
			}
		}
		for ( var i = 0; i < bid.options.length; i++) {
			if (old_bno == bid.options[i].value) {
				bid.options[i].selected = "selected";
			}
		}
		for ( var i = 0; i < year.options.length; i++) {
			if (yyyy == year.options[i].value) {
				year.options[i].selected = "selected";
			}
		}
		for ( var i = 0; i < month.options.length; i++) {
			if (mm == month.options[i].value) {
				month.options[i].selected = "selected";
			}
		}
		fillDay();
		for ( var i = 0; i < day.options.length; i++) {
			if (dd == day.options[i].value) {
				day.options[i].selected = "selected";
				return;
			}
		}
	};
</script>
   <meta http-equiv="Content-Type" content="text/html; charset=gb2312"></head>
  
<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="8" rowspan="3" background="/back/images/shadow.gif">
    <br></td>
    <td width="943" height="30" valign="middle" background="/back/images/table_bg001.gif"> &nbsp;&nbsp; <img src="/back/images/b_sing.gif" width="12" height="12" align="middle" />&nbsp;<font color="#999999">&nbsp;&nbsp; 提示信息及功能说明:更新一种鞋</font></td>
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
                	更新一种鞋
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
      	<s:form id="form1" name="form1" method="post" action="/back/shoe!ModifyShoes.action" enctype="multipart/form-data" onSubmit="return checkForm();">
        <input type="hidden" name="old_tno" id="old_tno" value="${shoe.types.tid }"/>
    	<input type="hidden" name="old_bno" id="old_bno" value="${shoe.brands.bid }"/>
    	<input type="hidden" name="sid" id="sid" value="${shoe.sid }"/>
    	<input type="hidden" name="old_sgender" id="old_sgender" value="${shoe.sgender}"/>
    	<input type="hidden" name="yyyy" id="yyyy" value="${requestScope.yyyy}"/>
    	<input type="hidden" name="mm" id="mm" value="${requestScope.mm}"/>
    	<input type="hidden" name="dd" id="dd" value="${requestScope.dd}"/>
    	 
    	  <table width="500"border="0" align="center" cellpadding="0" cellspacing="1">
            <tr>
              <td align="right" height="30" background="/back/images/b_table_header.gif">鞋子类型：</td>
              <td height="30" background="/back/images/b_table_header.gif">
              <select name="tid" id="tid">
    	      <c:forEach items="${sessionScope.lstTypes}" var="type">
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
              <td align="right" height="30" background="/back/images/b_table_header.gif">鞋子编号：</td>
              <td height="30" background="/back/images/b_table_header.gif">
                <input type="text" name="snum" id="snum" value="${requestScope.shoe.snum }"/>
                <input type="hidden" name="simage" id="simage" value="${requestScope.shoe.simage}" />
               </td>
            </tr>
            <tr>
              <td align="right" height="30" background="/back/images/b_table_header.gif">鞋子名称：</td>
              <td height="30" background="/back/images/b_table_header.gif"><input type="text" name="sname" id="sname" value="${requestScope.shoe.sname }"/></td>
            </tr>
            <tr>
              <td align="right" height="30" background="/back/images/b_table_header.gif">鞋子价格：</td>
              <td height="30" background="/back/images/b_table_header.gif"><input type="text" name="sprices" id="sprices" value="${requestScope.shoe.sprices}"/></td>
            </tr>
            <tr>
              <td align="right" height="30" background="/back/images/b_table_header.gif">现价：</td>
              <td height="30" background="/back/images/b_table_header.gif"><input type="text" name="sdiscount" id="sdiscount" value="${requestScope.shoe.sdiscount }"/></td>
            </tr>
            <tr>
              <td align="right" height="30" background="/back/images/b_table_header.gif">发布时间：</td>               
              <td height="30" background="/back/images/b_table_header.gif">
              	<select name="year" id="year" onchange="fillDay()"></select>
            	<select name="month" id="month" onchange="fillDay()"></select>
            	<select name="day" id="day"></select></td>
            </tr>
            <tr>
              <td align="right" height="30" background="/back/images/b_table_header.gif">生产商：</td>
              <td height="30" background="/back/images/b_table_header.gif"><input type="text" name="sproducer" id="sproducer" value="${requestScope.shoe.sproducer }"/></td>
            </tr>
            <tr>
              <td align="right" height="30" background="/back/images/b_table_header.gif">男女款式：</td>
              <td height="30" background="/back/images/b_table_header.gif">
              <c:if test="${requestScope.shoe.sgender eq '女'}">
              	<input type="radio" name="sgender" id="gender1" value="女" checked="checked">女
				<input type="radio" name="sgender" id="gender2" value="男">男    
				</c:if>      
				 <c:if test="${requestScope.shoe.sgender eq '男'}">
              	<input type="radio" name="sgender" id="gender1" value="女">女
				<input type="radio" name="sgender" id="gender2" value="男" checked="checked">男    
				</c:if>     
              </td>
            </tr>
            <tr>
              <td align="right" height="30" background="/back/images/b_table_header.gif">颜色：</td>
              <td height="30" height="30" background="/back/images/b_table_header.gif"> <input type="text" name="scolor" id="scolor" value="${requestScope.shoe.scolor }"/></td>
            </tr>
            <tr>
              <td align="right" height="30" background="/back/images/b_table_header.gif">相关信息：</td>
              <td height="30" background="/back/images/b_table_header.gif"><input type="text" name="sinfo" id="sinfo" value="${requestScope.shoe.sinfo}"/></td>
            </tr>
            <tr>
              <td align="right" height="30" background="/back/images/b_table_header.gif">卖出次数：</td>
              <td height="30" background="/back/images/b_table_header.gif"><input type="text" name="stimessold" id="stimessold" value="${requestScope.shoe.stimessold}"/></td>
            </tr>
            <tr>
              <td align="right" height="30" background="/back/images/b_table_header.gif">详细信息：</td>
              <td height="30" height="30" background="/back/images/b_table_header.gif"><input type="text" name="sdetail" id="sdetail" value="${requestScope.shoe.sdetail}"/></td>
            </tr>
            <tr>
              <td align="right" height="30" background="/back/images/b_table_header.gif">积分：</td>
              <td height="30" background="/back/images/b_table_header.gif"><input type="text" name="sintegral" id="sintegral" value="${requestScope.shoe.sintegral}"/></td>
            </tr>
            <tr>
            	<td align="right" height="30" background="/back/images/b_table_header.gif">
            	选择尺寸：<br/>
            	<p>&nbsp;</p>
            		<a href="javascript:;" onclick="SetChecked('chk');return false;"><font color="black">全选</font></a>&nbsp;&nbsp;<br/>
            		<p>&nbsp;</p>
            		<a href="javascript:;" onclick="SetReverseChecked('chk');return false;"><font color="black">反选</font></a>&nbsp;&nbsp;<br/>
            		<p>&nbsp;</p>
            		<a href="javascript:;" onclick="SetUnChecked('chk');return false;"><font color="black">清空</font></a>&nbsp;&nbsp;
            	</td>
            	<td  height="30" background="/back/images/b_table_header.gif">
            		<c:forEach items="${sessionScope.lstSizes }" var="size">
            			<c:set var="name" value="0"></c:set>
            			<c:forEach items="${sessionScope.lstSizeid }" var="sizeid">
            				<c:if test="${name == 0 }">
   								<c:if test="${size.sizenum == sizeid.sizes.sizenum}" var="condition" scope="page">
   									<input type="checkbox" name="chk" id="${size.sizeid }" onclick="checkCount(this)" value="${size.sizeid }" checked="checked"/>&nbsp;${size.sizenum}
   									&nbsp;num:<input name="inp" id="${size.sizeid }inp"  value="${sizeid.scount}" size="2" style="display: block;" /><br/>
   									<c:set var="name" value="1"></c:set>
   								</c:if>         	
   							</c:if>		
            			</c:forEach>
            			<c:if test="${name!=1 }">
            			 <input type="checkbox" name="chk" id="${size.sizeid }" onclick="checkCount(this)" value="${size.sizeid }"/>&nbsp;${size.sizenum}
            			 &nbsp;num:<input name="inp" id="${size.sizeid }inp"  value="0" size="2" style="display: none;" /><br/>
            			</c:if>
            		</c:forEach>
           		</td>
            </tr>
            <tr>
              <s:file name="uploadImage" id="uploadImage" label="继续上传图片" />	
              </tr>
            <tr>
              <td colspan="2" height="30" background="/back/images/b_table_header.gif">
              <input onclick="checkModify()" type="button" value="确认更改" />
              </td>
              </tr>
          </table>
      	  <p>&nbsp;</p>
      	  
      	  <table width="542" border="0" id="table1" >      	  
      	  <c:if test="${!empty requestScope.lstImage }">
      	  <c:forEach items="${requestScope.lstImage }" var="image">
            <tr>
              <td width="330"><img src="/back/upload/img/${image}" alt="" name="picture" width="322" height="325" align="middle"></td>
              <td width="202"><p>&nbsp;</p>
                <p>&nbsp;</p>
                <p>&nbsp;</p>
                <p>图片名称：${image}
                </p>
                <p><a href="javascript:if(confirm('确定删除该图片?')==true){location='/back/shoe!DelImage.action?sid=${shoe.sid}&simage=${image}';}">删除</a></p>
                </td>
            </tr>
            </c:forEach>
            </c:if>
          </table>
      	  <p>&nbsp;</p>
      	</s:form>
    	</td>
      </tr>
    </table>
    </td>
  </tr>
</table>
  </body>
</html>
