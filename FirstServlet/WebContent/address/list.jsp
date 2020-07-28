<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>
<table>
<div align="right">
	<a href="insert.do">회원등록 </a>/주소록 개수: <span id="cntSpan">${count}</span>
</div>
	<thead>
		<tr>
			<td>번호</td>
			<td>이름</td>
			<td>주소</td>
			<td>전화번호</td>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${listArr}" var="address">
		<tr>
			<td>${address.num}</td>
			<td><a href="detail.do?num=${address.num}">${address.name }</a></td>
			<td>${address.addr}</td>
			<td>${address.tel}</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
	<select name="field" id="field">
	<option value="name">이름</option>
	<option value="tel">전화</option>
	</select>
	<input type="text" name="word" id="word">
	<input type="button" value="검색" id="searchBtn">
	<script type="text/javascript">
		$("#searchBtn").click(function(){
			if($("#word").val()==""){
				alert("검색어를 입력하세요");
				$("#word").focus();
				return false;
			}
			$.getJSON("search.do",
					{"field":$("#field").val(),
					 "word":$("#word").val()},
					function(data){
						var htmlStr="";
						$.each(data.searchArr,function(key,val){
							htmlStr+="<tr>";
							htmlStr+="<td>"+val.num+"</td>";
							htmlStr+="<td>"+val.name+"</td>";
							htmlStr+="<td>"+val.addr+"</td>";
							htmlStr+="<td>"+val.tel+"</td>";
							htmlStr+="</tr>"
						})//each
						$("table tbody").html(htmlStr);
						$("#cntSpan").text(data.searchCount.scount);
					}//콜백함수
			)//getJSON
		})//searchBtn
	</script>
</body>
</html>