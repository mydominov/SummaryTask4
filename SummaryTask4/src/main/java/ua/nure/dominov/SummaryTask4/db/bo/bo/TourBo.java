/**
 * The main Business Object interface.
 */
package ua.nure.dominov.SummaryTask4.db.bo.bo;

import java.util.List;

import ua.nure.dominov.SummaryTask4.db.model.HotelType;
import ua.nure.dominov.SummaryTask4.db.model.Tour;
import ua.nure.dominov.SummaryTask4.db.model.TourStatus;
import ua.nure.dominov.SummaryTask4.db.model.TypeOfHoliday;

/**
 * @author calango
 *
 */
public interface TourBo {

	/**
	 * @param userId
	 * @return
	 */
	Tour getTheMostExpensiveUserTour(long userId);
	
	/**
	 * @param tour 
	 * @return logical result of this operation (true/false)
	 */
	boolean insert(Tour tour);
	
	/**
	 * @param id tour id
	 * @return tour
	 */
	Tour getTourById(long tourId);
	
	/**
	 * @return list of free tours
	 */
	List<Tour> getFreeTours();
	
	/**
	 * @return list of booked tours
	 */
	List<Tour> getBookedTours();
	
	/**
	 * @return all reserved tours
	 */
	List<Tour> getReservedTours();
	
	/**
	 * @param id user id
	 * @return tour found by user id
	 */
	List<Tour> getTourByUserId(long userId);
	
	/**
	 * @param tour tour after editing
	 * @return logical result of this operation (true/false)
	 */
	boolean update(Tour tour);
	
	/**
	 * @param id tour id
	 * @param price tour price
	 * @param statusId status of this tour
	 * @param maxDiscount maximum discount the user can get
	 * @param stepDiscount step of increment
	 * @param incrementDiscount increment value of this discount
	 * @return logical result of this operation (true/false)
	 */
	boolean managerUpdate(long tourId, long price, int statusId, int maxDiscount,
			int stepDiscount, int incrementDiscount);
	
	/**
	 * @param name tour name
	 * @param id tour id
	 * @return was successful
	 */
	boolean updateClient(String tourName, long userId);
	
	/**
	 * @param id tour id
	 * @return logical result of this operation (true/false)
	 */
	boolean delete(long tourId);
	
	/**
	 * @return list of holidays' types
	 */
	List<TypeOfHoliday> getAllTypesOfHoliday();
	
	/**
	 * @return list of hotels' types
	 */
	List<HotelType> getAllHotelTypes();
	
	/**
	 * @return list of tours' statuses
	 */
	List<TourStatus> getAllTourStatuses();
	
	/**
	 * @param name holiday type
	 * @return id of the type
	 */
	long getHolidayTypeId(String name);
	
	/**
	 * @param name hotel type
	 * @return id of the type
	 */
	long getHotelTypeId(String name);
	
	/**
	 * @param name status name
	 * @return id of the status
	 */
	int getStatusId(String name);
	
	/**
	 * @param query dinamic query
	 * @return user's tours he searched
	 */
	List<Tour> executeDinamicQuery(String query);
	
	/**
	 * @param name tour name
	 * @param isHot hot state
	 * @return logical result of this operation (true/false)
	 */
	boolean updateIsHot(String name, boolean isHot);
	
}
