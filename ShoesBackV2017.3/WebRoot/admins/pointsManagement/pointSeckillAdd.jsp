<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<title></title>
<style>
.date-picker-wp {	display: none;	position: absolute;	background: #f1f1f1;	left: 40px;	top: 40px;	border-top: 4px solid #3879d9;}
.date-picker-wp table {	border: 1px solid #ddd;}
.date-picker-wp td {background: #fafafa;width: 22px;height: 18px;border: 1px solid #ccc;	font-size: 12px;text-align: center;}
.date-picker-wp td.noborder {	border: none;	background: none;}
.date-picker-wp td a {	color: #1c93c4;	text-decoration: none;}
.strong {	font-weight: bold}
.hand {	cursor: pointer;	color: #3879d9}


body {
	color:#3E2300;
}
body, td, th {
	font-size: 12px;
}
table {
	cellspacing:0px;
	font-size:16px;
	border-collapse:collapse;
}
td {
	word-break:break-all;
	text-align:left;
}
.left {
	font-family: "幼圆";
	font-size: 13px;
	color: #603F00;
	text-align: left;
	padding-left:10px;
}
.right {
	font-family: "幼圆";
	font-size: 13px;
	color:#2C4600;
}
.secKillInfo {
	font-family: "幼圆";
	font-size: 15px;
	color: #3E2300;
}
.setinfo {
	font-family: "幼圆";
	font-size: 14px;
	color: #3E2300;
}
-->
</style>

<script language="javascript"> 
	var DatePicker = function () {
	var $ = function (i) {return document.getElementById(i)},
	addEvent = function (o, e, f) {o.addEventListener ? o.addEventListener(e, f, false) : o.attachEvent('on'+e, function(){f.call(o)})},
	getPos = function (el) {
	for (var pos = {x:0, y:0}; el; el = el.offsetParent) {
	pos.x += el.offsetLeft;
	pos.y += el.offsetTop;
	}
	return pos;
	}
	var init = function (n, config) {
	window[n] = this;
	Date.prototype._fd = function () {var d = new Date(this); d.setDate(1); return d.getDay()};
	Date.prototype._fc = function () {var d1 = new Date(this), d2 = new Date(this); d1.setDate(1); d2.setDate(1); d2.setMonth(d2.getMonth()+1); return (d2-d1)/86400000;};
	this.n = n;
	this.config = config;
	this.D = new Date;
	this.el = $(config.inputId);
	this.el.title = this.n+'DatePicker';
	this.update();
	this.bind();
	}
	init.prototype = {
	update : function (y, m) {
	var con = [], week = ['Su','Mo','Tu','We','Th','Fr','Sa'], D = this.D, _this = this;
	fn = function (a, b) {return '<td title="'+_this.n+'DatePicker" class="noborder hand" onclick="'+_this.n+'.update('+a+')">'+b+'</td>'},
	_html = '<table cellpadding=0 cellspacing=2>';
	y && D.setYear(D.getFullYear() + y);
	m && D.setMonth(D.getMonth() + m);
	var year = D.getFullYear(), month = D.getMonth() + 1, date = D.getDate();
	for (var i=0; i<week.length; i++) con.push('<td title="'+this.n+'DatePicker" class="noborder">'+week[i]+'</td>');
	for (var i=0; i<D._fd(); i++ ) con.push('<td title="'+this.n+'DatePicker" class="noborder">.</td>');
	for (var i=0; i<D._fc(); i++ ) con.push('<td class="hand" onclick="'+this.n+'.fillInput('+year+', '+month+', '+(i+1)+')">'+(i+1)+'</td>');
	var toend = con.length%7;
	if (toend != 0) for (var i=0; i<7-toend; i++) con.push('<td class="noborder">.</td>');
	_html += '<tr>'+fn("-1, null", "<<")+fn("null, -1", "<")+'<td title="'+this.n+'DatePicker" colspan=3 class="strong">'+year+'/'+month+'/'+date+'</td>'+fn("null, 1", ">")+fn("1, null", ">>")+'</tr>';
	for (var i=0; i<con.length; i++) _html += (i==0 ? '<tr>' : i%7==0 ? '</tr><tr>' : '') + con[i] + (i == con.length-1 ? '</tr>' : '');
	!!this.box ? this.box.innerHTML = _html : this.createBox(_html);
	},
	fillInput : function (y, m, d) {
	var s = this.config.seprator || '/';
	if(m<10)
	{m ="0"+m;}
	if(d<10)
	{d ="0"+d;}
	this.el.value = y + s + m + s + d;
	this.box.style.display = 'none';
	show();
	showEnd();
	 fixTimes();
	
	},
	show : function () {
	var s = this.box.style, is = this.mask.style;
	s['left'] = is['left'] = getPos(this.el).x + 'px';
	s['top'] = is['top'] = getPos(this.el).y + this.el.offsetHeight + 'px';
	s['display'] = is['display'] = 'block';
	is['width'] = this.box.offsetWidth - 2 + 'px';
	is['height'] = this.box.offsetHeight - 2 + 'px';
	},
	hide : function () {
	this.box.style.display = 'none';
	this.mask.style.display = 'none';
	},
	bind : function () {
	var _this = this;
	addEvent(document, 'click', function (e) {
	e = e || window.event;
	var t = e.target || e.srcElement;
	if (t.title != _this.n+'DatePicker') {_this.hide();} else {_this.show();}
	})
	},
	createBox : function (html) {
	var box = this.box = document.createElement('div'), mask = this.mask = document.createElement('iframe');
	box.className = this.config.className || 'datepicker';
	mask.src = 'javascript:false';
	mask.frameBorder = 0;
	box.style.cssText = 'position:absolute;display:none;z-index:9999';
	mask.style.cssText = 'position:absolute;display:none;z-index:9998';
	box.title = this.n+'DatePicker';
	box.innerHTML = html;
	document.body.appendChild(box);
	document.body.appendChild(mask);
	return box;
	}
	}
	return init;
	}();
	onload = function () {
		new DatePicker('_DatePicker_demo', {
		inputId: 'date-input',
		className: 'date-picker-wp',
		seprator: '-'
		});
		new DatePicker('_demo2', {
			inputId: 'demo2',
			className: 'date-picker-wp',
			seprator: '-'
		});
	} 
	
	/*  显示开始时间和结束时间的值 */
	function show(){ 
	 document.getElementById("skstarttimeStr").value=document.getElementById("date-input").value+" "+document.getElementById("h").value+":"+document.getElementById("min").value+":00"
	} 
	
	 
	function showEnd(){
	 document.getElementById("skduratoinStr").value=document.getElementById("demo2").value+" "+document.getElementById("hEnd").value+":"+document.getElementById("minEnd").value+":00"
	} 
	
	function fixTimes()
	{
	/*没有空间了。借用积分的提示消息显示时间的出错信息*/
		 if(document.getElementById("skstarttimeStr").value>=document.getElementById("skduratoinStr").value)
		 { 
			document.getElementById("chkSkintegral").innerHTML="*...开始时间不能【晚于】或【等于】结束时间";  
			
			document.getElementById("demo2").value = document.getElementById("date-input").value;
			document.getElementById("hEnd").value=document.getElementById("h").value;
			document.getElementById("minEnd").value=document.getElementById("min").value;	 
			document.getElementById("skduratoinStr").value=document.getElementById("date-input").value+" "+document.getElementById("h").value+":"+document.getElementById("min").value+":00"
		  	return false; 
		}
		else
		{
			document.getElementById("chkSkintegral").innerHTML="*";  
			return true;
		}
	}
	
	/*秒杀积分不能为空*/
	function funChkSkintegral()
 	{ 
	 	var usertelPattern = /^[0-9]*$/ ; 
		if (!document.getElementById("skintegral").value.match(usertelPattern)) {  
		 	document.getElementById("chkSkintegral").innerHTML ="* 只允许数字";  
			return false;  
    	}    
		else if(document.getElementById("skintegral").value=="" ||document.getElementById("skintegral").value=="0")
		{ 
			document.getElementById("chkSkintegral").innerHTML="* 不能为空";  
			return false;
		}
		else
		{
			document.getElementById("chkSkintegral").innerHTML="*";
			return true;
		}
	}
	
	
	/* 秒杀数量不能为空 */
	function funChkSkamount()
 	{
		var pattern = /^[0-9]*$/ ; 
		if (!document.getElementById("skamount").value.match(pattern)) {  
		 	document.getElementById("chkSkamount").innerHTML ="* 只允许数字";  
			return false;  
    	}    
		else if(document.getElementById("skamount").value=="" ||document.getElementById("skamount").value=="0")
		{ 
			document.getElementById("chkSkamount").innerHTML="* 不能为空";  
			return false;
		}
		else
		{
			document.getElementById("chkSkamount").innerHTML="*";  
			return true;
		}
	}
	
	function  cheSksize()
	{
		if(document.getElementById("sksize").value=="0")
		{
			document.getElementById("cheSksize").innerHTML="* 必须选择一个尺码";  
			return false; 
		}
		else
		{
			return true;
		}
	}
	function chkForm()
	{ 
		if(funChkSkintegral() && funChkSkamount() &&cheSksize()&&fixTimes())
		{ 
			return true; 
		}
		else
		{
			return false;
		}
	}
</script> 
</head>

<body>


<div align="center" >
<form  name="form_seckill" method="post" action="/back/kill!SaveSecondKills.action"   onSubmit="return chkForm()">

 <div align="left"style="padding-left:50px; color:#f90;">
	 <h3>	
	 	<a style=" color:#f90; text-decoration:none;"
	 		href="/back/kill!FindShoesByPage.action">-返回鞋子列表</a>&nbsp;&nbsp;更新为秒杀商品 
	 </h3>
 </div>
 <table  width="90%" border="1" style="margin:10px auto; border-bottom:0px;"  > 
 	<tr>
<td style="width:350px;height:350px;" colspan="2"> 
    		<c:if test="${requestScope.preAddshoesimg[0]==null}"> 
				<img src="/back/images/empty.gif"  style="width:300px; max-height:300px;"/>
			</c:if>
			<c:if test="${requestScope.preAddshoesimg[0]!=null}"> 
        		<img src="/back/upload/img/${requestScope.preAddshoesimg[0]}"  style="width:300px; max-height:300px;"/>
        	</c:if> 
   	  </td>
    	<td width="100%" height="400px;">
        	<table  height="400px"> 
              <tr>
              	<td colspan="4" align="center" valign="top" height="50">
                	<h1 style="color:#900; text-align:center; height:50px; vertical-align:bottom">${requestScope.preAddShoes.sname}</h1> 
                	<input name="shoesid" id="shoesid"  type="hidden" value="${requestScope.preAddShoes.sid}" />
                </td>
              </tr>
              <tr height="50px;">
                <td> 
                   &nbsp;开始时间&nbsp;
                   <input type="text" id="date-input" onChange="show();"  width="60px;" value="2015-01-01"  readonly="readonly"/>
                </td>
                <td>
                    <select name="h" id="h" onchange="show();">
                        <script>for(var i=0;i<=23;i++){ 
						if(i<10) {document.write("<option value=0"+i+">"+"0"+i+"</option>");}
							else{ document.write("<option value="+i+">"+i+"</option>"); }
						} </script>
				   </select>
                                                    时
				</td>
                <td>
                    <select name="min" id="min" onchange="show();">
                        <script>for(var i=0;i<=59;i++){
                      		if(i<10) {document.write("<option value=0"+i+">"+"0"+i+"</option>");}
							else{ document.write("<option value="+i+">"+i+"</option>"); }
							}
					  </script>
                    </select>
                                                    分
                </td>
                <td>
                    <input type="text" name="skstarttimeStr" id="skstarttimeStr" readonly="readonly" value="2015-01-01 00:00:00" /> 
                </td> 
              </tr>
              
              
              <tr height="50px;">
                <td>
                   &nbsp;结束时间&nbsp;
                   <input type="text"  id="demo2"  onChange="showEnd();"  width="60px;" value="2015-02-01"  readonly="readonly"/> 
                </td> 
                <td>
                    <select name="h" id="hEnd" onchange="showEnd();">
                      <script>for(var i=0;i<=23;i++){ 
						if(i<10) {document.write("<option value=0"+i+">"+"0"+i+"</option>");}
							else{ document.write("<option value="+i+">"+i+"</option>"); }
						} </script>
                    </select>
					 时
                </td>
                <td>
                    <select name="min" id="minEnd" onchange="showEnd();">
                      <script>for(var i=0;i<=59;i++){
                      		if(i<10) {document.write("<option value=0"+i+">"+"0"+i+"</option>");}
							else{ document.write("<option value="+i+">"+i+"</option>"); }
							} 
					  </script>
                    </select>
                   	 分
                </td>
                <td>
                    <input type="text" name="skduratoinStr" id="skduratoinStr" readonly="readonly" value="2015-01-01 00:00:00"/> 
                </td>
              </tr> 
              
              <tr  height="50px;">
                <td colspan="4">
                	&nbsp;耗费积分&nbsp;
                    <input type="text" id="skintegral" name="skintegral" width="90px;"
                     onblur="funChkSkintegral()"   style="padding-left:0"/>
                	 <span id="chkSkintegral"  style="color:#F00">*</span>
                </td>
              </tr> 
              
              <tr  height="50px;">
                <td colspan="4">
                    &nbsp;秒杀数量&nbsp;
                    <input type="text" id="skamount" name="skamount" width="90px;" style="padding-left:0"	onblur="funChkSkamount()"/>
                    <span id="chkSkamount"  style="color:#F00">*</span>
                </td>
              </tr>
              <tr  height="50px;">
                 <td colspan="4">  &nbsp;选择尺码&nbsp;
                	<select name="sksizes" id="sksizes" style="width:150px;"  onchange="cheSksize()">
                	 <option value="0">请选择</option>
                		<c:forEach items="${requestScope.preAddShoes.shoesizeses}" var="sizes">
                		 <option value="${sizes.sizes.sizenum}">${sizes.sizes.sizenum}</option>
                		</c:forEach>
                	 
                	</select>
                	<span id="cheSksize"  style="color:#F00">*</span>
                </td>
              </tr>
              <tr  height="50px;">
                 <td  colspan="3"  align="right"></td>
                 <td>
                   <input type="submit" name="submit" id="submit" style="height:60px;" value=" 确认提交秒杀商品 " />
                 </td>
              </tr>
            </table> 
        </td>
    </tr>
 </table>
  <table width="90%" border="1" style="margin:10px auto; border-bottom:0px;"  > 
    <tr>
    	<td width="100" class="left">编号：</td> 
    	<td width="600" class="right">${requestScope.preAddShoes.snum}</td>
    </tr>
    <tr>
      <td class="left">性别：</td>
      <td width="600" class="right"><c:if test="${requestScope.preAddShoes.sgender eq '男'}">男鞋 </c:if>
      <c:if test="${requestScope.preAddShoes.sgender eq '女'}">女鞋</c:if></td>  
    </tr>
    <tr>
      <td nowrap="nowrap" class="left">生产商：</td>
      <td class="right"> ${requestScope.preAddShoes.sproducer}</td>
    </tr>
    <tr>
      <td class="left">原价：</td>
      <td class="right" ><fmt:formatNumber value="${requestScope.preAddShoes.sprices}" pattern="0.00"/></td>
    </tr>
    <tr>
      <td class="left">折扣：</td>
      <td class="right" > 
      	<fmt:formatNumber value="${requestScope.preAddShoes.sdiscount}" pattern="0.0"/>
      </td>
    </tr>
    <tr>
      <td class="left">现价：</td>
      <td class="right" > 
      	<fmt:formatNumber value="${requestScope.preAddShoes.sprices*requestScope.preAddShoes.sdiscount}" pattern="0.00"/>
      </td>
    </tr>
    <tr>
      <td valign="middle" class="left">颜色:</td>
      <td   width="600" class="right">${requestScope.preAddShoes.scolor}</td> 
    </tr>
    <tr>
      <td class="left">品牌：</td>
      <td class="right"> ${requestScope.preAddShoes.brands.bname}</td>
    </tr>
    <tr>
      <td class="left">上市时间：</td>
      <td class="right"> ${requestScope.preAddShoes.spubtime}</td>
    </tr>
    <tr> 
    <tr> 
      <td class="left">类型：</td>
      <td class="right">${requestScope.preAddShoes.types.tname}</td>
    </tr>
    <tr>
      <td class="left">购买热度：</td>
      <td class="right">${requestScope.preAddShoes.stimessold}(/人次)</td>
    </tr> 
  </table> 
</form>
<div>
</body>
</html>
