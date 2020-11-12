package com.Socialnet.project.dao;

import java.sql.SQLException;
import java.util.List;

import com.Socialnet.project.entity.Message;

public interface IMessageDAO {

	List<Message> findAll() throws SQLException;
}
