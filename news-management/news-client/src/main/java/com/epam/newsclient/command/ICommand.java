package com.epam.newsclient.command;

import javax.servlet.http.HttpServletRequest;

import com.epam.newscommon.exception.ServiceException;

public interface ICommand {
	
	String execute(HttpServletRequest request) throws ServiceException;
}