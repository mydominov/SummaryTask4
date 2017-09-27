/**
 * 
 */
package ua.nure.dominov.SummaryTask4.util;

/**
 * @author calango
 *
 */
public final class MoneyEmountCounter {

	/**
	 * 
	 */
	private static final int MAXIMUMPERCENTAGE = 100;
	
	/**
	 * 
	 */
	private MoneyEmountCounter() {
		super();
	}
	
	/**
	 * @param price
	 * @param numberOfPurchases
	 * @param maxDiscount
	 * @param stepDiscount
	 * @param incrementDiscount
	 * @return
	 */
	public static long countPrice(final long price, final long numberOfPurchases,
			final int maxDiscount, final int stepDiscount, 
				final int incrementDiscount) {
		if (stepDiscount == 0) {
			return price;
		}
		long amount = (long) Math.floor(
				(numberOfPurchases * incrementDiscount) / stepDiscount);
		if (amount > maxDiscount) {
			amount = maxDiscount;
		}
		return  price - (long) Math.floor((price * amount) / MAXIMUMPERCENTAGE);
	}
	
}
