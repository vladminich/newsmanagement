package com.epam.newsclient.command.implementation;

import javax.servlet.http.HttpServletRequest;

import com.epam.newsclient.command.ICommand;
import com.epam.newsclient.utils.ConfigurationManager;

public class ChangeLanguageCommand implements ICommand {
	private final static String PAR_LOCALE = "locale";
	private static final String PATH_PAGE_SUCCESS = "path.page.index";

	@Override
	public String execute(HttpServletRequest request) {
		String language = request.getParameter(PAR_LOCALE);
		request.getSession().setAttribute(PAR_LOCALE, language);
		return ConfigurationManager.getProperty(PATH_PAGE_SUCCESS);
	}
}
