/**
 * 
 */
package ua.nure.dominov.SummaryTask4.db.dao.dao;

import java.io.Closeable;

import ua.nure.dominov.SummaryTask4.db.model.VerificationCode;

/**
 * @author calango
 *
 */
public interface VerificationCodeDao  extends Closeable, AutoCloseable {

	/**
	 * @param verCode verification code class
	 */
	int insert(VerificationCode verCode);
	
	/**
	 * @param id
	 * @return
	 */
	VerificationCode getVerificationCode(int verifCodeid);
	
	/**
	 * @param code int code
	 */
	String deleteVerificationCodeAndGetUserMail(int code);
	
}