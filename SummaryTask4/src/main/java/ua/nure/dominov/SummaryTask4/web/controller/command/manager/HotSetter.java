/**
 * 
 */
package ua.nure.dominov.SummaryTask4.web.controller.command.manager;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
@CommandUserRoleCoefficient(roleCoefficient = 90)
@CommandRequestMapping(url = "/manager/set-hot")
public class HotSetter extends AbstractCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see ua.nure.dominov.SummaryTask4.web.controller.command.Command#executePost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public final String executePost(final HttpServletRequest request,
			final HttpServletResponse response) throws IOException, 
				ServletException, ServiceException {
		final String name = request.getParameter("name");
		final boolean isHot = (request.getParameter("ishot") != null);
		AbstractBoFactory.getBoFactory(AbstractBoFactory.SIMPLEDEFAULTFAC).
			getTourBo().updateIsHot(name, isHot);
		return "set-hot";
	}

	/* (non-Javadoc)
	 * @see ua.nure.dominov.SummaryTask4.web.controller.command.Command#executeGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public final String executeGet(final HttpServletRequest request,
			final HttpServletResponse response) {
		return FilesPathsStorage.PAGE_MAIN_PAGE;
	}

}
