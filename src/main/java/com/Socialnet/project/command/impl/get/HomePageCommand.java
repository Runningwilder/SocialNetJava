package com.Socialnet.project.command.impl.get;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.Socialnet.project.command.ICommand;
import com.Socialnet.project.dao.DaoFactory;
import com.Socialnet.project.dao.IUserDAO;
import com.Socialnet.project.entity.User;
import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;

public class HomePageCommand implements ICommand {

	private static IUserDAO userDao;
	private static DaoFactory daoFactory;
	
	static {
		daoFactory = DaoFactory.getDaoFactory("MYSQL");
		userDao = daoFactory.getUserDAO();
	}
	
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		List<User> list;
		try {
			list = userDao.findAll();
			list.forEach(item -> System.out.println(item.getId() + " " + item.getName()));
			
			/////////////////
			
			User user1 = userDao.findByName("Tony");
			System.out.println("id: " + user1.getId());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "HomePage";
	}
}