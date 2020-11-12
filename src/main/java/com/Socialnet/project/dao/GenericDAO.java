package com.Socialnet.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class GenericDAO<T> {

	public List<T> findAll(Connection connection, String sql) throws SQLException {
		List<T> list = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(mapToEntity(rs));
			}
		} finally {
			ps.close();
			rs.close();
			connection.close();
		}
		return list;
	}

	protected abstract T mapToEntity(ResultSet rs) throws SQLException;

}
