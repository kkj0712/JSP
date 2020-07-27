<%@page import="com.address.AddressDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="ad" class="com.address.Address"/>
<jsp:setProperty property="*" name="ad"/>
<%
	int num=Integer.parseInt(request.getParameter("num"));
	AddressDAO dao=AddressDAO.getInstance();
	dao.addrDelete(num);
	response.sendRedirect("list.jsp");
%>