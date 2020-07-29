package org.address.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.ServerException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.address.model.SAddressDAO;
import org.address.model.SAddressDTO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class AddrSearchAction implements Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp)
			throws ServerException, IOException, ServletException {
		req.setCharacterEncoding("utf-8");
		String field=req.getParameter("field");
		String word=req.getParameter("word");
		SAddressDAO dao=SAddressDAO.getInstance();
		ArrayList<SAddressDTO> arr=dao.addressSearch(field, word);
		int count=dao.searchCount(field,word);
		
		JSONObject mainObj=new JSONObject();
		JSONArray jarr=new JSONArray();
		for(SAddressDTO dto:arr) {
			JSONObject obj=new JSONObject();
			obj.put("num", dto.getNum());
			obj.put("name", dto.getName());
			obj.put("addr", dto.getAddr());
			obj.put("tel", dto.getTel());
			obj.put("zipcode", dto.getZipcode());
			jarr.add(obj);
		}
		JSONObject objCount=new JSONObject();
		objCount.put("scount", count);
		
		mainObj.put("searchArr", jarr);
		mainObj.put("searchCount", objCount);
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out=resp.getWriter();
		out.println(mainObj.toString());
	}
}
