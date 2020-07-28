<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
    request.setCharacterEncoding("utf-8");
    int num1=Integer.parseInt(request.getParameter("num1"));
    int num2=Integer.parseInt(request.getParameter("num2"));
    int result=num1+num2;
%>
</head>
<body>
    덧셈결과: <%=result%><br>
    뺄셈결과: <%=num1-num2 %><br>
</body>
</html>