package com.address.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.address.model.SAddressDAO;
import com.address.model.SAddressDTO;

/**
 * Servlet implementation class SearchAction
 */
@WebServlet("/address/search.do")
public class SearchAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("utf-8");
			String field=request.getParameter("field");
			String word=request.getParameter("word");
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
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out=response.getWriter();
			out.println(mainObj.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
