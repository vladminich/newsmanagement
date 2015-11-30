package com.epam.newscommon.dao;

import java.util.List;

import com.epam.newscommon.exception.DAOException;
import com.epam.newscommon.entity.Author;

/**
 * Interface AuthorDAO. Classes which implements this interface contain action
 * with table "AUTHOR" in the database
 * 
 * @author Uladzislau_Minich
 *
 */
public interface AuthorDAO extends BaseDAO<Author> {
	/**
	 * Find Author object by author name
	 * 
	 * @param authorName
	 * @return Author object
	 * @throws DAOException
	 */
	Author findAuthorByName(String authorName) throws DAOException;

	/**
	 * Expires author
	 * 
	 * @param idAuthor
	 * @return
	 * @throws DAOException
	 */
	boolean expiredAuthor(Long idAuthor) throws DAOException;

	/**
	 * Find news's author
	 * 
	 * @param idNews
	 * @return
	 * @throws DAOException
	 */
	Author findAuthorByNews(Long idNews) throws DAOException;

	/**
	 * Find actual authors
	 * 
	 * @return list of authors
	 * @throws DAOException
	 */
	List<Author> findNotExpiredAuthors() throws DAOException;
	/**
	 * Find all authors
	 * 
	 * @return list of authors
	 * @throws DAOException
	 */
	List<Author> findAllAuthors() throws DAOException;

}
