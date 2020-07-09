<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<h3>인터셉터 결과 데이터</h3>
	</div>
	<div>
		<a href="${path}/interceptor/doA">doA 페이지로 이동</a>
	</div>
	<div>
		<a href="${path}/interceptor/doB">doB 페이지로 이동</a>
	</div>
	<div>
		<p>결과데이터 : ${result }</p>
	</div>
</body>
</html>