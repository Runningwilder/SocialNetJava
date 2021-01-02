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

	public abstract IUserDAO getUserDAO();

	public abstract IMessageDAO getMessageDAO();

//	public abstract Connection getConnection();

	public Connection beginTransaction() throws SQLException {
		Connection con = open();
		con.setAutoCommit(false);
		return con;
	}

	public abstract Connection open();

	public void rollback(Connection con) {
		try {
			con.rollback();
		} catch (SQLException ex) {
			System.out.println("rollback failed");
		}
	}

	public void endTransaction(Connection con) {
		try {
			con.setAutoCommit(true);
		} catch (SQLException e) {
			// noop
		}
		close(con);

	}

	public void close(Connection con) {
		if (con != null)
			try {
				con.close();
			} catch (SQLException e) {
				// noop
			}
	}

}
