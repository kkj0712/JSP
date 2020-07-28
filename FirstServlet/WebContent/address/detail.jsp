<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="update.do" method="post" name="frm">
<input type="hidden" name="num" value="${dto.num}">
<table>
	<tr>
		<td colspan="2">주소록 수정하기</td>
	</tr>
	<tr>
		<td>이름</td>
		<td><input type="text" name="name" 
		value="${dto.name}"></td>
	</tr>
	<tr>
		<td>우편번호 </td>
		<td><input type="text" name="zipcode" size=10
		value="${dto.zipcode}">
		<input type="button" name="search" value="검색" onclick="zipfinder()"></td>
	<tr>
		<td>주소
		<td><input type="text" name="addr" size=30
		value="${dto.addr}"></td>
	</tr>
	<tr>
		<td>전화번호</td>
		<td><input type="text" name="tel" 
		value="${dto.tel}"></td>
	</tr>
	<tr>
		<td colspan="2">
		<input type="submit" value="수정" onclick="location.href='update.do?num=${dto.num}'">
		<input type="button" value="삭제" onclick="location.href='delete.do?num=${dto.num}'"> <!-- del()이라는 함수를 부른다. -->
		<input type="reset" value="취소">
		</td>
	</tr>
</table>
</form>
</body>
</html>