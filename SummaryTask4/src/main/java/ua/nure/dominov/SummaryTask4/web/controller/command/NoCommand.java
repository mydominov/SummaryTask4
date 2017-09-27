/**
 * 
 */
package ua.nure.dominov.SummaryTask4.web.controller.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.nure.dominov.SummaryTask4.exceptions.ServiceException;
import ua.nure.dominov.SummaryTask4.util.FilesPathsStorage;
import ua.nure.dominov.SummaryTask4.web.controller.command.annotations.CommandRequestMapping;

/**
 * @author calango
 *
 */
@CommandRequestMapping(url = "/no-command")
public class NoCommand extends AbstractCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	private static final Logger LOGGER = LogManager.getLogger(
			NoCommand.class.getName());
	
	/**
	 * 
	 */
	public static final String ERRORMESSAGE = "#404 No such page was found!";

	/* (non-Javadoc)
	 * @see ua.nure.dominov.SummaryTask4.web.command.Command#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public final String executePost(final HttpServletRequest request, 
			final HttpServletResponse response) throws IOException, 
				ServletException, ServiceException {
		return "no-command";
	}

	@Override
	public final String executeGet(final HttpServletRequest request, 
			final HttpServletResponse response) {
		LOGGER.debug("Error command starts");
		request.setAttribute("errorMessage", ERRORMESSAGE);
		LOGGER.debug("Command finished");
		return FilesPathsStorage.PAGE_ERROR_PAGE;
	}

}
