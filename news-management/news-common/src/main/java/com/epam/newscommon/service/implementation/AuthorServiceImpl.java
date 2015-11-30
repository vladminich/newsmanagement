package com.epam.newscommon.service.implementation;

import java.util.List;

import com.epam.newscommon.exception.DAOException;
import com.epam.newscommon.exception.ServiceException;
import com.epam.newscommon.dao.AuthorDAO;
import com.epam.newscommon.dao.implementation.AuthorDAOImpl;
import com.epam.newscommon.entity.Author;
import com.epam.newscommon.service.AuthorService;

/**
 * Class AuthorServiceImpl. Implementation of the AuthorService
 * 
 * @author Uladzislau_Minich
 *
 */
public class AuthorServiceImpl implements AuthorService {
	private AuthorDAO authorDAO;

	/**
	 * @see {@link com.epam.newscommon.service.AuthorService#save()}
	 */
	@Override
	public Long save(Author entity) throws ServiceException {
		try {
			entity.setAuthorId(authorDAO.create(entity));
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return entity.getAuthorId();
	}

	/**
	 * @see {@link com.epam.newscommon.service.AuthorService#findById()}
	 */
	@Override
	public Author findById(Long id) throws ServiceException {
		Author author = null;
		try {
			author = authorDAO.findById(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return author;
	}

	/**
	 * @see {@link com.epam.newscommon.service.AuthorService#update()}
	 */
	@Override
	public boolean update(Author entity) throws ServiceException {
		boolean fl = false;
		try {
			fl = authorDAO.update(entity);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return fl;
	}

	/**
	 * @see {@link com.epam.newscommon.service.AuthorService#deleteById()}
	 */
	@Override
	public boolean deleteById(Long id) throws ServiceException {
		boolean fl = false;
		try {
			fl = authorDAO.deleteById(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return fl;
	}

	/**
	 * @see {@link com.epam.newscommon.service.AuthorService#findAuthorByName()}
	 */
	@Override
	public Author findAuthorByName(String authorName) throws ServiceException {
		Author author = null;
		try {
			author = authorDAO.findAuthorByName(authorName);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return author;
	}

	/**
	 * @see {@link com.epam.newscommon.service.AuthorService#expiredAuthor()}
	 */
	@Override
	public boolean expiredAuthor(Long idAuthor) throws ServiceException {
		boolean fl = false;
		try {
			fl = authorDAO.expiredAuthor(idAuthor);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return fl;
	}

	/**
	 * @see {@link com.epam.newscommon.service.AuthorService#findAuthorByNews()}
	 */
	@Override
	public Author findAuthorByNews(Long idNews) throws ServiceException {
		Author author = null;
		try {
			author = authorDAO.findAuthorByNews(idNews);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return author;
	}

	/**
	 * @see {@link com.epam.newscommon.service.AuthorService#findNotExpiredAuthors()}
	 */
	@Override
	public List<Author> findNotExpiredAuthors() throws ServiceException {
		List<Author> authors = null;
		try {
			authors = authorDAO.findNotExpiredAuthors();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return authors;

	}
	/**
	 * @see {@link com.epam.newscommon.service.AuthorService#findAllAuthors()}
	 */
	@Override
	public List<Author> findAllAuthors() throws ServiceException {
		List<Author> authors = null;
		try {
			authors = authorDAO.findAllAuthors();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return authors;

	}
	/**
	 * Set a field authorDAO
	 * 
	 * @param authorDAO
	 */
	public void setAuthorDAO(AuthorDAOImpl authorDAO) {
		this.authorDAO = authorDAO;
	}

}
