package com.epam.newscommon.dao;

import java.util.List;

import com.epam.newscommon.exception.DAOException;
import com.epam.newscommon.entity.Tag;

/**
 * Interface TagDAO. Classes which implements this interface contain action with
 * table "TAG" in the database
 * 
 * @author Uladzislau_Minich
 *
 */
public interface TagDAO extends BaseDAO<Tag> {
	/**
	 * Find all tags which contain some news
	 * 
	 * @param newsId
	 * @return list of entities tag
	 * @throws DAOException
	 */
	List<Tag> findAllNewsTag(Long newsId) throws DAOException;

	/**
	 * Find all tags
	 * 
	 * @return list of entities tag
	 * @throws DAOException
	 */
	List<Tag> findAllTag() throws DAOException;

}
