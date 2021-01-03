package com.Socialnet.project.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Socialnet.project.command.ICommand;
import com.Socialnet.project.command.factory.CommandFactory;

public class FrontController extends HttpServlet {

	private static final long serialVersionUID = 1108472926324664002L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String forward = handleRequest(req, resp);
		if (forward != null)
			req.getRequestDispatcher(forward + ".jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String redirect = handleRequest(req, resp);
		resp.sendRedirect(redirect);
		System.out.println("post request");
	}

	private String handleRequest(HttpServletRequest req, HttpServletResponse resp) {
		ICommand iCommand = CommandFactory.getCommand(req);
		try {
			return iCommand.execute(req, resp);
		} catch (SQLException e) {
			req.getSession().setAttribute("error", e.getMessage());
			e.printStackTrace();
			System.out.println("Error: " + e.getMessage());
//			return "ErrorPage";
//			e.printStackTrace();
//			throw new ServletException(e);
//			return null;
		}
		try {
			
			resp.sendRedirect("?command=ERROR_PAGE");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
