/**
 * 
 */
package ua.nure.dominov.SummaryTask4.web.controller.command.client;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.nure.dominov.SummaryTask4.db.bo.factory.AbstractBoFactory;
import ua.nure.dominov.SummaryTask4.db.model.User;
import ua.nure.dominov.SummaryTask4.util.FilesPathsStorage;
import ua.nure.dominov.SummaryTask4.web.controller.command.AbstractCommand;
import ua.nure.dominov.SummaryTask4.web.controller.command.annotations.CommandRequestMapping;
import ua.nure.dominov.SummaryTask4.web.controller.command.annotations.CommandUserRoleCoefficient;

/**
 * @author calango
 *
 */
@CommandUserRoleCoefficient(roleCoefficient = 50)
@CommandRequestMapping(url = "/client/reserve-tour")
public class ReserveTourCommand extends AbstractCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	private static final Logger LOGGER = LogManager.getLogger(
			ReserveTourCommand.class.getName());

	/* (non-Javadoc)
	 * @see ua.nure.dominov.SummaryTask4.web.controller.command.Command#executePost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public final String executePost(final HttpServletRequest request, 
			final HttpServletResponse response) {
		final String name = request.getParameter("name");
		final User user = (User) request.getSession().getAttribute("user");
		final long id = user.getUserId();
		AbstractBoFactory.getBoFactory(AbstractBoFactory.SIMPLEDEFAULTFAC).getTourBo().
			updateClient(name, id);
		return "reserve-tour";
	}

	/* (non-Javadoc)
	 * @see ua.nure.dominov.SummaryTask4.web.controller.command.Command#executeGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public final String executeGet(final HttpServletRequest request, 
			final HttpServletResponse response) {
		LOGGER.error("forvard");
		return FilesPathsStorage.PAGE_MAIN_PAGE;
	}

}
