/**
 * The main Business Object interface.
 */
package ua.nure.dominov.SummaryTask4.db.bo.bo;

import ua.nure.dominov.SummaryTask4.db.model.VerificationCode;

/**
 * @author calango
 *
 */
public interface VerificationCodeBo {

	/**
	 * @param verCode new user's verification code
	 * @return logical result of this operation (true/false)
	 */
	boolean insert(VerificationCode verCode);
	
	/**
	 * @param userCode verification code
	 * @return verification code object
	 */
	VerificationCode getVerificationCode(int userCode);
	
	/**
	 * @param code user's code from email
	 * @return user's email
	 */
	String deleteVerificationCodeAndGetUserMail(int code);
	
}
