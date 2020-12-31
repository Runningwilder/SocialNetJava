package com.Socialnet.project.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.Socialnet.project.dao.DaoFactory;
import com.Socialnet.project.dao.IUserDAO;
import com.Socialnet.project.entity.User;
import com.Socialnet.project.service.IUserService;

public class MySqlUserService implements IUserService {

	private static IUserDAO userDao;

	private static DaoFactory daoFactory;

	private static MySqlUserService instance;

	private MySqlUserService() {
		try {
			daoFactory = DaoFactory.getDaoFactory("MYSQL");
			userDao = daoFactory.getUserDAO();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

	public static IUserService getInstance() {
		if (instance == null) {
			instance = new MySqlUserService();
		}
		return instance;
	}

	@Override
	public void addUser(User user) throws SQLException {
		userDao.add(user);
	}

	@Override
	public void updateUser(User user) throws SQLException {
		userDao.update(user);
	}

	@Override
	public void deleteUserById(int id) throws SQLException {
		userDao.delete(id);
	}

	@Override
	public List<User> findAllUsers() throws SQLException {
		return userDao.findAll();
	}

	@Override
	public User findUserById(int userId) throws SQLException {
		return userDao.findById(userId);
	}

	@Override
	public User findUserByName(String name) throws SQLException {
		return userDao.findByName(name);
	}

}
