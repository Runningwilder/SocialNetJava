package com.Socialnet.project.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.Socialnet.project.dao.DaoFactory;
import com.Socialnet.project.dao.IMessageDAO;
import com.Socialnet.project.dao.IUserDAO;
import com.Socialnet.project.entity.User;
import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;

public class MySqlDAOFactory extends DaoFactory {
	private DataSource ds = new MysqlConnectionPoolDataSource();
	
	private MySqlDAOFactory() {
		Context initCtx;
		try {
			initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			ds = (DataSource) envCtx.lookup("jdbc/mydb");

			System.out.println("Connection succeeded");
		} catch (NamingException e) {
			e.printStackTrace();
			System.out.println("Connection failed");
		}
	}
	
	private static MySqlDAOFactory instance;

	public static DaoFactory getInstance() {
		if (instance == null)
			instance = new MySqlDAOFactory();
		return instance;
	}
	
	@Override
	public Connection getConnection() {
		try {
			return ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public IUserDAO getUserDAO() {
		return MySqlUserDAO.getIntance();
	}

	@Override
	public IMessageDAO getMessageDAO() {
		return MySqlMessageDAO.getIntance();
	}


//	@Override
//	public IMessageDAO getMessageDAO() {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
