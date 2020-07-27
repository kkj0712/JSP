<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
table{
	width: 600px;
	heigh: 400px;
}
table, th, td{
	border: 1px solid gray;
}
th.thColor{
	background-color: skyblue;
}
ol.olType{
	list-style-type: upper-roman;
}
</style>
<table>
</head>
<body>
<form action="introProc.jsp" method="post">
	<tr>
		<th>성명</th>
		<td><input type=text name=name></td>
		<th>성별</th>
		<th><input type=radio name="man" value="남" checked id="남"> 
			<label for="남">남</label>
		    <input type=radio name="man" value="여" checked id="여">
			<label for="여">여</label></th>
	</tr>
	<tr>
		<th>생년월일</th>
		<td colspan=3><input type=text name=year size=15>년 
		<input type=text name=month  size=5>월 
		<input type=text name=day size=5>일 
		<input type="radio" name="yy" value="양력" checked  id="양력">
		<label for="양력">양력</label>
		<input type="radio" name="yy" value="음력" checked id="음력">
		<label for="음력">음력</label></td>
	</tr>
	<tr>
		<th>주소</th>
		<td colspan=3><input type=text size=55 name=addr ></td>
	</tr>
	<tr>
		<th>전화번호</th>
		<td colspan=3><input type=text size=15 name=phone> - <input type=text size=15 name=phone1 > - <input type=text size=15 name=phone2 ></td>
	</tr>
	<tr>
		<th colspan=4 class="thColor">사용가능한 프로그램 선택하기</th>
	</tr>
	<tr>
		<td colspan=4>
			<ol class="olType">
				<li><input type=checkbox name="pgm" value="한글">한글
				<li><input type=checkbox name="pgm" value="포토샵">포토샵
				<li><input type=checkbox name="pgm" value="매크로 미디어 디렉터">매크로 미디어 디렉터
				<li><input type=checkbox name="pgm" value="드림위버">드림위버
				<li><input type=checkbox name="pgm" value="3D MAX">3D MAX</li>
			</ol>
		</td>
	</tr>
	<tr>
		<th colspan=4 class="thColor">가고싶은 여행지를 모두 선택하세요.</th>
	</tr>
	<tr>
		<td colspan=4><select size=3 name=play multiple>
				<option value="설악산" selected>설악산
				<option value="경포대">경포대
				<option value="토발">토발
				<option value="거제도">거제도
				<option value="변산반도">변산반도
				<option value="단양8경">단양8경
				</select>
		</td>
	</tr>
	<tr>
		<th colspan=4 class="thColor">미래의 꿈은 어떠한가요</th>
	</tr>	
	<tr>
		<td colspan=4>
			<textarea cols=50 rows=5 name="memo">미래의 꿈은 희망입니다.</textarea>
	</tr>
	<tr>
		<th colspan=4><input type=submit  value="전송" ><input type=reset name=reset value="다시쓰기"></th>
	</tr>

</table>
</form>
</body>
</html>