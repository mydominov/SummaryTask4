/**
 * 
 */
package ua.nure.dominov.SummaryTask4.web.controller.command.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.nure.dominov.SummaryTask4.exceptions.ServiceException;
import ua.nure.dominov.SummaryTask4.util.FilesPathsStorage;
import ua.nure.dominov.SummaryTask4.web.controller.command.AbstractCommand;
import ua.nure.dominov.SummaryTask4.web.controller.command.annotations.CommandUserRoleCoefficient;
import ua.nure.dominov.SummaryTask4.web.controller.command.annotations.CommandRequestMapping;

/**
 * @author calango
 *
 */
@CommandUserRoleCoefficient(roleCoefficient = 1)
@CommandRequestMapping(url = "/logout")
public class LogoutCommand extends AbstractCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	private static final Logger LOGGER = LogManager.getLogger(
			LogoutCommand.class.getName());

	/* (non-Javadoc)
	 * @see ua.nure.dominov.SummaryTask4.web.command.Command#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public final String executePost(final HttpServletRequest request, 
			final HttpServletResponse response) throws IOException, 
				ServletException, ServiceException {
		return "logout";
	}

	/* (non-Javadoc)
	 * @see ua.nure.dominov.SummaryTask4.web.controller.command.Command#executeGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public final String executeGet(final HttpServletRequest request, 
			final HttpServletResponse response) {
		LOGGER.debug("Command starts");
		
		final HttpSession session = request.getSession(false);
		
		if (session.getAttribute("user") != null) {
			session.removeAttribute("user");
		}
		
		LOGGER.debug("Command finished");
		return FilesPathsStorage.PAGE_LOGIN;
	}

}
