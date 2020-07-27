<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
	request.setCharacterEncoding("utf-8");
%>
<body>
결과 <hr>
id: <%=request.getParameter("id") %> <br>
pwd: <%=request.getParameter("pwd") %>
</body>
</html>