package com.Socialnet.project.service.impl;

import com.Socialnet.project.service.IMessageService;
import com.Socialnet.project.service.IUserService;
import com.Socialnet.project.service.ServiceFactory;

public class MySqlServiceFactory extends ServiceFactory{
	private static ServiceFactory instance;

	public static ServiceFactory getInstance() {
		if (instance == null) {
			instance = new MySqlServiceFactory();
		}
		return instance;
	}

	@Override
	public IUserService getUserService() {
		return MySqlUserService.getInstance();
	}

	@Override
	public IMessageService getMessageService() {
		return MySqlMessageService.getInstance();
	}
	
}
