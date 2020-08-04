<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
	<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
 	<!--Custom styles-->
	<link rel="stylesheet" type="text/css" href="../resources/css/style.css">
	<script src="/resources/js/boot.js"></script>
	<!-- 이걸로 곧 변경 -->
	<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	
	<!------ Include the above in your HEAD tag ---------->
	
	<script src="https://cdn.jsdelivr.net/jquery.validation/1.15.1/jquery.validate.min.js"></script>
	<link href="https://fonts.googleapis.com/css?family=Kaushan+Script" rel="stylesheet">
   	<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
	
	 	<title>이메일 인증</title>
</head>
<script type="text/javascript">

	$(document).ready(function(){
		$("#firstPage").on("click", function(){
			location.href="/member/login";
			/* 즉 데이터베이스에 이메일 인증을 한사람은 Y,
			인증을 하지 않은 사람은 "인증코드"로 저장된다.
			이 후 이메일로 본인인증이 된 고객과 안된 고객을 구분해서 다른서비스를 제공해줄 수 있을 것 같다. */
		})
		
		
	})
	
	function fn_emailAuth(){
		alert($("#userId").val()+"님의 "+$("#userEmail").val()+"로 인증번호가 전송되었습니다.");
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

<body>

	<div class="container">
		<div class="row">
		<div class="col-md-5 mx-auto">
			<div id="first">
				<div class="myform form">
						<div class="logo mb-3">
							<div class="col-md-12 text-center">
								<h3>"${userId}님 반갑습니다."</h3>
							</div>
						</div>
						<div class="form-group">
							<nav>
					    		<ul>
					     		 <%@include file="../board/nav.jsp" %>
					   			</ul>
							</nav>
						</div>
						<form action="/member/sendEmail" method="post" id="regForm">
							<div class="form-group">
								<label class="control-label" for="userEmail">이메일</label><br />
								<input class="text-center" type="text" id="userEmail" name="userEmail">
									<button type="button" class="btn mybtn btn-primary" id="sendEmail" onclick="fn_emailAuth();" >
										이메일 인증
									</button>
								</input>
								<input type="hidden" name="userId" id="userId" value="${userId}"/>
							</div>
							<div class="form-group">
								<input class="text-center" type="text" id="authKey" name="authKey">
									<button type="button" class="btn mybtn btn-primary" id="authKey" onclick="fn_authKey();">
										인증번호 입력
									</button>
								</input>
								<br />
							<%-- <input class="form-control" type="hidden" id="authKey" name="authKey" value="${authKey}"/> --%>
			
					  		<input style="border:none" type="text" name="result" id="result" readonly/>
							</div>
							<div class="col-md-12 text-center ">
								<button type="button" class=" btn btn-block mybtn btn-primary tx-tfm" id="loginPage" onclick="fn_finish();">완료</button>
								<button type="button" class=" btn btn-block mybtn btn-primary tx-tfm" id="firstPage">처음으로</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>



		<%-- <div class="review_box_nav">
					<nav>
					    <ul>
					      <%@include file="../board/nav.jsp" %>
					    </ul>
					</nav>
		</div>
		<br>
		<section id="container">
		<input style="border:none" type="text" value="${userId}님 반갑습니다." readonly />
		<form action="/member/sendEmail" method="post" id="regForm">

			<div class="form-group has-feedback">
				<label class="control-label" for="userEmail">이메일</label>
				<input class="form-control" type="text" id="userEmail" name="userEmail">
				<button type="button" class="sendEmail" id="sendEmail" onclick="fn_emailAuth();" >이메일 인증</button>
				<input type="hidden" name="userId" id="userId" value="${userId}"/>
			</div>
		</form>
		<form action="/member/keyAlter" method="post" id="confirm">
			<div>
				<input class="form-control" type="text" id="authKey" name="authKey"/>
				<br />
				<input class="form-control" type="hidden" id="authKey" name="authKey" value="${authKey}"/>
		  		<button type="button" class="authKey" id="authKey" onclick="fn_authKey();">인증번호 입력</button>
		  		<input style="border:none" type="text" name="result" id="result" readonly/>
			</div>
			<div>
				<br />
				<button type="button" class="loginPage" id="loginPage" onclick="fn_finish();">완료</button>
				<button type="button" class="firstPage" id="firstPage">처음으로</button>
			</div>
		</form>
		
	</section> --%>
</body>
</html>