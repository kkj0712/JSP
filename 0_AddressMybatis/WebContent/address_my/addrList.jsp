<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table>
<div align="left">
	<a href="insertAction.amy">글쓰기 <a/>/ 개수 (${count}) </span>
</div>
	<thead>
		<tr>
			<td>순서</td>
			<td>번호</td>
			<td>이름</td>
			<td>주소</td>
			<td>전화번호</td>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${arr}" var="arr" varStatus="st">
		<tr>
			<td>${count-st.index}</td>
			<td>${arr.num}</td>
			<td><a href="detail.do?num=${arr.num}">${arr.name}</a></td>
			<td>${arr.addr}</td>
			<td>${arr.tel}</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
	<select name="field" id="field">
	<option value="name">이름</option>
	<option value="tel">전화</option>
	</select>
	<input type="text" name="word" id="word">
	<input type="button" value="검색" id="searchBtn">
</body>
</html>