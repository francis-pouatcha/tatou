package cm.adorsys.gpao.utils;

import java.math.BigDecimal;

public class TaxeUtils {
	/**
	 * <p>the bigdecimal representation of 100.</p>
	 */
	public static BigDecimal HUNDRED = new BigDecimal(100);
	/**
	 * <p>the taxe value of an amount. </p> 
	 * <b>Example </b> <br />
	 * <p>
	 * <code>
	 * tva = 19.5% ; <br />
	 * computedTaxe = (amountHt*tva/100) </code></p>
	 * @param amountHt
	 * @param taxeValue
	 * @return the taxe value of amountHt
	 */
	public static BigDecimal computeTaxeByPercentage(BigDecimal amountHt,BigDecimal taxeValue) {
		BigDecimal taxe = amountHt.multiply(taxeValue).divide(TaxeUtils.HUNDRED);
		return taxe;
	}
	/**
	 * <p>
	 * 	Compute taxe per value of an amount </p>
	 * 	<b>Example</b> <br />
	 * <p>
	 * 	<code>
	 * 	tva = 0.19 ; <br />
	 * 	computedTaxe = amountHt*tva;
	 * </code>
	 * </p>
	 * @param amountHt
	 * @param taxeValue
	 * @return
	 */
	public static BigDecimal computeTaxeByValue(BigDecimal amountHt,BigDecimal taxeValue) {
		return amountHt.multiply(taxeValue);
	}
}
