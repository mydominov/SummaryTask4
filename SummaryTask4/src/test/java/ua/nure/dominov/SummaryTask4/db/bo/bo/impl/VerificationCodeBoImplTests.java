/**
 * 
 */
package ua.nure.dominov.SummaryTask4.db.bo.bo.impl;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import ua.nure.dominov.SummaryTask4.db.bo.bo.VerificationCodeBo;
import ua.nure.dominov.SummaryTask4.db.bo.factory.AbstractBoFactory;
import ua.nure.dominov.SummaryTask4.db.connectionpool.ConnectionPool;
import ua.nure.dominov.SummaryTask4.db.model.User;
import ua.nure.dominov.SummaryTask4.db.model.VerificationCode;

/**
 * @author calango
 *
 */
public class VerificationCodeBoImplTests {

	/**
	 * 
	 */
	private static VerificationCodeBo verificationCodeBo;
	
	/**
	 * 
	 */
	private static final String DBPROPERTYPATH = "ua/nure/dominov/SummaryTask4/properties/config/H2DbConnectionTests";
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ConnectionPool.init(DBPROPERTYPATH);
		verificationCodeBo = AbstractBoFactory.getBoFactory(AbstractBoFactory.
				SIMPLEDEFAULTFAC).getVerificationBo();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		ConnectionPool.dispose();
	}
	
	@Test
	public void testCRUD() {
		final VerificationCode verCode = new VerificationCode();
		verCode.setVerifCodeId(Long.MAX_VALUE);
		verCode.setVerificationUserCode(2);
		final User user = new User();
		user.setUserId(1);
		verCode.setUser(user);
		verificationCodeBo.insert(verCode);
		final VerificationCode temp = verificationCodeBo.getVerificationCode(2);
		if (!verCode.equals(temp)) {
			fail("Bad insert");
		}
		if(verificationCodeBo.deleteVerificationCodeAndGetUserMail(2) == null) {
			fail("Bad delete operation");
		}
	}

}
