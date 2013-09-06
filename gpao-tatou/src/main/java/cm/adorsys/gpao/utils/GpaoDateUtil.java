package cm.adorsys.gpao.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

public class GpaoDateUtil {

	public static final String DATE_PATTERN_LONG = "dd-MM-yyyy";
	public static final String DATE_PATTERN_TRIM = "ddMMyy";
	public static final String DATE_PATTERN_LONG2 = "yyyy-MM-dd";

	public static final String DATE_PATTERN_LONG_LIT = "EEE, dd MMM yyyy";
	public static final String DATE_PATTERN_SHORT = "dd-MM";
	public static final String DATE_PATTERN_SHORT_LIT = "EEE, dd MMM";

	public static final String DATETIME_PATTERN_LONG = "dd-MM-yyyy hh:mm";
	public static final String DATETIME_PATTERN_LONG_LIT = "EEE, dd MMM yyyy HH:mm";
	public static final String DATETIME_PATTERN_SHORT = "dd-MM HH:mm";
	public static final String DATETIME_PATTERN_SHORT_LIT = "HH:mm EEE, dd MMM";

	public static final String DATETIME_PATTERN_LONG_SEC = "dd-MM-yyyy HH:mm:ss";
	public static final String DATETIME_PATTERN_LONG_LIT_SEC = "EEE, dd MMM yyyy HH:mm:ss";
	public static final String DATETIME_PATTERN_SHORT_SEC = "dd-MM HH:mm:ss";
	public static final String DATETIME_PATTERN_SHORT_LIT_SEC = "HH:mm:ss EEE, dd MMM";
	public static final String DATE_PATTERN_YEAR = "yyyy";

	public static final String dateToString(Date date, String pattern){
		if (date == null) {
			return new SimpleDateFormat(pattern).format(new Date());
		}else {
			return new SimpleDateFormat(pattern).format(date);

		}
	}

	public static final Date stringToDate(String date, String pattern){
		try {
			return new SimpleDateFormat(pattern).parse(date);
		} catch (ParseException e) {

			return new DateUtils().addYears(new Date(), 0);
		}
	}


	public static Date getBeginDayDate(){
		Date date = new Date();
		String stringDate = dateToString(date, GpaoDateUtil.DATE_PATTERN_LONG);
		stringDate = stringDate + " 00:00" ;
		return stringToDate(stringDate, DATETIME_PATTERN_LONG);
	}

	public static Date getEndDayDate(){
		Date date = new Date();
		String stringDate = dateToString(date, GpaoDateUtil.DATE_PATTERN_LONG);
		stringDate = stringDate + " 23:59" ;
		return stringToDate(stringDate, DATETIME_PATTERN_LONG);
	}
	
	public static String getYear(){
		return dateToString(new Date(), DATE_PATTERN_YEAR);
	}


}
