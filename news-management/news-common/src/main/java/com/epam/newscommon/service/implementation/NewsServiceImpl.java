package com.epam.newscommon.service.implementation;

import java.util.List;

import com.epam.newscommon.exception.DAOException;
import com.epam.newscommon.exception.ServiceException;
import com.epam.newscommon.dao.NewsDAO;
import com.epam.newscommon.entity.News;
import com.epam.newscommon.entity.SearchCriteriaObject;
import com.epam.newscommon.service.NewsService;

/**
 * Class NewsServiceImpl. Implementation of the NewsService
 * 
 * @author Uladzislau_Minich
 *
 */
public class NewsServiceImpl implements NewsService {
	private NewsDAO newsDAO;

	/**
	 * @see {@link com.epam.newscommon.service.NewsService#save()}
	 */
	@Override
	public Long save(News entity) throws ServiceException {
		try {
			entity.setNewsId(newsDAO.create(entity));
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return entity.getNewsId();
	}

	/**
	 * @see {@link com.epam.newscommon.service.NewsService#findById()}
	 */
	@Override
	public News findById(Long id) throws ServiceException {
		News news = null;
		try {
			news = newsDAO.findById(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return news;
	}

	/**
	 * @see {@link com.epam.newscommon.service.NewsService#update()}
	 */
	@Override
	public boolean update(News entity) throws ServiceException {
		boolean flag = false;
		try {
			flag = newsDAO.update(entity);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return flag;
	}

	/**
	 * @see {@link com.epam.newscommon.service.NewsService#deleteById(int)}
	 */
	@Override
	public boolean deleteById(Long id) throws ServiceException {
		boolean flag = false;
		try {
			flag = newsDAO.deleteById(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return flag;
	}

	/**
	 * @see {@link com.epam.newscommon.service.NewsService#findAllNews()}
	 */
	@Override
	public List<News> findAllNews(int indexPageNews,int countNews) throws ServiceException {
		List<News> newsAll = null;
		try {
			newsAll = newsDAO.findAllNews(indexPageNews,countNews);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return newsAll;
	}

	/**
	 * @see {@link com.epam.newscommon.service.NewsService#findNewsByTitle(String)}
	 */
	@Override
	public News findNewsByTitle(String title) throws ServiceException {
		News news = null;
		try {
			news = newsDAO.findNewsByTitle(title);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return news;
	}

	/**
	 * @see {@link com.epam.newscommon.service.NewsService#findNewsBySearchCriteria(SearchCriteriaObject)}
	 */
	@Override
	public List<News> findNewsBySearchCriteria(SearchCriteriaObject sc,int indexPageNews,int countNews) throws ServiceException {
		List<News> searchNews = null;
		try {
			searchNews = newsDAO.findNewsBySearchCriteria(sc,indexPageNews,countNews);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return searchNews;
	}

	/**
	 * @see {@link com.epam.newscommon.service.NewsService#saveNewsAuthors(int, int)}
	 */
	@Override
	public boolean saveNewsAuthors(Long newsId, Long authorId) throws ServiceException {
		boolean flag = false;
		try {
			flag = newsDAO.saveNewsAuthors(newsId, authorId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return flag;
	}

	/**
	 * @see {@link com.epam.newscommon.service.NewsService#deleteNewsAuthor(int)}
	 */
	@Override
	public boolean deleteNewsAuthor(Long newsId) throws ServiceException {
		boolean flag = false;
		try {
			flag = newsDAO.deleteNewsAuthor(newsId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return flag;
	}

	/**
	 * @see {@link com.epam.newscommon.service.NewsService#addNewsTag(int, int)}
	 */
	@Override
	public boolean addNewsTag(Long tagId, Long newsId) throws ServiceException {
		boolean flag = false;
		try {
			flag = newsDAO.addNewsTag(tagId, newsId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return flag;
	}

	/**
	 * @see {@link com.epam.newscommon.service.NewsService#deleteNewsTag(int)}
	 */
	@Override
	public boolean deleteNewsTag(Long idNews) throws ServiceException {
		boolean flag = false;
		try {
			flag = newsDAO.deleteNewsTag(idNews);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return flag;
	}

	/**
	 * @see {@link com.epam.newscommon.service.NewsService#findAllAuthorNews(int)}
	 */
	@Override
	public List<News> findAllAuthorNews(Long authorId) throws ServiceException {
		List<News> searchNews = null;
		try {
			searchNews = newsDAO.findAllAuthorsNews(authorId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return searchNews;
	}
	/**
	 * @see {@link com.epam.newscommon.service.NewsService#countNews()}
	 */
	@Override
	public int countNews() throws ServiceException {
		int amount = 0;
		try {
			 amount = newsDAO.countNews();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return amount;
	}

	@Override
	public Long findPreviousNews(News currentNews) throws ServiceException {
		Long previousNewsId=0L;
		try {
			previousNewsId = newsDAO.findPreviousNews(currentNews);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return previousNewsId;
	}

	@Override
	public Long findNextNews(News currentNews) throws ServiceException {
		Long nextNewsId=0L;
		try {
			nextNewsId = newsDAO.findNextNews(currentNews);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return nextNewsId;
	}
	
	/**
	 * Set  newsDAO
	 * 
	 * @param newsDAO
	 */
	public void setNewsDAO(NewsDAO newsDAO) {
		this.newsDAO = newsDAO;
	}


	

}
