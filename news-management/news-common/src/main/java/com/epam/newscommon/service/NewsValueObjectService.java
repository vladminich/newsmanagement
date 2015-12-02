package com.epam.newscommon.service;

import java.util.List;

import com.epam.newscommon.exception.ServiceException;
import com.epam.newscommon.entity.Author;
import com.epam.newscommon.entity.Comment;
import com.epam.newscommon.entity.News;
import com.epam.newscommon.entity.NewsValueObject;
import com.epam.newscommon.entity.SearchCriteriaObject;
import com.epam.newscommon.entity.Tag;

/**
 * Interface NewsValueObjectService. Classes which implements it work with new
 * object (it contain {@link com.epam.newscommon.entity.Author},
 * {@link com.epam.newscommon.entity.News}, list of
 * {@link com.epam.newscommon.entity.Tag}, list of
 * {@link com.epam.newscommon.entity.Comment}
 * 
 * @author Uladzislau_Minich
 *
 */
public interface NewsValueObjectService {
	/**
	 * Save simple news message
	 * 
	 * @param obj
	 * @return news value object
	 * @throws ServiceException
	 */
	NewsValueObject saveNews(News news, Author author, List<Tag> tags) throws ServiceException;

	/**
	 * Delete simple news message
	 * 
	 * @param obj
	 * @return
	 * @throws ServiceException
	 */
	void deleteNews(Long idNews) throws ServiceException;

	/**
	 * Find simple news message by id
	 * 
	 * @param obj
	 * @return news value object
	 * @throws ServiceException
	 */
	NewsValueObject findNews(Long newsId) throws ServiceException;

	/**
	 * Update simple news message
	 * 
	 * @param obj
	 * @return news value object
	 * @throws ServiceException
	 */
	NewsValueObject updateNews(NewsValueObject obj) throws ServiceException;

	/**
	 * Find author by name
	 * 
	 * @param name
	 * @return author
	 * @throws ServiceException
	 */
	Author findAuthorByName(String name) throws ServiceException;

	/**
	 * Find all news some author
	 * 
	 * @param authorId
	 * @return list of authors
	 * @throws ServiceException
	 */
	List<NewsValueObject> findAllAuthorNews(Long authorId) throws ServiceException;

	/**
	 * Find simple news message
	 * 
	 * @return list of news value objects
	 * @throws ServiceException
	 */
	List<NewsValueObject> findAllNews(int indexPage, int countNews) throws ServiceException;

	/**
	 * Find news by search criteria
	 * 
	 * @return list of news value objects
	 * @throws ServiceException
	 */
	List<NewsValueObject> findBySearchCriteria(SearchCriteriaObject sc, int indexPageNews, int countNews)
			throws ServiceException;

	/**
	 * Find all tags
	 * 
	 * @return list of tags
	 * @throws ServiceException
	 */
	List<Tag> findAllTags() throws ServiceException;

	/**
	 * Find not expired authors
	 * 
	 * @return list of authors
	 * @throws ServiceException
	 */
	List<Author> getCurrentAuthors() throws ServiceException;

	/**
	 * Add comment
	 * 
	 * @param comment
	 * @throws ServiceException
	 */
	void addComment(Comment comment) throws ServiceException;

	/**
	 * Save authors
	 * 
	 * @param author
	 * @throws ServiceException
	 */
	void saveAuthor(Author author) throws ServiceException;

	/**
	 * Delete author
	 * 
	 * @param authorId
	 * @throws ServiceException
	 */
	void deleteAuthor(Long authorId) throws ServiceException;

	/**
	 * Delete comment
	 * 
	 * @param commentId
	 * @throws ServiceException
	 */
	void deleteComment(Long commentId) throws ServiceException;

	/**
	 * Save tag
	 * 
	 * @param tag
	 * @throws ServiceException
	 */
	void addTag(Tag tag, Long newsId) throws ServiceException;

	/**
	 * Delete tag
	 * 
	 * @param tagId
	 * @throws ServiceException
	 */
	void deleteTag(Long tagId, Long newsId) throws ServiceException;

	/**
	 * Expired author
	 * 
	 * @param author
	 * @throws ServiceException
	 */
	void expiredAuthor(Author author) throws ServiceException;

	/**
	 * Count news
	 * 
	 * @return amount of news
	 * @throws ServiceException
	 */
	int countNews() throws ServiceException;

	/**
	 * Find all authors
	 * 
	 * @return list of authors
	 * @throws ServiceException
	 */
	List<Author> findAllAuthors() throws ServiceException;

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
