<%@page import="com.member.MemberVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.member.MemberDAOImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" 
href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="member.js"></script>
<style>
div.divCss{
	text-align: right;
	background-color:  darkgray;
	padding-right: 20px;
}
a:hover { text-decoration: none;  }
a:link {text-decoration: none;  }
a:visited {text-decoration: none; }
</style>
</head>
<div class="divCss">
<a href="memberForm.jsp">추가하기 /</a>
<a href="memberList.jsp">전체보기</a>
</div>
<%
	request.setCharacterEncoding("utf-8");
	MemberDAOImpl dao=MemberDAOImpl.getInstance();
	ArrayList<MemberVO> arr=dao.memberList();
	String suserid=(String)session.getAttribute("userid");
	int count=dao.memberCount();
%>
<body>
<div align="right">
	<a href="memberView.jsp"><%=suserid%></a> 관리자님 반갑습니다.
	<a href="logout.jsp">로그아웃</a><br>
	<a href="../board/list.jsp">게시판 글 보기</a>
	<br>전체회원 수: <span id="cntSpan"><%=count %></span>
</div>
	<table class="table table-striped">
	<thead>
		<tr>
			<th scope="col">이름</th>
			<th scope="col">아이디</th>
			<th scope="col">전화번호</th>
			<th scope="col">이메일</th>
			<th scope="col">구분</th>
			<th scope="col">삭제</th>
		</tr>
	</thead>
	<tbody>
<%
	for(MemberVO m:arr){
		String mode=m.getAdmin()==0?"일반회원":"관리자";
%>
		<tr>
			<th scope="row"><%=m.getName() %></th>		
			<td><%=m.getUserid() %></td>		
			<td><%=m.getPhone() %></td>		
			<td><%=m.getEmail() %></td>		
			<td><%=mode %></td>	
			<td><a href="javascript:del('<%=m.getUserid() %>','<%=mode%>')">삭제</a></td>	
		</tr>		
<%
	}
%>	
	</tbody>
	</table>
</body>
</html>