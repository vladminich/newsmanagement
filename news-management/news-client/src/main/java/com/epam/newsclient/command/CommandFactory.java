package com.epam.newsclient.command;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.epam.newsclient.command.implementation.AddCommentCommand;
import com.epam.newsclient.command.implementation.ChangeLanguageCommand;
import com.epam.newsclient.command.implementation.ShowListNewsCommand;
import com.epam.newsclient.command.implementation.ViewNewsCommand;

public class CommandFactory {
	private static Logger log = Logger.getLogger(CommandFactory.class);
	private final static String PAR_COMMAND = "command";

	@Autowired
	private ShowListNewsCommand showListNewsCommand;
	@Autowired
	private ChangeLanguageCommand changeLanguageCommand;
	@Autowired
	private ViewNewsCommand viewNewsCommand;
	@Autowired
	private AddCommentCommand addCommentCommand;

	public ICommand defineCommand(HttpServletRequest request) {
		ICommand currentCommand = null;
		String action = request.getParameter(PAR_COMMAND);
		if (action == null || action.isEmpty()) {
			return currentCommand;
		}
		try {
			CommandName currentEnum = CommandName.valueOf(action.toUpperCase());
			switch (currentEnum) {
			case SHOW_LIST_NEWS:
				currentCommand = showListNewsCommand;
				break;
			case CHANGE_LANGUAGE:
				currentCommand = changeLanguageCommand;
				break;
			case VIEW_NEWS:
				currentCommand = viewNewsCommand;
				break;
			case ADD_COMMENT:
				currentCommand = addCommentCommand;
				break;
			default:
				throw new EnumConstantNotPresentException(null, action);

			}

		} catch (IllegalArgumentException | EnumConstantNotPresentException e) {
			log.error("No such command: "+action, e);
		}
		return currentCommand;
	}
}
