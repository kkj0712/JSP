<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../js/jquery-3.5.1.min.js"></script>
<script>
$(document).ready(function(){
	$("#postBtn").click(function(){
		$.post("process.jsp", //ajax 기술(비동기적 처리)로 콜백한다는 뜻. action, window.open과 비슷.
				{"id" : document.getElementById("id").value, //dom방식
				 //"id" : $("#id").val(), //id와 pwd 값을 들고 콜백함수로 간다.
				 "pwd" : $("#pwd").val(),
				 "method" : "post"},
				 function(data){	//콜백함수. 결과값을 data라는 변수에 담고 result영역에 뿌린다.
					$("#postResult").html(data);	 
				 }
		); //post
	});//postBtn
	$("#getBtn").click(function(){
		$.get("process.jsp",{
								"id" : $("#id").val(),					
								"pwd" : $("#pwd").val(),
								"method" : "get"
			  				},
			  				function(ret){
				  				$("#getResult").html(ret);
			  				}
		);//get
	});//getBtn
	$("#loadBtn").click(function(){
		$("#loadResult").load("process.jsp", {
											"id" : $("#id").val(),	
											"pwd" : $("#pwd").val(),
											"method" : "load"
										}
		); //load
	});//loadBtn
	$("#ajaxBtn").click(function(){
		$.ajax({
			type : "post",
			url : "process.jsp",
			data : {
				"id" : $("#id").val(),
				"pwd" : $("#pwd").val(),
				"method" : "ajax"
			},
			success : function(d){ //성공
				$("#ajaxResult").html(d);
			}, 
			error : function(e){ //실패
				alert("에러: "+e);
			}
		});
	});//ajaxBtn
});//document
</script>
</head>
<body>
id: <input type="text" id="id" name="id"><br>
pwd: <input type="password" id="pwd" name="pwd"><br>
<input type="button" id="postBtn" value="post전송">
<input type="button" id="getBtn" value="get전송">
<input type="button" id="loadBtn" value="load전송">
<input type="button" id="ajaxBtn" value="ajax전송">
<br>
<div id="postResult"></div>
<div id="getResult"></div>
<div id="loadResult"></div>
<div id="ajaxResult"></div>
</body>
</html>