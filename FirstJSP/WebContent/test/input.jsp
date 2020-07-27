<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function check(){
	if(document.getElementById("name").value==""){
		alert("이름을 입력하세요");
		return;
	}
	if(document.getElementById("studentNum").value==""){
		alert("학번을 입력하세요");
		return;
	}
	var obj=document.getElementsByName("hobby");
	var checkCnt=false;
	for(i=0;i<obj.length;i++){
		if(obj[i].checked){
			checkCnt=true;	
		}
	}
	if(checkCnt==false){
		alert("취미를 선택하세요");
		return;
	}
	frm.submit(); //직접 submit()이라는 메소드를 호출. 액션을 들고 가줌
}
</script>
</head>
<body>
<form action="inputResult.jsp" method="post" name="frm"> 
<!--method="post"하면 선택된 값들이 주소창에 안보임--> <!--method="get"하면 주소창에 선택값들이 표시됨 -->
이름: <input type="text" name="name" id="name"><br> <!-- DOM방식은 id로 접근 -->
학번: <input type="text" name="studentNum" id="studentNum"><br>
성별: 
<!-- 
<input type="radio" name="gender" value="man" checked>남자
<input type="radio" name="gender" value="woman">여자<br>
-->
<input type="radio" name="gender" value="man" checked  id="man">
<label for="man">남자</label> <!-- id, label을 이용하여 글자를 클릭해도 선택됨 -->
<input type="radio" name="gender" value="woman" checked  id="woman">
<label for="woman">여자</label><br>
전공: 
<select name="major">
	<option value="국문학과" selected>국문학과</option>
	<option value="영문학과">영문학과</option>
	<option value="수학과" >수학과</option>
	<option value="정치학과">정치학과</option>
	<option value="체육학과">체육학과</option>
</select>
<br>
취미<br>
<input type="checkbox" name="hobby" value="운동">운동
<input type="checkbox" name="hobby" value="운동1">운동1
<input type="checkbox" name="hobby" value="운동2">운동2
<input type="checkbox" name="hobby" value="운동3">운동3
<input type="checkbox" name="hobby" value="운동4">운동4 <br>
<input type="button" value="보내기" onclick="check()"> 
<!-- submit=form안에 있는 값들을 들고 액션한테 가라는 뜻. 그전에 검사하기-->
<!-- button=모양만 버튼. 아무 역할이 없음. 그래서 뒤에 onclick="check()"-->
<input type="reset" value="취소">
</form>
</body>
</html>