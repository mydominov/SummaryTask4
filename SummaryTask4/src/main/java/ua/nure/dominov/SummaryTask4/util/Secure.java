package ua.nure.dominov.SummaryTask4.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.nure.dominov.SummaryTask4.db.model.User;

/**
 * 
 */

/**
 * @author calango
 *
 */
public final class Secure {

	/**
	 * 
	 */
	private static final Logger LOGGER = LogManager.getLogger(
			Secure.class.getName());
	
	/**
	 * 
	 */
	private static final String SIPHIREALGORITHM = "SHA-256";

	/**
	 * 
	 */
	private static final String SALTALGORITHM = "SHA1PRNG";
	
	/**
	 * 
	 */
	private static final int SALTLENGTH = 24;
	
	/**
	 * 
	 */
	private Secure() {
		super();
	}

	/**
	 * @param password
	 * @param salt
	 * @return siphre presentation
	 */
	public static String siphire(final String password, final String salt) {
		if (password == null) {
			LOGGER.error("No password: " + NullPointerException.class);
		} else {
			try {
				final MessageDigest md = MessageDigest.getInstance(SIPHIREALGORITHM);
				md.update(password.getBytes("UTF-8"));
				md.update(salt.getBytes("UTF-8"));
				final byte[] byteData = md.digest();

				// convert the byte to hex format method 1
				final StringBuffer sb = new StringBuffer();
				for (int i = 0; i < byteData.length; i++) {
					sb.append(Integer.toString((byteData[i] & 0xff) 
							+ 0x100, 16).substring(1));
				}
				return sb.toString();
			} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
				LOGGER.error(e);
			}
		}
		return null;
	}

	/**
	 * @return salt
	 */
	public static String generateSalt() {
		try {
			final Random random = SecureRandom.getInstance(SALTALGORITHM);
			final byte[] salt = new byte[SALTLENGTH];
			random.nextBytes(salt);

			final StringBuffer hexString = new StringBuffer();

			for (byte d : salt) {
				hexString.append(Integer.toHexString(d & 0xff));
			}

			return hexString.substring(0, SALTLENGTH);
		} catch (Exception e) {
			LOGGER.error("Unable to generate secret key", e);
			return null;
		}
	}

	/**
	 * @param account
	 * @param password
	 * @return is client's password or not
	 */
	public static boolean checkPassword(final User account, 
			final String password) {
		return account.getPassword().equals(siphire(
				password, account.getSalt())) ? true : false;
	}

}
