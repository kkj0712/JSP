<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
$(function(){
	$("#loginBtn").click(function(){
		if($("#userid").val()==""){
			alert("아이디를 입력하세요");
			$("#userid").focus();
			return false;
		}
		if($("#pwd").val()==""){
			alert("암호를 입력하세요");
			$("#pwd").focus();
			return false;
		}
		$.ajax({
			type   : "post",
			url    : "loginPro.jsp",
			data   : {"userid": $("#userid").val(), "pwd": $("#pwd").val()},
			success: function(value){
				switch(value.trim()){
				case "0" : alert("로그인 성공"); 
							$(location).attr("href","memberView.jsp");
							break;
				case "1" : alert("관리자 로그인"); 
							location.href="memberList.jsp";
							break;
				case "-1" : alert("회원이 아닙니다.");
				 			break;
				case "2" : alert("비밀번호 오류"); break;
				default : alert(value().trim());
				}
			},
			error  : function(e){
				alert("error: "+e);
			}
		})
	}); //loginBtn
}); //document

</script>
<style>
#container { 
	width:250px;
	border:1px solid green;
	padding:20px 40px;
	margin:0 auto;
}
</style>
</head>
<body>
<div id="container">
<h2>로그인</h2>
<hr>
<form>
	<table>
		<tr>
			<td>아이디</td>
			<td><input type="text" name="userid" id="userid"></td>
		</tr>
		<tr>
			<td>암호</td>
			<td><input type="password" name="pwd" id="pwd"></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
			<input type="button" value="로그인" id="loginBtn">
			<input type="reset" value="취소">
			<input type="button" value="회원 가입" onclick="location.href='memberForm.jsp'">
			</td>
		</tr>
	</table>
</form>
</div>
</body>
</html>