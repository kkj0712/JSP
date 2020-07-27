<%@page import="com.jqueryAddress.ZipCodeBean"%>
<%@page import="com.jqueryAddress.JAddressDAO"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="jdk.nashorn.api.scripting.JSObject"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

  <%
  	request.setCharacterEncoding("utf-8");
  String dong = request.getParameter("dong");
 // String dong ="서면";
  JAddressDAO dao = JAddressDAO.getInstance();
  ArrayList<ZipCodeBean> arr = dao.zipcodeRead(dong);
  JSONArray  jarr = new JSONArray();
  for(ZipCodeBean z : arr){
	  JSONObject obj = new JSONObject();
	  obj.put("zipcode" , z.getZipcode());
	  obj.put("sido" , z.getSido());
	  obj.put("gugun" , z.getGugun());
	  obj.put("dong" , z.getDong());
	  obj.put("bunji" , z.getBunji());
	  jarr.add(obj);
  }
  out.println(jarr.toString());  
   %>
  
  
  
  
  
