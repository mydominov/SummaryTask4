package ua.nure.dominov.SummaryTask4.web.listener;

import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.nure.dominov.SummaryTask4.db.connectionpool.ConnectionPool;
import ua.nure.dominov.SummaryTask4.web.controller.command.CommandFactory;

/**
 * Application Lifecycle Listener implementation class ServletContextListenerImpl.
 *
 */
@WebListener
public class ContextListener implements ServletContextListener {
	
	/**
	 * 
	 */
	private static final Logger LOGGER = LogManager.
			getLogger(ContextListener.class.getName());

    /**
     * Default constructor. 
     */
    public ContextListener() {
    	super();
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    @Override
    public final void contextDestroyed(final ServletContextEvent arg0)  { 
    	LOGGER.info("Context destroying:");
         try {
			ConnectionPool.dispose();
			LOGGER.info("Connection pool was successfuly destroyed!");
		} catch (SQLException e) {
			LOGGER.error(e);
		}
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    @Override
    public final void contextInitialized(final ServletContextEvent arg0)  { 
    	LOGGER.info("Servlet context initialization starts");

		final ServletContext servletContext = arg0.getServletContext();
		initConnectionPool(servletContext);
		initCommand(servletContext);
    }
    
    /**
     * @param servletContext
     */
    public final void initConnectionPool(final ServletContext servletContext) {
    	//LOGGER.info("Log4J initialization started");
    	try {
    		final String path = (String) servletContext.getInitParameter("poolpath");
    		LOGGER.info("path pool -> " + path);
			ConnectionPool.init(path);
			LOGGER.info("Connection pool was successfuly launched!");
		} catch (SQLException e) {
			LOGGER.error(e);
		}
    }
    
    /**
     * @param servletContext
     */
    public final void initCommand(final ServletContext servletContext) {
    	final String pack = (String) servletContext.getAttribute("pack");
    	LOGGER.info("Launching command:");
    	CommandFactory.init(pack);
    	LOGGER.info("Command was initialized!");
    }
	
}
