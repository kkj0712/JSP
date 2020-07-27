<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!--다운받은 제이쿼리 이용<script src="../js/jquery-3.5.1.min.js"></script> --> 
<!--제이쿼리 주소이용 -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script>
/*
$(document).ready(function(){ //function이 로드될때까지 기다려라
	$("#btn").click(function(){ //btn이 클릭되면 함수를 호출할건데 그 함수의 이름 없이 바로 내용이 들어간다.
		alert("test");
	});	
})
*/
$(function(){
	$("#btn").on("click",function(){ //btn이 클릭되면 함수를 호출할건데 그 함수의 이름 없이 바로 내용이 들어간다.
		if($("#name").val()==""){ //get.ElementByName과 같은 뜻. val()은 밸류 값을 가져오는 함수
			alert("이름을 입력하세요");
			return false;
		} //if
		if($("#studentNum").val()==""){
			alert("학번을 입력하세요");
			return false;
		} //if
		if($("input[name='hobby']:checked").length==0){ //태그는 #안붙임. []는 속성
			alert("취미를 입력하세요");
			return false;
		} //if
		//frm.submit();
		$("#frm").submit();
	}); //click
}) //function
</script>
</head>
<body>
<form action="inputResult.jsp" method="post" name="frm" id="frm"> 
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
<select name="major" id="major">
	<option value="국문학과">국문학과</option>
	<option value="영문학과">영문학과</option>
	<option value="수학과">수학과</option>
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
<input type="button" value="보내기" id="btn"> 
<input type="reset" value="취소">
</form>

</body>
</html>