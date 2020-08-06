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
$(document).ready(function(){
	getData(1, "", "");
	$("#btnSearch").on("click", function(){
		getData(1, $("#field").val(), $("#word").val());
	})//btnSearch
	
})//document

function getData(pageNum, field, word){
	$.get("list", 
			{"pageNum":pageNum, "field":field, "word":word},
			function(d){
			$("#result").html(d);
	})
}//getData

</script>
</head>
<body>
<form action="insert" method="post">
<table>
	<tr>
		<td align="center">글쓴이</td>
		<td>
			<input type="text" size="20" id="writer" name="writer" >
		</td>
	</tr>
	<tr>
		<td align="center">제목</td>
		<td>
			<input type="text" size="20" id="subject" name="subject" >
		</td>
	</tr>
	<tr>
		<td align="center">내용</td>
		<td>
			<textarea rows="5" cols="40" id="content" name="content"></textarea>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="submit" value="submit전송">
		</td>
	</tr>
</table>
</form>
<br/><br/>
<div align="center">
	<form id="search" name="search">
		<select name="field" id="field">
			<option value="name">글쓴이</option>
			<option value="subject">제목</option>
		</select>
		<input type="text" name="word" id="word">
		<input type="button" value="찾기" id="btnSearch">
	</form>
</div>
<br/><br/>
<div align="center" id="result"></div>
<hr>
<div id="view"></div>
</body>
</html>