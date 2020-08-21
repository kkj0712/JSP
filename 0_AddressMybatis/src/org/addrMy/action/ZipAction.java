package org.addrMy.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.addrMy.config.MybatisManager;
import org.addrMy.model.ZipcodeVO;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.google.gson.Gson;

/**
 * Servlet implementation class ZipAction
 */
@WebServlet("/address_my/zipAction.amy")
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
		RequestDispatcher rd=request.getRequestDispatcher("zipCheck.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String dong=request.getParameter("dong");
		SqlSessionFactory sqlMapper=MybatisManager.getSqlMapper();
		SqlSession sqlSession=sqlMapper.openSession(ExecutorType.REUSE);
		List<ZipcodeVO> zarr=sqlSession.selectList("zipData", dong);
		
		HashMap<String, Object> hm=new HashMap<String, Object>();
		hm.put("zarr",zarr);
		//java->json형태로 값 출력
		Gson gson=new Gson();
		String obj=gson.toJson(hm); //자바 오브젝을 json으로 만들어준다.
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.println(obj.toString());
	}

}
