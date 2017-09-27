/**
 * 
 */
package ua.nure.dominov.SummaryTask4.db.model;

import java.io.Serializable;

/**
 * @author calango
 *
 */
public class TypeOfHoliday implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private long typeOfHolidayId;
	
	/**
	 * 
	 */
	private String holidayName;
	
	/**
	 * 
	 */
	public TypeOfHoliday() {
		super();
	}
	
	/**
	 * @param holidayName name
	 */
	public TypeOfHoliday(final String holidayName) {
		this.holidayName = holidayName;
	}
	
	/**
	 * @param id id
	 * @param holidayName name
	 */
	public TypeOfHoliday(final long typeOfHolidayId, final String holidayName) {
		this.typeOfHolidayId = typeOfHolidayId;
		this.holidayName = holidayName;
	}

	/**
	 * @return the id
	 */
	public final long getTypeOfHolidayId() {
		return typeOfHolidayId;
	}

	/**
	 * @param id the id to set
	 */
	public final void setTypeOfHolidayId(final long typeOfHolidayId) {
		this.typeOfHolidayId = typeOfHolidayId;
	}

	/**
	 * @return the holidayName
	 */
	public final String getHolidayName() {
		return holidayName;
	}

	/**
	 * @param holidayName the holidayName to set
	 */
	public final void setHolidayName(final String holidayName) {
		this.holidayName = holidayName;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public final boolean equals(final Object obj) {
		if (obj instanceof TypeOfHoliday) {
			final TypeOfHoliday typeOfHoliday = (TypeOfHoliday) obj;
			if ((this.typeOfHolidayId == typeOfHoliday.getTypeOfHolidayId()) 
					&& (this.holidayName.equals(
							typeOfHoliday.getHolidayName()))) {
				return true;
			}
		}
		return false;
	}
	
}
