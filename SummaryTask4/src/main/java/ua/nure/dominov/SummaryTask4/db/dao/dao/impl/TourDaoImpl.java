/**
 * 
 */
package ua.nure.dominov.SummaryTask4.db.dao.dao.impl;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.nure.dominov.SummaryTask4.db.dao.dao.TourDao;
import ua.nure.dominov.SummaryTask4.db.dao.factory.impl.DaoFactoryImpl;
import ua.nure.dominov.SummaryTask4.db.model.HotelType;
import ua.nure.dominov.SummaryTask4.db.model.Tour;
import ua.nure.dominov.SummaryTask4.db.model.TourStatus;
import ua.nure.dominov.SummaryTask4.db.model.TypeOfHoliday;
import ua.nure.dominov.SummaryTask4.db.model.User;
import ua.nure.dominov.SummaryTask4.db.model.UserRole;

/**
 * @author calango
 *
 */
public class TourDaoImpl implements TourDao, Closeable, AutoCloseable {

	/**
	 * 
	 */
	private static final String INCREMENT_DISCOUNT = "increment_discount";

	/**
	 * 
	 */
	private static final String STEP_DISCOUNT = "step_discount";

	/**
	 * 
	 */
	private static final String MAX_DISCOUNT = "max_discount";

	/**
	 * 
	 */
	private static final String IS_HOT = "is_hot";

	/**
	 * 
	 */
	private static final String NUMBER_OF_PEOPLE = "number_of_people";

	/**
	 * 
	 */
	private static final String PRICE = "price";

	/**
	 * 
	 */
	private static final String TOUR_NAME = "tour_name";

	/**
	 * 
	 */
	private static final String TOUR_ID = "tour_id";

	/**
	 * 
	 */
	private static final String STATUS_NAME = "status_name";

	/**
	 * 
	 */
	private static final String TYPE_NAME = "type_name";

	/**
	 * 
	 */
	private static final String HOTEL_TYPE_ID = "hotel_type_id";

	/**
	 * 
	 */
	private static final String HOLIDAY_NAME = "holiday_name";

	/**
	 * 
	 */
	private static final String TYPE_ID = "type_id";

	/**
	 * 
	 */
	private static final String STATUS_ID = "status_id";

	/**
	 * 
	 */
	private final Connection connection;

	/**
	 * 
	 */
	private static final Logger LOGGER = LogManager.getLogger(TourDaoImpl.class.getName());

	/**
	 * 
	 */
	private static final String INSERTSTATEMENT = "INSERT INTO tour (tour_name, price, holiday_type_id, number_of_people, hotel_type, status_id, is_hot, max_discount, step_discount, increment_discount) VALUES (?,?,?,?,?,?,?,?,?,?)";

	/**
	 * 
	 */
	private static final String GETFREETOURSSTATEMENT = "SELECT * FROM free_tour";

	/**
	 * 
	 */
	private static final String GETTOURBYIDSTATEMENT = "SELECT * FROM tour,type_of_holiday,hotel_type,tour_status WHERE type_of_holiday.type_id=tour.holiday_type_id AND hotel_type.hotel_type_id=tour.hotel_type AND tour_status.tour_status_id=tour.status_id AND tour_id=?";

	/**
	 * 
	 */
	private static final String GETBOOKEDTOURSSTATEMENT = "SELECT * FROM booked_tour";

	/**
	 * 
	 */
	private static final String GETREGISTEREDTOURSSTATEMENT = "SELECT * FROM registered_tour";

	/**
	 * 
	 */
	private static final String GETTOURBYUSERIDSTATEMENT = "SELECT * FROM tour,type_of_holiday,hotel_type,tour_status,user,user_role WHERE reserved_by_id=? AND type_of_holiday.type_id=tour.holiday_type_id AND hotel_type.hotel_type_id=tour.hotel_type AND tour_status.tour_status_id=tour.status_id AND user.user_id=tour.reserved_by_id AND user_role.role_id=user.user_role ORDER BY is_hot DESC";

