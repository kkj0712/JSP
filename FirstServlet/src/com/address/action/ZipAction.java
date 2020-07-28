package com.address.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.address.model.SAddressDAO;
import com.address.model.ZipcodeDTO;

/**
 * Servlet implementation class ZipAction
 */
@WebServlet("/address/zip.do")
public class ZipAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ZipAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.sendRedirect("zipCheck.jsp");
		RequestDispatcher rd=request.getRequestDispatcher("zipCheck.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String dong=request.getParameter("dong");
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
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.println(mainObj.toString());
	}

}
