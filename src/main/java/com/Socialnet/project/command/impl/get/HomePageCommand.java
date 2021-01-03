package com.Socialnet.project.command.impl.get;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Socialnet.project.command.ICommand;
import com.Socialnet.project.entity.Message;
import com.Socialnet.project.service.IMessageService;
import com.Socialnet.project.service.ServiceFactory;

public class HomePageCommand implements ICommand {

	private static ServiceFactory serviceFactory;
	private static IMessageService messageService;

	static {
		serviceFactory = ServiceFactory.getServiceFactory("MYSQL");
		messageService = serviceFactory.getMessageService();
	}

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
//		resp.setStatus(501);
		messageService.findMessagesByContent("%ok%");
		throw new SQLException("Some bad thing happened :(");
//		return "HomePage";
	}
}
