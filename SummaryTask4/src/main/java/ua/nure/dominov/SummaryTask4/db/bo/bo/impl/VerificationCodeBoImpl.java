/**
 * Basic Verification code business object implementation.
 */
package ua.nure.dominov.SummaryTask4.db.bo.bo.impl;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.nure.dominov.SummaryTask4.db.bo.bo.VerificationCodeBo;
import ua.nure.dominov.SummaryTask4.db.dao.dao.VerificationCodeDao;
import ua.nure.dominov.SummaryTask4.db.dao.factory.AbstractDaoFactory;
import ua.nure.dominov.SummaryTask4.db.model.VerificationCode;

/**
 * @author calango
 *
 */
public class VerificationCodeBoImpl implements VerificationCodeBo {
	
	/**
	 * 
	 */
	private AbstractDaoFactory daoFactory;
	
	/**
	 * 
	 */
	private static final Logger LOGGER = LogManager.getLogger(
			VerificationCodeBoImpl.class.getName());
	
	/**
	 * @param daoFactory initialization value
	 */
	public VerificationCodeBoImpl(final AbstractDaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	/* (non-Javadoc)
	 * @see ua.nure.dominov.SummaryTask4.db.bo.bo.VerificationCodeBo#insert(ua.nure.dominov.SummaryTask4.db.model.VerificationCode)
	 */
	@Override
	public final boolean insert(final VerificationCode verCode) {
		LOGGER.info("Creating new verification code.");
		try (VerificationCodeDao verificationCodeDao = 
				daoFactory.getVerificationDao()) {
			return verificationCodeDao.insert(verCode) > 0;
		} catch (IOException e) {
			LOGGER.error("Issue in 49 line:" + e);
			return false;
		}
		
	}

	/* (non-Javadoc)
	 * @see ua.nure.dominov.SummaryTask4.db.bo.bo.VerificationCodeBo#getVerificationCode(int)
	 */
	@Override
	public final VerificationCode getVerificationCode(final int userCode) {
		LOGGER.info("Getting code object by verification code.");
		try (VerificationCodeDao verificationCodeDao = 
				daoFactory.getVerificationDao()) {
			return verificationCodeDao.getVerificationCode(userCode);
		} catch (IOException e) {
			LOGGER.error("Issue in 82 line:" + e);
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see ua.nure.dominov.SummaryTask4.db.bo.bo.VerificationCodeBo#deleteVerificationCode(int)
	 */
	@Override
	public final String deleteVerificationCodeAndGetUserMail(final int code) {
		LOGGER.info("Deleting verification code & returning email.");
		try (VerificationCodeDao verificationCodeDao = 
				daoFactory.getVerificationDao()) {
			return verificationCodeDao.
					deleteVerificationCodeAndGetUserMail(code);
		} catch (IOException e) {
			LOGGER.error("Issue in 98 line:" + e);
			return null;
		}
		
	}

}
