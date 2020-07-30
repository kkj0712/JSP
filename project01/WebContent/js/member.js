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
	$("#idcheckBtn").click(function(){
		window.open("idCheck.me", "", "width=600 height=500")
	}); //idcheckBtn
	
	//id중복확인 사용버튼
	$("#useBtn").click(function(){
		if($("#userid").val()==""){
			alert("아이디를 입력하세요");
			$("#userid").focus();
			return false;
		}
		$.ajax({
			type: "post",
			url : "idCheck.me",
			data: {"userid":$("#userid").val()},
			success: function(val){
				if(val.trim()=="yes"){
					alert("사용가능한 아이디");
					$(opener.document).find("#userid").val($("#userid").val());
					self.close();
				}else if(val.trim()=="no"){
					alert("중복된 아이디");
					$("#userid").val("");
				}
			},
			error: function(e){
				alert("error:"+e);
			}
		}); //ajax
	}) //useBtn
	
});//document

function del(userid){
	if(confirm("정말 삭제할까요?")){
		$.getJSON("userDelete.me?userid="+userid, function(data){
			//alert(data.root.length);
			var htmlStr="";
			$.each(data.root, function(key, val){
				htmlStr+="<tr>";
				htmlStr+="<td>"+val.name+"</td>";
				htmlStr+="<td>"+val.userid+"</td>";
				htmlStr+="<td>"+val.phone+"</td>";
				htmlStr+="<td>"+val.email+"</td>";
				htmlStr+="<td>"+val.mode+"</td>";
				if(val.mode=='일반회원'){
					htmlStr+="<td onclick=del('"+val.userid+"')>삭제</td>";
				}else{
					htmlStr+="<td>Admin</td>";
				}
				htmlStr+="</tr>";
			}) //each
			$("table tbody").html(htmlStr);
			$("#cntSpan").text(data.rootCount.count);
		}) //getJSON
	} //confirm
}//del
