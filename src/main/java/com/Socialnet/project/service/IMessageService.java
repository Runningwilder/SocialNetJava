package com.Socialnet.project.service;

import java.sql.SQLException;
import java.util.List;

import com.Socialnet.project.entity.Message;

public interface IMessageService {

	void addMessage(Message message) throws SQLException;

	Message findMessageById(int messageId) throws SQLException;

	void updateMessage(Message message) throws SQLException;

	List<Message> findMessagesByContent(String substring) throws SQLException;

	List<Message> findMessageFromUserToUser(int fromUserId, int toUserId) throws SQLException;
	
	int getCount() throws SQLException;
}
