/**
 * 
 */
package ua.nure.dominov.SummaryTask4.web.controller.command.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.nure.dominov.SummaryTask4.db.bo.bo.UserBo;
import ua.nure.dominov.SummaryTask4.db.bo.factory.AbstractBoFactory;
import ua.nure.dominov.SummaryTask4.exceptions.ServiceException;
import ua.nure.dominov.SummaryTask4.util.FilesPathsStorage;
import ua.nure.dominov.SummaryTask4.web.controller.command.AbstractCommand;
import ua.nure.dominov.SummaryTask4.web.controller.command.annotations.CommandRequestMapping;
import ua.nure.dominov.SummaryTask4.web.controller.command.annotations.CommandUserRoleCoefficient;

/**
 * @author calango
 *
 */
@CommandUserRoleCoefficient(roleCoefficient = 100)
@CommandRequestMapping(url = "/admin/edit-user")
public class UpdateUserCommand extends AbstractCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private static final Logger LOGGER = LogManager.getLogger(
			UpdateUserCommand.class.getName());

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ua.nure.dominov.SummaryTask4.web.controller.command.Command#executePost(
	 * javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public final String executePost(final HttpServletRequest request, 
			final HttpServletResponse response) throws IOException, 
				ServletException, ServiceException {
		LOGGER.info("sub " + request.getParameter("submit"));
		if (request.getParameter("submit").equals("submit")) {
			final long userId = Long.parseLong(request.getParameter("id"));
			final String name = request.getParameter("name");
			final String email = request.getParameter("email");
			final String userRole = request.getParameter("userrole");
			final long balance = Long.parseLong(request.getParameter("balance"));
			LOGGER.info("blance " + balance);
			final AbstractBoFactory boFactory = AbstractBoFactory.getBoFactory(
					AbstractBoFactory.SIMPLEDEFAULTFAC);
			final UserBo userBo = boFactory.getUserBo();
			final long roleId = userBo.getRoleIdFromRoleName(userRole);
			if (!userBo.updateFromAdmin(userId, name, email, roleId, balance)) {
				LOGGER.error("Issue in executePost-SUBMIT!");
			}
		} else {
			final long id = Long.parseLong(request.getParameter("id"));
			final AbstractBoFactory boFactory = AbstractBoFactory.getBoFactory(
					AbstractBoFactory.SIMPLEDEFAULTFAC);
			if (!boFactory.getUserBo().deleteUserById(id)) {
				LOGGER.error("Issue in executePost-DELETE!");
			}
		}
		return "edit-user";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ua.nure.dominov.SummaryTask4.web.controller.command.Command#executeGet(
	 * javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public final String executeGet(final HttpServletRequest request,
			final HttpServletResponse response) {
		return FilesPathsStorage.COMMAND_TOUR_MENU;
	}

}
