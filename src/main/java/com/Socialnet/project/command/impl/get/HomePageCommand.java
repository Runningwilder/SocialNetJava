package com.Socialnet.project.command.impl.get;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Socialnet.project.command.ICommand;
import com.Socialnet.project.dao.DaoFactory;
import com.Socialnet.project.dao.IMessageDAO;
import com.Socialnet.project.dao.IUserDAO;
import com.Socialnet.project.entity.Message;
import com.Socialnet.project.entity.User;
import com.Socialnet.project.service.IMessageService;
import com.Socialnet.project.service.IUserService;
import com.Socialnet.project.service.ServiceFactory;

public class HomePageCommand implements ICommand {

//	private static IUserDAO userDao;
//	private static IMessageDAO messageDao;
//	private static DaoFactory daoFactory;

	

	private static ServiceFactory serviceFactory;
	private static IUserService userService;
	private static IMessageService messageService;
//	
	
	static {

		serviceFactory = ServiceFactory.getServiceFactory("MYSQL");
		userService = serviceFactory.getUserService();
		messageService = serviceFactory.getMessageService();
		
		
//		daoFactory = DaoFactory.getDaoFactory("MYSQL");
//		userDao = daoFactory.getUserDAO();
//		messageDao = daoFactory.getMessageDAO();
	}

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
		// User part
		
//		User user1 = new User();
//		user1.setName("Thor");
//		user1.setRoleId(1);
//		userDao.add(user1);
//		System.out.println("### User added. Id set to: " + user1.getId());
//		
//		user1.setName("Hawkeye");
//		userDao.update(user1);
//		System.out.println("### User updated");
//		User user2 = userDao.findById(user1.getId());
//		System.out.println("New name: " + user2.getName());
//		
//		List<User> list = userDao.findAll();
//		list.forEach(item -> System.out.println(item.getId() + " " + item.getName()));
//		
//		userDao.delete(user1.getId());
//		System.out.println("### User deleted");
//		
//		list = userDao.findAll();
//		list.forEach(item -> System.out.println(item.getId() + " " + item.getName()));
		
		// Messages
		
		Message msg = new Message();
		msg.setContent("Hi man");
		msg.setTime(LocalDateTime.now());
		msg.setFromId(1);
		msg.setToId(2);
		messageService.addMessage(msg);
		System.out.println("Message added. Id: " + msg.getId());
		List<Message> messages = messageService.findMessagesByContent("%m%");
		messages.forEach(message -> System.out.println(message.getContent()));
		msg.setContent("Sup man");
		messageService.updateMessage(msg);
		
		messages = messageService.findMessagesFromUserToUser(1, 2);
		messages.forEach(message -> System.out.println(message.getContent() + " " + message.getTime()));
		

		return "HomePage";
	}
}