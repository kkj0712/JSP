package org.addrMy.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.addrMy.config.MybatisManager;
import org.addrMy.model.AddressVO;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * Servlet implementation class UpdateAction
 */
@WebServlet("/address_my/updateAction.amy")
public class UpdateAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		AddressVO vo=new AddressVO();
		vo.setAddr(request.getParameter("addr"));
		vo.setName(request.getParameter("name"));
		vo.setNum(Integer.parseInt(request.getParameter("num")));
		vo.setTel(request.getParameter("tel"));
		vo.setZipcode(request.getParameter("zipcode"));
		SqlSessionFactory sqlMapper=MybatisManager.getSqlMapper();
		SqlSession sqlSession=sqlMapper.openSession(ExecutorType.REUSE);
		sqlSession.update("updateData",vo);
		sqlSession.commit();
		response.sendRedirect("listAction.amy");
	}

}