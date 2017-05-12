<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"  %>
<%@ taglib uri="/struts-tags" prefix="s"%>
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
		for ( var i = 1980; i <= 2011; i++)
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
		var year = document.getElementById("year"); //��ȡ��������б�
		var month = document.getElementById("month"); //��ȡ�·������б�
		var day = document.getElementById("day"); //��ȡ���������б�
		day.options.length = 0; //���������
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
			alert("�������Ь�����кţ�");
			form1.snum.focus();
			return false;
		}
		if (form1.sname.value.length == 0) {
			alert("�������Ь�����ƣ�");
			form1.sname.focus();
			return false;
		}
		if (form1.sprice.value.length == 0) {
			alert("�������Ь�ļ۸�");
			form1.sprice.focus();
			return false;
		}
		if (form1.sdiscount.value.length == 0) {
			alert("�������Ь���ۿۣ�");
			form1.sdiscount.focus();
			return false;
		}
		if (form1.sproducer.value.length == 0) {
			alert("�������Ь�������̣�");
			form1.sproducer.focus();
			return false;
		}
		if (form1.scolor.value.length == 0) {
			alert("�������Ь����ɫ��");
			form1.scolor.focus();
			return false;
		}		
		if (form1.stimessold.value.length == 0) {
			alert("�������Ь�������Ĵ�����");
			form1.stimessold.focus();
			return false;
		}		
		if (form1.sintegral.value.length == 0) {
			alert("�������Ь�Ļ��ֱ�����");
			form1.sintegral.focus();
			return false;
		}
	}
	function saveshoes(){
	   var ss=document.getElementById("form2");
	   if(confirm("ȷ�������Ь��Ϣ?")){
	      ss.submit();
	   }
	}
	// ��ѡ��ȫѡ����
	function SetChecked(boxname) {
		f = document.form1;
		for (i = 0; i < f.elements.length; i++) {
			if (f.elements[i].name == boxname) {
				f.elements[i].checked = true;
			}
		}
	}
	// ��ѡ��ѡ����
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
	function checkCount(sc){
	    var sv=document.getElementById(sc.id+"inp");
	    var s=document.getElementById(sc.id);
	    if(s.checked==true){
	        sv.style.display="block";
	    }else{
	        sv.style.display="none";
	    }
	}
	// ��ѡ��ȫ��ѡ����
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
	};
</script>
   </head>


<!--replace checkbox_name with the name of checkbox you want to select -->


