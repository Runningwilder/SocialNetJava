package com.Socialnet.project.dao.mysql;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.Socialnet.project.dao.DaoFactory;
import com.Socialnet.project.dao.IMessageDAO;
import com.Socialnet.project.dao.IUserDAO;

import java.sql.Connection;
import java.sql.SQLException;

public final class MySqlDAOFactory extends DaoFactory {

	private Connection connection;
	private static MySqlDAOFactory instance;
//	private DataSource ds = new MysqlConnectionPoolDataSource();
//	private MysqlConnectionPoolDataSource ds = new MysqlConnectionPoolDataSource();

	private MySqlDAOFactory() {
		// Option 1 (JNDI)
//		try {
//			// Obtain environment naming context
//			Context initCtx = new InitialContext();
//			Context envCtx = (Context) initCtx.lookup("java:comp/env");
//
//			// Look up data source
//			ds = (DataSource) envCtx.lookup("jdbc/epam");
//		} catch (NamingException e) {
//			log.error("Could'n connect to database", e);
//		}
		// Option 2 (ResourceBundle)
//		try {
//			ResourceBundle db = ResourceBundle.getBundle("app");
//			String user = db.getString("db.user");
//			String password = db.getString("db.password");
//			String host = db.getString("db.host");
//			String port = db.getString("db.port");
//			String dbName = db.getString("db.dbName");
//
//			String url = String.format("jdbc:mysql://%s:%s/%s?characterEncoding=utf-8", host, port, dbName);
//			ds.setUrl(url);
//			ds.setPassword(password);
//			ds.setUser(user);
//		} catch (MissingResourceException e) {
//			log.error("Error setting db - {}", e.getMessage());
//			e.printStackTrace();
//		}
	}

	public static DaoFactory getInstance() {
		if (instance == null) {
			instance = new MySqlDAOFactory();
		}
		return instance;
	}

	public Connection getConnection() {
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
//	public void open() throws SQLException {
//		try {
//			connection = ds.getConnection();
//		} catch (SQLException e) {
//			log.error("Error getting connection");
//			e.printStackTrace();
//			throw e;
//		}
//	}
//
//	@Override
//	public void close() {
//		if (connection != null) {
//			try {
//				connection.close();
//			} catch (SQLException e) {
//				log.error("Error closing connection");
//				e.printStackTrace();
//			}
//		}
//	}

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
