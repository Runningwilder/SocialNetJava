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

public class MySqlUserDAO extends GenericDAO<User> implements IUserDAO {

	private static MySqlUserDAO instance;
	private static DaoFactory daoFactory;
	
	
	static {
		daoFactory = DaoFactory.getDaoFactory("MYSQL");
	}

	public static IUserDAO getIntance() {
		if (instance == null) 
			instance = new MySqlUserDAO();
		return instance;
	}
	
	@Override
	public User findByName(String name) throws SQLException {
		Connection connection = daoFactory.getConnection();
		List<User> list = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = connection.prepareStatement("SELECT * FROM Users WHERE name = ?");
			ps.setString(1, name);
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
		return null;
	}

	@Override
	public List<User> findAll() throws SQLException {
		return findAll(daoFactory.getConnection(), "SELECT * FROM Users");
	}

	@Override
	protected User mapToEntity(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setName(rs.getString("name"));
		return user;
	}

}
