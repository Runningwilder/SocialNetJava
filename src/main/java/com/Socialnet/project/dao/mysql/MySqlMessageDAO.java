package com.Socialnet.project.dao.mysql;

import java.sql.SQLException;
import java.util.List;

import com.Socialnet.project.dao.DaoFactory;
import com.Socialnet.project.dao.IMessageDAO;
import com.Socialnet.project.dao.IUserDAO;
import com.Socialnet.project.entity.Message;
import com.Socialnet.project.entity.User;

public final class MySqlMessageDAO implements IMessageDAO {

	private static final String FIELD__ID = "id";
	private static final String FIELD__TIME = "time";
	private static final String FIELD__CONTENT = "content";
	private static final String FIELD__FROM_ID = "fromId";
	private static final String FIELD__TO_ID = "to_id";

	private static DaoFactory daoFactory;
	private static MySqlMessageDAO instance;

	static {
		daoFactory = DaoFactory.getDaoFactory("MYSQL");
	}

	public static IMessageDAO getInstance() {
		if (instance == null) {
			instance = new MySqlMessageDAO();
		}
		return instance;
	}

	@Override
	public List<Message> findAllByUser(int userId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	

}