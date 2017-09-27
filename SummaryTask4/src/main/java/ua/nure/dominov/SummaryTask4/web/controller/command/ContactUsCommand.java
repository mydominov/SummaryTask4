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

import ua.nure.dominov.SummaryTask4.db.model.User;
import ua.nure.dominov.SummaryTask4.exceptions.ServiceException;
import ua.nure.dominov.SummaryTask4.util.FilesPathsStorage;
import ua.nure.dominov.SummaryTask4.web.controller.command.annotations.CommandRequestMapping;
import ua.nure.dominov.SummaryTask4.web.controller.command.annotations.CommandUserRoleCoefficient;
import ua.nure.dominov.SummaryTask4.web.jms.JavaMail;

/**
 * @author calango
 *
 */
@CommandUserRoleCoefficient(roleCoefficient = 1)
@CommandRequestMapping(url = "/contact")
public class ContactUsCommand extends AbstractCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	private static final Logger LOGGER = LogManager.getLogger(
			ContactUsCommand.class.getName());

	/* (non-Javadoc)
	 * @see ua.nure.dominov.SummaryTask4.web.controller.command.Command#executePost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public final String executePost(final HttpServletRequest request, 
			final HttpServletResponse response) throws IOException, 
				ServletException, ServiceException {
		LOGGER.info("Sending letter to us begins");
		final User user = (User) request.getSession().getAttribute("user");
		final String email = request.getParameter("email");
		final String subject = request.getParameter("subject");
		final String text = request.getParameter("text");
		JavaMail.sendMail(user, email, subject, text);
		LOGGER.info("Sending letter to us finished");
		return "contact";
	}

	/* (non-Javadoc)
	 * @see ua.nure.dominov.SummaryTask4.web.controller.command.Command#executeGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public final String executeGet(final HttpServletRequest request, 
			final HttpServletResponse response) {
		request.setAttribute("successletter", true);
		return FilesPathsStorage.PAGE_CONTACT_US;
	}

}
