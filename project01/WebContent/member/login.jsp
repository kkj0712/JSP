<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
  <div class="container">
  
  <form action="login.me" method="post" id="loginFrm">
    <div class="form-group">
      <label for="userid">id:</label>
      <input type="text" class="form-control" id="userid" placeholder="Enter id" name="userid">
    </div>
    <div class="form-group">
      <label for="pwd">Password:</label>
      <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="pwd">
    </div>
    <div class="form-group form-check">
      <label class="form-check-label">
        <input class="form-check-input" type="checkbox" name="remember"> Remember me
      </label>
    </div>
    <button type="button" id="loginBtn" class="btn btn-primary">로그인</button>
  </form>
</div>
<script>
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
		type : "post",
		url  : "login.me",
		data : {"userid":$("#userid").val(), "pwd":$("#pwd").val()},
		success : function(value){
			switch(value.trim()){
			case "0" : alert("로그인 성공"); location.href="view.me"; break;
			case "1" : alert("관리자 로그인"); location.href="list.me"; break;
			case "2" : alert("비밀번호 오류"); break;
			case "-1": alert("회원이 아닙니다"); location.href="insert.me"; break;
			default: alert(value.trim());
			}
		},
		error: function(e){
			alert("error:"+e);
		}
	})
})//loginBtn
</script>
<%@ include file="../include/footer.jsp" %>