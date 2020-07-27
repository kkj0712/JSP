<%@page import="javax.swing.JTextArea"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="intropro.jsp" method="get">
<table border="1">
	<tr>
		<th>성명</th>
		<td><input type="text" name="name"></td>
		<th>성별</th>
		<td><input type="radio" name="gender" value="man" checked id="man">
		<label for="man">남</label>
		<input type="radio" name="gender" value="woman" checked id="woman">
		<label for="woman">여</label>
		</td>
	</tr>
	<tr>
		<th>생년월일</th>
		<td colspan="3"><input type="text" name="year">
		<label for="year">년</label>
		<input type="text" name="month">
		<label for="month">월</label>
		<input type="text" name="day">
		<label for="day">일</label>
		<input type="radio" name="eumyang" value="yang" checked id="yang">
		<label for="yang">양력</label>
		<input type="radio" name="eumyang" value="eum" checked id="eum">
		<label for="eum">음력</label>
		</td>
	</tr>
	<tr>
		<th>주소</th>
		<td colspan="3"><input type="text" name="addr"></td>
	</tr>
	<tr>
		<th>전화번호</th>
		<td colspan="3">
		<input type="text" name="phonehead">
		<label for="phonehead">-</label>
		<input type="text" name="phonemiddle">
		<label for="phonemiddle">-</label>
		<input type="text" name="phoneend">
		</td>
	</tr>
	<tr>
		<th colspan="4" style="background-color: cadetblue">사용가능한 프로그램 선택하기</th>
	</tr>
	<tr>
		<td colspan="4">
		<ol type="A">
			<li><input type="checkbox" name="program" value="한글">한글</li>
			<li><input type="checkbox" name="program" value="한글">포토샵</li>
			<li><input type="checkbox" name="program" value="한글">매크로 미디어 디렉터</li>
			<li><input type="checkbox" name="program" value="한글">드림위버</li>
			<li><input type="checkbox" name="program" value="한글">3D MAX</li>
		</ol>
		</td>
	</tr>
	<tr>
		<th colspan="4" style="background-color: cadetblue">가고싶은 여행지를 모두 선택하세요.</th>
	</tr>
	<tr>
		<td colspan="4">
		<select name="travel" size="3" multiple="multiple">
			<option value="설악산" selected>설악산</option>
			<option value="경포대">경포대</option>
			<option value="토발">토발</option>
		</select>
		</td>
	</tr>
	<tr>
		<th colspan="4" style="background-color: cadetblue">미래의 꿈은 어떠한가요</th>
	</tr>
	<tr>
		<td colspan="4">
		<textarea id="inputbox" rows="10" cols="80">미래의 꿈은 희망입니다...</textarea>
		</td>
	</tr>
	<tr>
		<th colspan="4">
		<input type="button" value="전송" onclick="">
		<input type="reset" value="다시쓰기">
		</th>
	</tr>
</table>

</form>
</body>
</html>