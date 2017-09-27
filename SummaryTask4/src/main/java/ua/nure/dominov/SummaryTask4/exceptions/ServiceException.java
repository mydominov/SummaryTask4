/**
 * 
 */
package ua.nure.dominov.SummaryTask4.exceptions;

/**
 * @author calango
 *
 */
public class ServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	public ServiceException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ServiceException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public ServiceException(final String message) {
		super(message);
	}

}
