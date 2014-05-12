package cm.adorsys.gpao.utils;


/**
 * @author clovisgakam
 * @author bwa
 */
public class GpaoSequenceGenerator {
	public static final String PORCHASE_SEQUENCE_PREFIX = "CF";
	public static final String DELIVERY_SEQUENCE_PREFIX = "LV";
	public static final String SUPPLY_SEQUENCE_PREFIX = "AP";
	public static final String LOT_SEQUENCE_PREFIX = "LO";
	public static final String ARTICLE_SEQUENCE_PREFIX = "AR";
	public static final String INVENTORY_SEQUENCE_PREFIX = "IN";
	public static final String TENDER_SEQUENCE_PREFIX = "AP";
	public static final String CUSTOMER_ORDER_SEQUENCE_PREFIX = "CO";
	public static final String MANUFACTURINGVOUCHER_SEQUENSE_PREFIX = "MF";
	public static final String RAWMATERIAL_SEQUENSE_PREFIX = "RM";
	public static final String RAWMATERIAL_DELIVERY_NOTE_SEQUENCE_PREFIX = "RDN";
	public static final String PRODUCTION_SEQUENSE_PREFIX = "PROD";


	public static String getSequence(Long index ,String prefixe){
		String sequence  = prefixe+"-"+GpaoDateUtil.getMonthYear() +"-"+ formatNumber(index.toString(), 4) ;
		return sequence;
	}
	
	public static String getSequenceWhitoutDate(Long index ,String prefixe){
		String sequence  = prefixe+"/"+ formatNumber(index.toString(), 4) ;
		return sequence;
	}


	private static String formatNumber(String stringToFormat , int patern){
		String format  = null ;
		if (stringToFormat.length() > patern-1) {
			return stringToFormat ;

		}else {

			format = new StringBuilder().append(getZero(patern-stringToFormat.length()-1)).append(stringToFormat).toString();
		}
		return format.trim();
	}

	private  static String getZero(int nbOfZero){
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i <= nbOfZero; i++) {
			stringBuilder.append(0);

		}
		return stringBuilder.toString();
	}
}
