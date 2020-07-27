<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
	request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="student" class="com.exam.StudentBean"/> 
<!-- jsp의 액션태그. StudentBean의 student객체를 만드는 것과 같음-->
<jsp:setProperty property="*" name="student"/>
<!-- id와 name을 동일하게. 데이터들이 연결되도록 -->
<%
	String[]h=student.getHobby();
	String strHobby="";
	for(int i=0;i<h.length;i++){
		strHobby+=h[i]+" ";
	}
%>
</head>
<body>
이름: <jsp:getProperty property="name" name="student"/>
<!-- student 객체의 name을 가져오기 -->
<hr>
이름: <%=student.getName() %> <br>
학번: <%=student.getStudentNum() %> <br>
성별: <%=student.getGender() %> <br>
전공: <%=student.getMajor() %> <br>
취미: <%=strHobby %>
</body>
</html>