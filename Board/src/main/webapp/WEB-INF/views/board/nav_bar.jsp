<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
	<head>
		<link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
		<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
		<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
		<!------ Include the above in your HEAD tag ---------->
	
		<!--Custom styles-->
		<link rel="stylesheet" type="text/css" href="../resources/css/nav_bar.css">
	</head>
	<script type="text/javascript">
		$(document).ready(function(){
			$("#logoutBtn").on("click", function(){
				location.href="../member/logout";
			})
			
			$("#memberUpdateBtn").on("click", function(){
				location.href="../member/memberUpdateView";
				//컨트롤러에 member/memberUpdateView로 가라는 말이 된다.
			})
			
			$("#memberDeleteBtn").on("click", function(){
				location.href="../member/memberDeleteView";
				//컨트롤러에 member/memberUpdateView로 가라는 말이 된다.
			})
		
		})
	</script>
	--------------------
	${member}
	--------------------
	<body>
		<div class="container">
		    <div class="row">
		        <div class="col-md-12">
		            <div class="navbar-wrapper">
		            	
		                <div class="container">
		                    <div class="navbar navbar-inverse navbar-static-top" role="navigation">
		                        <div class="container">
		                            <div class="navbar-header">
		                                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
		                                    <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span><span
		                                        class="icon-bar"></span><span class="icon-bar"></span>
		                                </button>
		                                <a class="navbar-brand" href="#">Board</a>
		                            </div>
		                            <div class="navbar-collapse collapse">
		                                <ul class="nav navbar-nav">
		                                    <li><a href="list">Home</a></li>
		                                </ul>
		                                <ul class="nav navbar-nav navbar-right">
		                                    <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">Account
		                                        <b class="caret"></b></a>
		                                        <ul class="dropdown-menu">
		                                            <li>
		                                                <div class="navbar-content">
		                                                    <div class="row">
		                                                        <div class="col-md-5">
		                                                            <img src="http://placehold.it/120x120"
		                                                                alt="Alternate Text" class="img-responsive" />
		                                                            <p class="text-center small">
		                                                                <a href="#">Change Photo</a></p>
		                                                        </div>
		                                                        <div class="col-md-7">
		                                                            <span>${member.userName}</span>
		                                                            <p class="text-muted small">${member.userId}</p>
		                                                            <div class="divider">
		                                                            </div>
		                                                            <a href="#" class="btn btn-primary btn-sm active">View Profile</a>
		                                                        </div>
		                                                    </div>
		                                                </div>
		                                                <div class="navbar-footer">
		                                                    <div class="navbar-footer-content">
		                                                        <div class="row">

		                                                            <div class="col-md-4">
		                                                            	<button id="memberUpdateBtn" type="button" href="#" class="btn btn-default btn-sm">Edit Account</button>
		                                                            </div>
		                                                            
		                                                            <div class="col-md-4">
		                                                            	<button id="memberDeleteBtn" type="button" href="#" class="btn btn-default btn-sm">Delete Account</button>
		                                                            </div>
		                                                            
		                                                            <div class="col-md-4">
		                                                                <button id="logoutBtn" type="button" href="#" class="btn btn-default btn-sm">Logout</button>
		                                                            </div>
		                                                        </div>
		                                                    </div>
		                                                </div>
		                                            </li>
		                                        </ul>
		                                    </li>
		                                </ul>
		                            </div>
		                        </div>
		                    </div>
		                </div>
		            </div>
		        </div>
		    </div>
		</div>
	</body>


</html>
