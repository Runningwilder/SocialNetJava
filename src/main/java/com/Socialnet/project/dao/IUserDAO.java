package com.Socialnet.project.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.Socialnet.project.entity.User;

public interface IUserDAO {

	User findByName(Connection connection, String name) throws SQLException;

	List<User> findAll(Connection connection) throws SQLException;

	User findById(Connection connection, int userId) throws SQLException;

	void add(Connection connection, User user) throws SQLException;

	void update(Connection connection, User user) throws SQLException;

	void delete(Connection connection, int userId) throws SQLException;
}
