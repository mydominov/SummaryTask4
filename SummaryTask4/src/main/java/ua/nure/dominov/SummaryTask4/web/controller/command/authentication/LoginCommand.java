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

import ua.nure.dominov.SummaryTask4.db.bo.factory.AbstractBoFactory;
import ua.nure.dominov.SummaryTask4.db.model.User;
import ua.nure.dominov.SummaryTask4.exceptions.ServiceException;
import ua.nure.dominov.SummaryTask4.util.FilesPathsStorage;
import ua.nure.dominov.SummaryTask4.util.Secure;
import ua.nure.dominov.SummaryTask4.web.controller.command.AbstractCommand;
import ua.nure.dominov.SummaryTask4.web.controller.command.annotations.CommandUserRoleCoefficient;
import ua.nure.dominov.SummaryTask4.web.controller.command.annotations.CommandRequestMapping;

/**
 * @author calango
 *
 */
@CommandUserRoleCoefficient(roleCoefficient = 1)
@CommandRequestMapping(url = "/login")
public class LoginCommand extends AbstractCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	private static final Logger LOGGER = LogManager.getLogger(
			LoginCommand.class.getName());
    
	@Override
	public final String executePost(final HttpServletRequest request, 
			final HttpServletResponse response) throws IOException,
				ServletException, ServiceException {
		LOGGER.debug("Command starts");

		request.getSession().removeAttribute("ex");
		final HttpSession session = request.getSession();

		// obtain login and password from a request
		final AbstractBoFactory boFactory = AbstractBoFactory.getBoFactory(
				AbstractBoFactory.SIMPLEDEFAULTFAC);
		
		final String email = request.getParameter("email");
		final String password = request.getParameter("pass");
		LOGGER.trace("Request parameter: email --> " + email);
		
		final User user = boFactory.getUserBo().getUserByEmail(email);
		LOGGER.trace("Found in DB: user --> " + user);
		LOGGER.info((user == null) + " " + password);
		if (user == null || user.getPassword() == null 
				|| !Secure.checkPassword(user, password)) {
			request.getSession().setAttribute("ex", 
					"Cannot find user with such login/password.");
		} else {

			LOGGER.trace("userRole --> " + user.getUserRole());

			session.setAttribute("user", user);
			LOGGER.trace("Set the session attribute: user --> " + user);
			LOGGER.trace("Set the session attribute: userRole --> " + user.getUserRole());

			LOGGER.info("User " + user + " logged as " + user.getUserRole().toString().toLowerCase());
		}
		LOGGER.debug("Command finished");
		return "login";
	}

	@Override
	public final String executeGet(final HttpServletRequest request, 
			final HttpServletResponse response) {
		return (request.getSession().getAttribute("ex") == null) ? 
				"/index.jsp" : FilesPathsStorage.PAGE_LOGIN;
	}
	
}
