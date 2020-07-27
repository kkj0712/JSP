<%@page import="com.exam.DateBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	DateBean bean=new DateBean();
%>
<%=bean.getToday()%>
<hr>
<%=bean.getDay() %>
</body>
</html>