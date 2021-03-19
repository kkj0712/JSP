<%@page import="com.address.Address"%>
<%@page import="com.address.AddressDAO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<style>
div.divCss{
	text-align: right;
	background-color: darkgray;
	padding-right: 20px;
}
a:hover{text-decoration: none;}
a:link{text-decoration: none;}
a:visited{text-decoration: none;}
</style>
</head>
<script src="../js/jquery-3.5.1.min.js"></script>
<script>
	function searchCheck(){
		if($("#word").val()==""){
			alert("검색어를 입력하세요");
			$("#word").focus();
			return false;
		}
		$("#searchFrm").submit();
	}
	
	function delFunc(no){
		if(confirm("정말 삭제할까요?")){
			location.href="deletePro.jsp?num="+no;
		}
	}
</script>
<%
	request.setCharacterEncoding("utf-8");
	String word ="";
	String field="";
	if(request.getParameter("word")!=null){
		field=request.getParameter("field");
		word=request.getParameter("word");
	}
	AddressDAO dao=AddressDAO.getInstance();
	ArrayList<Address> arr=dao.addrList(field, word);
	int count=dao.getCount(field, word); //전체 개수 혹은 검색한 개수 출력
%>
<body>
<div class="divCss"> <!-- 문단태그 -->
	주소록 갯수: <%=count %><br>
	<a href="insert.jsp">추가하기 /</a> 
	<a href="list.jsp">전체보기</a>
</div>
<table class="table">
        <thead class="thead-dark"> 				<!-- 이미 정의되어 있는 테이블 스타일 -->
          <tr>
            <th scope="col">번호</th>
            <th scope="col">이름</th>
            <th scope="col">우편번호</th>
            <th scope="col">주소</th>
            <th scope="col">전화번호</th>
            <th scope="col">삭제</th>
          </tr>
        </thead>
	<tbody>
	<%
		for(int i=0;i<arr.size();i++){
	%> <!-- 태그는 스크립틀릿안에 들어가면 안되므로 따로 빼줌 -->
		<tr>
			<th scope="row"><%=arr.get(i).getNum() %></th>
			<td>
			<a href="detail.jsp?num=<%=arr.get(i).getNum() %>">
			<%=arr.get(i).getName() %></a></td> 
			<td><%=arr.get(i).getZipcode() %></td>
			<td><%=arr.get(i).getAddr() %></td>
			<td><%=arr.get(i).getTel() %></td>
			<td onclick="delFunc(<%=arr.get(i).getNum()%>)">삭제</td>
		</tr>
	<%} //end for문
	%>
	</tbody>
</table>
<form action="list.jsp" name="searchFrm" id="searchFrm"> <!-- list.jsp가 새로고침됨. 문제가 발생할 수 있으니 '비동기' 아이작 기술을 사용. 특정 영역만 새로고침 -->
	<select name="field">
		<option value="name">이름</option>
		<option value="tel">전화번호</option>
	</select>
	<input type="text" name="word" id="word">
	<input type="button" value="검색" class="btn btn-primary" onclick="searchCheck()">
</form>
</body>
</html>