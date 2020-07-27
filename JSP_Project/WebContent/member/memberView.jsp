<%@page import="com.member.MemberVO"%>
<%@page import="com.member.MemberDAOImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="member.js"></script>
</head>
<%
	request.setCharacterEncoding("utf-8");
	String userid=(String)session.getAttribute("userid");
	MemberDAOImpl dao=MemberDAOImpl.getInstance();
	MemberVO m=dao.memberView(userid);
%>
<body>
<%=userid %>님 반갑습니다. /<a href="logout.jsp">로그 아웃</a><br>
<a href="../board/list.jsp">게시판으로</a>
<h2>
회원정보 변경/<a href="userDelete.jsp">회원탈퇴</a>
</h2>
<hr>
<form action="updatePro.jsp" method="post">
<input type="hidden" name="userid" value="<%=userid %>">
	<table>
	<thead>
		<tr>
			<td>이름</td>
			<td><input type="text" id="name" name="name" value="<%=m.getName()%>"></td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="password" id="pwd" name="pwd" value="<%=m.getPwd()%>"></td>
		</tr>
		<tr>
			<td>이메일</td>
			<td><input type="text" id="email" name="email" value="<%=m.getEmail()%>"></td>
		</tr>
		<tr>
			<td>전화번호</td>
			<td><input type="text" id="phone" name="phone" value="<%=m.getPhone()%>"></td>
		</tr>
		<tr>
			<td>등급</td>
			<td>
			<input type="radio" name="admin" value="0">일반회원
			<input type="radio" name="admin" value="1">관리자
			<script>
			if(<%=m.getAdmin()%>==0){
				$("input:radio[value='0']").prop("checked", true);
			}else{
				$("input:radio[value='1']").prop("checked", true);				
			}
			</script>
			</td>		
		</tr>
		<tr>
			<td colspan="2" align="center">
			<input type="submit" id="updateBtn" value="수정">
			<input type="reset" value="취소">
		</tr>
	</thead>
	</table>
</form>
</body>
</html>