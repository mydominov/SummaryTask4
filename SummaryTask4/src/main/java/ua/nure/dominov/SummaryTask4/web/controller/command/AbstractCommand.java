/**
 * 
 */
package ua.nure.dominov.SummaryTask4.web.controller.command;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.dominov.SummaryTask4.exceptions.ServiceException;
import ua.nure.dominov.SummaryTask4.web.controller.command.annotations.CommandUserRoleCoefficient;

/**
 * Main interface for the Command pattern implementation.
 * @author calango
 *
 */
@CommandUserRoleCoefficient
public abstract class AbstractCommand implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Execution method for command.
	 * @param request
	 * @param response
	 * @return Address to go once the command is executed.
	 * @throws IOException
	 * @throws ServletException
	 * @throws ServiceException
	 */
	public abstract String executePost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException,
				ServiceException;

	/**
	 * @param request
	 * @param response
	 * @return
	 */
	public abstract String executeGet(HttpServletRequest request,
			HttpServletResponse response);
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public final String toString() {
		return getClass().getSimpleName();
	}
}
