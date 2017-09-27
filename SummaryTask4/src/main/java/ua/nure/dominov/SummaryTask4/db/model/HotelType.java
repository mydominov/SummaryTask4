/**
 * 
 */
package ua.nure.dominov.SummaryTask4.db.model;

import java.io.Serializable;

/**
 * @author calango
 *
 */
public class HotelType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private long hotelTypeId;
	
	/**
	 * 
	 */
	private String typeName;
	
	/**
	 * 
	 */
	public HotelType() {
		super();
	}
	
	/**
	 * @param typeName hotel type name
	 */
	public HotelType(final String typeName) {
		this.typeName = typeName;
	}
	
	/**
	 * @param hotelTypeId hotel type id
	 * @param typeName hotel type name
	 */
	public HotelType(final long hotelTypeId, final String typeName) {
		this.hotelTypeId = hotelTypeId;
		this.typeName = typeName;
	}

	/**
	 * @return the hotelTypeId hotel type id
	 */
	public final long getHotelTypeId() {
		return hotelTypeId;
	}

	/**
	 * @param hotelTypeId the hotelTypeId to set
	 */
	public final void setHotelTypeId(final long hotelTypeId) {
		this.hotelTypeId = hotelTypeId;
	}

	/**
	 * @return the typeName
	 */
	public final String getTypeName() {
		return typeName;
	}

	/**
	 * @param typeName the typeName to set
	 */
	public final void setTypeName(final String typeName) {
		this.typeName = typeName;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public final boolean equals(final Object obj) {
		if (obj instanceof HotelType) {
			final HotelType hotelType = (HotelType) obj;
			if ((this.hotelTypeId == hotelType.getHotelTypeId()) 
					&& (this.typeName.equals(hotelType.getTypeName()))) {
				return true;
			} 
		}
		return false;
		
	}
	
}