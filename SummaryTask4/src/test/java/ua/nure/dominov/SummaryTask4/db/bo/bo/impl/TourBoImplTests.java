/**
 * 
 */
package ua.nure.dominov.SummaryTask4.db.bo.bo.impl;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import ua.nure.dominov.SummaryTask4.db.bo.bo.TourBo;
import ua.nure.dominov.SummaryTask4.db.bo.factory.AbstractBoFactory;
import ua.nure.dominov.SummaryTask4.db.connectionpool.ConnectionPool;
import ua.nure.dominov.SummaryTask4.db.model.Tour;

/**
 * @author calango
 *
 */
public class TourBoImplTests {

	/**
	 * 
	 */
	private static AbstractBoFactory boFactory;
	
	/**
	 * 
	 */
	private static final String DBPROPERTYPATH = "ua/nure/dominov/SummaryTask4/properties/config/H2DbConnectionTests";
	
	/**
	 * 
	 */
	private static final String ANOTHERNAME = "Not test";
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ConnectionPool.init(DBPROPERTYPATH);
		boFactory = AbstractBoFactory.getBoFactory(AbstractBoFactory.SIMPLEDEFAULTFAC);
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		ConnectionPool.dispose();
	}
	
	@Test
	public void testCRUD() {
		final Tour tour = new Tour(Long.MAX_VALUE,"Trip to test", 55, 2,
				"excursion", 3, 3, "***", 4, "active", false, 25, 
					5, 2);
		final TourBo tourBo = boFactory.getTourBo();
		System.out.println("test " + tour.getHotelType().getHotelTypeId() + " " + tour.getHotelType().getTypeName());
		tourBo.insert(tour);
		Tour temp = tourBo.getTourById(tour.getTourId());
		if (!tour.equals(temp)) {
			fail("Different objects");
		}
		tour.setTourName(ANOTHERNAME);
		tourBo.update(tour);
		temp = tourBo.getTourById(tour.getTourId());
		if (!tour.equals(temp)) {
			fail("Different objects after update");
		}
		tour.setMaxDiscount(99);
		tourBo.managerUpdate(tour.getTourId(), tour.getPrice(), tour.getTourStatus().getStatusId(), tour.getMaxDiscount(), tour.getStepDiscount(), tour.getIncrementDiscount());
		temp = tourBo.getTourById(tour.getTourId());
		if (!tour.equals(temp)) {
			fail("Different objects after manager update");
		}
		if (!tourBo.updateClient(tour.getTourName(), 1)) {
			fail("bad client update");
		}
		if (!tourBo.delete(tour.getTourId())) {
			fail("Delete issue");
		}
	}

	/**
	 * Test method for {@link ua.nure.dominov.SummaryTask4.db.bo.bo.impl.TourBoImpl#getFreeTours()}.
	 */
	@Test
	public void testGetFreeTours() {
		final TourBo tourBo = boFactory.
				getTourBo();
		if (tourBo.getFreeTours().size() < 0) {
			fail("Issue in testGetFreeTours");
		}
	}

	/**
	 * Test method for {@link ua.nure.dominov.SummaryTask4.db.bo.bo.impl.TourBoImpl#getBookedTours()}.
	 */
	@Test
	public void testGetBookedTours() {
		final TourBo tourBo = boFactory.
				getTourBo();
		if (tourBo.getBookedTours().size() < 0) {
			fail("No booked tours");
		}
	}

	/**
	 * Test method for {@link ua.nure.dominov.SummaryTask4.db.bo.bo.impl.TourBoImpl#getReservedTours()}.
	 */
	@Test
	public void testGetReservedTours() {
		final TourBo tourBo = boFactory.
				getTourBo();
		if (tourBo.getReservedTours().size() < 0) {
			fail("No free tours");
		}
	}

	/**
	 * Test method for {@link ua.nure.dominov.SummaryTask4.db.bo.bo.impl.TourBoImpl#getTourByUserId(long)}.
	 */
	@Test
	public void testGetTourByUserId() {
		final TourBo tourBo = boFactory.
				getTourBo();
		if (tourBo.getTourByUserId(1).size() < 0) {
			fail("No free tours");
		}
	}

	/**
	 * Test method for {@link ua.nure.dominov.SummaryTask4.db.bo.bo.impl.TourBoImpl#getAllTypesOfHoliday()}.
	 */
	@Test
	public void testGetAllTypesOfHoliday() {
		final TourBo tourBo = boFactory.
				getTourBo();
		if (tourBo.getAllTypesOfHoliday().size() < 0) {
			fail("No Holiday types");
		}
	}

	/**
	 * Test method for {@link ua.nure.dominov.SummaryTask4.db.bo.bo.impl.TourBoImpl#getAllHotelTypes()}.
	 */
	@Test
	public void testGetAllHotelTypes() {
		final TourBo tourBo = boFactory.
				getTourBo();
		if (tourBo.getAllHotelTypes().size() < 0) {
			fail("No Holiday types");
		}
	}

	/**
	 * Test method for {@link ua.nure.dominov.SummaryTask4.db.bo.bo.impl.TourBoImpl#getAllTourStatuses()}.
	 */
	@Test
	public void testGetAllTourStatuses() {
		final TourBo tourBo = boFactory.
				getTourBo();
		if (tourBo.getAllTourStatuses().size() < 0) {
			fail("No Holiday types");
		}
	}

	/**
	 * Test method for {@link ua.nure.dominov.SummaryTask4.db.bo.bo.impl.TourBoImpl#getHolidayTypeId(java.lang.String)}.
	 */
	@Test
	public void testGetHolidayTypeId() {
		final TourBo tourBo = boFactory.
				getTourBo();
		if (!(tourBo.getHolidayTypeId("recreation") == 1)) {
			fail("GetHolidayType error");
		}
	}

	/**
	 * Test method for {@link ua.nure.dominov.SummaryTask4.db.bo.bo.impl.TourBoImpl#getHotelTypeId(java.lang.String)}.
	 */
	@Test
	public void testGetHotelTypeId() {
		final TourBo tourBo = boFactory.
				getTourBo();
		if (!(tourBo.getHotelTypeId("*") == 1)) {
			fail("GetHotelType error");
		}
	}

	/**
	 * Test method for {@link ua.nure.dominov.SummaryTask4.db.bo.bo.impl.TourBoImpl#getStatusId(java.lang.String)}.
	 */
	@Test
	public void testGetStatusId() {
		final TourBo tourBo = boFactory.
				getTourBo();
		if (!(tourBo.getStatusId("active") == 4)) {
			fail("GetHotelType error");
		}
	}
	
	@Test
	public void testUpdateIsHot() {
		final Tour tour = new Tour(Long.MAX_VALUE,"Trip to test", 55, 2,
				"excursion", 3, 3, "***", 4, "active", false, 25, 
					5, 2);
		final TourBo tourBo = boFactory.getTourBo();
		System.out.println("test " + tour.getHotelType().getHotelTypeId() + " " + tour.getHotelType().getTypeName());
		tourBo.insert(tour);
		tour.setHot(true);
		tourBo.updateIsHot(tour.getTourName(), tour.getIsHot());
		final Tour temp = tourBo.getTourById(tour.getTourId());
		if (!tour.equals(temp)) {
			fail("Bad isHot update.");
		}
		tourBo.delete(tour.getTourId());
	}

}
