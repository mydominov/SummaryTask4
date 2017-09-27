/**
 * Main user entity.
 */
package ua.nure.dominov.SummaryTask4.db.model;

import java.io.Serializable;

/**
 * @author calango
 *
 */
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	private static final long DEFAULTBALANCE = 200;

	/**
	 * 
	 */
	private long userId;

	/**
	 * 
	 */
	private String name;

	/**
	 * 
	 */
	private String email;

	/**
	 * 
	 */
	private String password;

	/**
	 * 
	 */
	private String salt;

	/**
	 * 
	 */
	private UserRole userRole;

	/**
	 * 
	 */
	private boolean isConfirmed;

	/**
	 * 
	 */
	private long purchasedTours;

	/**
	 * 
	 */
	private long balance;

	private long theMostExpensivePrice;
	
	private String theMostExpensiveName;
	
	/**
	 * 
	 */
	public User() { 
		super();
	}

	/**
	 * @param name
	 * @param email
	 * @param password
	 * @param salt
	 */
	public User(final String name, final String email, 
			final String password, final String salt) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.salt = salt;
		this.userRole = new UserRole();
		this.isConfirmed = false;
		this.purchasedTours = 0;
		this.balance = DEFAULTBALANCE;
	}

	/**
	 * @param id
	 * @param name
	 * @param email
	 * @param password
	 * @param salt
	 * @param userRole
	 */
	public User(final int userId, final String name, final String email, 
			final String password, final String salt, 
			final UserRole userRole, final boolean isConfirmed, 
			final long purchasedTours, final long balance) {
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.salt = salt;
		this.userRole = userRole;
		this.isConfirmed = isConfirmed;
		this.purchasedTours = purchasedTours;
		this.balance = balance;
	}

	/**
	 * @return the id
	 */
	public final long getUserId() {
		return userId;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public final void setUserId(final long userId) {
		this.userId = userId;
	}

	/**
	 * @return the salt
	 */
	public final String getSalt() {
		return salt;
	}

	/**
	 * @param salt
	 *            the salt to set
	 */
	public final void setSalt(final String salt) {
		this.salt = salt;
	}

	/**
	 * @return the userRole
	 */
	public final UserRole getUserRole() {
		return userRole;
	}

	/**
	 * @param userRole
	 *            the userRole to set
	 */
	public final void setUserRole(final UserRole userRole) {
		this.userRole = userRole;
	}

	/**
	 * @return the name
	 */
	public final String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public final void setName(final String name) {
		this.name = name;
	}

	/**
	 * @return the email
	 */
	public final String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public final void setEmail(final String email) {
		this.email = email;
	}

	/**
	 * @return the password
	 */
	public final String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public final void setPassword(final String password) {
		this.password = password;
	}

	/**
	 * @return the userStatus
	 */
	public final boolean getIsConfirmed() {
		return isConfirmed;
	}

	/**
	 * @param isConfirmed
	 *            the isConfirmed to set
	 */
	public final void setIsConfirmed(final boolean isConfirmed) {
		this.isConfirmed = isConfirmed;
	}

	/**
	 * @return the purchasedTours
	 */
	public final long getPurchasedTours() {
		return purchasedTours;
	}

	/**
	 * @param purchasedTours
	 *            the purchasedTours to set
	 */
	public final void setPurchasedTours(final long purchasedTours) {
		this.purchasedTours = purchasedTours;
	}

	/**
	 * @param isConfirmed
	 *            the isConfirmed to set
	 */
	public final void setConfirmed(final boolean isConfirmed) {
		this.isConfirmed = isConfirmed;
	}

	/**
	 * @return the balance
	 */
	public final long getBalance() {
		return balance;
	}

	/**
	 * @param balance
	 *            the balance to set
	 */
	public final void setBalance(final long balance) {
		this.balance = balance;
	}

	/**
	 * @return string values of this class
	 */
	public final String[] getValues() {
		return new String[] { name, email, password };
	}

	/**
	 * @return
	 */
	public long getTheMostExpensivePrice() {
		return theMostExpensivePrice;
	}

	public void setTheMostExpensivePrice(long theMostExpensivePrice) {
		this.theMostExpensivePrice = theMostExpensivePrice;
	}

	public String getTheMostExpensiveName() {
		return theMostExpensiveName;
	}

	public void setTheMostExpensiveName(String theMostExpensiveName) {
		this.theMostExpensiveName = theMostExpensiveName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public final String toString() {
		return "Reg. form: [" + name + " " + email + " " + password 
				+ " " + salt + userRole + " " + isConfirmed + "]\n";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public final boolean equals(final Object obj) {
		if (obj instanceof User) {
			final User user = (User) obj;
			if ((this.userId == user.getUserId()) && (this.name.equals(user.getName()))
					&& (this.password.equals(user.getPassword())) 
					&& (this.salt.equals(user.getSalt()))
					&& (this.email.equals(user.getEmail())) 
					&& (this.isConfirmed == user.isConfirmed)
					&& (this.purchasedTours == user.getPurchasedTours()) 
					&& (userRole.equals(user.getUserRole()))) {
				return true;
			}
		}
		return false;

	}

}
