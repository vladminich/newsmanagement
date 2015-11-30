package com.epam.newscommon.dao;

import com.epam.newscommon.exception.DAOException;

/**
 * Interface Base DAO. Root DAO which implement four basic C.R.U.D operations.
 * <T> - the generic type
 * 
 * @author Uladzislau_Minich.
 */
public interface BaseDAO<T> {
	/**
	 * Create entity
	 * 
	 * @param entity
	 * @return
	 * @throws DAOException
	 */
	Long create(T entity) throws DAOException;

	/**
	 * Find object by id
	 * 
	 * @param id
	 * @return object
	 * @throws DAOException
	 */
	T findById(Long id) throws DAOException;

	/**
	 * Update object's information
	 * 
	 * @param entity
	 * @return
	 * @throws DAOException
	 */
	boolean update(T entity) throws DAOException;

	/**
	 * Delete entity by id
	 * 
	 * @param id
	 * @return
	 * @throws DAOException
	 */
	boolean deleteById(Long id) throws DAOException;

}
