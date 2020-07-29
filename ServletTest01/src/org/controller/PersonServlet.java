package org.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.model.Person;

public class PersonServlet  implements Action{
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		  req.setCharacterEncoding("utf-8");
		  String name = req.getParameter("name");
		  String id = req.getParameter("id");
		  String gender = req.getParameter("gender");
		  String[] notice = req.getParameterValues("notice");
		  String job = req.getParameter("job");
		  
		  Person person = new Person();
		  person.setGender(gender);
		  person.setId(id);
		  person.setJob(job);
		  person.setName(name);
		  person.setNotice(notice);
		   
		  req.setAttribute("p", person);
		  RequestDispatcher rd = req.getRequestDispatcher("personResult2.jsp");
		  rd.forward(req, resp);		
	}
}
