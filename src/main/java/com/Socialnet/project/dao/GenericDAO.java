package com.Socialnet.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
			closeStatementsAndResultSet(ps, rs, connection);
		}
		return list;
	}

	protected <V> List<T> findByFields(Connection connection, String sql, @SuppressWarnings("unchecked") V... values)
			throws SQLException {
		throw new SQLException("test");
//		List<T> list = new ArrayList<>();
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//
//		try {
//			ps = connection.prepareStatement(sql);
//			for (int i = 1; i <= values.length; i++) {
//				V value = values[i - 1];
//				switch (value.getClass().getSimpleName()) {
//				case "Integer":
//					ps.setInt(i, (Integer) value);
//					break;
//				case "String":
//					ps.setString(i, (String) value);
//					break;
//				default:
//					throw new IllegalArgumentException();
//				}
//			}
//			rs = ps.executeQuery();
//			while (rs.next()) {
//				list.add(mapToEntity(rs));
//			}
//		} finally {
//			closeStatementsAndResultSet(ps, rs, connection);
//		}
//		return list;
	}

	protected int add(Connection connection, String sql, T item) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			mapFromEntity(ps, item);
			if (ps.executeUpdate() > 0) {
				rs = ps.getGeneratedKeys();
				if (rs.next())
					return rs.getInt(1);
			}

		} finally {
			closeStatementsAndResultSet(ps, rs, connection);
		}
		throw new SQLException("Not added");
	}

	protected <V> void updateByField(Connection connection, String sql, T item, int parameterIndex, V value)
			throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = connection.prepareStatement(sql);
			switch (value.getClass().getSimpleName()) {
			case "Integer":
				ps.setInt(parameterIndex, (Integer) value);
				break;
			case "String":
				ps.setString(parameterIndex, (String) value);
				break;
			default:
				throw new IllegalArgumentException();
			}
			mapFromEntity(ps, item);
			if (ps.executeUpdate() == 0)
				throw new SQLException("Not updated");

		} finally {
			closeStatementsAndResultSet(ps, rs, connection);
		}
	}

	protected <V> void deleteByField(Connection connection, String sql, V value) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = connection.prepareStatement(sql);
			switch (value.getClass().getSimpleName()) {
			case "Integer":
				ps.setInt(1, (Integer) value);
				break;
			case "String":
				ps.setString(1, (String) value);
				break;
			default:
				throw new IllegalArgumentException();
			}
			if (ps.executeUpdate() == 0)
				throw new SQLException("Not deleted");

		} finally {
			closeStatementsAndResultSet(ps, rs, connection);
		}
	}

	private void closeStatementsAndResultSet(PreparedStatement ps, ResultSet rs, Connection connection) {
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
//		if (connection != null) {
//			try {
//				connection.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
	}

	protected abstract T mapToEntity(ResultSet rs) throws SQLException;

	protected abstract void mapFromEntity(PreparedStatement ps, T obj) throws SQLException;

}
