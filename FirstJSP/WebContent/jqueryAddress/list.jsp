<%@page import="com.jqueryAddress.Address"%>
<%@page import="com.jqueryAddress.JAddressDAO"%>
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
	background-color:  darkgray;
	padding-right: 20px;
}
a:hover { text-decoration: none;  }
a:link {text-decoration: none;  }
a:visited {text-decoration: none; }
</style>
</head>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script>
//searchBtn 이 클릭하면
$(document).ready(function(){
	$("#searchBtn").click(function(){
		$.getJSON("searchPro.jsp",
							  {"field" : $("#field").val(), "word" : $("#word").val()    },
							  function(data){//콜백함수
								  var htmlStr="";
								$.each(data, function(key, val){
									  htmlStr+="<tr>";
	    			        		  htmlStr+="<td>"+val.num+"</td>";
	    			        		  htmlStr+="<td>"+val.name+"</td>";
	    			        		  htmlStr+="<td>"+val.tel+"</td>";
	    			        		  htmlStr+="<td>"+val.addr+"</td>";
	    			        		  htmlStr+="<td onclick=delFunc("+val.num+")> 삭제</td>";
	    			        		  htmlStr+="</tr>";
								})//each
								$("table tbody").html(htmlStr);
							 }//콜백함수
		  ); //getJSON
	});//searchBtn
	
});//document



function delFunc(no){
	if(confirm("정말삭제할까요?")){
		location.href="deletePro.jsp?num="+no;
	}
}
</script>
<%
	request.setCharacterEncoding("utf-8");
	JAddressDAO dao = JAddressDAO.getInstance();
	ArrayList<Address> arr =  dao.addrList();
	int count = dao.getCount();
%>
<body>
<div class="divCss">
    주소록 갯수 : <%=count %> <br>
	<a href="insert.jsp">추가하기</a>  /  
	<a href="list.jsp">전체보기</a>
</div>
 <table class="table table-striped">
  <thead>
    <tr>
      <th scope="col">번호</th>
      <th scope="col">이름</th>
      <th scope="col">전화번호</th>
      <th scope="col">주소</th>
       <th scope="col">삭제</th>
    </tr>
  </thead>
	<tbody>
<%
	for(int i=0; i<arr.size();i++){
%>		
		<tr>
			<th scope="row"><%=arr.get(i).getNum() %></th>
			<td><a href="detail.jsp?num=<%=arr.get(i).getNum() %>"><%=arr.get(i).getName() %></a></td>
			<td><%=arr.get(i).getTel() %></td>
			<td><%=arr.get(i).getAddr() %></td>
			<td onclick="delFunc(<%=arr.get(i).getNum() %>)">삭제</td>
		</tr>
<%		
   }
%>	
</tbody>
</table>
<form  action="list.jsp" name="searchFrm" id="searchFrm">
<select name="field" id="field">
	  <option value="name">이름</option>
	  <option value="tel">전화번호</option>
</select>
<input type="text" name="word" id="word">
<input type="button"  class="btn btn-primary" value="검색"  id="searchBtn">

</form>
</body>
</html>


