package org.address.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.ServerException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.address.model.SAddressDAO;
import org.address.model.ZipcodeDTO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class AddrZipCodeAction implements Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp)
			throws ServerException, IOException, ServletException {
		req.setCharacterEncoding("utf-8");
		String dong=req.getParameter("dong");
		SAddressDAO dao=SAddressDAO.getInstance();
		ArrayList<ZipcodeDTO>arr=dao.zipSearch(dong);
		
		JSONObject mainObj=new JSONObject();
		JSONArray jarr=new JSONArray();
		
		for (ZipcodeDTO zip:arr) {
			JSONObject obj=new JSONObject();
			obj.put("zipcode", zip.getZipcode());
			obj.put("sido", zip.getSido());
			obj.put("gugun", zip.getGugun());
			obj.put("dong",zip.getDong());
			obj.put("bunji",zip.getBunji());
			jarr.add(obj);
		}
		mainObj.put("jarr",jarr);
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out=resp.getWriter();
		out.println(mainObj.toString());		
	}
}
