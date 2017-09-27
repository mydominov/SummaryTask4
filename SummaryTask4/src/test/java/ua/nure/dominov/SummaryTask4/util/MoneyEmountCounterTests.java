/**
 * 
 */
package ua.nure.dominov.SummaryTask4.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author calango
 *
 */
public class MoneyEmountCounterTests {

	//Test 1
	private final static long ANSWER1 = 155;
	
	private final static long PRICE1 = 155;
	private final static long NUMBER_OF_PURCHASES1 = 5;
	private final static int MAX_DISCOUNT1 = 340;
	private final static int STEP_DISCOUNT1 = 0;
	private final static int INCREMENT_DISCOUNT1 = 60;

	//Test 2
	private final static long ANSWER2 = 188;
	
	private final static long PRICE2 = 200;
	private final static long NUMBER_OF_PURCHASES2 = 15;
	private final static int MAX_DISCOUNT2 = 35;
	private final static int STEP_DISCOUNT2 = 5;
	private final static int INCREMENT_DISCOUNT2 = 2;
	
	//Test 2
	private final static long ANSWER3 = 160;
		
	private final static long PRICE3 = 200;
	private final static long NUMBER_OF_PURCHASES3 = 15;
	private final static int MAX_DISCOUNT3 = 20;
	private final static int STEP_DISCOUNT3 = 5;
	private final static int INCREMENT_DISCOUNT3 = 10;
		
	/**
	 * Test method for {@link ua.nure.dominov.SummaryTask4.util.MoneyEmountCounter#countPrice(long, long, short, short, short)}.
	 */
	@Test
	public void testCountPrice1() {
		assertEquals(ANSWER1, MoneyEmountCounter.countPrice(PRICE1,
				NUMBER_OF_PURCHASES1, MAX_DISCOUNT1, STEP_DISCOUNT1, 
					INCREMENT_DISCOUNT1));
	}

	/**
	 * Test method for {@link ua.nure.dominov.SummaryTask4.util.MoneyEmountCounter#countPrice(long, long, short, short, short)}.
	 */
	@Test
	public void testCountPrice2() {
		assertEquals(ANSWER2, MoneyEmountCounter.countPrice(PRICE2,
				NUMBER_OF_PURCHASES2, MAX_DISCOUNT2, STEP_DISCOUNT2, 
					INCREMENT_DISCOUNT2));
	}
	
	/**
	 * Test method for {@link ua.nure.dominov.SummaryTask4.util.MoneyEmountCounter#countPrice(long, long, short, short, short)}.
	 */
	@Test
	public void testCountPrice3() {
		assertEquals(ANSWER3, MoneyEmountCounter.countPrice(PRICE3,
				NUMBER_OF_PURCHASES3, MAX_DISCOUNT3, STEP_DISCOUNT3, 
					INCREMENT_DISCOUNT3));
	}
	
}
