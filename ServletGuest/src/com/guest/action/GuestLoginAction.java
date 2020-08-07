package com.guest.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.guest.model.GuestDAO;

/**
 * Servlet implementation class GuestLoginAction
 */
@WebServlet("/guestbook/login.gb")
public class GuestLoginAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GuestLoginAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd=request.getRequestDispatcher("login.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String userid=request.getParameter("userid");
		String pwd=request.getParameter("pwd");
		GuestDAO dao=GuestDAO.getInstance();
		int flag=dao.guestLoginCheck(userid, pwd); //jmember���̺� ���
		String path="";
		if(flag==0 || flag==1) {
			HttpSession session=request.getSession();
			session.setAttribute("login", userid);
			path="insert.jsp";
		}else if(flag==-1) { //ȸ�� �ƴ�
			request.setAttribute("errMsg", "ȸ���� �ƴմϴ�.");
			path="login.jsp";
		}else if(flag==2) { //��� ����
			request.setAttribute("errMsg", "��й�ȣ ����");
			path="login.jsp";
		}
		RequestDispatcher rd=request.getRequestDispatcher(path);
		rd.forward(request, response);
	}
}
