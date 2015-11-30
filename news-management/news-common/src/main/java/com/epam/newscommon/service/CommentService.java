package com.epam.newscommon.service;

import java.util.List;

import com.epam.newscommon.exception.ServiceException;
import com.epam.newscommon.entity.Comment;
/**
 * Interface CommentService. Classes which implements this interface contain
 * action with CommentDAO in DAO layer
 * 
 * @author Uladzislau_Minich
 *
 */
public interface CommentService extends BaseService<Comment>{
	/**
	 * Find all news's comments
	 * 
	 * @return list of objects Comment
	 * @throws ServiceException
	 */
	List<Comment> showAllNewsComment(Long newsId) throws ServiceException;
}
