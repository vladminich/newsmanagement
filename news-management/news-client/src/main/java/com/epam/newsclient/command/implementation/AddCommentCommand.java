package com.epam.newsclient.command.implementation;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.epam.newsclient.command.ICommand;
import com.epam.newsclient.utils.ConfigurationManager;
import com.epam.newscommon.entity.Comment;
import com.epam.newscommon.entity.NewsValueObject;
import com.epam.newscommon.exception.ServiceException;
import com.epam.newscommon.service.NewsValueObjectService;

public class AddCommentCommand implements ICommand{
	@Autowired
	private NewsValueObjectService service;
	private static final String PARAM_ID_NEWS="idNews";
	private static final String PARAM_TEXT_COMMENT="newComment";
	private static final String PATH_PAGE_SUCCESS="path.page.news";
	
	@Override
	public String execute(HttpServletRequest request) throws ServiceException {
		String idNews = request.getParameter(PARAM_ID_NEWS);
		String textComment = request.getParameter(PARAM_TEXT_COMMENT);
		Comment comment = new Comment();
		try{
		comment.setNewsId(Long.parseLong(idNews));
		comment.setText(textComment);
		service.addComment(comment);
		NewsValueObject newsVO = service.findNews(Long.parseLong(idNews));
		request.setAttribute("newsVO", newsVO);
		}catch(NumberFormatException e){
			throw new ServiceException("Invalid input data.",e);
		}
		return ConfigurationManager.getProperty(PATH_PAGE_SUCCESS);
	}

}