	/**
	 * 
	 */
	private static final String UPDATESTATEMENT = "UPDATE tour SET holiday_type_id=?,tour_name=?,price=?,number_of_people=?,hotel_type=?,status_id=?,is_hot=?,max_discount=?,step_discount=?,increment_discount=? WHERE tour_id=?";

	/**
	 * 
	 */
	private static final String MANAGERUPDATESTATEMENT = "UPDATE tour SET price=?,status_id=?,max_discount=?,step_discount=?,increment_discount=? WHERE tour_id=?";

	/**
	 * 
	 */
	private static final String CLIENTUPDATESTATEMENT = "UPDATE tour SET reserved_by_id=?,status_id=1 WHERE tour_name=?";

	/**
	 * 
	 */
	private static final String DELETESTATEMENT = "DELETE FROM tour WHERE tour_id=?";

	/**
	 * 
	 */
	private static final String ALLTYPESOFHOLIDAYSTATEMENT = "SELECT * FROM type_of_holiday";

	/**
	 * 
	 */
	private static final String ALLHOTELTYPESSTATEMENT = "SELECT * FROM hotel_type";

	/**
	 * 
	 */
	private static final String ALLTOURSTATUSSTATEMENT = "SELECT * FROM tour_status";

	/**
	 * 
	 */
	private static final String GETHOLIDAYTYPEIDBYNAMESTATEMENT = "SELECT type_id FROM type_of_holiday WHERE holiday_name=?";

	/**
	 * 
	 */
	private static final String GETHOTELTYPEIDBYNAMESTATEMENT = "SELECT hotel_type_id FROM hotel_type WHERE type_name=?";

	/**
	 * 
	 */
	private static final String GETTOURSTATUSIDBYNAMESTATEMENT = "SELECT tour_status_id FROM tour_status WHERE status_name=?";

	/**
	 * 
	 */
	private static final String UPDATEISHOTSTATEMENT = "UPDATE tour SET is_hot=? WHERE tour_name=?";

	/**
	 * 
	 */
	private static final String THEMOSTUSEREXPENSIVETOURSTATEMENT = "SELECT * FROM tour, (SELECT MAX(price) AS max_price FROM tour WHERE reserved_by_id IS NOT NULL AND reserved_by_id=?) AS m_price WHERE tour.reserved_by_id=? AND user_role.role_id=user.user_role AND m_price.max_price=tour.price";
	
	/**
	 * @param connection
	 *            connection from the factory
	 */
	public TourDaoImpl(final Connection connection) {
		this.connection = connection;
	}

	/**
	 * @return the connection
	 */
	public final Connection getConnection() {
		return connection;
	}

