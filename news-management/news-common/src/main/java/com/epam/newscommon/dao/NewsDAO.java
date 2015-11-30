package com.epam.newscommon.dao;

import java.util.List;

import com.epam.newscommon.exception.DAOException;
import com.epam.newscommon.entity.News;
import com.epam.newscommon.entity.SearchCriteriaObject;

/**
 * Interface NewsDAO. Classes which implements this interface contain action
 * with table "NEWS" in the database
 * 
 * @author Uladzislau_Minich
 *
 */
public interface NewsDAO extends BaseDAO<News> {
	/**
	 * Find all news
	 * 
	 * @return list of objects News
	 * @throws DAOException
	 */
	List<News> findAllNews(int indexPageNews,int countNews) throws DAOException;

	/**
	 * Find news by title
	 * 
	 * @param title
	 * @return object News
	 * @throws DAOException
	 */
	News findNewsByTitle(String title) throws DAOException;

	/**
	 * Find list of news which matches Search Criteria Object
	 * 
	 * @param sc
	 * @return list of object news
	 * @throws DAOException
	 */
	List<News> findNewsBySearchCriteria(SearchCriteriaObject sc,int indexPageNews,int countNews) throws DAOException;

	/**
	 * Add information to table news_author
	 * 
	 * @param newsId
	 * @param authorId
	 * @return
	 * @throws DAOException
	 */
	boolean saveNewsAuthors(Long newsId, Long authorId) throws DAOException;

	/**
	 * Delete information from table news_author
	 * 
	 * @param newsId
	 * @return
	 * @throws DAOException
	 */
	boolean deleteNewsAuthor(Long newsId) throws DAOException;

	/**
	 * Add information to table news_tag
	 * 
	 * @param newsId
	 * @return
	 * @throws DAOException
	 */
	boolean addNewsTag(Long tagId, Long newsId) throws DAOException;

	/**
	 * Delete information from table news_tag
	 * 
	 * @param idNews
	 * @return
	 * @throws DAOException
	 */
	boolean deleteNewsTag(Long idNews) throws DAOException;
	/**
	 * Find all news some authors
	 * @param authorId
	 * @return list of news
	 * @throws DAOException
	 */
	List<News> findAllAuthorsNews(Long authorId) throws DAOException;
	/**
	 * Count news
	 * @return amount of news
	 * @throws DAOException
	 */
	int countNews() throws DAOException;
}
