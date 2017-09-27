/**
 * 
 */
package ua.nure.dominov.SummaryTask4.db.dao.factory.impl;

import ua.nure.dominov.SummaryTask4.db.connectionpool.ConnectionPool;
import ua.nure.dominov.SummaryTask4.db.dao.dao.TourDao;
import ua.nure.dominov.SummaryTask4.db.dao.dao.UserDao;
import ua.nure.dominov.SummaryTask4.db.dao.dao.VerificationCodeDao;
import ua.nure.dominov.SummaryTask4.db.dao.dao.impl.TourDaoImpl;
import ua.nure.dominov.SummaryTask4.db.dao.dao.impl.UserDaoImpl;
import ua.nure.dominov.SummaryTask4.db.dao.dao.impl.VerificationCodeDaoImpl;
import ua.nure.dominov.SummaryTask4.db.dao.factory.AbstractDaoFactory;

/**
 * @author calango
 *
 */
public class DaoFactoryImpl extends AbstractDaoFactory {
	
	/**
	 * Main connection pool.
	 */
	private static ConnectionPool connectionPool;

	/**
	 * 
	 */
	public DaoFactoryImpl() {
		super();
		connectionPool = ConnectionPool.getInstance();
	}

	/**
	 * @return the connectionPool
	 */
	public static ConnectionPool getConnectionPool() {
		return connectionPool;
	}

	/* (non-Javadoc)
	 * @see ua.nure.dominov.SummaryTask4.db.dao.factory.DaoFactory#getUserDao()
	 */
	@Override
	public final UserDao getUserDao() {
		return new UserDaoImpl(connectionPool.takeConnection());
	}

	/* (non-Javadoc)
	 * @see ua.nure.dominov.SummaryTask4.db.dao.factory.DaoFactory#getVerificationDao()
	 */
	@Override
	public final VerificationCodeDao getVerificationDao() {
		return new VerificationCodeDaoImpl(connectionPool.takeConnection());
	}

	/* (non-Javadoc)
	 * @see ua.nure.dominov.SummaryTask4.db.dao.factory.DaoFactory#getTourDao()
	 */
	@Override
	public final TourDao getTourDao() {
		return new TourDaoImpl(connectionPool.takeConnection());
	}

}
