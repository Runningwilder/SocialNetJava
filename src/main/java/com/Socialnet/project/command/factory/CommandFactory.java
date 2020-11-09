package com.Socialnet.project.command.factory;

import javax.servlet.http.HttpServletRequest;

import com.Socialnet.project.command.CommandEnum;
import com.Socialnet.project.command.ICommand;

public final class CommandFactory {

	private CommandFactory() {

	}

	public static ICommand getCommand(HttpServletRequest req) {
		String command = req.getParameter("command");
		ICommand iCommand = null;

		if (command != null) {
			try {
				iCommand = CommandEnum.valueOf(command).getCommand();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
				iCommand = CommandEnum.ERROR_PAGE.getCommand();
			}
		} else {
			iCommand = CommandEnum.ERROR_PAGE.getCommand();
		}
		return iCommand;
	}

}
