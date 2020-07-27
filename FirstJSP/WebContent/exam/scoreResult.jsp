<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
	String name=request.getParameter("name");
	int kor=Integer.parseInt(request.getParameter("kor"));
	int eng=Integer.parseInt(request.getParameter("eng"));
	int math=Integer.parseInt(request.getParameter("math"));
	int tot=kor+eng+math;
	int avg=tot/3;
	String grade="";
	switch(avg/10){
	case 10: 
	case 9: grade="A학점"; break;
	case 8: grade="B학점"; break;
	case 7: grade="C학점"; break;
	default : grade="F학점";
	}
	out.println("name");
	out.println("kor");
	out.println("eng");
	out.println("math");
%>
<body>
결과<hr>
이름: <%=name %><br>
국어: <%=kor %><br>
영어: <%=eng %><br>
수학: <%=math %><br>
총점: <%=tot%><br>
평균: <%=avg%><br>
학점: <%=grade %><br>
</body>
</html>