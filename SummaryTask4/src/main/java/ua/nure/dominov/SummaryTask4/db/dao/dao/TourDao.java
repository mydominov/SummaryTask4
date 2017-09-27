package ua.nure.dominov.SummaryTask4.db.dao.dao;

import java.io.Closeable;
import java.util.List;

import ua.nure.dominov.SummaryTask4.db.model.HotelType;
import ua.nure.dominov.SummaryTask4.db.model.Tour;
import ua.nure.dominov.SummaryTask4.db.model.TourStatus;
import ua.nure.dominov.SummaryTask4.db.model.TypeOfHoliday;

/**
 * @author calango
 *
 */
public interface TourDao extends Closeable, AutoCloseable {

	Tour getTheMostExpensiveUserTour(long userId);
	
	/**
	 * @param tour new tour
	 * @return number of lines were inserted
	 */
	int insert(Tour tour);
	
	/**
	 * @param id tour id
	 * @return tour
	 */
	Tour getTourById(long tourId);
	
	/**
	 * @return free tours
	 */
	List<Tour> getFreeTours();
	
	/**
	 * @return booked tours
	 */
	List<Tour> getBookedTours();
	
	/**
	 * @return reserved tours
	 */
	List<Tour> getReservedTours();
	
	/**
	 * @param id user id
	 * @return list of tours
	 */
	List<Tour> getTourByUserId(long userId);
	
	/**
	 * @param tour tour
	 * @return number of lines were inserted
	 */
	int update(Tour tour);
	
	/**
	 * @param id tour id
	 * @param price tour price
	 * @param statusId status id
	 * @param maxDiscount maximum discount
	 * @param stepDiscount step of the discount
	 * @param incrementDiscount increment of step
	 * @return number of lines were inserted
	 */
	int managerUpdate(long tourId, long price, int statusId, int maxDiscount,
			int stepDiscount, int incrementDiscount);
	
	/**
	 * @param name tour name
	 * @param id user id
	 * @return number of lines were inserted
	 */
	int updateClient(String tourName, long userId);
	
	/**
	 * @param id tour id to delete
	 * @return number of lines were inserted
	 */
	int delete(long tourId);
	
	/**
	 * @return list of all types of holiday
	 */
	List<TypeOfHoliday> getAllTypesOfHoliday();
	
	/**
	 * @return list of all hotel types
	 */
	List<HotelType> getAllHotelTypes();
	
	/**
	 * @return list of all tour statuses
	 */
	List<TourStatus> getAllTourStatuses();
	
	/**
	 * @param name holiday type name
	 * @return holiday type id
	 */
	long getHolidayTypeId(String name);
	
	/**
	 * @param name hotel type name
	 * @return hotel type id
	 */
	long getHotelTypeId(String name);
	
	/**
	 * @param name status name
	 * @return status id
	 */
	int getStatusId(String name);
	
	/**
	 * @param name name of the tour
	 * @param isHot hot parameter
	 * @return number of lines were inserted
	 */
	int updateIsHot(String name, boolean isHot);
	
	/**
	 * @param query dinamic query
	 * @return search result
	 */
	List<Tour> executeDinamicQuery(String query);
	
}
