/**
 * Business object factory abstract class.
 */
package ua.nure.dominov.SummaryTask4.db.bo.factory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.nure.dominov.SummaryTask4.db.bo.bo.TourBo;
import ua.nure.dominov.SummaryTask4.db.bo.bo.UserBo;
import ua.nure.dominov.SummaryTask4.db.bo.bo.VerificationCodeBo;
import ua.nure.dominov.SummaryTask4.db.bo.factory.impl.BoFactoryImpl;


/**
 * @author calango
 *
 */
public abstract class AbstractBoFactory {

	/**
	 * 
	 */
	private static final Logger LOGGER = LogManager.getLogger(
			AbstractBoFactory.class.getName());

	
	/**
	 * Main factory.
	 */
	public static final int SIMPLEDEFAULTFAC = 1;
	

	/**
	 * @return implementation of the verification code business object
	 */
	public abstract VerificationCodeBo getVerificationBo();
	
	/**
	 * @return implementation of the user business object
	 */
	public abstract UserBo getUserBo();
	
	/**
	 * @return implementation of the tour business object
	 */
	public abstract TourBo getTourBo();

	/**
	 * @param whichFactory
	 *            id of the factory you need to use
	 * @return DAO factory you have chosen
	 */
	public static AbstractBoFactory getBoFactory(final int whichFactory) {
		if (whichFactory == SIMPLEDEFAULTFAC) {
			return new BoFactoryImpl();
		} else {
			LOGGER.error("Non-existent factory was selected");
			return null;
		}
	}
}
