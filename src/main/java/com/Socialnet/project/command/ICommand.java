package com.Socialnet.project.command;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ICommand {

	String execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException;

}
