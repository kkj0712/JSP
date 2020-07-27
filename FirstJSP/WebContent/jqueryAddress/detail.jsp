<%@page import="com.jqueryAddress.Address"%>
<%@page import="com.jqueryAddress.JAddressDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<%
request.setCharacterEncoding("utf-8");
int num =Integer.parseInt(request.getParameter("num"));
JAddressDAO dao= JAddressDAO.getInstance();
Address  address=dao.addrDetail(num);
%>
<script>
// query 이용
$(document).ready(function(){
	$("#deleteBtn").click(function(){
		if(confirm("정말 삭제할까요?")){
		 // location.href="deletePro.jsp?num=<%=num%>";
		 $(location).attr("href","deletePro.jsp?num=<%=num%>");
		}
	});
	
});


function del(){
	if(confirm("정말 삭제할까요?")){
	    location.href="deletePro.jsp?num=<%=num%>";
	}
}
// 매개변수 있는 함수
function dels(no){
	if(confirm("정말삭제할까요?")){
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
<input type="hidden"  name="num" value=<%=num %>>
<table>
		<tr>
				<td colspan="2">주소록 수정하기</td>
		</tr>
		<tr>
				<td>이름</td>
				<td><input type="text" name="name" 
				    value=<%=address.getName() %>></td>
		</tr>
		<tr>
				<td>우편번호</td>
				<td><input type="text" name="zipcode" size=7 
				          value="<%=address.getZipcode()%>">
						<input type="button" value="검색"  onclick="zipfinder()">
				</td>
		</tr>
		<tr>
				<td>주소</td>
				<td><input type="text" name="addr" size=50
				          value="<%=address.getAddr() %>"></td>
		</tr>
		<tr>
				<td>전화번호</td>
				<td><input type="text" name="tel"
				          value="<%=address.getTel() %>"></td>
		</tr>
		<tr>
		<td colspan="2">
				<input type="submit" value="수정">
				<input type="button" value="삭제" onclick="del()">
				<input type="button" value="매개변수삭제" onclick="dels(<%=num%>)">
				<input type="button" value=" jquery삭제" id="deleteBtn">
				<input type="reset" value="취소">
		</td>
		</tr>
</table>

</form>
</body>
</html>

