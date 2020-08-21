<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script>
$(document).ready(function(){
	$("#send").click(function(){
		if($("#dong").val()==""){
			alert("동이름 입력");
			return false;
		}
		
		$.post("zipAction.amy", {"dong":$("#dong").val()})
			.done(function(data){
				var res=JSON.parse(data); //JSON으로 부른게 아니기 때문에 파싱 필요
				var htmlStr="<table>";
				$.each(res.zarr,function(key,val){
					var bunji=val.bunji==null?"":val.bunji;
					var gugun=val.gugun==null?"":val.gugun;
					htmlStr+="<tr>";
					htmlStr+="<td>"+val.zipcode+"</td>";
					htmlStr+="<td>"+val.sido+"</td>";
					htmlStr+="<td>"+gugun+"</td>";
					htmlStr+="<td>"+val.dong+"</td>";
					htmlStr+="<td>"+bunji+"</td>";
					htmlStr+="</tr>";
				});//each
				htmlStr+="</table>";
				$("#area").html(htmlStr);
		});
	})//send
	
	$("#area").on("click","tr",function(){
		var address=$("td:eq(1)", this).text()+" "+
					$("td:eq(2)", this).text()+" "+
					$("td:eq(3)", this).text()+" "+
					$("td:eq(4)", this).text();
		$(opener.document).find("#zipcode").val($("td:eq(0)",this).text());
		$(opener.document).find("#addr").val(address);
		self.close();
	})//area
})//document
</script>
</head>
<body>
<table>
	<tr>
		<td> 동이름입력: <input type="text" name="dong" id="dong">
		<input type="button" value="검색" id="send">
		</td>
	</tr>
	<tr>
		<td>*주소를 클릭하면 주소록에 자동 입력 됩니다</td>
	</tr>
</table>
<div id="area"></div>
</body>
</html>