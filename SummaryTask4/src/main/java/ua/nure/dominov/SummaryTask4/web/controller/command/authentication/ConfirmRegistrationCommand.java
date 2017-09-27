/**
 * 
 */
package ua.nure.dominov.SummaryTask4.web.controller.command.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.nure.dominov.SummaryTask4.db.bo.factory.AbstractBoFactory;
import ua.nure.dominov.SummaryTask4.db.model.User;
import ua.nure.dominov.SummaryTask4.db.model.UserRole;
import ua.nure.dominov.SummaryTask4.exceptions.ServiceException;
import ua.nure.dominov.SummaryTask4.util.FilesPathsStorage;
import ua.nure.dominov.SummaryTask4.web.controller.command.AbstractCommand;
import ua.nure.dominov.SummaryTask4.web.controller.command.NoCommand;
import ua.nure.dominov.SummaryTask4.web.controller.command.annotations.CommandRequestMapping;
import ua.nure.dominov.SummaryTask4.web.controller.command.annotations.CommandUserRoleCoefficient;
import ua.nure.dominov.SummaryTask4.web.jms.JavaMail;

/**
 * @author calango
 *
 */
@CommandUserRoleCoefficient(roleCoefficient = 1)
@CommandRequestMapping(url = "/confirm")
public class ConfirmRegistrationCommand extends AbstractCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private static final Logger LOGGER = LogManager.getLogger(
			ConfirmRegistrationCommand.class.getName());
    
	/* (non-Javadoc)
	 * @see ua.nure.dominov.SummaryTask4.web.controller.command.Command#executePost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public final String executePost(final HttpServletRequest request, 
			final HttpServletResponse response) throws IOException, 
				ServletException, ServiceException {
		return new NoCommand().executePost(request, response);
	}

	/* (non-Javadoc)
	 * @see ua.nure.dominov.SummaryTask4.web.controller.command.Command#executeGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public final String executeGet(final HttpServletRequest request, 
			final HttpServletResponse response) {
		final int verificationCode = Integer.parseInt(request.getParameter("code"));
		final AbstractBoFactory boFactory = AbstractBoFactory.getBoFactory(
				AbstractBoFactory.SIMPLEDEFAULTFAC);
		final String email = boFactory.getVerificationBo().
				deleteVerificationCodeAndGetUserMail(verificationCode);
		if (email == null) {
			LOGGER.error("Verification code ERROR");
		} else {
			JavaMail.mailOfSuccess(email);
			if (request.getSession().getAttribute("user") != null) {
				final User user = (User) request.getSession().getAttribute("user");
				user.setUserRole(new UserRole("CLIENT", 25));
				request.getSession().removeAttribute("user");
				request.getSession().setAttribute("user", user);
			}
		}
		return FilesPathsStorage.PAGE_CONFIRM_REDIRECT;
	}

}
