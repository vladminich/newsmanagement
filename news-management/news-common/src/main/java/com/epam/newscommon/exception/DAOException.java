package com.epam.newscommon.exception;

/**
 * Class DAO Exception. Thrown in DAO layer
 * 
 * @author Uladzislau_Minich
 *
 */
public class DAOException extends Exception {
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates of the new DAO Exception
	 */
	public DAOException() {
		super();
	}

	/**
	 * Instantiates of the new DAO Exception
	 * 
	 * @param message
	 * @param exc
	 */
	public DAOException(String message, Throwable exc) {
		super(message, exc);
	}

	/**
	 * Instantiates of the new DAO Exception
	 * 
	 * @param message
	 */
	public DAOException(String message) {
		super(message);
	}

	/**
	 * Instantiates of the new DAO Exception
	 * 
	 * @param exc
	 */
	public DAOException(Throwable exc) {
		super(exc);
	}

}
