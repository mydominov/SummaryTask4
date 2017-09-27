package ua.nure.dominov.SummaryTask4.web.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.nure.dominov.SummaryTask4.exceptions.ServiceException;
import ua.nure.dominov.SummaryTask4.util.FilesPathsStorage;
import ua.nure.dominov.SummaryTask4.web.controller.command.AbstractCommand;
import ua.nure.dominov.SummaryTask4.web.controller.command.CommandFactory;

/**
 * Servlet implementation class MainController.
 */
@WebServlet("/pages/*")
public class FrontController extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	private static final Logger LOGGER = LogManager.getLogger(
			FrontController.class.getName());

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected final void doGet(final HttpServletRequest request, 
			final HttpServletResponse response) throws ServletException, 
				IOException {
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected final void doPost(final HttpServletRequest request, 
			final HttpServletResponse response) throws ServletException, 
				IOException {
		process(request, response);
	}
	
	/**
	 * Main method of this controller.
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void process(final HttpServletRequest request,
			final HttpServletResponse response) throws IOException, 
				ServletException {
		
		LOGGER.debug("Controller starts");
		
		// extract command name from the request
		final String commandName = request.getPathInfo();
		LOGGER.trace("Request parameter: command --> " + commandName);

		// obtain command object by its name
		final AbstractCommand command = CommandFactory.getCommand(
				request.getSession(), commandName);
		LOGGER.trace("Obtained command --> " + command);

		// execute command and get forward address
		String forward = FilesPathsStorage.PAGE_ERROR_PAGE;
		try {
			if (request.getMethod().equals("POST")) {
				forward = command.executePost(request, response);
			} else {
				forward = command.executeGet(request, response);
			}
		} catch (ServiceException ex) {
			request.getSession().setAttribute("errorMessage", ex.getMessage());
		}
		LOGGER.trace("Forward address --> " + forward);

		LOGGER.debug("Controller finished, now go to forward address --> " + forward);
		
		// go to forward
		if (request.getMethod().equals("POST")) {
			response.sendRedirect(forward);
		} else {
			request.getRequestDispatcher(forward).forward(request, response);
		}
	}

}