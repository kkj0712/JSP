package org.address.controller;

import java.io.IOException;
import java.rmi.ServerException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddrZipFormAction implements Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp)
			throws ServerException, IOException, ServletException {
		RequestDispatcher rd=req.getRequestDispatcher("zipCheck.jsp");
		rd.forward(req, resp);
	}
}
