package com.epam.newscommon.service;

import java.util.List;

import com.epam.newscommon.exception.ServiceException;
import com.epam.newscommon.entity.News;
import com.epam.newscommon.entity.SearchCriteriaObject;

/**
 * Interface AuthorService. Classes which implements this interface contain
 * action with NewsDAO in DAO layer
 * 
 * @author Uladzislau_Minich
 *
 */
public interface NewsService extends BaseService<News> {
	/**
	 * Find all news
	 * 
	 * @return list of news
	 * @throws ServiceException
	 */
	List<News> findAllNews(int indexPageNews,int countNews) throws ServiceException;

	/**
	 * Find news by title
	 * 
	 * @param title
	 * @return news
	 * @throws ServiceException
	 */
	News findNewsByTitle(String title) throws ServiceException;

	/**
	 * Find list of news which matches Search Criteria Object
	 * 
	 * @param sc
	 * @return list of news
	 * @throws ServiceException
	 */
	List<News> findNewsBySearchCriteria(SearchCriteriaObject sc,int indexPageNews,int countNews) throws ServiceException;

	/**
	 * Find all new of some author
	 * 
	 * @param authorId
	 * @return list of news
	 * @throws ServiceException
	 */
	List<News> findAllAuthorNews(Long authorId) throws ServiceException;

	/**
	 * Add information to table news_author
	 * 
	 * @param newsId
	 * @param authorId
	 * @return
	 * @throws ServiceException
	 */
	boolean saveNewsAuthors(Long newsId, Long authorId) throws ServiceException;

	/**
	 * Delete information from table news_author
	 * 
	 * @param newsId
	 * @return
	 * @throws ServiceException
	 */
	boolean deleteNewsAuthor(Long newsId) throws ServiceException;

	/**
	 * Add information to table news_tag
	 * 
	 * @param newsId
	 * @return
	 * @throws ServiceException
	 */
	boolean addNewsTag(Long tagId, Long newsId) throws ServiceException;

	/**
	 * Delete information from table news_tag
	 * 
	 * @param idNews
	 * @return
	 * @throws ServiceException
	 */
	boolean deleteNewsTag(Long idNews) throws ServiceException;
	/**
	 * Count news
	 * @return amount of news
	 * @throws ServiceException
	 */
	int countNews() throws ServiceException;
	/**
	 * Find news on the list, which is before the current
	 * 
	 * @param currentNewsId
	 * @return news's id
	 * @throws ServiceException
	 */
	Long findPreviousNews(News currentNews) throws ServiceException;
	/**
	 * Find news on the list, which is following the current
	 * 
	 * @param currentNewsId
	 * @return news's id
	 * @throws ServiceException
	 */
	Long findNextNews(News currentNews) throws ServiceException;
}
