/**
 * 
 */
package ua.nure.dominov.SummaryTask4.exceptions;

/**
 * @author calango
 *
 */
public class CommandException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	public CommandException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 */
	public CommandException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public CommandException(final String message) {
		super(message);
	}

}
