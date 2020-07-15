<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <!-- <link rel="stylesheet" href="community.css"> -->
  <link rel="stylesheet" href="/resources/css/board_temp.css">
  <title>오늘 뭐 먹지? -직장인 편-</title>
</head>
<body>

  <header>
    <div>
      <h1><a href="#"><span class="hide">맛집커뮤니티</span></a></h1>
      <%@include file="nav_bar.jsp" %>

    </div>
  </header>

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
          <ul>
            <p><li><a href="list">board</a></li></p>
            <li><a href="board_temp">board_temp</a></li>
            <li><a href="#">게시판3</a></li>
            <li><a href="#">게시판4</a></li>
            <li><a href="#">게시판5</a></li>
          </ul>
        </div>
      </div>

      <div class="review_box">
        <div class="review_box_list">
          <h3>회원님을 위한 맛집 리스트</h3>
          <div class="review_box_list--user">
            <div class="user">
              <a href="#"><img src="" alt=""><span class="hide">유저로고</span></a>
              <a href="#">KBS N</a>
              <a href="#">팔로우</a>
              <a href="#">6월 10일 오후 12:00 <span>지구</span></a>
              <button><span class="hide">더보기</span></button>
            </div>
            <div class="user_review">
              <h4>맛집탐방 가로수길 EP.08 어디가볼까?</h4>
              <p>오늘은 어디를 가서먹을까낭ㅎㅎㅎㅎㅎㅎㅎ</p>
              <div class="user_review--map"></div>
              <div class="user_review--reply">
                <button>댓글달기</button>
              </div>
            </div>
          </div>
          <div class="review_box_list--user">
            <div class="user">
              <a href="#"><img src="" alt=""><span class="hide">유저로고</span></a>
              <a href="#">KBS N</a>
              <a href="#">팔로우</a>
              <a href="#">6월 10일 오후 12:00 <span>지구</span></a>
              <button><span class="hide">더보기</span></button>
            </div>
            <div class="user_review">
              <h4>맛집탐방 가로수길 EP.08 어디가볼까?</h4>
              <p>오늘은 어디를 가서먹을까낭ㅎㅎㅎㅎㅎㅎㅎ</p>
              <div class="user_review--map"></div>
              <div class="user_review--reply">
                <button>댓글달기</button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </main>

</body>
</html>
