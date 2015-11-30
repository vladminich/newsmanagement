package com.epam.newscommon.service;

import java.util.List;

import com.epam.newscommon.exception.ServiceException;
import com.epam.newscommon.entity.Author;

/**
 * Interface AuthorService. Classes which implements this interface contain
 * action with AuthorDAO in DAO layer
 * 
 * @author Uladzislau_Minich
 *
 */
public interface AuthorService extends BaseService<Author> {
	/**
	 * Find Author by author name
	 * 
	 * @param authorName
	 * @return author 
	 * @throws ServiceException
	 */
	Author findAuthorByName(String authorName) throws ServiceException;

	/**
	 * Expires author
	 * 
	 * @param idAuthor
	 * @return	
	 * @throws ServiceException
	 */
	boolean expiredAuthor(Long idAuthor) throws ServiceException;

	/**
	 * Find news's author
	 * 
	 * @param idNews
	 * @return author 
	 * @throws ServiceException
	 */
	Author findAuthorByNews(Long idNews) throws ServiceException;

	/**
	 * Find actual authors
	 * 
	 * @return list of authors
	 * @throws ServiceException
	 */
	List<Author> findNotExpiredAuthors() throws ServiceException;
	/**
	 * Find all authors
	 * 
	 * @return list of authors
	 * @throws ServiceException
	 */
	List<Author> findAllAuthors() throws ServiceException;
}
