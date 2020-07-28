<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
이름11 : ${p.name }<br>
아이디11 : ${p.id }<br>
성별11 : ${p.gender }<br>
직업11 : ${p.job }<br>
메일11 :
<c:forEach items="${p. notice}" var="mail">
  ${mail }
</c:forEach><br>

</body>
</html>



