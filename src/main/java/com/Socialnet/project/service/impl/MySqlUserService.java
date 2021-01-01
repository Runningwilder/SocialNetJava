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
			con = daoFactory.beginTransation();
			User bruce = null;
			try {
				bruce = userDao.findByName(con, user.getName());
			} catch (SQLException e) {
				System.out.println(Thread.currentThread().getName() + ": user not found");
			}
			if (bruce == null) {
				userDao.add(con, user);
				System.out.println(Thread.currentThread().getName() + ": added new user");
			}

			con.commit();
		} catch (SQLException e) {
			daoFactory.rollback(con);
			throw e;
		} finally {
			daoFactory.endTransaction(con);
		}

//		userDao.add(daoFactory.open(),user);
	}

	@Override
	public void updateUser(User user) throws SQLException {
		userDao.update(daoFactory.open(), user);
	}

	@Override
	public void deleteUserById(int id) throws SQLException {
		userDao.delete(daoFactory.open(), id);
	}

	@Override
	public List<User> findAllUsers() throws SQLException {
		List<User> users;
		Connection con = null;
		try {
			con = daoFactory.beginTransation();

			users = userDao.findAll(con);
			System.out.println("Users count: " + users.size());

			users = userDao.findAll(con);
			System.out.println("Users count: " + users.size());

			con.commit();
		} catch (SQLException e) {
			daoFactory.rollback(con);
			throw e;
		} finally {
			daoFactory.endTransaction(con);
		}
		return users;
//		return userDao.findAll(daoFactory.open());
	}

	@Override
	public User findUserById(int userId) throws SQLException {
		return userDao.findById(daoFactory.open(), userId);
	}

	@Override
	public User findUserByName(String name) throws SQLException {
		return userDao.findByName(daoFactory.open(), name);
	}

}
