/**
 * Basic Tour business object implementation.
 */
package ua.nure.dominov.SummaryTask4.db.bo.bo.impl;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.nure.dominov.SummaryTask4.db.bo.bo.TourBo;
import ua.nure.dominov.SummaryTask4.db.dao.dao.TourDao;
import ua.nure.dominov.SummaryTask4.db.dao.factory.AbstractDaoFactory;
import ua.nure.dominov.SummaryTask4.db.model.HotelType;
import ua.nure.dominov.SummaryTask4.db.model.Tour;
import ua.nure.dominov.SummaryTask4.db.model.TourStatus;
import ua.nure.dominov.SummaryTask4.db.model.TypeOfHoliday;

/**
 * @author calango
 *
 */
public class TourBoImpl implements TourBo {

	/**
	 * 
	 */
	private AbstractDaoFactory daoFactory;
	
	/**
	 * 
	 */
	private static final Logger LOGGER = LogManager.getLogger(
			TourBoImpl.class.getName());
	
	@Override
	public final Tour getTheMostExpensiveUserTour(long userId) {
		try (TourDao tourDao = daoFactory.getTourDao()) {
			return tourDao.getTheMostExpensiveUserTour(userId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * @param daoFactory initialization value
	 */
	public TourBoImpl(final AbstractDaoFactory daoFactory) {
		LOGGER.info("Tour bo init");
		this.daoFactory = daoFactory;
	}
	
	/* (non-Javadoc)
	 * @see ua.nure.dominov.SummaryTask4.db.bo.bo.TourBo#insert(ua.nure.dominov.SummaryTask4.db.model.Tour)
	 */
	@Override
	public final boolean insert(final Tour tour) {
		LOGGER.info("Creating new tour.");
		try (TourDao tourDao = daoFactory.getTourDao()) {
			return tourDao.insert(tour) > 0;
		} catch (IOException e) {
			LOGGER.error("Issue happend in insert:" + e);
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see ua.nure.dominov.SummaryTask4.db.bo.bo.TourBo#getFreeTour()
	 */
	@Override
	public final List<Tour> getFreeTours() {
		LOGGER.info("Getting free tours.");
		try (TourDao tourDao = daoFactory.getTourDao()) {
			return tourDao.getFreeTours();
		} catch (IOException e) {
			LOGGER.error("Issue happend in getFreeTour:" + e);
			return null;
		}
		
	}

	/* (non-Javadoc)
	 * @see ua.nure.dominov.SummaryTask4.db.bo.bo.TourBo#getBookedTours()
	 */
	@Override
	public final List<Tour> getBookedTours() {
		LOGGER.info("Getting booked tours.");
		try (TourDao tourDao = daoFactory.getTourDao()) {
			return tourDao.getBookedTours();
		} catch (IOException e) {
			LOGGER.error("Issue happend in getBookedTours:" + e);
			return null;
		}
		
	}

	/* (non-Javadoc)
	 * @see ua.nure.dominov.SummaryTask4.db.bo.bo.TourBo#getReservedTours()
	 */
	@Override
	public final List<Tour> getReservedTours() {
		LOGGER.info("Getting registered tours.");
		try (TourDao tourDao = daoFactory.getTourDao()) {
			return tourDao.getReservedTours();
		} catch (IOException e) {
			LOGGER.error("Issue happend in getBookedTours:" + e);
			return null;
		}
	}
	
	/* (non-Javadoc)
	 * @see ua.nure.dominov.SummaryTask4.db.bo.bo.TourBo#getTourByUserId(int)
	 */
	@Override
	public final List<Tour> getTourByUserId(final long id) {
		LOGGER.info("Getting tour by user id.");
		try (TourDao tourDao = daoFactory.getTourDao()) {
			return tourDao.getTourByUserId(id);
		} catch (IOException e) {
			LOGGER.error("Issue happend in getBookedTours:" + e);
			return null;
		}
		
	}

	/* (non-Javadoc)
	 * @see ua.nure.dominov.SummaryTask4.db.bo.bo.TourBo#update(ua.nure.dominov.SummaryTask4.db.model.Tour)
	 */
	@Override
	public final boolean update(final Tour tour) {
		LOGGER.info("Updating tour.");
		try (TourDao tourDao = daoFactory.getTourDao()) {
			return tourDao.update(tour) > 0;
		} catch (IOException e) {
			LOGGER.error("Issue happend in update:" + e);
			return false;
		}

	}
	
	/* (non-Javadoc)
	 * @see ua.nure.dominov.SummaryTask4.db.bo.bo.TourBo#managerUpdate(long, long, int, short, short, short)
	 */
	@Override
	public final boolean managerUpdate(final long tourId, final long price, 
			final int statusId, final int maxDiscount,
			final int stepDiscount, final int incrementDiscount) {
		LOGGER.info("Manager serving tour.");
		try (TourDao tourDao = daoFactory.getTourDao()) {
			return tourDao.managerUpdate(tourId, price, statusId, maxDiscount,
					stepDiscount, incrementDiscount) > 0;
		} catch (IOException e) {
			LOGGER.error("Issue happend in update:" + e);
			return false;
		}
	}
	
	/* (non-Javadoc)
	 * @see ua.nure.dominov.SummaryTask4.db.bo.bo.TourBo#updateClient(java.lang.String, long)
	 */
	@Override
	public final boolean updateClient(final String tourName, final long userId) {
		LOGGER.info("Client is reserving the tour.");
		try (TourDao tourDao = daoFactory.getTourDao()) {
			return tourDao.updateClient(tourName, userId) > 0;
		} catch (IOException e) {
			LOGGER.error("Issue happend in update:" + e);
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see ua.nure.dominov.SummaryTask4.db.bo.bo.TourBo#delete(ua.nure.dominov.SummaryTask4.db.model.Tour)
	 */
	@Override
	public final boolean delete(final long id) {
		LOGGER.info("Deleting tour.");
		try (TourDao tourDao = daoFactory.getTourDao()) {
			return tourDao.delete(id) > 0;
		} catch (IOException e) {
			LOGGER.error("Issue happend in delete:" + e);
			return false;
		}
		
	}

	/* (non-Javadoc)
	 * @see ua.nure.dominov.SummaryTask4.db.bo.bo.TourBo#getAllTypesOfHoliday()
	 */
	@Override
	public final List<TypeOfHoliday> getAllTypesOfHoliday() {
		LOGGER.info("Getting all types of holiday.");
		try (TourDao tourDao = daoFactory.getTourDao()) {
			return tourDao.getAllTypesOfHoliday();
		} catch (IOException e) {
			LOGGER.error("Issue happend in getAllTypesOfHoliday:" + e);
			return null;
		}
		
	}

	/* (non-Javadoc)
	 * @see ua.nure.dominov.SummaryTask4.db.bo.bo.TourBo#getAllHotelTypes()
	 */
	@Override
	public final List<HotelType> getAllHotelTypes() {
		LOGGER.info("Getting all types of hotel.");
		try (TourDao tourDao = daoFactory.getTourDao()) {
			return tourDao.getAllHotelTypes();
		} catch (IOException e) {
			LOGGER.error("Issue happend in getAllHotelTypes:" + e);
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see ua.nure.dominov.SummaryTask4.db.bo.bo.TourBo#getAllTourStatuses()
	 */
	@Override
	public final List<TourStatus> getAllTourStatuses() {
		LOGGER.info("Getting all tour statuses.");
		try (TourDao tourDao = daoFactory.getTourDao()) {
			return tourDao.getAllTourStatuses();
		} catch (IOException e) {
			LOGGER.error("Issue happend in getAllTourStatuses:" + e);
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see ua.nure.dominov.SummaryTask4.db.bo.bo.TourBo#getHolidayTypeId(java.lang.String)
	 */
	@Override
	public final long getHolidayTypeId(final String name) {
		LOGGER.info("Defining id by holiday name.");
		try (TourDao tourDao = daoFactory.getTourDao()) {
			return tourDao.getHolidayTypeId(name);
		} catch (IOException e) {
			LOGGER.error("Issue happend in getHolidayTypeId:" + e);
			return -1;
		}
	}

	/* (non-Javadoc)
	 * @see ua.nure.dominov.SummaryTask4.db.bo.bo.TourBo#getHotelTypeId(java.lang.String)
	 */
	@Override
	public final long getHotelTypeId(final String name) {
		LOGGER.info("Defining id by hotel name.");
		try (TourDao tourDao = daoFactory.getTourDao()) {
			return tourDao.getHotelTypeId(name);
		} catch (IOException e) {
			LOGGER.error("Issue happend in insert:" + e);
			return -1;
		}
	}

	/* (non-Javadoc)
	 * @see ua.nure.dominov.SummaryTask4.db.bo.bo.TourBo#getStatusId(java.lang.String)
	 */
	@Override
	public final int getStatusId(final String name) {
		LOGGER.info("Defining id by status name.");
		try (TourDao tourDao = daoFactory.getTourDao()) {
			return tourDao.getStatusId(name);
		} catch (IOException e) {
			LOGGER.error("Issue happend in insert:" + e);
			return -1;
		}
	}

	/* (non-Javadoc)
	 * @see ua.nure.dominov.SummaryTask4.db.bo.bo.TourBo#executeDinamicQuery(java.lang.String)
	 */
	@Override
	public final List<Tour> executeDinamicQuery(final String query) {
		LOGGER.info("Getting toures.");
		try (TourDao tourDao = daoFactory.getTourDao()) {
			return tourDao.executeDinamicQuery(query);
		} catch (IOException e) {
			LOGGER.error("Issue happend in executeDinamicQuery:" + e);
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see ua.nure.dominov.SummaryTask4.db.bo.bo.TourBo#getTourById(long)
	 */
	@Override
	public final Tour getTourById(final long id) {
		try (TourDao tourDao = daoFactory.getTourDao()) {
			return tourDao.getTourById(id);
		} catch (IOException e) {
			LOGGER.error("Issue in getTourById: " + e);
			return null;
		}
	}
	
	/* (non-Javadoc)
	 * @see ua.nure.dominov.SummaryTask4.db.bo.bo.TourBo#updateIsHot(java.lang.String, boolean)
	 */
	@Override
	public final boolean updateIsHot(final String name, final boolean isHot) {
		try (TourDao tourDao = daoFactory.getTourDao()) {
			return tourDao.updateIsHot(name, isHot) > 0;
		} catch (IOException e) {
			LOGGER.error("Issue in daoFactory: " + e);
			return false;
		}
	}
	
}
