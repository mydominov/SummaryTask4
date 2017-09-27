package ua.nure.dominov.SummaryTask4.web.controller.command.client;

/**
 * @author calango
 *
 */
public class SearchQueryBean {
	
	/**
	 * 
	 */
	private boolean isMin;
	
	/**
	 * 
	 */
	private boolean isMax;
	
	/**
	 * 
	 */
	private boolean isTypeOfHoliday;
	
	/**
	 * 
	 */
	private boolean isNumberOfPeople;
	
	/**
	 * 
	 */
	private boolean isHotelType;
	
	/**
	 * 
	 */
	private long minVal;
	
	/**
	 * 
	 */
	private long maxVal;
	
	/**
	 * 
	 */
	private long typeOfHolidayId;
	
	/**
	 * 
	 */
	private int numberOfPeople;
	
	/**
	 * 
	 */
	private long hotelTypeId;
	
	/**
	 * 
	 */
	public SearchQueryBean() {
		super();
	}

	/**
	 * @return the isMin
	 */
	public final boolean isMin() {
		return isMin;
	}

	/**
	 * @param isMin the isMin to set
	 */
	public final void setMin(final boolean isMin) {
		this.isMin = isMin;
	}

	/**
	 * @return the isMax
	 */
	public final boolean isMax() {
		return isMax;
	}

	/**
	 * @param isMax the isMax to set
	 */
	public final void setMax(final boolean isMax) {
		this.isMax = isMax;
	}

	/**
	 * @return the isTypeOfHoliday
	 */
	public final boolean isTypeOfHoliday() {
		return isTypeOfHoliday;
	}

	/**
	 * @param isTypeOfHoliday the isTypeOfHoliday to set
	 */
	public final void setTypeOfHoliday(final boolean isTypeOfHoliday) {
		this.isTypeOfHoliday = isTypeOfHoliday;
	}

	/**
	 * @return the isNumberOfPeople
	 */
	public final boolean isNumberOfPeople() {
		return isNumberOfPeople;
	}

	/**
	 * @param isNumberOfPeople the isNumberOfPeople to set
	 */
	public final void setNumberOfPeople(final boolean isNumberOfPeople) {
		this.isNumberOfPeople = isNumberOfPeople;
	}

	/**
	 * @return the isHotelType
	 */
	public final boolean isHotelType() {
		return isHotelType;
	}

	/**
	 * @param isHotelType the isHotelType to set
	 */
	public final void setHotelType(final boolean isHotelType) {
		this.isHotelType = isHotelType;
	}

	/**
	 * @return the min
	 */
	public final long getMinVal() {
		return minVal;
	}

	/**
	 * @param min the min to set
	 */
	public final void setMinVal(final long minVal) {
		this.minVal = minVal;
	}

	/**
	 * @return the max
	 */
	public final long getMaxVal() {
		return maxVal;
	}

	/**
	 * @param max the max to set
	 */
	public final void setMaxVal(final long maxVal) {
		this.maxVal = maxVal;
	}

	/**
	 * @return the typeOfHolidayId
	 */
	public final long getTypeOfHolidayId() {
		return typeOfHolidayId;
	}

	/**
	 * @param typeOfHolidayId the typeOfHolidayId to set
	 */
	public final void setTypeOfHolidayId(final long typeOfHolidayId) {
		this.typeOfHolidayId = typeOfHolidayId;
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
	 * @return the hotelTypeId
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
	
}
