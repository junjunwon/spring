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
	 	<title>회원가입</title>
</head>

<script type="text/javascript">
	$(document).ready(function(){
		
		$(".cancel").on("click",function(){
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
	
	
	<section id="container">
		<form action="/member/register" method="post" id="regForm">
			<div class="form-group has-feedback">
				<label class="control-label" for="userId">아이디</label>
				<input class="form-control" type="text" id="userId" name="userId">
				<button type="button" class="idChk" id="idChk" onclick="fn_idChk();" value="N">중복확인</button>
			</div>
			<div class="form-group has-feedback">
				<label class="control-label" for="userPass">패스워드</label>
				<input class="form-control" type="password" id="userPass" name="userPass">
			</div>
			<div class="form-group has-feedback">
				<label class="control-label" for="userName">성명</label>
				<input class="form-control" type="text" id="userName" name="userName">
				<button type="button" class="nameChk" id="nameChk" onclick="fn_nameChk();" value="N">중복확인</button>
			</div>
			
			<div class="form-group has-feedback">
				<button type="submit" class="btn btn-success" id="submit">회원가입</button>
				<button type="button" class="cancel btn-danger">취소</button>
			</div>
		</form>
	</section>

</body>
</html>