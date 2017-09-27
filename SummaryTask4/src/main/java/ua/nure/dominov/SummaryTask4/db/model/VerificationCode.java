/**
 * 
 */
package ua.nure.dominov.SummaryTask4.db.model;

import java.io.Serializable;

import ua.nure.dominov.SummaryTask4.util.HashCode;

/**
 * @author calango
 *
 */
public class VerificationCode implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private long verifCodeId;
	
	/**
	 * 
	 */
	private User user;
	
	/**
	 * 
	 */
	private int verificationUserCode;
	
	/**
	 * 
	 */
	public VerificationCode() {
		super();
	}
	
	/**
	 * @param id
	 * @param regForm registration form
	 */
	public VerificationCode(final long verifCodeId, final User regForm) {
		this.verifCodeId = verifCodeId;
		this.user = regForm;
		setVerificationUserCode(regForm);
	}
	
	/**
	 * @param regForm registration form
	 */
	public VerificationCode(final User regForm) {
		this.user = regForm;
		setVerificationUserCode(regForm);
	}

	/**
	 * @return the user
	 */
	public final User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public final void setUser(final User user) {
		this.user = user;
	}

	/**
	 * @param verificationCode the verificationCode to set
	 */
	public final void setVerificationUserCode(final int verificationUserCode) {
		this.verificationUserCode = verificationUserCode;
	}

	/**
	 * @return the verificationCode
	 */
	public final int getVerificationUserCode() {
		return verificationUserCode;
	}

	/**
	 * @param regForm the regForm to set
	 */
	public final void setVerificationUserCode(final User regForm) {
		this.verificationUserCode = HashCode.calculateRegFormHashCode(regForm);
	}

	/**
	 * @return the id
	 */
	public final long getVerifCodeId() {
		return verifCodeId;
	}

	/**
	 * @param id the id to set
	 */
	public final void setVerifCodeId(final long verifCodeId) {
		this.verifCodeId = verifCodeId;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public final boolean equals(final Object obj) {
		if (obj instanceof VerificationCode) {
			VerificationCode verCode = (VerificationCode) obj;
			if ((this.verifCodeId == verCode.getVerifCodeId())
					&& (this.user.getUserId() == verCode.getUser().getUserId())
					&& (this.verificationUserCode == verCode.
						getVerificationUserCode())) {
				return true;
			}
		}
		return false;
	}
	
}
