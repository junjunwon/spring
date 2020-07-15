<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- <%@ page session="false" %>  session이 false로 되잇는지 확인하자! false면 session값을 전달하지 못한다. --%> 
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<head>
 	
	 	<!--Custom styles-->
		<link rel="stylesheet" type="text/css" href="../resources/css/style.css">
		<script src="../resources/js/boot.js"></script>
		<!-- 이걸로 곧 변경 -->
		<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
		<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
		<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<!------ Include the above in your HEAD tag ---------->
		
		<script src="https://cdn.jsdelivr.net/jquery.validation/1.15.1/jquery.validate.min.js"></script>
		<link href="https://fonts.googleapis.com/css?family=Kaushan+Script" rel="stylesheet">
    	<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
		
		<title>HOME</title>
</head>

<script type="text/javascript">
	$(document).ready(function(){
		$("#logoutBtn").on("click", function(){
			location.href="member/logout";
		})
		$("#signup").on("click", function(){
			location.href="register";
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
	
	<div class="container">
		<div class="row">
			<div class="col-md-5 mx-auto">
			<div id="first">
				<div class="myform form">
					<div class="logo mb-3">
						<div class="col-md-12 text-center">
							<h1>Sign in</h1>
						</div>
					</div>
					<form name='homeForm' method='post' action="/member/loginPost">
						
						<c:if test="${member==null}">
							<div class="form-group">
								<label for="exampleInputEmail1">User ID</label>
								<input type="text" class="form-control" aria-describedby="emailHelp" placeholder="username" id="userId" name="userId">
							</div>
							<div class="form-group">
								<label for="exampleInputEmail1">User Password</label>
								<input type="password" class="form-control" aria-describedby="emailHelp" placeholder="password" id="userPass" name="userPass">
							</div>
							<div class="form-group">
								<input type="checkbox"> Remember Me</input>
							</div>
							
							<div class="col-md-12 text-center ">
                              <button  type="submit" class=" btn btn-block mybtn btn-primary tx-tfm" id="login_Btn">Login</button>
                           	</div>
                           	
							<div class="col-md-12 ">
                              <div class="login-or">
                                 <hr class="hr-or">
                                 <span class="span-or">or</span>
                              </div>
                           </div>
							<div class="col-md-12 mb-3">
                              <p class="text-center">
                                 <a href="javascript:void();" class="google btn mybtn"><i class="fa fa-google-plus">
                                 </i> Signup using Google
                                 </a>
                              </p>
                           </div>
                           <div class="form-group">
                              <p class="text-center">Don't have account? <a href="#" id="signup">Sign up here</a></p>
                           </div>
						
						</c:if>
						
						<c:if test="${member!=null}">
						
							<div class="col-md-12 mb-3">
								<a href="/board/list">Go to board</a>
							</div>
							
							<div class="col-md-12 text-center ">
                              <button id="memberUpdateBtn" type="button" class=" btn btn-block mybtn btn-primary tx-tfm">Edit Account</button>
                           	</div>
							<hr />
							<div class="col-md-12 text-center ">
                              <button id=memberDeleteBtn type="button" class=" btn btn-block mybtn btn-primary tx-tfm">Delete Account</button>
                           	</div>
                           	<hr />
                           	<div class="col-md-12 text-center ">
                              <button id=logoutBtn type="button" class=" btn btn-block mybtn btn-primary tx-tfm">Logout</button>
                           	</div>
							<hr />
					
						</c:if>
						
						<c:if test="${msg==false}">
						
							<script type="text/javascript">
								alert("로그인 실패! 아이디와 패스워드를 확인해주세요.");
							</script>
						</c:if>
					</form>
				</div>
			</div>
			</div>
		</div>
	</div>
	

</body>
</html>
