<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
<div class="container">
  <form action="update.me" method="post" id="frm">
	<br/>
  	<div class="form-group">
  	  <label for="id">id:</label>
   	  <input type="text" name="userid" id="userid" value="${member.userid}" readonly="readonly">
  	</div>
    <div class="form-group">
      <label for="name">Name:</label>
      <input type="text" class="form-control" id="name" value="${member.name}" name="name">
    </div>
    <div class="form-group">
      <label for="pwd">Password:</label>
      <input type="password" class="form-control" id="pwd" value="${member.pwd}" name="pwd">
    </div>
    <div class="form-group">
      <label for="email">Email:</label>
      <input type="text" class="form-control" id="email" value="${member.email}" name="email">
    </div>
    
    <div class="form-group">
      <label for="phone">Phone:</label>
      <input type="text" class="form-control" id="phone" value="${member.phone}" name="phone">
    </div>
    <div class="form-check-inline">
	  	<label class="form-check-label">
	    <input type="radio" class="form-check-input" name="admin" value="1">관리자
	  	</label>
	</div>
	<div class="form-check-inline">
	  <label class="form-check-label">
	    <input type="radio" class="form-check-input" name="admin"  value="0" checked>일반회원
	  </label>
	</div>
    <script>
			if(${member.getAdmin()==0}){
				$("input:radio[value='0']").prop("checked", true);
			}else{
				$("input:radio[value='1']").prop("checked", true);				
			}
	</script>
	<br/>
	 <button type="submit"  class="btn btn-primary">수정</button>
</div>
</form>
<%@ include file="../include/footer.jsp" %>