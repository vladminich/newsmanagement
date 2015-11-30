package com.epam.newscommon.service.implementation;

import java.util.List;

import com.epam.newscommon.exception.DAOException;
import com.epam.newscommon.exception.ServiceException;
import com.epam.newscommon.dao.CommentDAO;
import com.epam.newscommon.entity.Comment;
import com.epam.newscommon.service.CommentService;

/**
 * Class CommentServiceImpl. Implementation of the CommentService
 * 
 * @author Uladzislau_Minich
 *
 */
public class CommentServiceImpl implements CommentService {
	private CommentDAO commentDAO;

	/**
	 * @see {@link com.epam.newscommon.service.CommentService#save()}
	 */
	@Override
	public Long save(Comment entity) throws ServiceException {
		try {
			entity.setCommentId(commentDAO.create(entity));
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return entity.getCommentId();
	}

	/**
	 * @see {@link com.epam.newscommon.service.CommentService#findById()}
	 */
	@Override
	public Comment findById(Long id) throws ServiceException {
		Comment comment = null;
		try {
			comment = commentDAO.findById(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return comment;

	}

	/**
	 * @see {@link com.epam.newscommon.service.CommentService#update()}
	 */
	@Override
	public boolean update(Comment entity) throws ServiceException {
		boolean flag = false;
		try {
			flag = commentDAO.update(entity);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return flag;
	}

	/**
	 * @see {@link com.epam.newscommon.service.CommentService#deleteById()}
	 */
	@Override
	public boolean deleteById(Long id) throws ServiceException {
		boolean flag = false;
		try {
			flag = commentDAO.deleteById(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return flag;
	}

	/**
	 * @see {@link com.epam.newscommon.service.CommentService#showAllNewsComment()}
	 */
	@Override
	public List<Comment> showAllNewsComment(Long id) throws ServiceException {
		List<Comment> comments = null;
		try {
			comments = commentDAO.showAllNewsComment(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return comments;
	}

	/**
	 * Set a field commentDAO
	 * 
	 * @param commentDAO
	 */
	public void setCommentDAO(CommentDAO commentDAO) {
		this.commentDAO = commentDAO;
	}

}
