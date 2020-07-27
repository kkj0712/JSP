<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
request.setCharacterEncoding("utf-8"); //method가 post라도 한글이 안깨짐
	String[] hobby=request.getParameterValues("hobby");
%>
<body>
결과 <hr>
이름: <%=request.getParameter("name") %><br>
학번: <%=request.getParameter("studentNum") %><br>
성별: <%=request.getParameter("gender") %><br>
전공: <%=request.getParameter("major")%><br>
<%
	String str="";
	if(hobby!=null){
		for(int i=0;i<hobby.length;i++){
		str+=hobby[i]+" ";
		}
	}
%>
취미: <%=str%>
</body>
</html>