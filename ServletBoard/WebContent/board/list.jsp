<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</head>
<body>
<h4>게시물 (<span id="cntSpan">${count}</span>)</h4>
<table class="table table-hover">
	<thead>
		<tr>
			<th>번호</th>
			<th>seq</th>
			<th>글쓴이</th>
			<th>제목</th>
			<th>내용</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${board}" var="board" varStatus="st">
		<tr>
			<td>${rowNo-st.index}</td>
			<td>${board.num}</td>
			<td>${board.writer}</td>
			<td>${board.subject}</td>
			<td>${board.content}</td>
			<td>${board.reg_date}</td>
			<td>${board.readcount}</td>
		</tr>
		</c:forEach>
	</tbody>
</table>
<div align="center">
	<c:if test="${pu.startPage>pu.pageBlock }"> <!-- 이전 -->
		<a href="javascript:getData(${pu.startPage-pu.pageBlock},'${pu.field}','${pu.word}')">[이전]</a>
	</c:if>
	<c:forEach begin="${pu.startPage}" end="${pu.endPage}" var="i"><!-- 페이지 출력 -->
		<c:if test="${i==pu.currentPage}"> <!-- 현재 페이지 -->
			<c:out value="${i}"/>
		</c:if>
		<c:if test="${i!=pu.currentPage}"> <!-- 현재 페이지 아닌 경우 링크 부여-->
			<a href="javascript:getData(${i},'${pu.field}','${pu.word}')">${i}</a>
		</c:if>
	</c:forEach>
	<c:if test="${pu.endPage<pu.totPage}"> <!-- 다음-->
		<a href="javascript:getData(${pu.endPage+1},'${pu.field}','${pu.word}')">[다음]</a>
	</c:if>
</div>
</body>
</html>