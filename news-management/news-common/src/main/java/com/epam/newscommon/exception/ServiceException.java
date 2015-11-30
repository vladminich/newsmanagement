package com.epam.newscommon.exception;

/**
 * Class Service Exception. Thrown in Service layer
 * 
 * @author Uladzislau_Minich
 *
 */
public class ServiceException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates of the new Service Exception
	 */
	public ServiceException() {
		super();
	}

	/**
	 * Instantiates of the new Service Exception
	 * 
	 * @param message
	 * @param cause
	 */
	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates of the new Service Exception
	 * 
	 * @param message
	 */
	public ServiceException(String message) {
		super(message);
	}

	/**
	 * Instantiates of the new Service Exception
	 * 
	 * @param cause
	 */
	public ServiceException(Throwable cause) {
		super(cause);
	}

}
