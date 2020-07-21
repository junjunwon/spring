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
	 	<title>이메일 인증</title>
</head>
<script type="text/javascript">

	$(document).ready(function(){
		$("#firstPage").on("click", function(){
			location.href="/member/login";
		})
		
		
	})
	
	function fn_emailAuth(){
		alert($("#userEmail").val());
		alert($("#userId").val());
		$.post("/member/sendEmail", 
				{"userEmail":$("#userEmail").val(),
				"userId":$("#userId").val()});
	}
	
	function fn_finish(){
		
		if($("#result").val()=='인증번호가 일치합니다.'){
			alert("회원가입이 완료되었습니다.");
			location.href="/member/login";
			$.post("/member/keyAlter",
				{"authKey":$("#authKey").val(),
				"userId":$("#userId").val()}		
			);
			
		}else{
			alert("올바른 인증번호를 입력해주세요.");
			$("#result").focus();
			return false;
		}

	}
	
 	function fn_authKey(){
		alert($("#authKey").val());
		
		var i=0;
 		$.ajax({
			url:"/member/authKey",
			type:"post",
			dataType:"json",
			data:{"authKey":$("#authKey").val()},
			success:function(data){
				alert("진입 성공");
				if(data==1){
					$("#result").val('인증번호가 일치합니다.');

				}else{
					$("#result").val('잘못된 인증번호 입니다.');

				}
			}
		})  
		
	}  
</script>
-------------
${userId}
-------------
${serviceKey}
-------------
${KeyValue}
-------------
<body>
		<div class="review_box_nav">
					<nav>
					    <ul>
					      <%@include file="../board/nav.jsp" %>
					    </ul>
					</nav>
		</div>
		<br>
		<section id="container">
		<form action="/member/sendEmail" method="post" id="regForm">

			<div class="form-group has-feedback">
				<label class="control-label" for="userEmail">이메일</label>
				<input class="form-control" type="text" id="userEmail" name="userEmail">
				<button type="button" class="sendEmail" id="sendEmail" onclick="fn_emailAuth();" >이메일 인증</button>
				<input type="text" name="userId" id="userId" value="${userId}"/>
			</div>
		</form>
		<form action="/member/keyAlter" method="post" id="confirm">
			<div>
				<input class="form-control" type="text" id="authKey" name="authKey"/>
				<%-- <input class="form-control" type="hidden" id="authKey" name="authKey" value="${authKey}"/> --%>
		  		<button type="button" class="authKey" id="authKey" onclick="fn_authKey();">인증번호 입력</button>
		  		<input type="text" name="result" id="result"/>
			</div>
			<div>
				<button type="button" class="loginPage" id="loginPage" onclick="fn_finish();">완료</button>
				<button type="button" class="firstPage" id="firstPage">처음으로</button>
			</div>
		</form>
		
	</section>
</body>
</html>