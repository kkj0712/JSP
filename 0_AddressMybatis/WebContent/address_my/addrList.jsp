<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script>
$(document).ready(function(){
	$("#btnSearch").click(function(){
		$.getJSON("searchAction.amy", 
				 {"field":$("#field").val(), "word":$("#word").val()},
				 function(data){
					 fsuccess(data);
				 }
		); //getJSON
	})//btnSearch
	
	fsuccess=function(data){
	 	$("#count").html("개수:"+data.count);
		var htmlStr="";
		$.each(data.arr, function(key,val){
			htmlStr+="<tr>";
			htmlStr+="<td>순서</td>";
			htmlStr+="<td>"+val.num+"</td>";
			htmlStr+="<td><a href='viewAction.amy?num="+val.num+"'>"+val.name+"</a></td>";
			htmlStr+="<td>"+val.addr+"</td>";
			htmlStr+="<td>"+val.tel+"</td>";
			htmlStr+="<td onclick='fdelete("+val.num+")'>삭제</td>";
			htmlStr+="</tr>";
		});
		$("table tbody").html(htmlStr);
	}//fsuccess
	
});//document

function fdelete(num){
	if(confirm("정말 삭제하시겠습니까?")){
		$.getJSON("deleteAjaxAction.amy", {"num":num}, 
				 function(data){
					fsuccess(data);
				 }
		)//getJSON
	}
}//fdelete
</script>
</head>
<body>
<table>
<a href="insertAction.amy">글쓰기</a>/<a href="listAction.amy">전체보기</a>
<div id="count">개수:${count}</div>
	<thead>
		<tr>
			<td>순서</td>
			<td>번호</td>
			<td>이름</td>
			<td>주소</td>
			<td>전화번호</td>
			<td>삭제</td>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${arr}" var="arr" varStatus="st">
		<tr>
			<td>${count-st.index}</td>
			<td>${arr.num}</td>
			<td><a href="viewAction.amy?num=${arr.num}">${arr.name}</a></td>
			<td>${arr.addr}</td>
			<td>${arr.tel}</td>
			<td onclick="fdelete(${arr.num})">삭제</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
<div align="left">
	<form name="search" id="search">
		<select name="field" id="field">
			<option value="name">이름</option>
			<option value="tel">전화</option>
		</select>
		<input type="text" name="word" id="word">
		<input type="button" value="검색" id="btnSearch">
	</form>
</div>
</body>
</html>