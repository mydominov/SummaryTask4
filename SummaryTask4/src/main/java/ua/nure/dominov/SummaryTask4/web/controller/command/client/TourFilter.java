/**
 * 
 */
package ua.nure.dominov.SummaryTask4.web.controller.command.client;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.nure.dominov.SummaryTask4.db.bo.factory.AbstractBoFactory;
import ua.nure.dominov.SummaryTask4.db.model.Tour;
import ua.nure.dominov.SummaryTask4.exceptions.ServiceException;
import ua.nure.dominov.SummaryTask4.util.FilesPathsStorage;
import ua.nure.dominov.SummaryTask4.web.controller.command.AbstractCommand;
import ua.nure.dominov.SummaryTask4.web.controller.command.annotations.CommandRequestMapping;
import ua.nure.dominov.SummaryTask4.web.controller.command.annotations.CommandUserRoleCoefficient;

/**
 * @author calango
 *
 */
@CommandUserRoleCoefficient(roleCoefficient = 10)
@CommandRequestMapping(url = "/client/filter")
public class TourFilter extends AbstractCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	private static final Logger LOGGER = LogManager.getLogger(
			TourFilter.class.getName());
	
	
	/* (non-Javadoc)
	 * @see ua.nure.dominov.SummaryTask4.web.controller.command.Command#executePost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public final String executePost(final HttpServletRequest request, 
			final HttpServletResponse response) throws IOException, 
				ServletException, ServiceException {
		
		final SearchQueryBean sqb = new SearchQueryBean();
		
		SearchQueryConstructor.generateMin(request, sqb);
		SearchQueryConstructor.generateMax(request, sqb);
		SearchQueryConstructor.normalization(sqb);
		SearchQueryConstructor.generateTypeOfHoliday(request, sqb);
		SearchQueryConstructor.generateNumberOfPeople(request, sqb);
		SearchQueryConstructor.generateHotelType(request, sqb);
		
		final String query = SearchQueryConstructor.generateSQLQuery(sqb);
		LOGGER.info(query);
		final List<Tour> tours = AbstractBoFactory.getBoFactory(AbstractBoFactory.SIMPLEDEFAULTFAC).
				getTourBo().executeDinamicQuery(query);
		request.getSession().removeAttribute("freetours");
		request.getSession().setAttribute("freetours", tours);
		return "filter";
	}

	@Override
	public final String executeGet(final HttpServletRequest request, 
			final HttpServletResponse response) {
		return FilesPathsStorage.COMMAND_TOUR_LIST;
	}

}
