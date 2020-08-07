<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="login.gb" method="post">
  <label for="userid">ID</label>
  <input type="text" id="userid" name="userid">
  <label for="pwd">PW</label>
  <input type="password" id="pwd" name="pwd">
  <input type="submit" value="로그인">
  <span style="color:red">${errMsg}</span>
</form>
</body>
</html>