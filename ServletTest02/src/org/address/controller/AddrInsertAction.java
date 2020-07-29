package org.address.controller;

import java.io.IOException;
import java.rmi.ServerException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.address.model.SAddressDAO;
import org.address.model.SAddressDTO;


public class AddrInsertAction implements Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		req.setCharacterEncoding("utf-8");
		SAddressDTO dto = new SAddressDTO();
		dto.setAddr(req.getParameter("addr"));
		dto.setName(req.getParameter("name"));
		dto.setTel(req.getParameter("tel"));
		dto.setZipcode(req.getParameter("zipcode"));
		SAddressDAO dao = SAddressDAO.getInstance();
		dao.insertAddress(dto);
		resp.sendRedirect("list.ad");
	}

}
