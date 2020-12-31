package com.Socialnet.project.command;

import com.Socialnet.project.command.impl.get.ErrorPageCommand;
import com.Socialnet.project.command.impl.get.HomePageCommand;
import com.Socialnet.project.command.impl.get.UsersPageCommand;

public enum CommandEnum {

	HOME_PAGE(new HomePageCommand()), USERS_PAGE(new UsersPageCommand()), ERROR_PAGE(new ErrorPageCommand());

	private ICommand command;

	CommandEnum(ICommand command) {
		this.command = command;
	}

	public ICommand getCommand() {
		return command;
	}
}
