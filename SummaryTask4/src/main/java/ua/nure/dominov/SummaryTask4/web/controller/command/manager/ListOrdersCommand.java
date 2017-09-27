/**
 * 
 */
package ua.nure.dominov.SummaryTask4.web.controller.command.manager;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.nure.dominov.SummaryTask4.db.bo.bo.TourBo;
import ua.nure.dominov.SummaryTask4.db.bo.factory.AbstractBoFactory;
import ua.nure.dominov.SummaryTask4.db.model.Tour;
import ua.nure.dominov.SummaryTask4.exceptions.ServiceException;
import ua.nure.dominov.SummaryTask4.util.FilesPathsStorage;
import ua.nure.dominov.SummaryTask4.web.controller.command.AbstractCommand;
import ua.nure.dominov.SummaryTask4.web.controller.command.annotations.CommandUserRoleCoefficient;
import ua.nure.dominov.SummaryTask4.web.controller.command.annotations.CommandRequestMapping;

/**
 * @author calango
 *
 */
@CommandUserRoleCoefficient(roleCoefficient = 90)
@CommandRequestMapping(url = "/manager/order-list")
public class ListOrdersCommand extends AbstractCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private static final Logger LOGGER = LogManager.
			getLogger(ListOrdersCommand.class.getName());

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
		LOGGER.info("Command starts");
		final TourBo tourBo =  AbstractBoFactory.getBoFactory(AbstractBoFactory.
				SIMPLEDEFAULTFAC).getTourBo();
		final List<Tour> reserved = tourBo.getReservedTours();
		final List<Tour> freeTours = tourBo.getFreeTours();
		request.getSession().setAttribute("reserved", reserved);
		request.getSession().setAttribute("tours", freeTours);
		LOGGER.info("Command finished");

		return "order-list";
	}

	/* (non-Javadoc)
	 * @see ua.nure.dominov.SummaryTask4.web.controller.command.Command#executeGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public final String executeGet(final HttpServletRequest request, 
			final HttpServletResponse response) {
		return FilesPathsStorage.PAGE_LIST_ORDERS;
	}

}
