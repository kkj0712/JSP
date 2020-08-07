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
	getData(1, "", ""); //페이지 로드 시 전체 리스트 보기
	
	$("#btnSearch").on("click", function(){//검색버튼 클릭
		getData(1, $("#field").val(), $("#word").val());
		
	})//btnSearch
	
	$("#send").click(function(){ //전송버튼 클릭
		if(${sessionScope.login==null}){
			alert("로그인 필요");
			return false;
		}
		var name=$("#name").val();
		var content=$("#content").val();
		var grade=$("input:radio[name=grade]:checked").val();
		var postString="name="+name+"&content="+content+"&grade="+grade;
		$.ajax({
			type:	"post",
			url :	"create.gb",
			data:	postString,
			success:function(d){
				$("#result").html(d);
			},
			beforeSend: showRequest, //빈칸 확인
			error: function(e){
				alert("error:"+e);
			}
		})//ajax
	})//send
})//document

//전체보기 함수
function getData(pageNum, field, word){
	$.get("list.gb", 
			{"pageNum":pageNum, "field":field, "word":word},
			function(d){
			$("#result").html(d);
	})
}//getData

//빈칸 확인
function showRequest(){
	if($("#name").val()==""){
		alert("이름을 입력하세요");
		$("#name").focus();
		return false;
	}
	if($("#content").val()==""){
		alert("내용을 입력하세요");
		$("#content").focus();
		return false;
	}
	if($("input:radio[name=grade]:checked").length==0){
		alert("평가를 해주세요");
		return false;
	}
	return true;
}//showRequest

//글자수 초과 함수
function textCount(obj,target){ //target: nameCount, contentCount
	var len=$("#"+obj.id).val().length;
	if(obj.size==len){
		alert("글자수 초과");
		return false;
	}
	$("#"+target).text(len);
}//textCount

//상세보기 함수
function fview(num){
	$.getJSON ("view.gb", {"num": num}, function(data){
				var htmlStr="<table align='center' border='1'>";
					htmlStr+="<tr>";
					htmlStr+="<th>번호</th>";
					htmlStr+="<td>"+data.num+"</td>";
					htmlStr+="</tr>";
					htmlStr+="<tr>";
					htmlStr+="<th>글쓴이</th>";
					htmlStr+="<td>"+data.name+"</td>";
					htmlStr+="</tr>";
					htmlStr+="<tr>";
					htmlStr+="<th>내용</th>";
					htmlStr+="<td>"+data.content+"</td>";
					htmlStr+="</tr>";
					htmlStr+="<tr>";
					htmlStr+="<th>평가</th>";
					htmlStr+="<td>"+data.grade+"</td>";
					htmlStr+="</tr>";
					htmlStr+="<th>작성일</th>";
					htmlStr+="<td>"+data.created+"</td>";
					htmlStr+="</tr>";
					htmlStr+="<tr>";
					htmlStr+="<th>ip주소</th>";
					htmlStr+="<td>"+data.ipaddr+"</td>";
					htmlStr+="</tr>";
					htmlStr+="</table>";
				$("#view").html(htmlStr); 
			}//콜백함수
	)
} //fview

//삭제 함수
function fdelete(num, name){
	if(confirm("["+name+"]의 게시물을 삭제할까요?")){
		$.get ("delete.gb?num="+num, function(data){
			$("#result").html(data);
		})
	}
}
</script>
</head>
<body>
<c:if test="${sessionScope.login==null}">
	<a href="login.jsp">로그인</a>
</c:if>
<c:if test="${sessionScope.login!=null}">
	${login}님 반갑습니다.
	<a href="logout.gb">로그아웃</a>
</c:if>
<br/><br/>
<form action="create.gb" method="post">
<table align="center" width=900px>
	<tr>
		<td align="center">글쓴이</td>
		<td>
			<input type="text" size="20" maxlength="20" id="name" name="name" onKeyup="textCount(this,'nameCount')">
			*20글자이내 (<span id="nameCount" style="color:red">0</span>)
		</td>
	</tr>
	<tr>
		<td align="center">내용</td>
		<td>
			<input type="text" size="70" maxlength="70" id="content" name="content" onKeyup="textCount(this,'contentCount')">
			*70글자이내 (<span id="contentCount" style="color:red">0</span>)
		</td>
	</tr>
	<tr>
		<td align="center">평가</td>
		<td>
			<input type="radio" name="grade" value="excellent">아주잘함(excellent)
			<input type="radio" name="grade" value="good">잘함(good)
			<input type="radio" name="grade" value="normal">보통(normal)
			<input type="radio" name="grade" value="fail">노력(fail)
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="submit" value="submit전송">
			<input type="button" value="ajax버튼 전송" id="send">
		</td>
	</tr>
</table>
</form>
<br/><br/>
<div align="center">
<form name="search" id="search">
	<select name="field" id="field">
		<option value="name">이름</option>
		<option value="content">내용</option>
	</select>
	<input type="text" name="word" id="word">
	<input type="button" value="찾기" id="btnSearch">
</form>
</div>
<br/><br/>
<div id="result" align="center"></div>
<hr>
<div id="view"></div>
</body>
</html>