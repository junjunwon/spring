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
	<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<title>게시판</title>
 	
	
</head>

<body>
  <header>
    <div>
      <h1><a href="#"><span class="hide">맛집커뮤니티</span></a></h1>
      <nav>
        <ul>
       	  <%@include file="nav_bar.jsp" %>

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
	            <li><a href="list">board</a></li>
	            <li><a href="board_temp">board_temp</a></li>
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
				       	  <%@include file="nav.jsp" %>
				        </ul>
				    </nav>
			    </div>
			    <br>
			    
				<div class="review_box_list">
		
					<form role="form" method="get">
						<div class="table-responsive">
							<table class="table table-striped">
								<tr><th>번호</th><th>제목</th><th>작성자</th><th>등록일</th><th>조회수</th></tr>
								
								<c:forEach items="${list}" var = "list">
										<tr>
											<td><c:out value="${list.bno}" /></td>
											<td>
												<a href="/board/readView?bno=${list.bno}&
																		page=${scri.page}&
																		perPageNum=${scri.perPageNum}&
																		searchType=${scri.searchType}&
																		keyword=${scri.keyword}"><c:out value="${list.title}"/></a>
											</td>
										
											<td><c:out value="${list.writer}" /></td>
											<td><fmt:formatDate value="${list.regdate}" pattern="yyyy-MM-dd"/></td>
											<td><c:out value="${list.hit}" /></td>
										</tr>
								</c:forEach>
									
							</table>
						</div>
						
						<div class="search row">
							<div class="col-xs-2 col-sm-2">
							    <select name="searchType" class="form-control">
							      <option value="n"<c:out value="${scri.searchType == null ? 'selected' : ''}"/>>-----</option>
							      <option value="t"<c:out value="${scri.searchType eq 't' ? 'selected' : ''}"/>>제목</option>
							      <option value="c"<c:out value="${scri.searchType eq 'c' ? 'selected' : ''}"/>>내용</option>
							      <option value="w"<c:out value="${scri.searchType eq 'w' ? 'selected' : ''}"/>>작성자</option>
							      <option value="tc"<c:out value="${scri.searchType eq 'tc' ? 'selected' : ''}"/>>제목+내용</option>
							    </select>
						    </div>
						    
						    <div class="col-xs-10 col-sm-10">
						    	<div class="input-group">
						    		<input type="text" name="keyword" id="keywordInput" value="${scri.keyword }" class="form-control"/>
						    		<span class="input-group-btn">
						    			<button id="searchBtn" type="button" class="btn btn-default">검색</button>
						    		</span>
						    	</div>
						    </div>
						    
						    
		
						    <script>
						    	$(function(){
						    		$('#searchBtn').click(function(){
						    			self.location="list"+'${pageMaker.makeQuery(1)}'+ "&searchType="+$("select option:selected").val()
						    							+"&keyword="+encodeURIComponent($('#keywordInput').val());
						    		});
						    	});
						    </script>
						    
						</div>
						<div class="col-md-offset-3">
						  <ul class="pagination">
						    <c:if test="${pageMaker.prev}">
						    	<li><a href="list${pageMaker.makeSearch(pageMaker.startPage - 1)}">이전</a></li>
						    </c:if> 
						
						    <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
						    	<li><a href="list${pageMaker.makeSearch(idx)}">${idx}</a></li>
						    </c:forEach>
						
						    <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
						    	<li><a href="list${pageMaker.makeSearch(pageMaker.endPage + 1)}">다음</a></li>
						    </c:if> 
						  </ul>
						</div>
						
					</form>
				</div>
			</div>
		</div>
	</main>
</body>
</html>