package com.board.action;

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

import com.board.model.BoardDAOImpl;
import com.board.model.CommentDTO;

/**
 * Servlet implementation class CommentListAction
 */
@WebServlet("/board/commentList")
public class CommentListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentListAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int bnum=Integer.parseInt(request.getParameter("num"));
		BoardDAOImpl dao=BoardDAOImpl.getInstance();
		ArrayList<CommentDTO> arr=dao.commentList(bnum);
		//arr를 json형태로 리턴
		JSONObject mainObj=new JSONObject();
		JSONArray jarr=new JSONArray();
		
		for(CommentDTO cd:arr) {
			JSONObject obj=new JSONObject();
			obj.put("bnum", cd.getBnum());
			obj.put("cnum", cd.getCnum());
			obj.put("msg", cd.getMsg());
			obj.put("userid", cd.getUserid());
			obj.put("reg_date", cd.getReg_date());
			jarr.add(obj);
		}
		mainObj.put("carr", jarr);
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
