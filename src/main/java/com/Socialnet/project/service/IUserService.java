package com.Socialnet.project.service;

import java.sql.SQLException;
import java.util.List;

import com.Socialnet.project.entity.User;

public interface IUserService {

	void addUser(User user) throws SQLException;

	void updateUser(User user) throws SQLException;

	void deleteUserById(int id) throws SQLException;

	List<User> findAllUsers() throws SQLException;

	User findUserById(int userId) throws SQLException;

	User findUserByName(String name) throws SQLException;

}
