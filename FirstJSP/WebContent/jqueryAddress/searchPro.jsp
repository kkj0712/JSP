<%@page import="com.jqueryAddress.Address"%>
<%@page import="com.jqueryAddress.JAddressDAO"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
String field =request.getParameter("field");
String word = request.getParameter("word");
JAddressDAO dao = JAddressDAO.getInstance();
ArrayList<Address> arr =  dao.addrList(field, word);

JSONArray jarr = new JSONArray();
for(Address ad : arr){
	JSONObject obj = new JSONObject();
	obj.put("num", ad.getNum());
	obj.put("name", ad.getName());
	obj.put("tel" , ad.getTel());
	obj.put("zipcode" , ad.getZipcode());
	obj.put("addr", ad.getAddr());
	jarr.add(obj);
}
out.println(jarr.toString());
%>


