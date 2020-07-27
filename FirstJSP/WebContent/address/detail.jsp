<%@page import="com.address.Address"%>
<%@page import="com.address.AddressDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../js/jquery-3.5.1.min.js"> </script> <!-- cdn방식 -->
<%
	request.setCharacterEncoding("utf-8");
	int num=Integer.parseInt(request.getParameter("num"));
	AddressDAO dao=AddressDAO.getInstance();
	Address address=dao.addrDetail(num);
%>
<script>
//query 이용
$(document).ready(function(){ //document.ready를 통해 메소드를 로드해야함. 
	$("#deleteBtn").click(function(){
		if(confirm("정말 삭제할까요?")){
			//location.href="deletePro.jsp?num=<%=num%>"; 
			$(location).attr("href","deletePro.jsp?num=<%=num%>");
		}
	});
})

function del(){
	if(confirm("정말 삭제할까요?")){
		location.href="deletePro.jsp?num=<%=num%>"; 
	}
}
//매개변수 있는 함수
function dels(no){
	if(confirm("정말 삭제할까요?")){
		location.href="deletePro.jsp?num="+no;
	}
}
function zipfinder(){
	window.open("zipCheck.jsp","","width=700 height=400");	
}
</script>
</head>
<body>
<form action="updatePro.jsp" method="post" name="frm">
<input type="hidden" name="num" value=<%=num %>> <!-- hidden: 사용자에겐 보이지 않지만 서버한테 전달됨 -->>
<table>
	<tr>
		<td colspan="2">주소록 수정하기</td>
	</tr>
	<tr>
		<td>이름</td>
		<td><input type="text" name="name" 
		value="<%=address.getName()%>"></td>
	</tr>
	<tr>
		<td>우편번호 </td>
		<td><input type="text" name="zipcode" size=10
		value="<%=address.getZipcode()%>">
		<input type="button" name="search" value="검색" onclick="zipfinder()"></td>
	<tr>
		<td>주소
		<td><input type="text" name="addr" size=30
		value="<%=address.getAddr()%>"></td>
	</tr>
	<tr>
		<td>전화번호</td>
		<td><input type="text" name="tel" 
		value="<%=address.getTel()%>"></td>
	</tr>
	<tr>
		<td colspan="2">
		<input type="submit" value="수정">
		<input type="button" value="삭제" onclick="del()"> <!-- del()이라는 함수를 부른다. -->
		<input type="button" value="매개변수삭제" onclick="dels(<%=num%>)"> <!-- 인자가 있는 함수를 부른다.파라미터값이 하나--> 
		<input type="button" value="jquery삭제" id="deleteBtn"> <!-- deleteBtn이라는 아이디 값을 하나 준다. --> 
		<input type="reset" value="취소">
		</td>
	</tr>
</table>
</form>
</body>
</html>