/**
 * 
 */
package ua.nure.dominov.SummaryTask4.db.model;

import java.io.Serializable;

/**
 * @author calango
 *
 */
public class UserRole implements Serializable {
	
	/**
	 * 
	 */
	private static final long UNCONFIRMEDID = 3;
	
	/**
	 * 
	 */
	private static final int UNCONFIRMEDSTRENGTH = 25;
	
	/**
	 * 
	 */
	private static final String UNCONFIRMEDNAME = "UNCONFIRMED";

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private long roleId;

	/**
	 * 
	 */
	private String roleName;

	/**
	 * 
	 */
	private int officeCoefficient;

	/**
	 * 
	 */
	public UserRole() {
		this.roleId = UNCONFIRMEDID;
		this.roleName = UNCONFIRMEDNAME;
		this.officeCoefficient = UNCONFIRMEDSTRENGTH;
	}

	/**
	 * @param roleId
	 * @param roleName
	 * @param officeCoefficient
	 */
	public UserRole(final long roleId, final String roleName, 
			final int officeCoefficient) {
		this.roleId = roleId;
		this.roleName = roleName;
		this.officeCoefficient = officeCoefficient;
	}

	/**
	 * @param roleName
	 * @param officeCoefficient
	 */
	public UserRole(final String roleName, final int officeCoefficient) {
		this.roleName = roleName;
		this.officeCoefficient = officeCoefficient;
	}

	/**
	 * @return the roleId
	 */
	public final long getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId
	 *            the roleId to set
	 */
	public final void setRoleId(final long roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return the roleName
	 */
	public final String getRoleName() {
		return roleName;
	}

	/**
	 * @param roleName
	 *            the roleName to set
	 */
	public final void setRoleName(final String roleName) {
		this.roleName = roleName;
	}

	/**
	 * @return the officeCoefficient
	 */
	public final int getOfficeCoefficient() {
		return officeCoefficient;
	}

	/**
	 * @param officeCoefficient
	 *            the officeCoefficient to set
	 */
	public final void setOfficeCoefficient(final int officeCoefficient) {
		this.officeCoefficient = officeCoefficient;
	}

	@Override
	public final String toString() {
		return roleId + " " + roleName + " " + officeCoefficient;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public final boolean equals(final Object obj) {
		if (obj instanceof UserRole) {
			final UserRole userRole = (UserRole) obj;
			if ((this.roleId == userRole.getRoleId()) 
					&& (this.roleName.equals(userRole.getRoleName()))
					&& (this.officeCoefficient 
							== userRole.getOfficeCoefficient())) {
				return true;
			}
		}
		return false;
	}

}
