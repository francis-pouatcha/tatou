package cm.adorsys.gpao.utils;


/**
 * @author clovisgakam
 *
 */
public class GpaoSequenceGenerator {
	public static String PORCHASE_SEQUENCE_PREFIX = "CD";
	public static String DELIVERY_SEQUENCE_PREFIX = "LV";
	public static String LOT_SEQUENCE_PREFIX = "LO";
	public static String ARTICLE_SEQUENCE_PREFIX = "AR";
	public static String INVENTORY_SEQUENCE_PREFIX = "IN";
	public static String TENDER_SEQUENCE_PREFIX = "AP";


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
