package com.Socialnet.project.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.Socialnet.project.dao.DaoFactory;
import com.Socialnet.project.dao.IMessageDAO;
import com.Socialnet.project.entity.Message;
import com.Socialnet.project.service.IMessageService;

public class MySqlMessageService implements IMessageService {

	private static IMessageDAO messageDao;

	private static DaoFactory daoFactory;

	private static MySqlMessageService instance;

	private MySqlMessageService() {
		try {
			daoFactory = DaoFactory.getDaoFactory("MYSQL");
			messageDao = daoFactory.getMessageDAO();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

	public static IMessageService getInstance() {
		if (instance == null) {
			instance = new MySqlMessageService();
		}
		return instance;
	}
	
	@Override
	public int getCount() throws SQLException {

		int count = 0;
		Connection con = null;
		try {
			con = daoFactory.beginTransation();
			count = messageDao.getCount(con);
			con.commit();
		} catch (SQLException e) {
			daoFactory.rollback(con);
			throw e;
		} finally {
			daoFactory.endTransaction(con);
		}
		return count;

//		Connection con = null;
//		int count = 0;
//		try {
//			con = daoFactory.getConnection();
//			con.setAutoCommit(false);
//			count = messageDao.getCount();
//			System.out.println("count 1: " + count);
//			messageDao.findAll().forEach(s -> System.out.println(s.getContent()));
//			System.out.println("===");
//
//			count = messageDao.getCount();
//			System.out.println("count 2: " + count);
//			messageDao.findAll().forEach(s -> System.out.println(s.getContent()));
//
//			con.commit();
//		} catch (SQLException e) {
//			try {
//				con.rollback();
//			} catch (SQLException ex) {
//				throw ex;
//			}
//
//			throw e;
//		} finally {
//			if (con != null) {
//				try {
//					con.setAutoCommit(true);
//				} catch (SQLException e) {
//					// noop
//				}
//				try {
//					con.close();
//				} catch (SQLException e) {
//					// noop
//				}
//			}
//		}
//		return count;
	}

	@Override
	public void addMessage(Message message) throws SQLException {
		Connection con = null;
		try {
			con = daoFactory.beginTransation();
			messageDao.add(con, message);
			con.commit();
		} catch (SQLException e) {
			daoFactory.rollback(con);
			throw e;
		} finally {
			daoFactory.endTransaction(con);
		}

//		Connection con = null;
//		try {
//			con = daoFactory.getConnection();
//			con.setAutoCommit(false);
//			messageDao.add( message);
//			System.out.println("Message added. Start sleeping...");
////			try {
////				Thread.sleep(10_000);
////			} catch (InterruptedException e) {
////				// TODO Auto-generated catch block
////				e.printStackTrace();
////			}
//			System.out.println("End sleeping...");
//			con.commit();
//		} catch (SQLException e) {
//			try {
//				con.rollback();
//			} catch (SQLException ex) {
//				throw ex;
//			}
//
//			throw e;
//		} finally {
//			if (con != null) {
//				try {
//					con.setAutoCommit(true);
//				} catch (SQLException e) {
//					// noop
//				}
//				try {
//					con.close();
//				} catch (SQLException e) {
//					// noop
//				}
//			}
//		}

	}

	@Override
	public Message findMessageById(int messageId) throws SQLException {
		return messageDao.findById(daoFactory.open(), messageId);
	}

	
	
	@Override
	public void updateMessage(Message message) throws SQLException {
		Connection con = null;
		try {
			con = daoFactory.beginTransation();
			messageDao.update(con, message);
			con.commit();
		} catch (SQLException e) {
			daoFactory.rollback(con);
			throw e;
		} finally {
			daoFactory.endTransaction(con);
		}
//		messageDao.update(daoFactory.open(), message);
	}

	@Override
	public List<Message> findMessagesByContent(String substring) throws SQLException {
		return messageDao.findByContent(daoFactory.open(), substring);
	}

	@Override
	public List<Message> findMessageFromUserToUser(int fromUserId, int toUserId) throws SQLException {
		List<Message> resultList = new ArrayList<>();
//		Connection con = null;
//		try {
//			con = daoFactory.getConnection();
//			con.setAutoCommit(false);
//			resultList = messageDao.findByFields(fromUserId, toUserId);
//
//			System.out.println("size 1: " + resultList.size());
//
//			resultList = messageDao.findByFields(fromUserId, toUserId);
//			System.out.println("size 2: " + resultList.size());
//
//			con.commit();
//		} catch (SQLException e) {
//			try {
//				con.rollback();
//			} catch (SQLException ex) {
//				System.out.println("rollback failed");
//				throw ex;
//			}
//
//			throw e;
//		} finally {
//			if (con != null) {
//				try {
//					con.setAutoCommit(true);
//				} catch (SQLException e) {
//					// noop
//				}
//				try {
//					con.close();
//				} catch (SQLException e) {
//					// noop
//				}
//			}
//		}

//		try {
//			daoFactory.beginTransation();
//			courseDao.update(courseId);
//			daoFactory.getConnection().commit();
//		} catch (SQLException e) {
//			daoFactory.rollback();
//			throw new DBCourseException("dberror.course.update", e);
//		} finally {
//			daoFactory.endTransaction();
//		}

//		return messageDao.findByFields(fromUserId, toUserId);
		return resultList;
	}


}
