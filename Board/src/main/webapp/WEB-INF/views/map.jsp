<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8"/>
	<title>Map</title>
</head>
<body>
	<div id="map" style="width:100%;height:350px;"></div>
	<p>
		<button onclick="setCenter()">지도 중심좌표 이동시키기</button>
		<button onclick="panTo()">지도 중심좌표 부드럽게 이동시키기</button>
	</p>
	<p>
		<button onclick="zoomIn()">zoomIn</button>
		<button onclick="zoomOut()">zoomOut</button>
		<span id="mapLevel"></span>
	</p>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=551273a62de892a4b339e05db63a98c5"></script>
	<script>
		var mapContainer = document.getElementById('map'); //지도를 표시할 div
		var mapOption = {
			center: new kakao.maps.LatLng(33.450701, 126.570667),//지도 중심 좌표
			level: 3 //지도 확대 레벨
		};

		var map = new kakao.maps.Map(mapContainer, mapOption); //지도 생성
		
		//일반 지도, 스카이뷰 지도 타입 전환 컨트롤러
		var mapTypeControl=new kakao.maps.MapTypeControl();
		
		//지도에 컨트롤을 추가해야 지도위에 표시된다.
		map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);
		
/* 		//지도 확대 축소 컨트롤러
		var zoomControl=new kakao.maps.ZoomControl();
		alert(zoomControl.value());
		map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT); */
		
		displayLevel();
		
		function setCenter(){
			//이동할 위도 경도 위치를 생성한다.
			var moveLatLon=new kakao.maps.LatLng(33.452613, 126.570888);
			
			//지도 중심을 이동시킨다.
			map.setCenter(moveLatLon);
		}
		
		function panTo(){
			//이동할 위도 경도 위치 생성
			var moveLatLon=new kakao.maps.LatLng(33.452613, 126.570888);
			
			//지도 중심을 부드럽게 이동한다.
			//만약 이동할 거리가 지도 화면보다 크면 부드러운 효과 없이 이동한다.
			map.panTo(moveLatLon);
			map.setLevel(3);
			displayLevel();
			
		}
		
		function zoomIn(){
			//현재 지도의 레벨을 얻어옵니다.
			var level=map.getLevel();
			map.setLevel(level-1);
			displayLevel();
		}
		function zoomOut(){
			var level=map.getLevel();
			map.setLevel(level+1);
			displayLevel();
		}
		
		function displayLevel(){
			var levelEl=document.querySelector('#mapLevel');
			levelEl.innerHTML='현재 지도 레벨은 '+map.getLevel()+' 레벨 입니다.';
		}
		
	</script>
</body>
</html>