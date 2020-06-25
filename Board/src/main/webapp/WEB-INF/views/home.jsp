<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- <%@ page session="false" %>  session이 false로 되잇는지 확인하자! false면 session값을 전달하지 못한다. --%> 
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<head>
		<!-- 합쳐지고 최소화된 최신 CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
		<!-- 부가적인 테마 -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
	
	 	<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	 	<title>HOME</title>
</head>

<a href="/board/list">게시판</a>

<script type="text/javascript">
	$(document).ready(function(){
		$("#logoutBtn").on("click", function(){
			location.href="member/logout";
		})
		$("#registerBtn").on("click", function(){
			location.href="member/register";
		})
		
		$("#memberUpdateBtn").on("click", function(){
			location.href="member/memberUpdateView";
			//컨트롤러에 member/memberUpdateView로 가라는 말이 된다.
		})
		$("#memberDeleteBtn").on("click", function(){
			location.href="member/memberDeleteView";
			//컨트롤러에 member/memberUpdateView로 가라는 말이 된다.
		})
	})
</script>

<body>

<%-- ${member} --%>
<%-- ${member}로 세션값이 잘 넘어오는지 확인하자. --%>

	<form name='homeForm' method='post' action="/member/login">
		
		<c:if test="${member==null}">
			<div>
				<label for="userId"></label>
				<input type="text" id="userId" name="userId">
			</div>
			<div>
				<label for="userPass"></label>
				<input type="password" id="userPass" name="userPass">
			</div>
			<div>
				<button type="submit">로그인</button>
				<button type="button" id="registerBtn">회원가입</button>
			</div>
		
		</c:if>
		
		<c:if test="${member!=null}">
			<p>${member.userId}님 안녕하세요.</p>
			<button id="memberUpdateBtn" type="button">회원정보 수정</button>
			<button id="memberDeleteBtn" type="button">회원탈퇴</button>
			<button id="logoutBtn" type="button">로그아웃</button>
		</c:if>
		
		<c:if test="${msg==false}">
			<p style="color:red;">로그인 실패! 아이디와 패스워드를 확인해주세요.</p>
		</c:if>
	</form>
</body>
</html>
