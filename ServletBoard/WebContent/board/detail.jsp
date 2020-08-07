<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	$.getJSON("commentList", 
				{ num:$("#num").val()}, 
				function(d){
					var htmlStr="<table class='table table-striped'>";
					$.each(d.carr, function(key, val){ 
						htmlStr+="<tr>";
						htmlStr+="<th>"+val.userid+"</th>";
						htmlStr+="<td>"+val.msg+"</td>";
						htmlStr+="<td>"+val.reg_date+"</td>";
						htmlStr+="</tr>";
					})
						htmlStr+="</table>";
						$("#commentResult").html(htmlStr);
				}
	)
	
	$("#commentBtn").on("click", function(){
		$.ajax({
			type: "get",
			url: "commentInsert",
			data: {"msg":$("#msg").val(), "num":$("#num").val()},
			success: function(d){
				if(d.trim()==1){
					alert("로그인 후 댓글 작성");
					location.href="login.jsp";
				}else{
					var htmlStr="<table class='table table-striped'>";
					d=JSON.parse(d);
					$.each(d.carr, function(key, val){ 
						htmlStr+="<tr>";
						htmlStr+="<th>"+val.userid+"</th>";
						htmlStr+="<td>"+val.msg+"</td>";
						htmlStr+="<td>"+val.reg_date+"</td>";
						htmlStr+="</tr>";
					})
						htmlStr+="</table>";
						$("#commentResult").html(htmlStr);
				}
			},
			error: function(e){
				alert("error:"+e);
			}
		})//ajax
	})//commentBtn
})//document

//삭제 함수
function fdelete(num){
	if(confirm("게시물을 삭제 하시겠습니까?")){
		location.href="delete?num=${dto.num}";
	}
}
//취소 함수
function back(){
	if(confirm("나가겠습니까? 변경사항은 저장되지 않습니다.")){
		location.href="list.jsp";
	}
}
</script>
</head>
<body>
<div class="container">
  <h2>글내용 보기</h2>
<form action="update" method="post">

<input type="hidden" name="num" value="${dto.num}" id="num">

  <label>글번호 ${dto.num}</label>
  <label>조회수 ${dto.readcount}</label><br/>
  <label>글쓴이 ${dto.writer}</label>
  <div class="form-group">
    <label>제목</label>
    <input type="text" name="subject" id="subject" size=37 value="${dto.subject}">
  </div>
  <div class="form-group">
    <label>내용</label>
 	  <textarea rows="5" cols="40" id="content" name="content">${dto.content}</textarea>
  </div>
  <div class="form-group">
    <label>작성일	${dto.reg_date}</label>
  </div>
  	<input type="submit" class="btn btn-primary" value="수정" onclick="location.href='update?num=${dto.num}'">
	<input type="button" class="btn btn-gray" id="delBtn" value="삭제" onclick="javascript:fdelete(${dto.num})">
	<input type="reset"  class="btn btn-gray" value="취소" onclick="javascript:back()">
<hr>
	<div class="form-group">
		<textarea rows="3" cols="50" id="msg"></textarea>
		<input type="button" value="댓글" id="commentBtn">
	</div>
<hr>
	<div class="form-group" id="commentResult"></div>
</form>
</div>
</body>
</html>