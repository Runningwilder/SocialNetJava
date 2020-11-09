package com.Socialnet.project.dao;

import java.sql.SQLException;
import java.util.List;

import com.Socialnet.project.entity.User;

public interface IUserDAO {

	User findByName(String login) throws SQLException;
	
	List<User> findAll() throws SQLException;

	////////////////////////
	
//	void add(User user) throws SQLException;
//
//	void update(User user) throws SQLException;
//
//	void delete(int userId) throws SQLException;

}