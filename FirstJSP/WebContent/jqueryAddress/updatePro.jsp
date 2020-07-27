<%@page import="com.jqueryAddress.JAddressDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%
     request.setCharacterEncoding("utf-8");
 %>
 <jsp:useBean id="ad"  class="com.jqueryAddress.Address"/>
 <jsp:setProperty property="*" name="ad"/>
 <%
 JAddressDAO dao = JAddressDAO.getInstance();
 dao.addrUpdate(ad);
 response.sendRedirect("list.jsp");
 %>
