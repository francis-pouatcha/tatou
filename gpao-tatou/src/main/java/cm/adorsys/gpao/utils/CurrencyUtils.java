package cm.adorsys.gpao.utils;

import java.math.BigDecimal;

import cm.adorsys.gpao.model.Company;
import cm.adorsys.gpao.model.Devise;

/**
 * @author clovisgakam
 *
 */
public class CurrencyUtils {

	public static BigDecimal convertAmount(Devise amountCurrency,Devise target,BigDecimal amount){
		if(amountCurrency==null||target==null||amount==null) throw new IllegalArgumentException("all the parameters are required !");
		BigDecimal companyAmount = convertToCompanyCurrency(amountCurrency, amount);
		BigDecimal targetAmount = companyAmount.divide(target.getRatio(),2);
		return targetAmount ;
	}

	public static BigDecimal convertToCompanyCurrency(Devise amountCurrency ,BigDecimal amount){
		if(amountCurrency==null||amount==null) throw new IllegalArgumentException("all the parameters are required !");
		return amount.multiply(amountCurrency.getRatio());
	}

   public static Devise getCompanyCurrency(){
	   return Company.getOwnComapny().getDevise();
   }
}