	@Override
	public final Tour getTheMostExpensiveUserTour(long userId) {
		Tour tour = new Tour();
		try (PreparedStatement prepStatement = connection.prepareStatement(
				THEMOSTUSEREXPENSIVETOURSTATEMENT)) {
			prepStatement.setLong(1, userId);
			prepStatement.setLong(2, userId);
			try (ResultSet resultSet = prepStatement.executeQuery()) {
				if (resultSet.next()) {

					tour.setTourId(resultSet.getLong(TOUR_ID));
					tour.setTourName(resultSet.getString(TOUR_NAME));
					tour.setPrice(resultSet.getLong(PRICE));
					tour.setNumberOfPeople(resultSet.getInt(NUMBER_OF_PEOPLE));
					tour.setHot(resultSet.getBoolean(IS_HOT));
					tour.setMaxDiscount(resultSet.getShort(MAX_DISCOUNT));
					tour.setStepDiscount(resultSet.getShort(STEP_DISCOUNT));
					tour.setIncrementDiscount(resultSet.getShort(INCREMENT_DISCOUNT));
					
				}
			}
		} catch (SQLException e) {
			LOGGER.info("Issue in getTheMostExpensiveUserTour: " + e);
		}
		return tour;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see ua.nure.dominov.SummaryTask4.db.dao.TourDao#insert(ua.nure.dominov.
	 * SummaryTask4.db.model.Tour)
	 */
	@Override
	public final int insert(final Tour tour) {
		int result = -1;
		try (PreparedStatement prepStatement = connection.prepareStatement(INSERTSTATEMENT,
				Statement.RETURN_GENERATED_KEYS)) {
			prepStatement.setString(1, tour.getTourName());
			prepStatement.setLong(2, tour.getPrice());
			prepStatement.setLong(3, tour.getTypeOfHoliday().getTypeOfHolidayId());
			prepStatement.setInt(4, tour.getNumberOfPeople());
			prepStatement.setLong(5, tour.getHotelType().getHotelTypeId());
			prepStatement.setLong(6, tour.getTourStatus().getStatusId());
			prepStatement.setBoolean(7, tour.getIsHot());
			prepStatement.setInt(8, tour.getMaxDiscount());
			prepStatement.setInt(9, tour.getStepDiscount());
			prepStatement.setInt(10, tour.getIncrementDiscount());
			result = prepStatement.executeUpdate();

			try (ResultSet generatedKeys = prepStatement.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					tour.setTourId(generatedKeys.getLong(1));
				}
			}
			LOGGER.info("Tour was created successfully!");
		} catch (SQLException e) {
			LOGGER.error(e);
		}
		return result;

	}

	@Override
	public final Tour getTourById(final long tourId) {
		final Tour tour = new Tour();
		try (PreparedStatement preparedStatement = connection.prepareStatement(GETTOURBYIDSTATEMENT)) {
			preparedStatement.setLong(1, tourId);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					final TypeOfHoliday typeOfHoliday = new TypeOfHoliday();
					final HotelType hotelType = new HotelType();
					final TourStatus tourStatus = new TourStatus();

					typeOfHoliday.setTypeOfHolidayId(resultSet.getLong(TYPE_ID));
					typeOfHoliday.setHolidayName(resultSet.getString(HOLIDAY_NAME));

					hotelType.setHotelTypeId(resultSet.getLong(HOTEL_TYPE_ID));
					hotelType.setTypeName(resultSet.getString(TYPE_NAME));

					tourStatus.setStatusId(resultSet.getInt(STATUS_ID));
					tourStatus.setStatusName(resultSet.getString(STATUS_NAME));

					tour.setTourId(resultSet.getLong(TOUR_ID));
					tour.setTourName(resultSet.getString(TOUR_NAME));
					tour.setPrice(resultSet.getLong(PRICE));
					tour.setNumberOfPeople(resultSet.getInt(NUMBER_OF_PEOPLE));
					tour.setHot(resultSet.getBoolean(IS_HOT));
					tour.setMaxDiscount(resultSet.getShort(MAX_DISCOUNT));
					tour.setStepDiscount(resultSet.getShort(STEP_DISCOUNT));
					tour.setIncrementDiscount(resultSet.getShort(INCREMENT_DISCOUNT));

					tour.setTypeOfHoliday(typeOfHoliday);
					tour.setHotelType(hotelType);
					tour.setTourStatus(tourStatus);
				}
			}
		} catch (SQLException e) {
			LOGGER.error("Issue happened in getTourById: " + e);
		}
		return tour;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ua.nure.dominov.SummaryTask4.db.dao.TourDao#getFreeTour()
	 */
	@Override
	public final List<Tour> getFreeTours() {
		final List<Tour> list = new LinkedList<>();
		Tour temp;
		TypeOfHoliday typeOfHoliday;
		HotelType hotelType;
		TourStatus tourStatus;
		try (PreparedStatement prepStatement = connection.prepareStatement(GETFREETOURSSTATEMENT)) {
			try (ResultSet resultSet = prepStatement.executeQuery()) {
				while (resultSet.next()) {
					// Inits new values
					temp = new Tour();
					typeOfHoliday = new TypeOfHoliday();
					hotelType = new HotelType();
					tourStatus = new TourStatus();

					typeOfHoliday.setTypeOfHolidayId(resultSet.getLong(TYPE_ID));
					typeOfHoliday.setHolidayName(resultSet.getString(HOLIDAY_NAME));

					hotelType.setHotelTypeId(resultSet.getLong(HOTEL_TYPE_ID));
					hotelType.setTypeName(resultSet.getString(TYPE_NAME));

					tourStatus.setStatusId(resultSet.getInt(STATUS_ID));
					tourStatus.setStatusName(resultSet.getString(STATUS_NAME));

					temp.setTourId(resultSet.getLong(TOUR_ID));
					temp.setTourName(resultSet.getString(TOUR_NAME));
					temp.setPrice(resultSet.getLong(PRICE));
					temp.setNumberOfPeople(resultSet.getInt(NUMBER_OF_PEOPLE));
					temp.setHot(resultSet.getBoolean(IS_HOT));
					temp.setMaxDiscount(resultSet.getShort(MAX_DISCOUNT));
					temp.setStepDiscount(resultSet.getShort(STEP_DISCOUNT));
					temp.setIncrementDiscount(resultSet.getShort(INCREMENT_DISCOUNT));

					temp.setTypeOfHoliday(typeOfHoliday);
					temp.setHotelType(hotelType);
					temp.setTourStatus(tourStatus);

					list.add(temp);
				}
			}
		} catch (SQLException e) {
			LOGGER.error(e);
		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ua.nure.dominov.SummaryTask4.db.dao.TourDao#getBookedTourByUserId(int)
	 */
	@Override
	public final List<Tour> getBookedTours() {
		final List<Tour> list = new LinkedList<>();
		Tour temp;
		TypeOfHoliday typeOfHoliday;
		HotelType hotelType;
		TourStatus tourStatus;
		User reservedBy;
		UserRole userRole;
		try (PreparedStatement prepStatement = connection.prepareStatement(GETBOOKEDTOURSSTATEMENT)) {
			try (ResultSet resultSet = prepStatement.executeQuery()) {
				while (resultSet.next()) {
					// Inits new values
					temp = new Tour();
					typeOfHoliday = new TypeOfHoliday();
					hotelType = new HotelType();
					tourStatus = new TourStatus();
					reservedBy = new User();
					userRole = new UserRole();

					typeOfHoliday.setTypeOfHolidayId(resultSet.getLong(TYPE_ID));
					typeOfHoliday.setHolidayName(resultSet.getString(HOLIDAY_NAME));

					hotelType.setHotelTypeId(resultSet.getLong(HOTEL_TYPE_ID));
					hotelType.setTypeName(resultSet.getString(TYPE_NAME));

					tourStatus.setStatusId(resultSet.getInt(STATUS_ID));
					tourStatus.setStatusName(resultSet.getString(STATUS_NAME));

					userRole.setRoleId(resultSet.getLong("role_id"));
					userRole.setRoleName(resultSet.getString("role_name"));
					userRole.setOfficeCoefficient(resultSet.getShort("office_coefficient"));

					reservedBy.setUserId(resultSet.getInt("user_id"));
					reservedBy.setName(resultSet.getString("user_name"));
					reservedBy.setEmail(resultSet.getString("user_email"));
					reservedBy.setPassword(resultSet.getString("user_password"));
					reservedBy.setSalt(resultSet.getString("user_salt"));
					reservedBy.setUserRole(userRole);
					reservedBy.setIsConfirmed(resultSet.getBoolean("is_confirmed"));

					temp.setTourId(resultSet.getLong(TOUR_ID));
					temp.setTourName(resultSet.getString(TOUR_NAME));
					temp.setPrice(resultSet.getLong(PRICE));
					temp.setNumberOfPeople(resultSet.getInt(NUMBER_OF_PEOPLE));
					temp.setHot(resultSet.getBoolean(IS_HOT));
					temp.setMaxDiscount(resultSet.getShort(MAX_DISCOUNT));
					temp.setStepDiscount(resultSet.getShort(STEP_DISCOUNT));
					temp.setIncrementDiscount(resultSet.getShort(INCREMENT_DISCOUNT));

					temp.setTypeOfHoliday(typeOfHoliday);
					temp.setHotelType(hotelType);
					temp.setTourStatus(tourStatus);
					temp.setReservedBy(reservedBy);

					list.add(temp);
				}
			}
		} catch (SQLException e) {
			LOGGER.error(e);
		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ua.nure.dominov.SummaryTask4.db.dao.TourDao#getBookedTourByUserId(int)
	 */
	@Override
	public final List<Tour> getReservedTours() {
		final List<Tour> list = new LinkedList<>();
		Tour temp;
		TypeOfHoliday typeOfHoliday;
		HotelType hotelType;
		TourStatus tourStatus;
		User reservedBy;
		UserRole userRole;
		try (PreparedStatement prepStatement = connection.prepareStatement(GETREGISTEREDTOURSSTATEMENT)) {
			try (ResultSet resultSet = prepStatement.executeQuery()) {
				while (resultSet.next()) {
					// Inits new values
					temp = new Tour();
					typeOfHoliday = new TypeOfHoliday();
					hotelType = new HotelType();
					tourStatus = new TourStatus();
					reservedBy = new User();
					userRole = new UserRole();

					typeOfHoliday.setTypeOfHolidayId(resultSet.getLong(TYPE_ID));
					typeOfHoliday.setHolidayName(resultSet.getString(HOLIDAY_NAME));

					hotelType.setHotelTypeId(resultSet.getLong(HOTEL_TYPE_ID));
					hotelType.setTypeName(resultSet.getString(TYPE_NAME));

					tourStatus.setStatusId(resultSet.getInt(STATUS_ID));
					tourStatus.setStatusName(resultSet.getString(STATUS_NAME));

					userRole.setRoleId(resultSet.getLong("role_id"));
					userRole.setRoleName(resultSet.getString("role_name"));
					userRole.setOfficeCoefficient(resultSet.getShort("office_coefficient"));

					reservedBy.setUserId(resultSet.getInt("user_id"));
					reservedBy.setName(resultSet.getString("user_name"));
					reservedBy.setEmail(resultSet.getString("user_email"));
					reservedBy.setPassword(resultSet.getString("user_password"));
					reservedBy.setSalt(resultSet.getString("user_salt"));
					reservedBy.setUserRole(userRole);
					reservedBy.setIsConfirmed(resultSet.getBoolean("is_confirmed"));

					temp.setTourId(resultSet.getLong(TOUR_ID));
					temp.setTourName(resultSet.getString(TOUR_NAME));
					temp.setPrice(resultSet.getLong(PRICE));
					temp.setNumberOfPeople(resultSet.getInt(NUMBER_OF_PEOPLE));
					temp.setHot(resultSet.getBoolean(IS_HOT));
					temp.setMaxDiscount(resultSet.getShort(MAX_DISCOUNT));
					temp.setStepDiscount(resultSet.getShort(STEP_DISCOUNT));
					temp.setIncrementDiscount(resultSet.getShort(INCREMENT_DISCOUNT));

					temp.setTypeOfHoliday(typeOfHoliday);
					temp.setHotelType(hotelType);
					temp.setTourStatus(tourStatus);
					temp.setReservedBy(reservedBy);

					list.add(temp);
				}
			}
		} catch (SQLException e) {
			LOGGER.error(e);
		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ua.nure.dominov.SummaryTask4.db.dao.TourDao#getTourById(int)
	 */
	@Override
	public final List<Tour> getTourByUserId(final long userId) {
		final List<Tour> list = new LinkedList<>();
		Tour tour;
		TypeOfHoliday typeOfHoliday;
		HotelType hotelType;
		TourStatus tourStatus;
		User reservedBy;
		UserRole userRole;
		try (PreparedStatement prepStatement = connection.prepareStatement(GETTOURBYUSERIDSTATEMENT)) {
			prepStatement.setLong(1, userId);
			try (ResultSet resultSet = prepStatement.executeQuery()) {
				while (resultSet.next()) {
					// Inits new values
					tour = new Tour();
					typeOfHoliday = new TypeOfHoliday();
					hotelType = new HotelType();
					tourStatus = new TourStatus();
					reservedBy = new User();
					userRole = new UserRole();

					typeOfHoliday.setTypeOfHolidayId(resultSet.getLong(TYPE_ID));
					typeOfHoliday.setHolidayName(resultSet.getString(HOLIDAY_NAME));

					hotelType.setHotelTypeId(resultSet.getLong(HOTEL_TYPE_ID));
					hotelType.setTypeName(resultSet.getString(TYPE_NAME));

					tourStatus.setStatusId(resultSet.getInt(STATUS_ID));
					tourStatus.setStatusName(resultSet.getString(STATUS_NAME));

					userRole.setRoleId(resultSet.getLong("role_id"));
					userRole.setRoleName(resultSet.getString("role_name"));
					userRole.setOfficeCoefficient(resultSet.getShort("office_coefficient"));

					reservedBy.setUserId(resultSet.getInt("user_id"));
					reservedBy.setName(resultSet.getString("user_name"));
					reservedBy.setEmail(resultSet.getString("user_email"));
					reservedBy.setPassword(resultSet.getString("user_password"));
					reservedBy.setSalt(resultSet.getString("user_salt"));
					reservedBy.setUserRole(userRole);
					reservedBy.setIsConfirmed(resultSet.getBoolean("is_confirmed"));

					tour.setTourId(resultSet.getLong(TOUR_ID));
					tour.setTourName(resultSet.getString(TOUR_NAME));
					tour.setPrice(resultSet.getLong(PRICE));
					tour.setNumberOfPeople(resultSet.getInt(NUMBER_OF_PEOPLE));
					tour.setHot(resultSet.getBoolean(IS_HOT));
					tour.setMaxDiscount(resultSet.getShort(MAX_DISCOUNT));
					tour.setStepDiscount(resultSet.getShort(STEP_DISCOUNT));
					tour.setIncrementDiscount(resultSet.getShort(INCREMENT_DISCOUNT));

					tour.setTypeOfHoliday(typeOfHoliday);
					tour.setHotelType(hotelType);
					tour.setTourStatus(tourStatus);
					tour.setReservedBy(reservedBy);

					list.add(tour);
				}
			}
		} catch (SQLException e) {
			LOGGER.error(e);
		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ua.nure.dominov.SummaryTask4.db.dao.TourDao#update(ua.nure.dominov.
	 * SummaryTask4.db.model.Tour)
	 */
	@Override
	public final int update(final Tour tour) {
		int result = -1;
		try (PreparedStatement prepStatement = connection.prepareStatement(UPDATESTATEMENT)) {
			prepStatement.setLong(1, tour.getTypeOfHoliday().getTypeOfHolidayId());
			prepStatement.setString(2, tour.getTourName());
			prepStatement.setLong(3, tour.getPrice());
			prepStatement.setInt(4, tour.getNumberOfPeople());
			prepStatement.setLong(5, tour.getHotelType().getHotelTypeId());
			prepStatement.setLong(6, tour.getTourStatus().getStatusId());
			prepStatement.setBoolean(7, tour.getIsHot());
			prepStatement.setInt(8, tour.getMaxDiscount());
			prepStatement.setInt(9, tour.getStepDiscount());
			prepStatement.setInt(10, tour.getIncrementDiscount());
			prepStatement.setLong(11, tour.getTourId());
			result = prepStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Issue happened in update: " + e);
		}
		return result;

	}

	@Override
	public final int managerUpdate(final long tourId, final long price, final int statusId, final int maxDiscount,
			final int stepDiscount, final int incrementDiscount) {
		int result = -1;
		try (PreparedStatement prepStatement = connection.prepareStatement(MANAGERUPDATESTATEMENT)) {
			prepStatement.setLong(1, price);
			prepStatement.setLong(2, statusId);
			prepStatement.setInt(3, maxDiscount);
			prepStatement.setInt(4, stepDiscount);
			prepStatement.setInt(5, incrementDiscount);
			prepStatement.setLong(6, tourId);
			result = prepStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error(e);
		}
		return result;
	}

	@Override
	public final int updateClient(final String tourName, final long userId) {
		int result = -1;
		try (PreparedStatement prepStatement = connection.prepareStatement(CLIENTUPDATESTATEMENT)) {
			prepStatement.setLong(1, userId);
			prepStatement.setString(2, tourName);
			result = prepStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Issue happened in 463 line: " + e);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ua.nure.dominov.SummaryTask4.db.dao.TourDao#delete(ua.nure.dominov.
	 * SummaryTask4.db.model.Tour)
	 */
	@Override
	public final int delete(final long tourId) {
		int result = -1;
		try (PreparedStatement prepStatement = connection.prepareStatement(DELETESTATEMENT)) {
			prepStatement.setLong(1, tourId);
			result = prepStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error(e);
		}
		return result;
	}

	@Override
	public final List<TypeOfHoliday> getAllTypesOfHoliday() {
		final List<TypeOfHoliday> list = new LinkedList<>();
		TypeOfHoliday typeOfHoliday;
		try (PreparedStatement prepStatement = connection.prepareStatement(ALLTYPESOFHOLIDAYSTATEMENT)) {
			try (ResultSet resultSet = prepStatement.executeQuery()) {
				while (resultSet.next()) {
					typeOfHoliday = new TypeOfHoliday();

					typeOfHoliday.setTypeOfHolidayId(resultSet.getLong(TYPE_ID));
					typeOfHoliday.setHolidayName(resultSet.getString(HOLIDAY_NAME));

					list.add(typeOfHoliday);
				}
			}
		} catch (SQLException e) {
			LOGGER.error("Issue happened in getAllTypesOfHoliday:" + e);
		}
		return list;
	}

	@Override
	public final List<HotelType> getAllHotelTypes() {
		final List<HotelType> list = new LinkedList<>();
		HotelType hotelType;
		try (PreparedStatement prepStatement = connection.prepareStatement(ALLHOTELTYPESSTATEMENT)) {
			try (ResultSet resultSet = prepStatement.executeQuery()) {
				while (resultSet.next()) {
					hotelType = new HotelType();

					hotelType.setHotelTypeId(resultSet.getLong(HOTEL_TYPE_ID));
					hotelType.setTypeName(resultSet.getString(TYPE_NAME));

					list.add(hotelType);
				}
			}
		} catch (SQLException e) {
			LOGGER.error("Issue happened in getAllHotelTypes:" + e);
		}
		return list;
	}

	@Override
	public final List<TourStatus> getAllTourStatuses() {
		final List<TourStatus> list = new LinkedList<>();
		TourStatus tourStatus;
		try (PreparedStatement prepStatement = connection.prepareStatement(ALLTOURSTATUSSTATEMENT)) {
			try (ResultSet resultSet = prepStatement.executeQuery()) {
				while (resultSet.next()) {
					tourStatus = new TourStatus();

					tourStatus.setStatusId(resultSet.getInt("tour_status_id"));
					tourStatus.setStatusName(resultSet.getString(STATUS_NAME));

					list.add(tourStatus);
				}
			}
		} catch (SQLException e) {
			LOGGER.error("Issue happened in getAllTourStatuses:" + e);
		}
		return list;
	}

	@Override
	public final long getHolidayTypeId(final String name) {
		long result = -1;
		try (PreparedStatement prepStatement = connection.prepareStatement(GETHOLIDAYTYPEIDBYNAMESTATEMENT)) {
			prepStatement.setString(1, name);
			try (ResultSet resultSet = prepStatement.executeQuery()) {
				if (resultSet.next()) {
					result = resultSet.getLong(1);
				}
			}
		} catch (SQLException e) {
			LOGGER.error("Issue happened in getHolidayTypeId:" + e);
		}
		return result;
	}

	@Override
	public final long getHotelTypeId(final String name) {
		long result = -1;
		try (PreparedStatement prepStatement = connection.prepareStatement(GETHOTELTYPEIDBYNAMESTATEMENT)) {
			prepStatement.setString(1, name);
			try (ResultSet resultSet = prepStatement.executeQuery()) {
				if (resultSet.next()) {
					result = resultSet.getLong(1);
				}
			}
		} catch (SQLException e) {
			LOGGER.error("Issue happened in getHotelTypeId:" + e);
		}
		return result;
	}

	@Override
	public final int getStatusId(final String name) {
		int result = -1;
		try (PreparedStatement prepStatement = connection.prepareStatement(GETTOURSTATUSIDBYNAMESTATEMENT)) {
			prepStatement.setString(1, name);
			try (ResultSet resultSet = prepStatement.executeQuery()) {
				if (resultSet.next()) {
					result = resultSet.getInt(1);
				}
			}
		} catch (SQLException e) {
			LOGGER.error("Issue happened in getHolidayTypeId:" + e);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ua.nure.dominov.SummaryTask4.db.dao.dao.TourDao#executeDinamicQuery(java.
	 * lang.String)
	 */
	@Override
	public final List<Tour> executeDinamicQuery(final String query) {
		final List<Tour> list = new LinkedList<>();
		Tour temp;
		TypeOfHoliday typeOfHoliday;
		HotelType hotelType;
		TourStatus tourStatus;
		try (Statement statement = connection.createStatement()) {
			try (ResultSet resultSet = statement.executeQuery(query)) {
				while (resultSet.next()) {
					// Inits new values
					temp = new Tour();
					typeOfHoliday = new TypeOfHoliday();
					hotelType = new HotelType();
					tourStatus = new TourStatus();

					typeOfHoliday.setTypeOfHolidayId(resultSet.getLong(TYPE_ID));
					typeOfHoliday.setHolidayName(resultSet.getString(HOLIDAY_NAME));

					hotelType.setHotelTypeId(resultSet.getLong(HOTEL_TYPE_ID));
					hotelType.setTypeName(resultSet.getString(TYPE_NAME));

					tourStatus.setStatusId(resultSet.getInt(STATUS_ID));
					tourStatus.setStatusName(resultSet.getString(STATUS_NAME));

					temp.setTourId(resultSet.getLong(TOUR_ID));
					temp.setTourName(resultSet.getString(TOUR_NAME));
					temp.setPrice(resultSet.getLong(PRICE));
					temp.setNumberOfPeople(resultSet.getInt(NUMBER_OF_PEOPLE));
					temp.setHot(resultSet.getBoolean(IS_HOT));
					temp.setMaxDiscount(resultSet.getShort(MAX_DISCOUNT));
					temp.setStepDiscount(resultSet.getShort(STEP_DISCOUNT));
					temp.setIncrementDiscount(resultSet.getShort(INCREMENT_DISCOUNT));

					temp.setTypeOfHoliday(typeOfHoliday);
					temp.setHotelType(hotelType);
					temp.setTourStatus(tourStatus);

					list.add(temp);
				}
			}
		} catch (SQLException e) {
			LOGGER.error("Issue in executeDinamicQuery: " + e);
		}
		return list;
	}

	@Override
	public final int updateIsHot(final String name, final boolean isHot) {
		int result = -1;
		try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATEISHOTSTATEMENT)) {
			preparedStatement.setBoolean(1, isHot);
			preparedStatement.setString(2, name);
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Issue in updateIsHot: " + e);
		}
		return result;
	}

	@Override
	public final void close() throws IOException {
		DaoFactoryImpl.getConnectionPool().releaseConnection(connection);
	}

}