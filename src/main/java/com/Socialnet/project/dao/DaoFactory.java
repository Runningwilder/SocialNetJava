package com.Socialnet.project.dao;

import java.sql.Connection;

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

//	public abstract IMessageDAO getMessageDAO();

	public abstract Connection getConnection();

	public abstract IMessageDAO getMessageDAO();

}
