package com.epam.newsclient.command.implementation;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.epam.newsclient.command.ICommand;
import com.epam.newsclient.utils.ConfigurationManager;
import com.epam.newscommon.entity.NewsValueObject;
import com.epam.newscommon.exception.ServiceException;
import com.epam.newscommon.service.NewsValueObjectService;

public class ViewNewsCommand implements ICommand {
	@Autowired
	private NewsValueObjectService service;
	private final static String NEWS_ID = "news_id";
	private final static String NEWS = "newsVO";
	private final static String PATH_PAGE_SUCCES = "path.page.news";
	private final static String INDEX_PREVIOUS_NEWS_PAGE = "indexPreviousPage";

	@Override
	public String execute(HttpServletRequest request) throws ServiceException {
		int indexPreviousPage = 0;
		try {
			indexPreviousPage = Integer.parseInt(request.getParameter(INDEX_PREVIOUS_NEWS_PAGE));
		} catch (NumberFormatException e) {
			throw new ServiceException(e);
		}
		Long newsId = Long.valueOf(request.getParameter(NEWS_ID));
		NewsValueObject news = service.findNews(newsId);
		request.setAttribute(NEWS, news);
		request.getSession().setAttribute(INDEX_PREVIOUS_NEWS_PAGE, indexPreviousPage);
		return ConfigurationManager.getProperty(PATH_PAGE_SUCCES);
	}

}
