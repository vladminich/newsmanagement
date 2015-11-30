package com.epam.newscommon.service;

import java.util.List;

import com.epam.newscommon.exception.ServiceException;
import com.epam.newscommon.entity.Tag;

/**
 * Interface TagService. Classes which implements this interface contain action
 * with TagDAO in DAO layer
 * 
 * @author Uladzislau_Minich
 *
 */
public interface TagService extends BaseService<Tag> {
	/**
	 * Find all tags which contain some news
	 * 
	 * @param newsId
	 * @return list of tags
	 * @throws ServiceException
	 */
	List<Tag> findAllNewsTag(Long newsId) throws ServiceException;

	/**
	 * Find all tags
	 * 
	 * @return list of tags
	 * @throws ServiceException
	 */
	List<Tag> findAllTag() throws ServiceException;
}
