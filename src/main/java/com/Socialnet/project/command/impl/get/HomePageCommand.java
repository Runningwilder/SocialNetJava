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

		// Testing Message Service
		Message testMessage = new Message();
		testMessage.setContent("It's ok");
		testMessage.setFromId(2);
		testMessage.setToId(1);
		testMessage.setTime(LocalDateTime.now());

		messageService.addMessage(testMessage);
		System.out.println("Message added. Id: " + testMessage.getId());
		List<Message> messages = messageService.findMessagesByContent("%ok%");
		messages.forEach(msg -> System.out.println(msg.getContent()));

		testMessage.setContent("It's not that good");
		messageService.updateMessage(testMessage);

		messages = messageService.findMessageFromUserToUser(2, 1);
		messages.forEach(msg -> System.out.println(msg.getContent()));

		return "HomePage";
	}
}
