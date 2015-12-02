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
	private final static String INDEX_PREVIOUS_NEWS_PAGE = "idPreviousNewsPage";
	private final static String ID_PREVIOUS_NEWS = "idPreviousNews";
	private final static String ID_NEXT_NEWS = "idNextNews";

	@Override
	public String execute(HttpServletRequest request) throws ServiceException {
		int indexPreviousPage = 0;
		Long idNextNews = 0L;
		Long idPreviousPage = 0L;
		Long newsId = 0L;
		try {
			indexPreviousPage = (int) request.getSession().getAttribute(INDEX_PREVIOUS_NEWS_PAGE);
			newsId = Long.valueOf(request.getParameter(NEWS_ID));
		} catch (NumberFormatException e) {
			throw new ServiceException(e);
		}
		NewsValueObject news = service.findNews(newsId);
		if (news != null) {
			idNextNews = service.findNextNews(news.getNews());
			idPreviousPage = service.findPreviousNews(news.getNews());
			if (idNextNews != 0 && idPreviousPage != 0) {
				request.setAttribute(ID_PREVIOUS_NEWS, idPreviousPage);
				request.setAttribute(ID_NEXT_NEWS, idNextNews);
			}else if(idNextNews == 0 && idPreviousPage != 0){
				request.setAttribute(ID_PREVIOUS_NEWS, idPreviousPage);
				request.setAttribute(ID_NEXT_NEWS, newsId);
			}else if(idNextNews != 0 && idPreviousPage == 0){
				request.setAttribute(ID_PREVIOUS_NEWS, newsId);
				request.setAttribute(ID_NEXT_NEWS, idNextNews);
			}
		}
		request.setAttribute(NEWS, news);
		request.getSession().setAttribute(INDEX_PREVIOUS_NEWS_PAGE, indexPreviousPage);
		return ConfigurationManager.getProperty(PATH_PAGE_SUCCES);
	}

}
