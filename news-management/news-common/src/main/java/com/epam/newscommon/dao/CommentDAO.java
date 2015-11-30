package com.epam.newscommon.dao;

import java.util.List;

import com.epam.newscommon.exception.DAOException;
import com.epam.newscommon.entity.Comment;

/**
 * Interface CommentDAO. Classes which implements this interface contain action
 * with table "COMMENTS" in the database
 * 
 * @author Uladzislau_Minich
 *
 */
public interface CommentDAO extends BaseDAO<Comment> {
	/**
	 * Find all news's comments
	 * 
	 * @return list of objects Comment
	 * @throws DAOException
	 */
	List<Comment> showAllNewsComment(Long newsId) throws DAOException;
}
