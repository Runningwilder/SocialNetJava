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
import com.Socialnet.project.entity.User;
import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;

public class HomePageCommand implements ICommand {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		try {
			main(null);
		} catch (SQLException | NamingException e) {
			e.printStackTrace();
		}
		return "HomePage";
	}

	public static void main(String[] args) throws SQLException, NamingException {
		// OPTION 1
//		String username = "user";
//		String password = "userpass";
//		String host = "localhost";
//		String port = "3306";
//		String dbName = "mydb";
//		
//		String url = String.format("jdbc:mysql://%s:%s/%s?characterEncoding=utf-8", host, port, dbName);
//		
//		MysqlConnectionPoolDataSource ds = new MysqlConnectionPoolDataSource();
//		
//		ds.setUrl(url);
//		ds.setPassword(password);
//		ds.setUser(username);

		// OPTION 2

//		MysqlConnectionPoolDataSource ds = new MysqlConnectionPoolDataSource();
//		ResourceBundle db = ResourceBundle.getBundle("app");
//		String username = db.getString("username");
//		String password = db.getString("password");
//		String host = db.getString("host");
//		String port = db.getString("port");
//		String dbName = db.getString("dbName");
//		
//		String url = String.format("jdbc:mysql://%s:%s/%s?characterEncoding=utf-8", host, port, dbName);
//		ds.setUrl(url);
//		ds.setPassword(password);
//		ds.setUser(username);

		// OPTION 3
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/mydb");

		Connection connection = ds.getConnection();
		List<User> list = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = connection.prepareStatement("SELECT * FROM Users");
			rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				;
				user.setName(rs.getString("name"));
				list.add(user);
			}
		} finally {
			ps.close();
			rs.close();
			connection.close();
		}
		list.forEach(item -> System.out.println(item.getId() + " " + item.getName()));

	}
}