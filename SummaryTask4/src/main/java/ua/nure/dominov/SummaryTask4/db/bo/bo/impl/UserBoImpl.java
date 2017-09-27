/**
 * Basic User business object implementation.
 */
package ua.nure.dominov.SummaryTask4.db.bo.bo.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.nure.dominov.SummaryTask4.db.bo.bo.UserBo;
import ua.nure.dominov.SummaryTask4.db.dao.dao.UserDao;
import ua.nure.dominov.SummaryTask4.db.dao.factory.AbstractDaoFactory;
import ua.nure.dominov.SummaryTask4.db.model.User;
import ua.nure.dominov.SummaryTask4.db.model.UserRole;

/**
 * @author calango
 *
 */
public class UserBoImpl implements UserBo {
	
	/**
	 * 
	 */
	private final AbstractDaoFactory daoFactory;
	
	/**
	 * 
	 */
	private static final Logger LOGGER = LogManager.getLogger(
			UserBoImpl.class.getName());
	
	/**
	 * @param daoFactory initialization value
	 */
	public UserBoImpl(final AbstractDaoFactory daoFactory) {
		LOGGER.info("User bo init");
		this.daoFactory = daoFactory;
	}

	/* (non-Javadoc)
	 * @see ua.nure.dominov.SummaryTask4.web.bo.bo.UserBo#insert(ua.nure.dominov.SummaryTask4.db.model.User)
	 */
	@Override
	public final boolean insert(final User user) throws SQLException {
		LOGGER.info("Creating new user.");
		try (UserDao userDao = daoFactory.getUserDao()) {
			return userDao.insert(user) > 0;
		} catch (IOException e) {
			LOGGER.error("Issue happend in 52 line:" + e);
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see ua.nure.dominov.SummaryTask4.web.bo.bo.UserBo#getUserById(long)
	 */
	@Override
	public final User getUserById(final long userId) {
		LOGGER.info("Getting user by id.");
		try (UserDao userDao = daoFactory.getUserDao()) {
			return userDao.getUserById(userId);
		} catch (IOException e) {
			LOGGER.error("Issue happend in 66 line:" + e);
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see ua.nure.dominov.SummaryTask4.web.bo.bo.UserBo#getUserByEmail(java.lang.String)
	 */
	@Override
	public final User getUserByEmail(final String email) {
		LOGGER.info("Getting user by email.");
		try (UserDao userDao = daoFactory.getUserDao()) {
			return userDao.getUserByEmail(email);
		} catch (IOException e) {
			LOGGER.error("Issue happend in 80 line:" + e);
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see ua.nure.dominov.SummaryTask4.web.bo.bo.UserBo#updateUser(ua.nure.dominov.SummaryTask4.db.model.User)
	 */
	@Override
	public final boolean updateUser(final User user) {
		LOGGER.info("Updating user.");
		try (UserDao userDao = daoFactory.getUserDao()) {
			return userDao.updateUser(user) > 0;
		} catch (IOException e) {
			LOGGER.error("Issue happend in 94 line:" + e);
			return false;
		}

	}
	
	/* (non-Javadoc)
	 * @see ua.nure.dominov.SummaryTask4.db.bo.bo.UserBo#updateFromAdmin(long, java.lang.String, java.lang.String, long, long)
	 */
	@Override
	public final boolean updateFromAdmin(final long userId, final String name, 
			final String email, final long roleId, final long balance) {
		LOGGER.info("Admin updating user.");
		try (UserDao userDao = daoFactory.getUserDao()) {
			return userDao.updateFromAdmin(userId, name, email, roleId,
					balance) > 0;
		} catch (IOException e) {
			LOGGER.error("Issue happend in 108 line:" + e);
			return false;
		}
	}
	
	/* (non-Javadoc)
	 * @see ua.nure.dominov.SummaryTask4.db.bo.bo.UserBo#getRoleIdFromRoleName(java.lang.String)
	 */
	@Override
	public final long getRoleIdFromRoleName(final String roleName) {
		LOGGER.info("Defining id from role name.");
		try (UserDao userDao = daoFactory.getUserDao()) {
			return userDao.getRoleIdFromRoleName(roleName);
		} catch (IOException e) {
			LOGGER.error("Issue happend in 122 line:" + e);
			return -1;
		}
	}

	/* (non-Javadoc)
	 * @see ua.nure.dominov.SummaryTask4.db.bo.bo.UserBo#getAllUsers()
	 */
	@Override
	public final List<User> getAllUsers() {
		LOGGER.info("Getting user list.");
		try (UserDao userDao = daoFactory.getUserDao()) {
			return userDao.getAllUsers();
		} catch (IOException e) {
			LOGGER.error("Issue happend in 136 line:" + e);
			return null;
		}
	}
	
	/* (non-Javadoc)
	 * @see ua.nure.dominov.SummaryTask4.db.bo.bo.UserBo#getAllUserRoles()
	 */
	@Override
	public final List<UserRole> getAllUserRoles() {
		LOGGER.info("Getting all user roles.");
		try (UserDao userDao = daoFactory.getUserDao()) {
			return userDao.getAllUserRoles();
		} catch (IOException e) {
			LOGGER.error("Issue happend in 150 line:" + e);
			return null;
		}
	}
	
	/* (non-Javadoc)
	 * @see ua.nure.dominov.SummaryTask4.web.bo.bo.UserBo#deleteUserById(long)
	 */
	@Override
	public final boolean deleteUserById(final long userId) {
		LOGGER.info("Deleting user.");
		try (UserDao userDao = daoFactory.getUserDao()) {
			return userDao.deleteUserById(userId) > 0;
		} catch (IOException e) {
			LOGGER.error("Issue happend in 163 line:" + e);
			return false;
		}

	}

	/* (non-Javadoc)
	 * @see ua.nure.dominov.SummaryTask4.db.bo.bo.UserBo#payForTheTour(long, long)
	 */
	@Override
	public final boolean payForTheTour(final long userId, final long price) {
		LOGGER.info("Get money from the user.");
		try (UserDao userDao = daoFactory.getUserDao()) {
			return userDao.payForTheTour(userId, price) > 0;
		} catch (IOException e) {
			LOGGER.error("Issue happend in 163 line:" + e);
			return false;
		}
	}
	
}
