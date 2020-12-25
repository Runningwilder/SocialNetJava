package com.Socialnet.project.service;

import com.Socialnet.project.service.impl.MySqlServiceFactory;

/**
 * Base abstract service factory class
 *
 */
public abstract class ServiceFactory {

	public static ServiceFactory getServiceFactory(String db) {
		switch (db) {
		case "MYSQL":
			return MySqlServiceFactory.getInstance();
		default:
			throw new IllegalArgumentException();
		}
	}

	public abstract IUserService getUserService();

	public abstract IMessageService getMessageService();
}
