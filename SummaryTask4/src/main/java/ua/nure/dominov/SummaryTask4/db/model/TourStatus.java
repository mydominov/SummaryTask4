/**
 * 
 */
package ua.nure.dominov.SummaryTask4.db.model;

import java.io.Serializable;

/**
 * @author calango
 *
 */
public class TourStatus implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private int statusId;
	
	/**
	 * 
	 */
	private String statusName;
	
	/**
	 * 
	 */
	public TourStatus() {
		super();
	}
	
	/**
	 * @param statusName name
	 */
	public TourStatus(final String statusName) {
		this.statusName = statusName;
	}

	/**
	 * @param statusId id
	 * @param statusName name
	 */
	public TourStatus(final int statusId, final String statusName) {
		this.statusId = statusId;
		this.statusName = statusName;
	}

	/**
	 * @return the statusId
	 */
	public final int getStatusId() {
		return statusId;
	}

	/**
	 * @param statusId the statusId to set
	 */
	public final void setStatusId(final int statusId) {
		this.statusId = statusId;
	}

	/**
	 * @return the statusName
	 */
	public final String getStatusName() {
		return statusName;
	}

	/**
	 * @param statusName the statusName to set
	 */
	public final void setStatusName(final String statusName) {
		this.statusName = statusName;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public final boolean equals(final Object obj) {
		if (obj instanceof TourStatus) {
			final TourStatus tourStatus = (TourStatus) obj;
			if ((this.statusId == tourStatus.getStatusId()) 
					&& (this.statusName.equals(tourStatus.getStatusName()))) {
				return true;
			}
		}
		return false;
	}
	
}
