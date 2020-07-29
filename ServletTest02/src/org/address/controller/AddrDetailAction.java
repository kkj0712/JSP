package org.address.controller;

import java.io.IOException;
import java.rmi.ServerException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.address.model.SAddressDAO;
import org.address.model.SAddressDTO;


public class AddrDetailAction implements Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp)
			throws ServerException, IOException, ServletException {
		req.setCharacterEncoding("utf-8");
		int num=Integer.parseInt(req.getParameter("num"));
		SAddressDAO dao = SAddressDAO.getInstance();
		SAddressDTO dto = dao.addressDetail(num);
		req.setAttribute("dto", dto);
		RequestDispatcher rd=req.getRequestDispatcher("detail.jsp");
		rd.forward(req, resp);
	}
}
