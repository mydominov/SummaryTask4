/**
 * 
 */
package ua.nure.dominov.SummaryTask4.web.controller.command.client;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.nure.dominov.SummaryTask4.db.bo.factory.AbstractBoFactory;

/**
 * @author calango
 *
 */
public final class SearchQueryConstructor {
	
	/**
	 * 
	 */
	private static final Logger LOGGER = LogManager.getLogger(
			SearchQueryConstructor.class.getName());
	
	/**
	 * 
	 */
	private static final String BASE = "SELECT * FROM tour,type_of_holiday,hotel_type,tour_status WHERE tour.status_id=4 AND type_of_holiday.type_id=tour.holiday_type_id AND hotel_type.hotel_type_id=tour.hotel_type AND tour_status.tour_status_id=tour.status_id";
	
	/**
	 * 
	 */
	private static final String ANDWORD = " AND ";

	/**
	 * 
	 */
	private static final String ENDING = " ORDER BY is_hot DESC";
	
	/**
	 * 
	 */
	private SearchQueryConstructor() {
		super();
	}
	
	/**
	 * @param request
	 * @param sqb SearchQueryBean
	 * @return SearchQueryBean
	 */
	public static SearchQueryBean generateMin(final HttpServletRequest request, 
			final SearchQueryBean sqb) {
		if ((request.getParameter("min") != null) 
				&& (!request.getParameter("min").equals(""))) {
			sqb.setMin(true);
			sqb.setMinVal(Integer.parseInt(request.getParameter("min")));
		} else {
			sqb.setMin(false);
		}
		return sqb;
	}
	
	/**
	 * @param request
	 * @param sqb SearchQueryBean
	 * @return
	 */
	public static SearchQueryBean generateMax(final HttpServletRequest request, 
			final SearchQueryBean sqb) {
		if ((request.getParameter("max") != null) 
				&& (!request.getParameter("max").equals(""))) {
			sqb.setMax(true);
			sqb.setMaxVal(Integer.parseInt(request.getParameter("max")));
		} else {
			sqb.setMax(false);
		}
		return sqb;
	}
	
	/**
	 * @param request
	 * @param sqb SearchQueryBean
	 * @return SearchQueryBean
	 */
	public static SearchQueryBean generateTypeOfHoliday(
			final HttpServletRequest request, final SearchQueryBean sqb) {
		if ((request.getParameter("holidaytype") != null) 
				&& (!request.getParameter("holidaytype").
						equals("Nothing"))) {
			final String typeOfHoliday = request.getParameter("holidaytype");
			sqb.setTypeOfHoliday(true);
			final long typeOfHolidayId = AbstractBoFactory.getBoFactory(
					AbstractBoFactory.SIMPLEDEFAULTFAC).getTourBo().
						getHolidayTypeId(typeOfHoliday);
			LOGGER.info("holiday - " + typeOfHolidayId);
			sqb.setTypeOfHolidayId(typeOfHolidayId);
		} else {
			sqb.setTypeOfHoliday(false);
		}
		return sqb;
	}
	
	/**
	 * @param request
	 * @param sqb
	 * @return
	 */
	public static SearchQueryBean generateNumberOfPeople(
			final HttpServletRequest request, final SearchQueryBean sqb) {
		if ((request.getParameter("numberofpeople") != null) 
				&& (!request.getParameter("numberofpeople").
						equals(""))) {
			sqb.setNumberOfPeople(true);
			sqb.setNumberOfPeople(Integer.parseInt(request.
					getParameter("numberofpeople")));
		} else {
			sqb.setNumberOfPeople(false);
		}
		return sqb;
	}
	
	/**
	 * @param request
	 * @param sqb
	 * @return
	 */
	public static SearchQueryBean generateHotelType(
			final HttpServletRequest request, final SearchQueryBean sqb) {
		if ((request.getParameter("hoteltype") != null) 
				&& (!request.getParameter("hoteltype").
						equals("Nothing"))) {
			final String hotelType = request.getParameter("hoteltype");
			sqb.setHotelType(true);
			final long hoteltypeId = AbstractBoFactory.getBoFactory(
					AbstractBoFactory.SIMPLEDEFAULTFAC).getTourBo().
						getHotelTypeId(hotelType);
			LOGGER.info(hoteltypeId);
			sqb.setHotelTypeId(hoteltypeId);
		} else {
			sqb.setHotelType(false);
		}
		return sqb;
	}
	
	/**
	 * @param sql
	 * @param sqb
	 * @return
	 */
	public static StringBuilder sqlMin(final StringBuilder sql, 
			final SearchQueryBean sqb) {
		if (sqb.isMin()) {
			sql.append(ANDWORD);
			sql.append("tour.price>=").append(sqb.getMinVal());
		}
		return sql;
	}
	
	/**
	 * @param sql
	 * @param sqb
	 * @return
	 */
	public static StringBuilder sqlMax(final StringBuilder sql, 
			final SearchQueryBean sqb) {
		if (sqb.isMax()) {
			sql.append(ANDWORD);
			sql.append("tour.price<=").append(sqb.getMaxVal());
		}
		return sql;
	}
	
	/**
	 * @param sql
	 * @param sqb
	 * @return
	 */
	public static StringBuilder sqlTypeOfHoliday(final StringBuilder sql, 
			final SearchQueryBean sqb) {
		if (sqb.isTypeOfHoliday()) {
			sql.append(ANDWORD);
			sql.append("tour.holiday_type_id=").
			append(sqb.getTypeOfHolidayId());
		}
		return sql;
	}
	
	/**
	 * @param sql
	 * @param sqb
	 * @return
	 */
	public static StringBuilder sqlNumberOfpeople(final StringBuilder sql, 
			final SearchQueryBean sqb) {
		if (sqb.isNumberOfPeople()) {
			sql.append(ANDWORD);
			sql.append("tour.number_of_people=").append(sqb.getNumberOfPeople());
		}
		return sql;
	}
	
	/**
	 * @param sql
	 * @param sqb
	 * @return
	 */
	public static StringBuilder sqlHotelType(final StringBuilder sql, 
			final SearchQueryBean sqb) {
		if (sqb.isHotelType()) {
			sql.append(ANDWORD);
			sql.append("tour.hotel_type=").append(sqb.getHotelTypeId());
		}
		return sql;
	}
	
	/**
	 * @param sqb
	 * @return
	 */
	public static SearchQueryBean normalization(final SearchQueryBean sqb) {
		long min = sqb.getMinVal();
		long max = sqb.getMaxVal();
		
		if (max < min) {
			//swap min & max
			max ^= min;
			min = max ^ min;
			max ^= min;
		}
		
		sqb.setMinVal(min);
		sqb.setMaxVal(max);
		
		return sqb;
	}
	
	/**
	 * @param sqb
	 * @return
	 */
	public static String generateSQLQuery(final SearchQueryBean sqb) {
		final StringBuilder query = new StringBuilder();
		query.append(BASE);
		sqlMin(query, sqb);
		sqlMax(query, sqb);
		sqlTypeOfHoliday(query, sqb);
		sqlNumberOfpeople(query, sqb);
		sqlHotelType(query, sqb);
		query.append(ENDING);
		return query.toString();
	}
	
}
