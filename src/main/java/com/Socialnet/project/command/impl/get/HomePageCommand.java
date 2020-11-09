package com.Socialnet.project.command.impl.get;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Socialnet.project.command.ICommand;

public class HomePageCommand implements ICommand {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		return "HomePage";
	}

}
