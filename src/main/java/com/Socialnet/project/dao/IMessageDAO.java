package com.Socialnet.project.dao;

import java.sql.SQLException;
import java.util.List;

import com.Socialnet.project.entity.Message;

public interface IMessageDAO {

	List<Message> findAll() throws SQLException;

	void add(Message message) throws SQLException;

	Message findById(int messageId) throws SQLException;

	List<Message> findByContent(String substring) throws SQLException;

	List<Message> findByFields(int fromUserId, int toUserId) throws SQLException;

	void update(Message message) throws SQLException;
}
