var exp= /^[0-9]{3}-[0-9]{4}-[0-9]{4}$/;
$(document).ready(function(){
	$("#send").click(function(){
		if($("#name").val()==""){
			alert("이름을 입력하세요");
			$("#name").focus();
			return false;
		}
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
		//전화번호 확인(정규식을 만들어놓고 비교)
		if(!$("#phone").val().match(exp)){
			alert("전화번호를 정확하게 입력하세요");
			$("#phone").focus();
			return false;
		}
		
		$("#frm").submit();
	});//send
	
	
	//아이디 중복체크 버튼을 눌렀을때
	$("#idBtn").click(function(){
		window.open("idCheck.jsp","","width=600 height=500")
	}); //idBtn
	
	//중복체크 새 창에서 아이디 중복확인
	$("#idCheckBtn").click(function(){
		if($("#userid").val()==""){
			alert("아이디를 입력하세요");
			$("#userid").focus();
			return false;
		}
		$.ajax({
			type:"post",
			url :"idCheckPro.jsp",
			data: {"userid":$("#userid").val()},
			success: function(value){
				if(value.trim()=="yes"){
					alert("사용가능한 아이디")
					$(opener.document).find("#userid").val($("#userid").val());
					$(opener.document).find("#uid").val($("#userid").val());
					self.close();
				}else{
					alert("중복된 아이디")
				}
			},
			error: function(e){
				alert("error:"+e)
			}
		});
	}) //idCheckBtn
	
})//document



function del(userid, mode){
	  if(mode=="관리자"){
		  alert("관리자는 삭제할 수 없음");
		  return;
	  }
	$.getJSON("memberDelete.jsp",
			{"userid": userid},
			function(data){
			  var htmlStr="";
			  $.each(data.jarr,function(key,val){
				  htmlStr+="<tr>";
				  htmlStr+="<td>"+val.name+"</td>";
				  htmlStr+="<td>"+val.userid+"</td>";
				  htmlStr+="<td>"+val.phone+"</td>";
				  htmlStr+="<td>"+val.email+"</td>";
				  htmlStr+="<td>"+val.mode+"</td>";
				  htmlStr+="<td><a href=javascript:del('"+val.userid+"','"+val.mode+"')>삭제2</a></td>";
				  htmlStr+="</tr>";
			  })
			  $("table tbody").html(htmlStr);
			  $("#cntSpan").text(data.cntObj.count);
			} //콜백함수
	); //getJSON
} //del() 함수