/**
 * 
 */
package ua.nure.dominov.SummaryTask4.db.dao.factory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.nure.dominov.SummaryTask4.db.dao.dao.TourDao;
import ua.nure.dominov.SummaryTask4.db.dao.dao.UserDao;
import ua.nure.dominov.SummaryTask4.db.dao.dao.VerificationCodeDao;
import ua.nure.dominov.SummaryTask4.db.dao.factory.impl.DaoFactoryImpl;


/**
 * @author calango
 *
 */
public abstract class AbstractDaoFactory {
	
	/**
	 * 
	 */
	private static final Logger LOGGER = LogManager.getLogger(
			AbstractDaoFactory.class.getName());

	
	/**
	 * Main factory.
	 */
	public static final int SIMPLEDEFAULTFAC = 1;
	

	/**
	 * @return implementation of the verification code DAO
	 */
	public abstract VerificationCodeDao getVerificationDao();
	
	/**
	 * @return implementation of the user DAO
	 */
	public abstract UserDao getUserDao();
	
	/**
	 * @return implementation of the tour DAO
	 */
	public abstract TourDao getTourDao();

	/**
	 * @param whichFactory
	 *            id of the factory you need to use
	 * @return DAO factory you have chosen
	 */
	public static AbstractDaoFactory getDaoFactory(final int whichFactory) {
		if (whichFactory == SIMPLEDEFAULTFAC) {
			return new DaoFactoryImpl();
		} else {
			LOGGER.error("Non-existent factory was selected");
			return null;
		}
	}
	
}
