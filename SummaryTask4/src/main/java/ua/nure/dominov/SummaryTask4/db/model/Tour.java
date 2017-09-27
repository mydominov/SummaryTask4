/**
 * Main tour entity.
 */
package ua.nure.dominov.SummaryTask4.db.model;

import java.io.Serializable;

/**
 * @author calango
 *
 */
public class Tour implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private long tourId;
	
	/**
	 * 
	 */
	private String tourName;
	
	/**
	 * 
	 */
	private long price;
	
	/**
	 * 
	 */
	private TypeOfHoliday typeOfHoliday;
	
	/**
	 * 
	 */
	private int numberOfPeople;
	
	/**
	 * 
	 */
	private HotelType hotelType;
	
	/**
	 * 
	 */
	private TourStatus tourStatus;
	
	/**
	 * 
	 */
	private User reservedBy;
	
	/**
	 * 
	 */
	private boolean isHot;
	
	/**
	 * 
	 */
	private int maxDiscount;
	
	/**
	 * 
	 */
	private int stepDiscount;
	
	/**
	 * 
	 */
	private int incrementDiscount;
	
	/**
	 * 
	 */
	public Tour() {
		super();
	}
	
	/**
	 * @param id
	 * @param holidayId
	 * @param holidayType
	 * @param numberOfPeople
	 * @param hotelTypeId
	 * @param typeName
	 * @param statusId
	 * @param statusName
	 */
	public Tour(final long tourId, final String tourName, final long price,
			final long holidayId, final String holidayType, 
			final int numberOfPeople, final long hotelTypeId, 
			final String typeName, final int statusId, 
			final String statusName, final boolean isHot, 
			final int maxDiscount, final int stepDiscount, 
			final int incrementDiscount) {
		this.tourId = tourId;
		this.tourName = tourName;
		this.price = price;
		this.typeOfHoliday = new TypeOfHoliday(holidayId, holidayType);
		this.numberOfPeople = numberOfPeople;
		this.hotelType = new HotelType(hotelTypeId, typeName);
		this.tourStatus = new TourStatus(statusId, statusName);
		this.isHot = isHot;
		this.maxDiscount = maxDiscount;
		this.stepDiscount = stepDiscount;
		this.incrementDiscount = incrementDiscount;
	}
	
	/**
	 * @param id
	 * @param holidayId
	 * @param holidayType
	 * @param numberOfPeople
	 * @param hotelTypeId
	 * @param typeName
	 * @param statusId
	 * @param statusName
	 * @param user
	 */
	public Tour(final long tourId, final String tourName, final long price,
			final long holidayId, final String holidayType, 
			final int numberOfPeople, final long hotelTypeId, 
			final String typeName, final int statusId, final String statusName,
			final User user, final boolean isHot, 
			final int maxDiscount, final int stepDiscount, 
			final int incrementDiscount) {
		this.tourId = tourId;
		this.tourName = tourName;
		this.price = price;
		this.typeOfHoliday = new TypeOfHoliday(holidayId, holidayType);
		this.numberOfPeople = numberOfPeople;
		this.hotelType = new HotelType(hotelTypeId, typeName);
		this.tourStatus = new TourStatus(statusId, statusName);
		this.reservedBy = user;
		this.isHot = isHot;
		this.maxDiscount = maxDiscount;
		this.stepDiscount = stepDiscount;
		this.incrementDiscount = incrementDiscount;
	}

	/**
	 * @param holidayType
	 * @param numberOfPeople
	 * @param typeName
	 * @param statusName
	 */
	public Tour(final String tourName, final long price,
			final String holidayType, final int numberOfPeople, 
			final String typeName, final String statusName) {
		this.tourName = tourName;
		this.price = price;
		this.typeOfHoliday = new TypeOfHoliday(holidayType);
		this.numberOfPeople = numberOfPeople;
		this.hotelType = new HotelType(typeName);
		this.tourStatus = new TourStatus(statusName);
	}

	/**
	 * @return the id
	 */
	public final long getTourId() {
		return tourId;
	}

	/**
	 * @param id the id to set
	 */
	public final void setTourId(final long tourId) {
		this.tourId = tourId;
	}

	/**
	 * @return the typeOfHoliday
	 */
	public final TypeOfHoliday getTypeOfHoliday() {
		return typeOfHoliday;
	}

	/**
	 * @param typeOfHoliday the typeOfHoliday to set
	 */
	public final void setTypeOfHoliday(final TypeOfHoliday typeOfHoliday) {
		this.typeOfHoliday = typeOfHoliday;
	}

	/**
	 * @return the numberOfPeople
	 */
	public final int getNumberOfPeople() {
		return numberOfPeople;
	}

	/**
	 * @param numberOfPeople the numberOfPeople to set
	 */
	public final void setNumberOfPeople(final int numberOfPeople) {
		this.numberOfPeople = numberOfPeople;
	}

	/**
	 * @return the hotelType
	 */
	public final HotelType getHotelType() {
		return hotelType;
	}

	/**
	 * @param hotelType the hotelType to set
	 */
	public final void setHotelType(final HotelType hotelType) {
		this.hotelType = hotelType;
	}

	/**
	 * @return the tourStatus
	 */
	public final TourStatus getTourStatus() {
		return tourStatus;
	}

	/**
	 * @param tourStatus the tourStatus to set
	 */
	public final void setTourStatus(final TourStatus tourStatus) {
		this.tourStatus = tourStatus;
	}

	/**
	 * @return the reservedBy
	 */
	public final User getReservedBy() {
		return reservedBy;
	}

	/**
	 * @param reservedBy the reservedBy to set
	 */
	public final void setReservedBy(final User reservedBy) {
		this.reservedBy = reservedBy;
	}
	
	/**
	 * @return the isHot
	 */
	public final boolean getIsHot() {
		return isHot;
	}

	/**
	 * @param isHot the isHot to set
	 */
	public final void setHot(final boolean isHot) {
		this.isHot = isHot;
	}

	/**
	 * @return the tourName
	 */
	public final String getTourName() {
		return tourName;
	}

	/**
	 * @param tourName the tourName to set
	 */
	public final void setTourName(final String tourName) {
		this.tourName = tourName;
	}

	/**
	 * @return the price
	 */
	public final long getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public final void setPrice(final long price) {
		this.price = price;
	}

	/**
	 * @return the maxDiscount
	 */
	public final int getMaxDiscount() {
		return maxDiscount;
	}

	/**
	 * @param maxDiscount the maxDiscount to set
	 */
	public final void setMaxDiscount(final int maxDiscount) {
		this.maxDiscount = maxDiscount;
	}

	/**
	 * @return the stepDiscount
	 */
	public final int getStepDiscount() {
		return stepDiscount;
	}

	/**
	 * @param stepDiscount the stepDiscount to set
	 */
	public final void setStepDiscount(final int stepDiscount) {
		this.stepDiscount = stepDiscount;
	}

	/**
	 * @return the incrementDiscount
	 */
	public final int getIncrementDiscount() {
		return incrementDiscount;
	}

	/**
	 * @param incrementDiscount the incrementDiscount to set
	 */
	public final void setIncrementDiscount(final int incrementDiscount) {
		this.incrementDiscount = incrementDiscount;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public final boolean equals(final Object obj) {
		if (obj instanceof Tour) {
			final Tour tour = (Tour) obj;
			if ((this.tourId == tour.getTourId())
					&& (this.tourName.equals(tour.getTourName())) 
					&& (this.price == tour.price) 
					&& (this.typeOfHoliday.equals(tour.getTypeOfHoliday())) 
					&& (this.numberOfPeople == tour.getNumberOfPeople()) 
					&& (this.hotelType.equals(tour.getHotelType())) 
					&& (this.tourStatus.equals(tour.getTourStatus())) 
					&& (this.isHot == tour.getIsHot()) 
					&& (this.maxDiscount == tour.getMaxDiscount()) 
					&& (this.stepDiscount == tour.getStepDiscount()) 
					&& (this.incrementDiscount 
							==  tour.getIncrementDiscount())) {
				return true;
			}
		}
		return false;
	}
	
}
