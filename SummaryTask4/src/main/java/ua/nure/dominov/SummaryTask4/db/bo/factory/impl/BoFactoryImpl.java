/**
 * Business Object factory primary implementation.
 */
package ua.nure.dominov.SummaryTask4.db.bo.factory.impl;

import ua.nure.dominov.SummaryTask4.db.bo.bo.TourBo;
import ua.nure.dominov.SummaryTask4.db.bo.bo.UserBo;
import ua.nure.dominov.SummaryTask4.db.bo.bo.VerificationCodeBo;
import ua.nure.dominov.SummaryTask4.db.bo.bo.impl.TourBoImpl;
import ua.nure.dominov.SummaryTask4.db.bo.bo.impl.UserBoImpl;
import ua.nure.dominov.SummaryTask4.db.bo.bo.impl.VerificationCodeBoImpl;
import ua.nure.dominov.SummaryTask4.db.bo.factory.AbstractBoFactory;
import ua.nure.dominov.SummaryTask4.db.dao.factory.AbstractDaoFactory;

/**
 * @author calango
 *
 */
public class BoFactoryImpl extends AbstractBoFactory {

	/**
	 * Data Object Model factory.
	 */
	private final AbstractDaoFactory daoFactory;
	
	/**
	 * Business object initialisation.
	 */
	public BoFactoryImpl() {
		super();
		daoFactory = AbstractDaoFactory.getDaoFactory(AbstractDaoFactory.SIMPLEDEFAULTFAC);
	}
	
	/* (non-Javadoc)
	 * @see ua.nure.dominov.SummaryTask4.db.bo.factory.BoFactory#getVerificationBo()
	 */
	@Override
	public final VerificationCodeBo getVerificationBo() {
		return new VerificationCodeBoImpl(daoFactory);
	}

	/* (non-Javadoc)
	 * @see ua.nure.dominov.SummaryTask4.db.bo.factory.BoFactory#getUserBo()
	 */
	@Override
	public final UserBo getUserBo() {
		return new UserBoImpl(daoFactory);
	}

	/* (non-Javadoc)
	 * @see ua.nure.dominov.SummaryTask4.db.bo.factory.BoFactory#getTourBo()
	 */
	@Override
	public final TourBo getTourBo() {
		return new TourBoImpl(daoFactory);
	}

}
