package com.epam.newscommon.service.implementation;

import java.util.List;

import com.epam.newscommon.exception.DAOException;
import com.epam.newscommon.exception.ServiceException;
import com.epam.newscommon.dao.TagDAO;
import com.epam.newscommon.entity.Tag;
import com.epam.newscommon.service.TagService;

/**
 * Class TagServiceImpl. Implementation of the TagService
 * 
 * @author Uladzislau_Minich
 *
 */
public class TagServiceImpl implements TagService {
	private TagDAO tagDAO;

	/**
	 * @see {@link com.epam.newscommon.service.TagService#findAllNewsTag(int)}
	 */
	@Override
	public List<Tag> findAllNewsTag(Long newsId) throws ServiceException {
		List<Tag> tags = null;
		try {
			tags = tagDAO.findAllNewsTag(newsId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return tags;
	}

	/**
	 * @see {@link com.epam.newscommon.service.TagService#save(Tag)}
	 */
	@Override
	public Long save(Tag entity) throws ServiceException {
		try {
			entity.setTagId(tagDAO.create(entity));
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return entity.getTagId();

	}

	/**
	 * @see {@link com.epam.newscommon.service.TagService#findById(int)}
	 */
	@Override
	public Tag findById(Long id) throws ServiceException {
		Tag tag = null;
		try {
			tag = tagDAO.findById(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return tag;
	}

	/**
	 * @see {@link com.epam.newscommon.service.TagService#update(Tag)}
	 */
	@Override
	public boolean update(Tag entity) throws ServiceException {
		boolean flag = false;
		try {
			flag = tagDAO.update(entity);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return flag;
	}

	/**
	 * @see {@link com.epam.newscommon.service.TagService#deleteById(int)}
	 */
	@Override
	public boolean deleteById(Long id) throws ServiceException {
		boolean flag = false;
		try {
			flag = tagDAO.deleteById(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return flag;
	}

	/**
	 * @see {@link com.epam.newscommon.service.TagService#findAllTag()}
	 */
	@Override
	public List<Tag> findAllTag() throws ServiceException {
		List<Tag> tags = null;
		try {
			tags = tagDAO.findAllTag();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return tags;
	}

	/**
	 * Set a field tagDAO
	 * 
	 * @param tagDAO
	 */
	public void setTagDAO(TagDAO tagDAO) {
		this.tagDAO = tagDAO;
	}

}
