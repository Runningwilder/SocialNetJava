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
		
		User user1 = new User();
		user1.setName("Thor");
		user1.setRoleId(1);
		userDao.add(user1);
		System.out.println("### User added. Id set to: " + user1.getId());
		
		user1.setName("Hawkeye");
		userDao.update(user1);
		System.out.println("### User updated ");
		User user2 = userDao.findById(user1.getId());
		System.out.println("New name: " + user2.getName());
		
		List<User> list = userDao.findAll();
		list.forEach(item -> System.out.println(item.getId() + " " + item.getName()));
		
		userDao.delete(user1.getId());
		System.out.println("### User deleted");
		
		list = userDao.findAll();
		list.forEach(item -> System.out.println(item.getId() + " " + item.getName()));
		
		return "HomePage";
	}
}