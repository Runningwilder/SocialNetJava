package com.Socialnet.project.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.Socialnet.project.dao.mysql.MySqlDAOFactory;

public abstract class DaoFactory {
	
	public static DaoFactory getDaoFactory(String db) {
		switch (db) {
		case "MYSQL":
			return MySqlDAOFactory.getInstance();
		default:
			throw new IllegalArgumentException();
		}
	}


	public void close(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public Connection beginTransation() throws SQLException {
		Connection con = open();
		con.setAutoCommit(false);
		con.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
		return con;
	}

	public void endTransaction(Connection con) {
		try {
			con.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close(con);
	}

	public void rollback(Connection con) {
		try {
			con.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public abstract Connection open() throws SQLException;
	
	public abstract IUserDAO getUserDAO();

	public abstract IMessageDAO getMessageDAO();

//	public abstract Connection getConnection();

}
