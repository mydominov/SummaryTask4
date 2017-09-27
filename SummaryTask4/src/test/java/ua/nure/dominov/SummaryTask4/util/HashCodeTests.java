package ua.nure.dominov.SummaryTask4.util;

import static org.junit.Assert.*;

import org.junit.Test;

import ua.nure.dominov.SummaryTask4.db.model.User;
import ua.nure.dominov.SummaryTask4.db.model.UserRole;

/**
 * @author calango
 *
 */
public class HashCodeTests {

	/**
	 * 
	 */
	private static final User USER1 = new User(5, "Vasya", "vasya.pupkin@odnoklassniki.ru", "06ec6dadc765f435aae942e6c1a828da94909439729ec4efd9bb2645d096ceaa", "c95dffa6b32a2d3283b4e1cd3", new UserRole(2,"UNCONFIRMED", 25), false, 0, 200);
	
	/**
	 * 
	 */
	private static final User USER2 = new User(6, "Vasya", "vasya.pupkin@odnoklassniki.ru", "06ec6dadc765f435aae942e6c1a828da94909439729ec4efd9bb2645d096ceaa", "c95dffa6b32a2d3283b4e1cd3", new UserRole(2,"UNCONFIRMED", 25), false, 0, 200);
	
	/**
	 * 
	 */
	@Test
	public void testCalculateRegFormHashCode() {
		assertEquals(HashCode.calculateRegFormHashCode(USER1), 
				HashCode.calculateRegFormHashCode(USER2));
		assertEquals(838135800, HashCode.calculateRegFormHashCode(USER1));
	}

}
