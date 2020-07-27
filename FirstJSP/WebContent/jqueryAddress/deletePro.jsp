<%@page import="com.jqueryAddress.JAddressDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
 int num = Integer.parseInt(request.getParameter("num"));
JAddressDAO dao = JAddressDAO.getInstance();
dao.addrDelete(num);
response.sendRedirect("list.jsp");
%>