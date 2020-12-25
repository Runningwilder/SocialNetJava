package com.Socialnet.project.service.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.Socialnet.project.dao.DaoFactory;
import com.Socialnet.project.dao.IUserDAO;
import com.Socialnet.project.entity.User;
import com.Socialnet.project.service.IUserService;


public class MySqlUserService implements IUserService {

	private DaoFactory daoFactory;

	private IUserDAO userDao;

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

//	private static String hashedPwd(String pwd) throws NoSuchAlgorithmException {
//		String generatedPassword = null;
//		// Create MessageDigest instance for MD5
//		MessageDigest md = MessageDigest.getInstance("MD5");
//		// Add password bytes to digest
//		md.update(pwd.getBytes());
//		// Get the hash's bytes
//		byte[] bytes = md.digest();
//		// This bytes[] has bytes in decimal format;
//		// Convert it to hexadecimal format
//		StringBuilder sb = new StringBuilder();
//		for (int i = 0; i < bytes.length; i++) {
//			int upper = (bytes[i] >> 4) & 0xF;
//			int lower = bytes[i] & 0xF;
//
//			if (upper > 9)
//				sb.append((char) ('a' + upper - 10));
//			else
//				sb.append(upper);
//
//			if (lower > 9)
//				sb.append((char) ('a' + lower - 10));
//			else
//				sb.append(lower);
//		}
//		// Get complete hashed password in hex format
//		generatedPassword = sb.toString();
//
//		return generatedPassword;
//	}


	@Override
	public User findUserByName(String name) throws SQLException {
		return userDao.findByName(name);
		
//		try {
//			daoFactory.open();
//			return userDao.findByName(name);
//		} catch (SQLException e) {
//			throw new DBUserException("dberror.user.get", e);
//		} finally {
//			daoFactory.close();
//		}
	}

	@Override
	public User findUserById(int userId) throws SQLException {
		return userDao.findById(userId);
		
//		try {
//			daoFactory.open();
//			return userDao.findById(userId);
//		} catch (SQLException e) {
//			throw new DBUserException("dberror.user.get", e);
//		} finally {
//			daoFactory.close();
//		}
	}

	@Override
	public void addUser(User user) throws SQLException {
		userDao.add(user);
//		try {
//			user.setPassword(hashedPwd(user.getPassword()));
//			daoFactory.beginTransation();
//			userDao.add(user);
//			daoFactory.getConnection().commit();
//		} catch (SQLException | NoSuchAlgorithmException e) {
//			daoFactory.rollback();
//			throw new DBUserException("dberror.user.add", e);
//		} finally {
//			daoFactory.endTransaction();
//		}
	}

	@Override
	public List<User> findAllUsers() throws SQLException {
		return userDao.findAll();
//		try {
//			daoFactory.open();
//			return userDao.findAll();
//		} catch (SQLException e) {
//			throw new DBUserException("dberror.user.findAll", e);
//		} finally {
//			daoFactory.close();
//		}
	}

	@Override
	public void updateUser(User user)  throws SQLException {
		userDao.update(user);
//		try {
//			daoFactory.beginTransation();
//			userDao.update(user);
//			daoFactory.getConnection().commit();
//		} catch (SQLException e) {
//			daoFactory.rollback();
//			throw new DBUserException("dberror.user.update", e);
//		} finally {
//			daoFactory.endTransaction();
//		}
	}

//	@Override
//	public List<User> findAllUsersByRole(int roleId) throws DBUserException {
//		try {
//			daoFactory.open();
//			return userDao.findAllByRole(roleId);
//		} catch (SQLException e) {
//			throw new DBUserException("dberror.user.findAll", e);
//		} finally {
//			daoFactory.close();
//		}
//	}

	@Override
	public void deleteUserById(int userId) throws SQLException {
		userDao.delete(userId);
//		try {
//			daoFactory.beginTransation();
//			userDao.delete(userId);
//			daoFactory.getConnection().commit();
//		} catch (SQLException e) {
//			daoFactory.rollback();
//			throw new DBUserException("dberror.user.delete", e);
//		} finally {
//			daoFactory.endTransaction();
//		}
	}

//	@Override
//	public List<User> findAllUsersFromTo(int limit, int offset) throws DBUserException {
//		try {
//			daoFactory.open();
//			return userDao.findAllFromTo(limit, offset);
//		} catch (SQLException e) {
//			throw new DBUserException("dberror.user.findAll", e);
//		} finally {
//			daoFactory.close();
//		}
//	}

//	@Override
//	public int getUsersCount() throws DBUserException {
//		try {
//			daoFactory.open();
//			return userDao.getCount();
//		} catch (SQLException e) {
//			throw new DBUserException("dberror.user.getCount", e);
//		} finally {
//			daoFactory.close();
//		}
//	}


//	@Override
//	public boolean confirmPassword(User user, String password) {
//		String hashedPwd = "";
//		try {
//			hashedPwd = hashedPwd(password);
//		} catch (NoSuchAlgorithmException e) {
//			log.error("Hashing error", e.getMessage());
//		}
//		return hashedPwd.equals(user.getPassword());
//	}

//	@Override
//	public int getCountByRole(int id) throws DBUserException {
//		try {
//			daoFactory.open();
//			return userDao.getCountByRole(id);
//		} catch (SQLException e) {
//			throw new DBUserException("dberror.user.getCount", e);
//		} finally {
//			daoFactory.close();
//		}
//	}
}
