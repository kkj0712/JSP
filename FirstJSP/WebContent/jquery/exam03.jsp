<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../js/jquery-3.5.1.min.js"></script>
<script>
$(document).ready(function(){
	$("#b1").click(function(){
		$.get("data.txt", function(data,status){ //중간값 생략. 결과값만 가지고 옴
				//alert("data: "+data+"\n status:"+status);		
				if(status=="success"){
					alert("성공입니다.");
				}
				var str="데이터: "+data+"\n 처리상태:"+status;
				$("#result").text(str); 
			}
		); //get
	}) //b1
	$("#b2").click(function(){
		var htmlStr="";
		$.getJSON("data.txt", function(data){
			//alert(data.length);
			$.each(data,function(key,val){ //jquery의 반복문
				htmlStr+="회원번호: "+val.memberNumber+"<br>";
				htmlStr+="번호: "+val.irum+"<br>";
				htmlStr+="이미지: "+val.picture+"<hr>";
			})
			$("#result").html(htmlStr);
		}); //json형태로 이미 파싱되어 넘어옴. 한번더 파싱할 필요가 없음
	}) //b2
}); //document

</script>
</head>
<body>
<button id="b1">결과</button>
<button id="b2">결과2</button>
<div id="result"></div> <!-- 결과값이 표시될 영역 지정 -->
</body>
</html>