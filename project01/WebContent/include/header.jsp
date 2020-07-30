<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">                        
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
  <script src="/project01/js/member.js"></script>
</head>
<body>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
  <!-- Brand/logo -->
  <a class="navbar-brand" href="#">
    <h3>LOGO</h3>
  </a>
  
  <!-- Links -->
  <ul class="navbar-nav">
  <c:choose>
  	<c:when test="${empty sessionScope.userid}">
  		<li class="nav-item">
	      <a class="nav-link" href="/project01/member/login.me">로그인</a>
	    </li>
	    <li class="nav-item">
      	  <a class="nav-link" href="/project01/member/insert.me">회원가입</a>
    	</li>
  	</c:when>
  	<c:otherwise>
	  	<li class="nav-item">
	      <a class="nav-link" href="/project01/member/logout.me">로그아웃</a>
	    </li>
	    <li class="nav-item">
      	  <a class="nav-link" href="/project01/member/view.me">정보변경</a>
    	</li>
    	<li class="nav-item">
      	  <a class="nav-link" href="/project01/member/delete.me">회원탈퇴</a>
    	</li>
  	</c:otherwise>
  </c:choose>
        <li class="nav-item">
          <a class="nav-link" href="/project01/board/board.me">게시판</a>
        </li>
  </ul>
  
  <c:if test="${not empty sessionScope.userid}">
  	<span class="navbar-text">
  	 (${sessionScope.userid} 님 반갑습니다.)
  	</span>
  </c:if>
</nav>