/**
 * 
 */
package ua.nure.dominov.SummaryTask4.web.controller.command.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.nure.dominov.SummaryTask4.db.bo.factory.AbstractBoFactory;
import ua.nure.dominov.SummaryTask4.db.model.User;
import ua.nure.dominov.SummaryTask4.exceptions.ServiceException;
import ua.nure.dominov.SummaryTask4.util.FilesPathsStorage;
import ua.nure.dominov.SummaryTask4.util.Secure;
import ua.nure.dominov.SummaryTask4.web.controller.command.AbstractCommand;
import ua.nure.dominov.SummaryTask4.web.controller.command.annotations.CommandRequestMapping;
import ua.nure.dominov.SummaryTask4.web.controller.command.annotations.CommandUserRoleCoefficient;

/**
 * @author calango
 *
 */
@CommandUserRoleCoefficient(roleCoefficient = 10)
@CommandRequestMapping(url = "/client/edit-profile")
public class EditProfile extends AbstractCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	private static final Logger LOGGER = LogManager.getLogger(
			EditProfile.class.getName());

	/* (non-Javadoc)
	 * @see ua.nure.dominov.SummaryTask4.web.controller.command.Command#executePost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public final String executePost(final HttpServletRequest request, 
			final HttpServletResponse response) throws IOException, 
				ServletException, ServiceException {
		
		final User user = (User) request.getSession().getAttribute("user");
		final String name = request.getParameter("name");
		final String email = request.getParameter("email");
		final String oldPassword = request.getParameter("oldpassword");
		final String newPassword = request.getParameter("newpassword");
		
		if ((name != null) && (name != "")) {
			user.setName(name);
		}
		if ((email != null) && (email != "")) {
			user.setEmail(email);
		}
		
		if (oldPassword != null) {
			String encryptedOldPassword = Secure.siphire(oldPassword,
					user.getSalt());
			if (encryptedOldPassword.equals(user.getPassword())) {
				String encryptedNewPassword = Secure.siphire(newPassword, user.getSalt());
				user.setPassword(encryptedNewPassword);
			}
		}
		if (!AbstractBoFactory.getBoFactory(AbstractBoFactory.SIMPLEDEFAULTFAC).getUserBo().
			updateUser(user)) {
			LOGGER.error("Issue in EditProfile: I can't update user in db");
		}
		request.getSession().removeAttribute("user");
		request.getSession().setAttribute("user", user);
		
		return "edit-profile";
	}

	/* (non-Javadoc)
	 * @see ua.nure.dominov.SummaryTask4.web.controller.command.Command#executeGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public final String executeGet(final HttpServletRequest request, 
			final HttpServletResponse response) {
		return FilesPathsStorage.PAGE_SETTINGS;
	}

}
