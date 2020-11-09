package com.Socialnet.project.dao.mysql;

import java.sql.SQLException;
import java.util.List;

import com.Socialnet.project.dao.DaoFactory;
import com.Socialnet.project.dao.IUserDAO;
import com.Socialnet.project.entity.User;

public final class MySqlUserDAO implements IUserDAO {

	private static final String FIELD__ID = "id";
	private static final String FIELD__NAME = "name";
	private static final String FIELD__PASSWORD = "pwd";
	private static final String FIELD__ROLE_NAME = "roles.name";
	private static final String FIELD__ENABLED = "enabled";

	private static DaoFactory daoFactory;
	private static MySqlUserDAO instance;

	static {
		daoFactory = DaoFactory.getDaoFactory("MYSQL");
	}

	public static IUserDAO getInstance() {
		if (instance == null) {
			instance = new MySqlUserDAO();
		}
		return instance;
	}

	@Override
	public User findByName(String login) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}