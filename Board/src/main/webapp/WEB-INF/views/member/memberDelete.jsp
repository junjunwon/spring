<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
		<!-- 합쳐지고 최소화된 최신 CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
		<!-- 부가적인 테마 -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
	
	 	<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	 	<title>회원탈퇴</title>
</head>
<script type="text/javascript">
	$(document).ready(function(){
		
		$(".cancel").on("click", function(){
			location.href="/";
		})
		$("#submit").on("click", function(){
			if($("#userPass").val()==""){
				alert("패스워드를 입력하세요.");
				("#userPass").focus();
				return false;
			}

			
		})
		
	})
</script>
<body>
	<section id="container">
		<form action="/member/memberDelete" method="post">
			<div class="form-group has-feedback">
				<label class="control-label" for="userId">아이디</label>
				<input class="form-control" type="text" id="userId" name="userId" value="${member.userId}" readonly="readonly"/>
			</div>
			<div class="form-group has-feedback">
				<label class="control-label" for="userPass">패스워드</label>
				<input class="form-control" type="text" id="userPass" name="userPass"/>
			</div>
			<div class="form-group has-feedback">
				<label class="control-label" for="userName">성명</label>
				<input class="form-control" type="text" id="userName" name="userName" value="${member.userName}" readonly="readonly"/>
			</div>
			<div class="form-group has-feedback">
				<button class="btn btn-success" type="submit" id="submit">회원탈퇴</button>
				<button class="cancel btn btn-danger" type="button">취소</button>
			</div>
			<c:if test="${msg==false}">
				<a style="color:red;">패스워드가 틀립니다. 다시 입력해주세요.</a>
			</c:if>
		</form>
	
	</section>
</body>
</html>