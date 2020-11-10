package com.Socialnet.project.dao.mysql;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.Socialnet.project.dao.DaoFactory;
import com.Socialnet.project.dao.IMessageDAO;
import com.Socialnet.project.dao.IUserDAO;
import com.Socialnet.project.entity.User;
import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class MySqlDAOFactory extends DaoFactory {

	private Connection connection;
	private static MySqlDAOFactory instance;
	private DataSource ds = new MysqlConnectionPoolDataSource();
//	private MysqlConnectionPoolDataSource ds = new MysqlConnectionPoolDataSource();

	private MySqlDAOFactory() {
		// OPTION 1
//		MysqlConnectionPoolDataSource ds = new MysqlConnectionPoolDataSource();

//		String username = "user";
//		String password = "userpass";
//		String host = "localhost";
//		String port = "3306";
//		String dbName = "mydb";
//
//		String url = String.format("jdbc:mysql://%s:%s/%s?characterEncoding=utf-8", host, port, dbName);
//		ds.setUrl(url);
//		ds.setPassword(password);
//		ds.setUser(username);

		// OPTION 2
//		DataSource ds = new MysqlConnectionPoolDataSource();
		// Obtain environment naming context
		Context initCtx;
		try {
			initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			ds = (DataSource) envCtx.lookup("jdbc/mydb");
			System.out.println("Connection succeeded");
		} catch (NamingException e) {
			e.printStackTrace();
		}

		// OPTION 3
//				MysqlConnectionPoolDataSource ds = new MysqlConnectionPoolDataSource();
//				ResourceBundle db = ResourceBundle.getBundle("app");
//				String username = db.getString("db.user");
//				String password = db.getString("db.password");
//				String host = db.getString("db.host");
//				String port = db.getString("db.port");
//				String dbName = db.getString("db.dbName");
		//
//				String url = String.format("jdbc:mysql://%s:%s/%s?characterEncoding=utf-8", host, port, dbName);
//				ds.setUrl(url);
//				ds.setPassword(password);
//				ds.setUser(username);

	}
//	@Override
//	public void open() throws SQLException {
////		try {
//			connection = ds.getConnection();
////		} catch (SQLException e) {
////			log.error("Error getting connection");
////			e.printStackTrace();
////			throw e;
////		}
//	}
//
//	@Override
//	public void close() {
////		if (connection != null) {
//			try {
//				connection.close();
//			} catch (SQLException e) {
////				log.error("Error closing connection");
//				e.printStackTrace();
//			}
////		}
//	}
	
	public static DaoFactory getInstance() {
		if (instance == null) {
			instance = new MySqlDAOFactory();
		}
		return instance;
	}

	public Connection getConnection() {
		try {
			connection = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	@Override
	public IUserDAO getUserDAO() {
		return MySqlUserDAO.getInstance();
	}

	@Override
	public IMessageDAO getMessageDAO() {
		return MySqlMessageDAO.getInstance();
	}



//	@Override
//	public void beginTransation() throws SQLException {
//		open();
//		connection.setAutoCommit(false);
//	}
//
//	@Override
//	public void endTransaction() {
//		try {
//			connection.setAutoCommit(true);
//		} catch (SQLException e) {
//			log.error("Error ending transaction ", e);
//			e.printStackTrace();
//		}
//		close();
//	}
//
//	@Override
//	public void rollback() {
//		try {
//			connection.rollback();
//		} catch (SQLException e) {
//			log.error("Error rollbacking transaction ", e);
//			e.printStackTrace();
//		}
//	}

}
