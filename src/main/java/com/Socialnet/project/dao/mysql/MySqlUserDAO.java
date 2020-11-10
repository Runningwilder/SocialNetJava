package com.Socialnet.project.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Socialnet.project.dao.DaoFactory;
import com.Socialnet.project.dao.IUserDAO;
import com.Socialnet.project.entity.User;

public final class MySqlUserDAO implements IUserDAO {

	private static final String FIELD__ID = "id";
	private static final String FIELD__NAME = "name";
	private static final String FIELD__PASSWORD = "pwd";
	private static final String FIELD__ROLE_NAME = "roles.name";
	private static final String FIELD__ENABLED = "enabled";

	private static DaoFactory daoFactory;
	private static MySqlUserDAO instance;

	static {
		daoFactory = DaoFactory.getDaoFactory("MYSQL");
	}

	public static IUserDAO getInstance() {
		if (instance == null) {
			instance = new MySqlUserDAO();
		}
		return instance;
	}

	@Override
	public User findByName(String login) throws SQLException {
		Connection connection = daoFactory.getConnection();
		List<User> list = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement("SELECT * FROM Users WHERE name = ?");
			ps.setString(1, login);
			rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				list.add(user);
			}
		} finally {
			ps.close();
			rs.close();
			connection.close();
		}
		if (list.size() != 0)
			return list.get(0);
		throw new SQLException("Not found");
	}

	@Override
	public List<User> findAll() throws SQLException {
		Connection connection = daoFactory.getConnection();
		List<User> list = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement("SELECT * FROM Users");
			rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				list.add(user);
			}
		} finally {
			ps.close();
			rs.close();
			connection.close();
		}
//		list.forEach(item -> System.out.println(item.getId() +  " " + item.getName()));
		return list;
	}

}