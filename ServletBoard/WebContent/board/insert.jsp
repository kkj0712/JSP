<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<script>
//취소 함수
function back(){
	if($("#writer").val()!="" || $("#writer").val()!="" || $("#writer").val()!=""){
		if(confirm("글 작성을 취소하시겠습니까? 작성하시던 글은 저장되지 않습니다.")){
			location.href="list.jsp";
		}
	}else{
		location.href="list.jsp";
	}
}
//공백확인 함수
function check(){
	if($("#writer").val()==""){
		alert("글쓴이를 입력하세요");
		$("#writer").focus();
		return false;
	}
	if($("#subject").val()==""){
		alert("제목을 입력하세요");
		$("#subject").focus();
		return false;
	}
	if($("#content").val()==""){
		alert("내용을 입력하세요");
		$("#content").focus();
		return false;
	}
	return true;
}
</script>
</head>
<body>
<div class="container">
<h2>글작성</h2>
<form action="insert" method="post" onsubmit="return check();">
      <label>글쓴이</label>
    <div class="form-group">
      <input type="text" size="20" id="writer" name="writer" >
    </div>
      <label>제목</label>
    <div class="form-group">
      <input type="text" size="37" id="subject" name="subject" >
    </div>
      <label>내용</label>
    <div class="form-group">
      <textarea rows="5" cols="40" id="content" name="content"></textarea>
    </div>
   	 	<button type="submit" class="btn btn-primary">전송</button>
   	 	<button type="reset" class="btn btn-gray" onclick="javascript:back()">취소</button>
</form>
</div>
</body>
</html>