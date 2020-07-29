var exp= /^[0-9]{3}-[0-9]{4}-[0-9]{4}$/;
$(document).ready(function(){
	$("#send").click(function(){
		//아이디가 공백일때
		if($("#userid").val()==""){
			alert("아이디를 입력하세요");
			$("#userid").focus();
			return false;
		}
		//암호가 공백일때
		if($("#pwd").val()==""){
			alert("암호를 입력하세요");
			$("#pwd").focus();
			return false;
		}
		//암호확인이 공백일때
		if($("#pwd_check").val()==""){
			alert("암호확인 필수");
			$("#pwd_check").focus();
			return false;
		}
		//암호 일치확인
		if($("#pwd").val()!=$("#pwd_check").val()){
			alert("암호 불일치");
			$("#pwd_check").focus();
			return false;
		}
		if($("#name").val()==""){
			alert("이름을 입력하세요");
			return false;
		}
		if($("#email").val()==""){
			alert("이메일을 입력하세요");
			return false;
		}
		if($("#phone").val()==""){
			alert("전화번호를 입력하세요");
			return false;
		}
		//전화번호 확인
		if(!$("#phone").val().match(exp)){
			alert("전화번호 입력 양식이 아닙니다.");
			$("#phone").focus();
			return false;
		}
		$("#frm").submit();
	})//send
	
	//아이디 중복체크 버튼
	$("#idcheck").click(function(){
		window.open("idCheck.jsp", "", "width=600 height=500")
	}); //idcheck
	
	
});//document