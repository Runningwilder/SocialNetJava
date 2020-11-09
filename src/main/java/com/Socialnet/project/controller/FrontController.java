package com.Socialnet.project.controller;

import java.io.IOException;

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
		return iCommand.execute(req, resp);
	}
}
