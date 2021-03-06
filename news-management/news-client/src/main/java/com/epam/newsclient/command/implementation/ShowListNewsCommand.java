package com.epam.newsclient.command.implementation;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.epam.newsclient.command.ICommand;
import com.epam.newsclient.utils.ConfigurationManager;
import com.epam.newsclient.utils.CreatorEntity;
import com.epam.newscommon.entity.Author;
import com.epam.newscommon.entity.NewsValueObject;
import com.epam.newscommon.entity.SearchCriteriaObject;
import com.epam.newscommon.entity.Tag;
import com.epam.newscommon.exception.ServiceException;
import com.epam.newscommon.service.NewsValueObjectService;

public class ShowListNewsCommand implements ICommand {
	@Autowired
	private NewsValueObjectService service;

	private static final int COUNT_NEWS_ON_PAGE = 3;
	private static final String AUTHORS = "authors";
	private static final String TAGS = "tags";
	private static final String INDEX_PAGE = "indexNewsPage";
	private static final String NEWS_LIST = "newsList";
	private static final String COUNT_NEWS = "countNews";
	private static final String PATH_PAGE_SUCCESS = "path.page.newslist";
	private static final String PATH_PAGE_ERROR = "path.page.error";
	private static final String ATTR_SESSION_AUTHOR = "author";
	private static final String ATTR_SESSION_TAGS = "tags";
	private final static String ID_PREVIOUS_PAGE = "idPreviousNewsPage";
	private static final String PAR_RESET_FILTER = "clear";	

	public String execute(HttpServletRequest request) throws ServiceException {
		
		String authorSearch = (String) request.getSession().getAttribute(ATTR_SESSION_AUTHOR);
		String[] tagsSearch = (String[]) request.getSession().getAttribute(ATTR_SESSION_TAGS);
		int indexPageNews = Integer.valueOf(request.getParameter(INDEX_PAGE));
		String resetFilter = request.getParameter(PAR_RESET_FILTER) ;
		List<NewsValueObject> news = new ArrayList<>();
		if(resetFilter!=null && resetFilter.equals("true")){
			request.getSession().setAttribute(ATTR_SESSION_AUTHOR, null);
			request.getSession().setAttribute(ATTR_SESSION_TAGS, null);
		}
		if ((tagsSearch != null || authorSearch != null) && resetFilter==null) {
			SearchCriteriaObject sc = CreatorEntity.createSearchCriteria(authorSearch, tagsSearch);
			news = service.findBySearchCriteria(sc,indexPageNews,COUNT_NEWS_ON_PAGE);
			request.setAttribute(COUNT_NEWS, news.size());
			request.setAttribute(INDEX_PAGE, indexPageNews);
			request.getSession().setAttribute(ATTR_SESSION_AUTHOR, authorSearch);
			request.getSession().setAttribute(ATTR_SESSION_TAGS, tagsSearch);
		} else {
			news = service.findAllNews(indexPageNews, COUNT_NEWS_ON_PAGE);
			request.setAttribute(COUNT_NEWS, service.countNews());
			request.setAttribute(INDEX_PAGE, indexPageNews);
		}
		
		List<Author> authors = service.findAllAuthors();
		List<Tag> tags = service.findAllTags();
		if (authors.isEmpty() || authors == null || tags.isEmpty() || tags == null) {
			return ConfigurationManager.getProperty(PATH_PAGE_ERROR);
		}
		request.getSession().setAttribute(ID_PREVIOUS_PAGE, indexPageNews);
		request.setAttribute(AUTHORS, authors);
		request.setAttribute(TAGS, tags);
		request.setAttribute(NEWS_LIST, news);
		return ConfigurationManager.getProperty(PATH_PAGE_SUCCESS);
	}
}
