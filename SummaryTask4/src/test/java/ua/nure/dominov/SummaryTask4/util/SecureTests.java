/**
 * 
 */
package ua.nure.dominov.SummaryTask4.util;

import static org.junit.Assert.*;

import java.security.NoSuchAlgorithmException;

import org.junit.Test;

import ua.nure.dominov.SummaryTask4.db.model.User;

/**
 * @author calango
 *
 */
public class SecureTests {
	
	/**
	 * 
	 */
	private static final String SALT = "dcae8c8eb530a6e95f2db144";

	/**
	 * 
	 */
	private static final String PASS1 = "Hello world!";
	
	/**
	 * 
	 */
	private static final String PASS2 = "Is Vasya Pupkin from Ukraine?!";
	
	/**
	 * 
	 */
	private static final String PASS_ANS1 = "6a39d2dc90df55939d0ef62debda3e6ee15992b75d06d11f03e8ee059d38f89e";
	
	/**
	 * 
	 */
	private static final String PASS_ANS2 = "3928e5ba53589b6f3ced4104384d92143015214c5575325dc52619a461352fd9";
			

	@Test
	public void testSiphire1() {
		final String encPass = Secure.siphire(PASS1, SALT);
		assertEquals(PASS_ANS1, encPass);
	}
	

	@Test
	public void testSiphire2() {
		final String encPass = Secure.siphire(PASS2, SALT);
		assertEquals(PASS_ANS2, encPass);
	}

	/**
	 * Test method for {@link ua.nure.dominov.SummaryTask4.util.Secure#generateSalt()}.
	 * @throws NoSuchAlgorithmException 
	 */
	@Test
	public void testGenerateSalt() throws NoSuchAlgorithmException {
		final String randomSalt = Secure.generateSalt();
		assertFalse(randomSalt.equals(Secure.generateSalt()));
	}
	
	@Test
	public void testCheckPass1() {
		final User user = new User();
		user.setPassword(Secure.siphire(PASS1, SALT));
		user.setSalt(SALT);
		assertTrue(Secure.checkPassword(user, PASS1));
	}
	
	@Test
	public void testCheckPass2() {
		final User user = new User();
		user.setPassword(Secure.siphire(PASS2, SALT));
		user.setSalt(SALT);
		assertFalse(Secure.checkPassword(user, PASS1));
	}

}