<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="8" rowspan="3" background="/back/images/shadow.gif">
    <br></td>
    <td width="943" height="30" valign="middle" background="/back/images/table_bg001.gif"> &nbsp;&nbsp; <img src="/back/images/b_sing.gif" width="12" height="12" align="absmiddle" />&nbsp;<font color="#999999">&nbsp;&nbsp; ��ʾ��Ϣ������˵��:���һ���µ�Ь</font></td>
  </tr>
   <!----------------------�����ⲿ��------------------------------>
  <tr>
    <td height="580" valign="top" background="/back/images/table_bg001.gif" style="padding:5px;">
    <table width="100%" border="0" cellspacing="1" cellpadding="0" align="center">
      <tr>
        <td colspan="5"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="6%"><img src="/back/images/b_table_header_left.gif" width="58" height="31" /></td>
            <td width="92%" background="/back/images/b_table_title_bg.gif">
            	<div style="text-align:center; font:12px; color:#FFF; font-weight:bold;">
                	���һ���µ�Ь
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
      	<s:form id="form2" name="form1" method="post" action="/back/shoe!CreateShoes.action" enctype="multipart/form-data" onSubmit="return checkForm();">
    	<table width="500" border="0" align="center" cellpadding="0" cellspacing="1">
            <tr>
              <td align="right" height="30" background="/back/images/b_table_header.gif">Ь�����ͣ�</td>
              <td height="30" background="/back/images/b_table_header.gif">
              <select name="tid" id="tid">              
    	        <c:forEach items="${sessionScope.lstTypes }" var="type">
    	        <option value="${type.tid}">${type.tname}</option>
    	        </c:forEach>
  	        </select></td>
            </tr>
            <tr>
              <td align="right" height="30" background="/back/images/b_table_header.gif">Ь��Ʒ�ƣ�</td>
              <td height="30" background="/back/images/b_table_header.gif">
              <select name="bid" id="bid">              
    	      <c:forEach items="${sessionScope.lstBrands}" var="brand">
    	        <option value="${brand.bid}">${brand.bname}</option>
    	        </c:forEach>
  	        </select></td>
            </tr>
            <tr>
              <td align="right" height="30" background="/back/images/b_table_header.gif">���кţ�</td>
              <td height="30" background="/back/images/b_table_header.gif">
                 <input type="text" name="snum" id="snum"/></td>
            </tr>
            <tr>
              <td align="right" height="30" background="/back/images/b_table_header.gif">Ь�����ƣ�</td>
              <td height="30" background="/back/images/b_table_header.gif">
              <input type="text" name="sname" id="sname"/></td>
            </tr>
            <tr>
              <td align="right" height="30" background="/back/images/b_table_header.gif">Ь�Ӽ۸�</td>
              <td height="30" background="/back/images/b_table_header.gif">
              <input type="text" name="sprices" id="sprices"/></td>
            </tr>
            <tr>
              <td align="right" height="30" height="30" background="/back/images/b_table_header.gif">�ּۣ�</td>
              <td height="30" background="/back/images/b_table_header.gif">
              <input type="text" name="sdiscount" id="sdiscount"/></td>
            </tr>
            <tr>
              <td align="right" height="30" background="/back/images/b_table_header.gif">����ʱ�䣺</td>
              <td height="30" background="/back/images/b_table_header.gif">
              	<select name="year" id="year" onchange="fillDay()"></select>
            	<select name="month" id="month" onchange="fillDay()"></select>
            	<select name="day" id="day"></select></td>
            </tr>
            <tr>
              <td align="right" height="30" background="/back/images/b_table_header.gif">�����̣�</td>
              <td height="30" background="/back/images/b_table_header.gif">
              <input type="text" name="sproducer" id="sproducer"/></td>
            </tr>
            <tr>
              <td align="right" height="30" background="/back/images/b_table_header.gif">��Ů��ʽ��</td>
              <td height="30" background="/back/images/b_table_header.gif">
              	<input type="radio" name="sgender" id="sgender1" value="Ů" checked="checked">Ů
				<input type="radio" name="sgender" id="sgender2" value="��">��              
              </td>
            </tr>
            <tr>
              <td align="right" height="30" background="/back/images/b_table_header.gif">��ɫ��</td>
              <td height="30" background="/back/images/b_table_header.gif"> 
              <input type="text" name="scolor" id="scolor"/></td>
            </tr>
            <tr>
              <td align="right" height="30" background="/back/images/b_table_header.gif">�����Ϣ��</td>
              <td height="30" background="/back/images/b_table_header.gif">
              <input type="text" name="sinfo" id="sinfo"/></td>
            </tr>
            <tr>
              <td align="right" height="30" background="/back/images/b_table_header.gif">����������</td>
              <td height="30" background="/back/images/b_table_header.gif">
              <input type="text" name="stimessold" id="stimessold"/></td>
            </tr>
            <tr>
              <td align="right" height="30" background="/back/images/b_table_header.gif">��ϸ��Ϣ��</td>
              <td height="30" background="/back/images/b_table_header.gif">
              <input type="text" name="sdetail" id="sdetail"/></td>
            </tr>
            <tr>
              <td align="right" height="30" background="/back/images/b_table_header.gif">���֣�</td>
              <td height="30" background="/back/images/b_table_header.gif">
              <input type="text" name="sintegral" id="sintegral"/></td>
            </tr>
            <tr>
              <td align="right" height="30" background="/back/images/b_table_header.gif">״̬��</td>
              <td height="30" background="/back/images/b_table_header.gif">
              <input type="checkbox" name="sdelete" id="sdelete" checked="checked" value="0"/></td>
            </tr>
            <tr>
            	<td align="right" height="30" background="/back/images/b_table_header.gif">
            		ѡ��ߴ�:<br/>
            		<p>&nbsp;</p>
            		<a href="javascript:;" onclick="SetChecked('chk');return false;"><font color="black">ȫѡ</font></a>&nbsp;&nbsp;<br/>
            		<p>&nbsp;</p>
            		<a href="javascript:;" onclick="SetReverseChecked('chk');return false;"><font color="black">��ѡ</font></a>&nbsp;&nbsp;<br/>
            		<p>&nbsp;</p>
            		<a href="javascript:;" onclick="SetUnChecked('chk');return false;"><font color="black">���</font></a>&nbsp;&nbsp;
            	</td>
            	<td  height="30" background="/back/images/b_table_header.gif">
            		<c:forEach items="${sessionScope.lstSizes}" var="size">
            			 <input type="checkbox" name="chk" id="${size.sizeid }" onclick="checkCount(this)" value="${size.sizeid }"/>&nbsp;${size.sizenum}
            			  &nbsp;num: <input name="inp" id="${size.sizeid}inp" value="0" style="display: none;" /><br/>
            		</c:forEach>
           		</td>
            </tr>
            <tr>
               <s:file name="uploadImage" id="uploadImage" label="�ϴ�ͼƬ" />	
              </tr>
            <tr>
              <td height="30" colspan="2" background="/back/images/b_table_header.gif">
              <input value="ȷ�����" type="button" onclick="saveshoes()" /></td>
              </tr>
          </table>
    	</s:form>
    	</td>
      </tr>
    </table>
    </td>
  </tr>
</table>
  </body>
</html>
