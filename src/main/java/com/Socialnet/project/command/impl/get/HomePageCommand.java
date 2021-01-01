package com.Socialnet.project.command.impl.get;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Socialnet.project.command.ICommand;
import com.Socialnet.project.entity.Message;
import com.Socialnet.project.entity.User;
import com.Socialnet.project.service.IMessageService;
import com.Socialnet.project.service.IUserService;
import com.Socialnet.project.service.ServiceFactory;

public class HomePageCommand implements ICommand {

	private static ServiceFactory serviceFactory;
	private static IUserService userService;

	static {
		serviceFactory = ServiceFactory.getServiceFactory("MYSQL");
		userService = serviceFactory.getUserService();
	}

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException {

		User user = new User();
		user.setName("Bruce");
		user.setPwd("anger");
		
//		// Some other person adds new user
//		new Thread(() -> {
//			try {
//				userService.addUser(user);
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}).run();
//		
//		try {
//			Thread.sleep(5000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		userService.addUser(user);
		
		return "HomePage";
	}
}
