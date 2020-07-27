<%@page import="com.address.AddressDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8"); //한글처리
%>
<jsp:useBean id="ad" class="com.address.Address"/>
<jsp:setProperty property="*" name="ad"/>
<%
	AddressDAO dao=AddressDAO.getInstance(); //생성된 객체를 반환하기 위해 getInstance()메소드 이용 
	dao.addrInsert(ad);
	response.sendRedirect("list.jsp"); //화면의 내용을 받아넣고 list.jsp로 가라는 뜻
%>