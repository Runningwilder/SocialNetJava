package com.Socialnet.project.dao;

import java.sql.SQLException;
import java.util.List;

import com.Socialnet.project.entity.Message;
import com.Socialnet.project.entity.User;

public interface IMessageDAO {

	List<Message> findAllByUser(int userId) throws SQLException;

	////////////////////////
	
//	void add(Message message) throws SQLException;
//
//	void update(Message message) throws SQLException;
//
//	void delete(int messageId) throws SQLException;

}
