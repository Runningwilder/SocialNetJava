package com.Socialnet.project.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.Socialnet.project.entity.Message;

public interface IMessageDAO {

	List<Message> findAll(Connection connection) throws SQLException;

	void add(Connection connection, Message message) throws SQLException;

	Message findById(Connection connection, int messageId) throws SQLException;

	List<Message> findByContent(Connection connection, String substring) throws SQLException;

	List<Message> findByFields(Connection connection, int fromUserId, int toUserId) throws SQLException;

	void update(Connection connection, Message message) throws SQLException;
}
