package com.Socialnet.project.command.impl.get;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Socialnet.project.command.ICommand;
import com.Socialnet.project.dao.DaoFactory;
import com.Socialnet.project.dao.IMessageDAO;
import com.Socialnet.project.dao.IUserDAO;
import com.Socialnet.project.entity.Message;
import com.Socialnet.project.entity.User;

public class HomePageCommand implements ICommand {

	private static IUserDAO userDao;
	private static IMessageDAO messageDao;
	private static DaoFactory daoFactory;

	static {
		daoFactory = DaoFactory.getDaoFactory("MYSQL");
		userDao = daoFactory.getUserDAO();
		messageDao = daoFactory.getMessageDAO();
	}

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
		// User part
		
		List<User> list = userDao.findAll();
		list.forEach(item -> System.out.println(item.getId() + " " + item.getName()));

		// Message part

		List<Message> msgs = messageDao.findAll();
		msgs.forEach(item -> System.out.println(item.getId() + " " + item.getContent()));

		return "HomePage";
	}
}