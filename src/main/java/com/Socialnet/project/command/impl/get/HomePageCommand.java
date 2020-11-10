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

	private static DaoFactory daoFactory;
	private static IUserDAO userDao;
	
	static {
		daoFactory = DaoFactory.getDaoFactory("MYSQL");
		userDao = daoFactory.getUserDAO();
	}
	
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
//		try {
//			main(null);
//		} catch (SQLException | NamingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		List<User> list = userDao.findAll();
		list.forEach(item -> System.out.println(item.getName()));
		
		User user1 = userDao.findByName("Tony");
		System.out.println("id: " + user1.getId());
		User user2 = userDao.findByName("Bruce");
		System.out.println("id: " + user2.getId());
		
		return "HomePage";
	}

}
