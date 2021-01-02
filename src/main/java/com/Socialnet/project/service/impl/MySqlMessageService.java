package com.Socialnet.project.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.Socialnet.project.dao.DaoFactory;
import com.Socialnet.project.dao.IMessageDAO;
import com.Socialnet.project.entity.Message;
import com.Socialnet.project.service.IMessageService;

public class MySqlMessageService implements IMessageService {

	private static IMessageDAO messageDao;

	private static DaoFactory daoFactory;

	private static MySqlMessageService instance;

	private MySqlMessageService() {
		try {
			daoFactory = DaoFactory.getDaoFactory("MYSQL");
			messageDao = daoFactory.getMessageDAO();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

	public static IMessageService getInstance() {
		if (instance == null) {
			instance = new MySqlMessageService();
		}
		return instance;
	}

	@Override
	public void addMessage(Message message) throws SQLException {
		Connection con = null;
		try {
			con = daoFactory.beginTransaction();
			messageDao.add(con, message);
			con.commit();
		} catch (SQLException e) {
			daoFactory.rollback(con);
			throw e;
		} finally {
			daoFactory.endTransaction(con);
		}
	}

	@Override
	public Message findMessageById(int messageId) throws SQLException {
		Connection con = null;
		try {
			con = daoFactory.open();
			return messageDao.findById(con, messageId);
		} finally {
			daoFactory.close(con);
		}
	}

	@Override
	public void updateMessage(Message message) throws SQLException {
		Connection con = null;
		try {
			con = daoFactory.beginTransaction();
			messageDao.update(con, message);
			con.commit();
		} catch (SQLException e) {
			daoFactory.rollback(con);
			throw e;
		} finally {
			daoFactory.endTransaction(con);
		}
	}

	@Override
	public List<Message> findMessagesByContent(String substring) throws SQLException {
		Connection con = null;
		try {
			con = daoFactory.open();
			return messageDao.findByContent(con, substring);
		} finally {
			daoFactory.close(con);
		}
	}

	@Override
	public List<Message> findMessageFromUserToUser(int fromUserId, int toUserId) throws SQLException {
		Connection con = null;
		try {
			con = daoFactory.open();
			return messageDao.findByFields(con, fromUserId, toUserId);
		} finally {
			daoFactory.close(con);
		}
	}

}
