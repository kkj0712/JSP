<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../js/jquery-3.5.1.min.js"></script>
<script>
$(function(){
	$("#btn").on("click",function(){
		if($("#name").val()==""){
			alert("이름을 입력하세요");
			return false;
		}
		if($("#zipcode").val()==""){
			alert("우편번호을 입력하세요");
			return false;
		}
		if($("#addr").val()==""){
			alert("주소를 입력하세요");
			return false;
		}
		if($("#tel").val()==""){
			alert("전화번호를 입력하세요");
			return false;
		}
		frm.submit();
	});
})
function zipfinder(){
	window.open("zipCheck.jsp","","width=700 height=400");	
}

</script>
</head>
<body>
<a href="list.jsp">전체보기</a> <br>
<form action="insertPro.jsp" method="post" name="frm" id="frm">
<table>
	<tr>
		<td colspan="2">주소록 등록하기</td>
	</tr>
	<tr>
		<td>이름</td>
		<td><input type="text" name="name" id="name" size=15></td>
	</tr>
	<tr>
		<td>우편번호 </td>
		<td><input type="text" name="zipcode" id="zipcode" size=10>
		<input type="button" name="search" value="검색" onclick="zipfinder()"></td>
	<tr>
		<td>주소
		<td colspan="2"><input type="text" name="addr" id="addr" size=30>
		</td>
	</tr>
	</tr>
		<td>전화번호</td>
		<td colspan="2"><input type="text" name="tel" id="tel" size=15></td>
	<tr>
		<td colspan="2">
		<input type="submit" value="등록">
		<input type="reset" value="취소"></td>
	</tr>
</table>
</form>
</body>
</html>