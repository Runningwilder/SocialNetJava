package com.Socialnet.project.dao;

import java.sql.SQLException;
import java.util.List;

import com.Socialnet.project.entity.User;

public interface IUserDAO {

	User findByName(String name) throws SQLException;
	
	
	List<User> findAll() throws SQLException;
	
}
