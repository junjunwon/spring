<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>


	<!-- <link rel="icon" href="../../favicon.ico"> -->
	
	<link rel="stylesheet" href="/resources/css/board_temp.css">
	
	
	<!-- 합쳐지고 최소화된 최신 CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<!-- 부가적인 테마 -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<!-- 	<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script> -->
	<title>게시판</title>
 	
	
</head>

<script type="text/javascript">
	function fn_cancel(){
		location.href="../board/list/"
	}

	
	$(document).ready(function(){
		var result="${test}";
		console.log("flag 값은 "+result);
	})
	
</script>

<body>
  <header>
    <div>
      <h1><a href="#"><span class="hide">맛집커뮤니티</span></a></h1>
      <nav>
        <ul>
       	  <%@include file="../board/nav_bar.jsp" %>

        </ul>
      </nav>
    </div>

  </header>	
  <br />
  <br />	
  	<main>
  		<div>
	      <div class="lnb">
	        <h2>Review</h2>
	        <form>
	          <fieldset>
	            <legend>동영상 검색</legend>
	            <input type="search" placeholder="동영상 검색">
	            <button><span class="hide">검색</span></button>
	          </fieldset>
	        </form>
	        <div>
	          <ul style="width:100% height:auto">
	            <li><a href="/board/list">board</a></li>
	            <li><a href="/board/board_temp">board_temp</a></li>
	            <li><a href="#">게시판3</a></li>
	            <li><a href="#">게시판4</a></li>
	            <li><a href="#">게시판5</a></li>
	          </ul>
	          
	        </div>
	      </div>
	  	
	  	
			<div class="review_box">
		
	
	<!-- 			<h1 class="page-header">게시판</h1> -->
				<div class="review_box_nav">
					<nav>
				        <ul>
				       	  <%@include file="../board/nav.jsp" %>
				        </ul>
				    </nav>
			    </div>
			    <br>
			    <div class="review_box_list">
			    	<h3 class="page-header">로그인 관리</h3>고객님의 로그인 정보 확인을 통해 안전하게 계정을 관리할 수 있습니다.
				</div>
				<hr />
				<br/>
				<div class="review_box_list">
					<p>본인확인</p>
					<p>고객님의 소중한 개인정보보호를 위해서 본인확인을 진행합니다.</p>
				</div>
				<form action="/member/confirmPassword" method='post'>
					<input type="hidden" name="result" value="${test}"/>
					<div class="review_box_list">
						<label class="review_box_list" for="userPass">계정 비밀번호</label>
						<input class="review_box_list" type="password" id="userPass" name="userPass"/>
					</div>
					<div class="form-group has-feedback">
						<button class="btn btn-success" type="submit" id="submit" onclick="fn_passChk();">확인</button>
						<button class="cancel btn btn-danger" type="button" onclick="fn_cancel();">취소</button>
					</div>
				</form>
			</div>
		</div>
	</main>
</body>
</html>