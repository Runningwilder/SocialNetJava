package com.Socialnet.project.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.Socialnet.project.dao.GenericDAO;
import com.Socialnet.project.dao.IUserDAO;
import com.Socialnet.project.entity.User;

public class MySqlUserDAO extends GenericDAO<User> implements IUserDAO {

	private static MySqlUserDAO instance;

	public static IUserDAO getIntance() {
		if (instance == null)
			instance = new MySqlUserDAO();
		return instance;
	}

	@Override
	public User findByName(Connection connection, String name) throws SQLException {
		List<User> list = findByFields(connection, "SELECT * FROM Users WHERE name = ?", name);
		if (list.isEmpty())
			throw new SQLException();
		return list.get(0);
	}

	@Override
	public List<User> findAll(Connection connection) throws SQLException {
		return findAll(connection, "SELECT * FROM Users");
	}

	@Override
	public User findById(Connection connection, int userId) throws SQLException {
		List<User> list = findByFields(connection, "SELECT * FROM Users WHERE id = ?", userId);
		if (list.isEmpty())
			throw new SQLException();
		return list.get(0);
	}

	@Override
	public void add(Connection connection, User user) throws SQLException {
		int id = add(connection,
				"INSERT INTO Users (name, pwd, status, enabled, image, role_id) VALUES (?, ?, ?, ?, ?, ?)", user);
		user.setId(id);
	}

	@Override
	public void update(Connection connection, User user) throws SQLException {
		updateByField(connection,
				"UPDATE Users SET name=?, pwd=?, status=?, enabled=?, image=?, role_id=? WHERE id = ?", user, 7,
				user.getId());

	}

	@Override
	public void delete(Connection connection, int userId) throws SQLException {
		deleteByField(connection, "DELETE FROM Users WHERE id = ?", userId);
	}

	@Override
	protected User mapToEntity(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setName(rs.getString("name"));
		user.setPwd(rs.getString("pwd"));
		user.setStatus(rs.getString("status"));
		user.setEnabled(rs.getBoolean("enabled"));
		user.setImage(rs.getString("image"));
		user.setRoleId(rs.getInt("role_id"));

		return user;
	}

	@Override
	protected void mapFromEntity(PreparedStatement ps, User user) throws SQLException {
		ps.setString(1, user.getName());
		ps.setString(2, user.getPwd());
		ps.setString(3, user.getStatus());
		ps.setBoolean(4, user.isEnabled());
		ps.setString(5, user.getImage());
		ps.setInt(6, user.getRoleId());
	}

}
