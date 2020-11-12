package com.Socialnet.project.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Socialnet.project.dao.DaoFactory;
import com.Socialnet.project.dao.IMessageDAO;
import com.Socialnet.project.dao.IUserDAO;
import com.Socialnet.project.entity.Message;
import com.Socialnet.project.entity.User;

public class MySqlMessageDAO extends GenericDAO<Message> implements IMessageDAO {

	private static MySqlMessageDAO instance;
	private static DaoFactory daoFactory;
	
	
	static {
		daoFactory = DaoFactory.getDaoFactory("MYSQL");
	}

	public static IMessageDAO getIntance() {
		if (instance == null) 
			instance = new MySqlMessageDAO();
		return instance;
	}
	
	@Override
	public List<Message> findAll() throws SQLException {
		return findAll(daoFactory.getConnection(), "SELECT * FROM Messages");
	}

	@Override
	protected Message mapToEntity(ResultSet rs) throws SQLException {
		Message msg = new Message();
		msg.setId(rs.getInt("id"));
		msg.setContent(rs.getString("content"));
		msg.setTime(rs.getDate("time").toLocalDate());
		return msg;
	}

}
