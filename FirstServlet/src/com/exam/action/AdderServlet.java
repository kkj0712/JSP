package com.exam.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdderServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		int num1=Integer.parseInt(req.getParameter("num1"));
		int num2=Integer.parseInt(req.getParameter("num2"));
		int sum=num1+num2;
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out=resp.getWriter(); //Ãâ·Â°´Ã¼
		out.println("<html>");
		out.println("<head><title>°á°ú</title></head>");
		out.println("<body>");
		out.println("µ¡¼À°á°ú:"+sum);
		out.println("</body>");
		out.println("</html>");
	}
}
