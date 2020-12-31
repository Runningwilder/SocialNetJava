package com.Socialnet.project.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
	public void add(Message message) throws SQLException {
		int id = add(daoFactory.getConnection(),
				"INSERT INTO Messages (time, content, from_id, to_id) VALUES (?, ?, ?, ?)", message);
		message.setId(id);
	}

	@Override
	public Message findById(int messageId) throws SQLException {
		List<Message> list = findByFields(daoFactory.getConnection(), "SELECT * FROM Messages WHERE id = ?", messageId);
		if (list.isEmpty())
			throw new SQLException();
		return list.get(0);
	}

	@Override
	public List<Message> findByContent(String substring) throws SQLException {
		return findByFields(daoFactory.getConnection(), "SELECT * FROM Messages WHERE content LIKE ?", substring);
	}

	@Override
	public List<Message> findByFields(int fromUserId, int toUserId) throws SQLException {
		return findByFields(daoFactory.getConnection(), "SELECT * FROM Messages WHERE from_id = ? AND to_id = ?",
				fromUserId, toUserId);
	}

	@Override
	public void update(Message message) throws SQLException {
		updateByField(daoFactory.getConnection(),
				"UPDATE Messages SET time = ?, content = ?, from_id = ?, to_id = ? WHERE id = ?", message, 5,
				message.getId());
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
		msg.setFromId(rs.getInt("from_id"));
		msg.setToId(rs.getInt("to_id"));
		msg.setTime(rs.getTimestamp("time").toLocalDateTime());
		return msg;
	}

	@Override
	protected void mapFromEntity(PreparedStatement ps, Message msg) throws SQLException {
		ps.setTimestamp(1, Timestamp.valueOf(msg.getTime()));
		ps.setString(2, msg.getContent());
		ps.setInt(3, msg.getFromId());
		ps.setInt(4, msg.getToId());
	}

}
