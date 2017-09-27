/**
 * 
 */
package ua.nure.dominov.SummaryTask4.web.controller.command.authentication;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.nure.dominov.SummaryTask4.db.bo.bo.VerificationCodeBo;
import ua.nure.dominov.SummaryTask4.db.bo.factory.AbstractBoFactory;
import ua.nure.dominov.SummaryTask4.db.model.User;
import ua.nure.dominov.SummaryTask4.db.model.VerificationCode;
import ua.nure.dominov.SummaryTask4.exceptions.ServiceException;
import ua.nure.dominov.SummaryTask4.util.FilesPathsStorage;
import ua.nure.dominov.SummaryTask4.util.Secure;
import ua.nure.dominov.SummaryTask4.util.Validation;
import ua.nure.dominov.SummaryTask4.web.controller.command.AbstractCommand;
import ua.nure.dominov.SummaryTask4.web.controller.command.annotations.CommandUserRoleCoefficient;
import ua.nure.dominov.SummaryTask4.web.controller.command.annotations.CommandRequestMapping;
import ua.nure.dominov.SummaryTask4.web.jms.JavaMail;

/**
 * @author calango
 *
 */
@CommandUserRoleCoefficient(roleCoefficient = 1)
@CommandRequestMapping(url = "/signup")
public class SignUpCommand extends AbstractCommand {

	private static final String REGISTRATION_EXCEPTION = "regEx";

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private static final Logger LOGGER = LogManager.getLogger(
			SignUpCommand.class.getName());

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ua.nure.dominov.SummaryTask4.web.controller.command.Command#execute(javax
	 * .servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public final String executePost(final HttpServletRequest request, 
			final HttpServletResponse response) throws IOException,
				ServletException, ServiceException {
		LOGGER.info("Registration begins!");

		request.getSession().removeAttribute(REGISTRATION_EXCEPTION);
		final String gRecaptchaResponse = request.getParameter(
				"g-recaptcha-response");
		final boolean isValid = Validation.verifyRecaptcha(gRecaptchaResponse);
		LOGGER.info("ReCapcha status => " + isValid);
		if (!isValid) {
			LOGGER.warn("Bad recapcha!");
			request.getSession().setAttribute(REGISTRATION_EXCEPTION, 
					"Bad recapcha, try again.");
		} else {

			final String name = request.getParameter("name");
			final String email = request.getParameter("email");
			final String salt = Secure.generateSalt();
			final String password = Secure.siphire(
					request.getParameter("pass"), salt);
			final User user = new User(name, email, password, salt);
			LOGGER.info(user);
			final AbstractBoFactory boFactory = AbstractBoFactory.getBoFactory(
					AbstractBoFactory.SIMPLEDEFAULTFAC);
			try {
				boFactory.getUserBo().insert(user);
				final VerificationCode userCode = new VerificationCode(user);
				final VerificationCodeBo verificationCodeBo = boFactory.getVerificationBo();
				verificationCodeBo.insert(userCode);
				LOGGER.info("User verification code: " 
						+ userCode.getVerificationUserCode());
				
				JavaMail.confirmMail(user, userCode);

				request.getSession().setAttribute("user", user);
			} catch (SQLException e) {
				LOGGER.error("User duplication: " + e);
				request.getSession().setAttribute(REGISTRATION_EXCEPTION, 
						"This email is already used by another user!");
			}
		}

		return "signup";
	}

	@Override
	public final String executeGet(final HttpServletRequest request, 
			final HttpServletResponse response) {
		return (request.getSession().getAttribute(REGISTRATION_EXCEPTION) == null) 
				? FilesPathsStorage.PAGE_VIEW_REG_STATUS
						: "/registration.jsp";
	}

}
