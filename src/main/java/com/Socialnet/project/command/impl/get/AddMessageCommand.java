package com.Socialnet.project.command.impl.get;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Socialnet.project.command.ICommand;
import com.Socialnet.project.entity.Message;
import com.Socialnet.project.entity.User;
import com.Socialnet.project.service.IMessageService;
import com.Socialnet.project.service.IUserService;
import com.Socialnet.project.service.ServiceFactory;

public class AddMessageCommand implements ICommand {

	private static ServiceFactory serviceFactory;
	private static IUserService userService;

	static {
		serviceFactory = ServiceFactory.getServiceFactory("MYSQL");
		userService = serviceFactory.getUserService();
	}

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
		
		List<User> users = userService.findAllUsers();
		
		return "ChatPage";
	}

}
