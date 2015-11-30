package com.epam.newscommon.service;

import com.epam.newscommon.exception.ServiceException;

/**
 * Interface BaseService.
 * 
 * @author Uladzislau_Minich
 *
 */
public interface BaseService<T> {
	/**
	 * Create entity
	 * 
	 * @param entity
	 * @return entity's id  
	 * @throws ServiceException
	 */
	Long save(T entity) throws ServiceException;

	/**
	 * Find object by id
	 * 
	 * @param id
	 * @return object
	 * @throws ServiceException
	 */
	T findById(Long id) throws ServiceException;

	/**
	 * Update object's information
	 * 
	 * @param entity
	 * @return
	 * @throws ServiceException
	 */
	boolean update(T entity) throws ServiceException;

	/**
	 * Delete entity by id
	 * 
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	boolean deleteById(Long id) throws ServiceException;

}
