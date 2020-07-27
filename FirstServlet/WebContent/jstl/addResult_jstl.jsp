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
	첫번째: ${param.num1}<br/>
	첫번째: ${param.num2}<br/>
	<hr/>
	결과: ${param.num1+param.num2}<br/>
	<c:set var="no" value="${param.num1}"/>
	no: ${no }<br/>
	<c:out value="${no}"/><br/>
	<hr/>
	<c:choose>
	<c:when test="${no mod 2==0}">
		짝수
	</c:when>
	<c:otherwise>
		홀수
	</c:otherwise>
	</c:choose>
	<hr>
	
	<c:if test="${no%2==0 }">
		짝수
	</c:if>
	<c:if test="${no%2!=0 }">
		홀수
	</c:if>
</body>
</html>