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
		
	 	<title>회원가입</title>
</head>

<script type="text/javascript">
	$(document).ready(function(){

		$("#alert-success").hide();
		$("#alert-danger").hide();
		$("input").keyup(function(){ //keyup : 키를 올려놓고 작성할떄마다 동적으로 이벤트발생.
			var userPass=$("#userPass").val();
			var userPassChk=$("#userPassChk").val();
			if(userPass!="" || userPassChk!=""){
				if(userPass==userPassChk){
					console.log("userPass==userPassChk");
					$("#alert-success").show();
					$("#alert-danger").hide();
					$("#submit").removeAttr("disabled"); //차단된 회원가입 버튼 able.
				}else{
					console.log("userPass!=userPassChk");
					$("#alert-success").hide();
					$("#alert-danger").show();
					$("#submit").attr("disabled", "disabled");//회원가입 버튼 클릭 차단
				}
			}
		});


		$("#cancel").on("click",function(){
			location.href="/";
		})


		$("#serviceKey").on("click", function(){
			var serviceKey="${serviceKey}";
			alert("serviceKey is : "+serviceKey);

		})

		$("#submit").on("click", function(){
			if($("#userId").val()==""){
				alert("아이디를 입력해주세요.");
				$("#userId").focus();
				return false;
			}
			if($("#userPass").val()==""){
				alert("패스워드를 입력해주세요.");
				$("#userPass").focus();
				return false;
			}
			if($("#userName").val()==""){
				alert("성명을 입력해주세요.");
				$("#userName").focus();
				return false;
			}

			var idChkVal=$("#idChk").val();
			var nameChkVal=$("#nameChk").val();

			if(idChkVal=="N"){
				alert("아이디 중복확인 버튼을 눌러주세요.");
				$("#idChk").focus();
				return false;
			}
			if(nameChkVal=="N"){
				alert("닉네임 중복확인 버튼을 눌러주세요.");
				$("#nameChk").focus();
				return false;
			}
			else if(idChkVal=="Y" && nameChkVal=="Y"){
				alert($("#userId").val()+" 아이디로 회원가입이 완료가 완료되었습니다. \n이메일 인증 단계로 이동합니다.");
				$("#regForm").submit();
			}

		});
	})



	function fn_idChk(){

		//한글 아이디로 가입못하게 정규화.
		var korean =/[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
		var string=$("#userId").val();

		$.ajax({

			url:"/member/idChk",
			type:"post",
			dataType:"json",
			data:{"userId":$("#userId").val()},
			success:function(data){

				if($("#userId").val()==""){
					alert("아이디를 입력해주세요.");
					$("#userId").focus();
					return false;
				} else{
					if(korean.test(string)){
						alert("영문 아이디를 입력해주세요.");
					} else{
						if(data==1){
							alert("중복된 아이디입니다.");
						}else if(data==0){
							$("#idChk").attr("value","Y");
							alert("사용 가능한 아이디입니다.");
						}
					}
				}


			}

		})
	}


	function fn_nameChk(){

		$.ajax({

			url:"/member/nameChk",
			type:"post",
			dataType:"json",
			data:{"userName":$("#userName").val()},
			success:function(data){
				if($("#userName").val()==""){
					alert("성명을 입력해주세요.");
					$("#userName").focus();
					return false;
				} else{
					if(data==1){
						alert("중복된 닉네임입니다.");
					}else if(data==0){
						$("#nameChk").attr("value","Y");
						alert("사용 가능한 닉네임입니다.");
					}
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
								<h1>회원가입</h1>
							</div>
						</div>
						<form action="/member/register" method="post" id="regForm">
							<div class="form-group">
								<label class="control-label" for="userId">아이디</label>
								<input class="form-control" type="text" id="userId" name="userId">
								<button type="button" class="idChk" id="idChk" onclick="fn_idChk();" value="N">중복확인</button>
							</div>
							<div class="form-group">
								<label class="control-label" for="userPass">패스워드</label>
								<input class="form-control" type="password" id="userPass" name="userPass" required />
							</div>
							<div class="form-group">
								<label class="control-label" for="userPass">패스워드 확인d</label>
								<input class="form-control" type="password" id="userPassChk" name="userPassChk" required />
								<div class="alert alert-success" id="alert-success">비밀번호가 일치합니다.</div>
								<div class="alert alert-danger" id="alert-danger">비밀번호가 일치하지 않습니다.</div>
							</div>
							<div class="form-group">
								<label class="control-label" for="userName">성명</label>
								<input class="form-control" type="text" id="userName" name="userName">
								<button type="button" class="nameChk" id="nameChk" onclick="fn_nameChk();" value="N">중복확인</button>
							</div>
							<div class="col-md-12 text-center ">
              					<button  type="submit" class=" btn btn-block mybtn btn-primary tx-tfm" id="submit">회원가입</button>
								<button  type="button" class=" btn btn-block mybtn btn-primary tx-tfm" id="cancel">취소</button>
              				</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>


</body>
</html>
