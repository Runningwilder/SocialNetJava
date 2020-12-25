package com.Socialnet.project.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.Socialnet.project.dao.DaoFactory;
import com.Socialnet.project.dao.IMessageDAO;
import com.Socialnet.project.entity.Message;
import com.Socialnet.project.service.IMessageService;

public class MySqlMessageService implements IMessageService {
	private DaoFactory daoFactory;

	private IMessageDAO messageDao;

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
		messageDao.add(message);
	}

	@Override
	public Message findMessageById(int messageId) throws SQLException {
		return messageDao.findById(messageId);
	}

	@Override
	public List<Message> findMessagesByContent(String substring) throws SQLException {
		return messageDao.findByContent(substring);
	}

	@Override
	public List<Message> findMessagesFromUserToUser(int fromUserId, int toUserId) throws SQLException {
		return messageDao.findByFields(fromUserId, toUserId);
	}

	@Override
	public void updateMessage(Message message) throws SQLException {
		messageDao.update(message);
	}

}
