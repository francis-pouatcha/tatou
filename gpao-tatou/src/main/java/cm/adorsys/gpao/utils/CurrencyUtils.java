package cm.adorsys.gpao.utils;

import java.math.BigDecimal;

import cm.adorsys.gpao.model.Devise;

public class CurrencyUtils {

	public static BigDecimal convertAmount(Devise amountCurrency,Devise targetCurrency,BigDecimal amount){
		if(amountCurrency==null||targetCurrency==null||amount==null) throw new IllegalArgumentException("all the parameters are required !");
		BigDecimal companyAmount = convertToCompanyCurrency(amountCurrency, amount);
		BigDecimal targetAmount = companyAmount.divide(targetCurrency.getRatio(),2);
		return targetAmount ;
	}

	public static BigDecimal convertToCompanyCurrency(Devise amountCurrency ,BigDecimal amount){
		if(amountCurrency==null||amount==null) throw new IllegalArgumentException("all the parameters are required !");
		return amount.multiply(amountCurrency.getRatio());
	}


}
