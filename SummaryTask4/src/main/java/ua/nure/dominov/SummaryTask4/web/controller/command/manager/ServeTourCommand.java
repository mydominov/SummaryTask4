/**
 * 
 */
package ua.nure.dominov.SummaryTask4.web.controller.command.manager;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.nure.dominov.SummaryTask4.db.bo.bo.TourBo;
import ua.nure.dominov.SummaryTask4.db.bo.factory.AbstractBoFactory;
import ua.nure.dominov.SummaryTask4.db.model.User;
import ua.nure.dominov.SummaryTask4.exceptions.ServiceException;
import ua.nure.dominov.SummaryTask4.util.FilesPathsStorage;
import ua.nure.dominov.SummaryTask4.util.MoneyEmountCounter;
import ua.nure.dominov.SummaryTask4.web.controller.command.AbstractCommand;
import ua.nure.dominov.SummaryTask4.web.controller.command.annotations.CommandRequestMapping;
import ua.nure.dominov.SummaryTask4.web.controller.command.annotations.CommandUserRoleCoefficient;
import ua.nure.dominov.SummaryTask4.web.jms.JavaMail;

/**
 * @author calango
 *
 */
@CommandUserRoleCoefficient(roleCoefficient = 90)
@CommandRequestMapping(url = "/manager/serve-tour")
public class ServeTourCommand extends AbstractCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private static final Logger LOGGER = LogManager.getLogger(
			ServeTourCommand.class.getName());
	
	/* (non-Javadoc)
	 * @see ua.nure.dominov.SummaryTask4.web.controller.command.Command#executePost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public final String executePost(final HttpServletRequest request, 
			final HttpServletResponse response) throws IOException, 
				ServletException, ServiceException {
		//get info from request
		final long id = Long.parseLong(request.getParameter("id"));
		long price = Long.parseLong(request.getParameter("price"));
		
		final String tourName = request.getParameter("tourname");
		final String email = request.getParameter("reservedBy");
		final String status = request.getParameter("status");
		
		final int maxDiscount = Integer.parseInt(request.getParameter("maxdiscount"));
		final int stepDiscount = Integer.parseInt(request.getParameter("stepdiscount"));
		final int incrementDiscount = Integer.parseInt(request.getParameter("incrementdiscount"));
		
		//Get Bo factory
		final AbstractBoFactory boFactory = AbstractBoFactory.getBoFactory(AbstractBoFactory.SIMPLEDEFAULTFAC);
		
		final TourBo tourBo = boFactory.getTourBo();
		
		final User user = boFactory.getUserBo().getUserByEmail(email);
		final int statusId = tourBo.getStatusId(status);
		LOGGER.info("price: " + price);
		price = MoneyEmountCounter.countPrice(price, user.getPurchasedTours(),
				maxDiscount, stepDiscount, incrementDiscount);
		LOGGER.info("price with discount " + price);
		LOGGER.info("status  - " + status);
		if (!tourBo.managerUpdate(id, price, statusId, 
				maxDiscount, stepDiscount, incrementDiscount)) {
			LOGGER.error("Issue in 58 line. It was not possible to serve a tour");
		} else {
			JavaMail.tourReservationStatus(tourName, user, status, price);
			if (status.equals("paid")) {
				LOGGER.info("Paying...");
				boFactory.getUserBo().payForTheTour(user.getUserId(), price);
				LOGGER.info("Was successfully paid!");
			}
		}
		
		return "serve-tour";
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
