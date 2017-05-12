<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<html> 
<head> 
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no" /> 
<meta http-equiv="content-type" content="text/html; charset=gb2312"/> 
<style type="text/css"> 
.labelUse{
	text-align:center;
	font-weight:bold;
	font-size:10px;
	font-family:"Lucida Sans Unicode", "Lucida Grande", sans-serif;
	color:#900;
	padding:4px 2px 1px 2px;
	border:2px dotted #900;
	width:150px;
	background:none;
}
.contentUse{
	font-family:"微软雅黑";
	font-size:12px;
}
html {height: auto;}
body {height: auto;margin: 0;padding: 0;}
#map_canvas {width:535px;height: 455px;position: absolute;}
@media print {#map_canvas {height: 950px;}}
</style> 
<title>搜狗地图</title> 

<script type="text/javascript" src="http://api.go2map.com/maps/js/api_v2.0.js"></script> 
<script type="text/javascript"> 
var adds='${requestScope.expinfo}';
var map;
var poly;
var path;
var spot=1;
var lineStyle;
var detail="";
function initialize() {	
  lineStyle={id:"L02","v:stroke":{color:"#0cf",weight:"5",endcap:"Round",opacity:"75%",endArrow:"Classic",endarrowlength:"long",endarrowwidth:"wide"}};
  var pairs=adds.split(";");
  var avgLat=0;
  var avgLng=0;
  var datetime="";
  var info="";
  for(var i=0;i<pairs.length-1;i++){
	  var pair=pairs[i].split(",");
  	avgLat+=pair[0]*1;
	avgLng+=pair[1]*1;
  }
  avgLat=avgLat/(pairs.length-1);
  avgLng=avgLng/(pairs.length-1);
  avgLat=avgLat.toFixed(7);
  avgLng=avgLng.toFixed(7);
  var myOptions = {
    zoom: 4,
    center: new sogou.maps.LatLng(avgLat*1,avgLng*1),
	mapControl:true,
	mapControlType: 3,
	scaleControl:true,
    mapTypeId: sogou.maps.MapTypeId.ROADMAP
  }
  map = new sogou.maps.Map(document.getElementById("map_canvas"), myOptions);
  
  poly = new sogou.maps.Polyline({
            map:map,
            style:lineStyle,
            zIndex:2
  });
  poly.setMap(map);
  
  path = poly.getPath()||[];
    //将点击位置的左边添加到多边形节点
  
  var infowindow = new sogou.maps.InfoWindow();
  for(var i=0;i<pairs.length-1;i++){
	  var pair=pairs[i].split(",");
	  pair[0]=(pair[0]*1).toFixed(7);
	  pair[1]=(pair[1]*1).toFixed(7);
	  datetime=pair[2];
	  info=pair[3];
	  createMarker(map,infowindow,pair[0],pair[1],datetime,info);	 
  }
}

//创建标记
  function createMarker(map,infowindow,lat,lng,datetime,info)
  {
	  path.push(new sogou.maps.LatLng(lat,lng));
  	  poly.setPath(path);
	  if(path.length>1){
	  poly = new sogou.maps.Polyline({
                    map:map,
                    styles:[lineStyle],
                    zIndex:2
                });
	  
      path=[];
      path.push(new sogou.maps.LatLng(lat,lng));
	  }
	  
      var marker = new sogou.maps.Marker({
        position: new sogou.maps.LatLng(lat,lng),
        map: map,
		title:datetime,
		styleId:"S189"+(spot-1),
        label:{style:"labelUse",visible:true,align:"TOP"}
    });
    sogou.maps.event.addListener(marker, 'mouseover', function() {
		//Convertor只需创建一个唯一的对象即可。
    var convertor=getInstance(sogou.maps.Convertor);
    //标准经纬度转换为Sogou地图坐标
    var sogouCoord=convertor.toSogou(new sogou.maps.LatLng(lat,lng));
	  var request={
    		location:{
          points:sogouCoord,
          type:0
    		}
	  }
	  var geo=new sogou.maps.Geocoder();
	  geo.geocode(request,function(a){
			if(a.status='ok'){
         		var g=a.data[0];
		 		if(a.data.length>0){
		 			detail="详细信息："+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
							+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
							+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
							+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
							+"<li>"+""+g.province+" "+g.city+" "+g.address+"</li>"
							+"<li>"+info+"</li>";
				}else{
					detail="定位至 - "+new sogou.maps.LatLng(lat,lng)+"\n未能解析地址...";
				}
     		}					 						 
		});
		
    });
	sogou.maps.event.addListener(marker, 'click', function() {
		infowindow.open(map,marker); 
		infowindow.setContent("<div class='contentUse'>"+detail+"</div>"); 
	});
	spot=spot+1;
  }

function getInstance(a) {
    a.hasOwnProperty("_instance")||(a._instance = new a);
    return a._instance
  }
</script> 
</head> 
<body onLoad="initialize()"> 
<c:if test="${empty requestScope.expinfo }">
	<script language="javascript">location="/back/order!GetExpInfo.action?oid=${requestScope.oid}"</script>
</c:if>
  <div id="map_canvas"></div> 
</body> 
</html> 