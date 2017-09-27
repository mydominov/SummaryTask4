/**
 * 
 */
package ua.nure.dominov.SummaryTask4.util;

import ua.nure.dominov.SummaryTask4.db.model.User;

/**
 * @author calango
 *
 */
public final class HashCode {
	
	/**
	 * 
	 */
	private static final int PRIME = 31;
	
	/**
	 * 
	 */
	private HashCode() {
		super();
	}
	
	/**
	 * @param form registration form
	 * @return hash code
	 */
	public static int calculateRegFormHashCode(final User form) {
		int result = 1;
		for (String value : form.getValues()) {
			result = result * PRIME + value.hashCode();
		}
		return result;
	}
}
