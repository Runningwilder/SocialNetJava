package com.Socialnet.project.service.impl;

import java.sql.Connection;
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
		Connection con = null;
		try {
			con = daoFactory.beginTransaction();
			userDao.add(con, user);
			con.commit();
		} catch (SQLException e) {
			daoFactory.rollback(con);
			throw e;
		} finally {
			daoFactory.endTransaction(con);
		}
	}

	@Override
	public void updateUser(User user) throws SQLException {
		Connection con = null;
		try {
			con = daoFactory.beginTransaction();
			userDao.update(con, user);
			con.commit();
		} catch (SQLException e) {
			daoFactory.rollback(con);
			throw e;
		} finally {
			daoFactory.endTransaction(con);
		}
	}

	@Override
	public void deleteUserById(int id) throws SQLException {
		Connection con = null;
		try {
			con = daoFactory.beginTransaction();
			userDao.delete(con, id);
			con.commit();
		} catch (SQLException e) {
			daoFactory.rollback(con);
			throw e;
		} finally {
			daoFactory.endTransaction(con);
		}
	}

	@Override
	public List<User> findAllUsers() throws SQLException {
		Connection con = null;
		try {
			con = daoFactory.open();
			return userDao.findAll(con);
		} finally {
			daoFactory.close(con);
		}
	}

	@Override
	public User findUserById(int userId) throws SQLException {
		Connection con = null;
		try {
			con = daoFactory.open();
			return userDao.findById(con, userId);
		} finally {
			daoFactory.close(con);
		}

	}

	@Override
	public User findUserByName(String name) throws SQLException {

		Connection con = null;
		try {
			con = daoFactory.open();
			return userDao.findByName(con, name);
		} finally {
			daoFactory.close(con);
		}

	}

}
