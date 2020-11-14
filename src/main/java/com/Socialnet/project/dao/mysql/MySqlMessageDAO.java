package com.Socialnet.project.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.Socialnet.project.dao.DaoFactory;
import com.Socialnet.project.dao.GenericDAO;
import com.Socialnet.project.dao.IMessageDAO;
import com.Socialnet.project.entity.Message;

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
		return msg;
	}

	@Override
	protected void mapFromEntity(PreparedStatement ps, Message obj) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
