package cm.adorsys.gpao.model.excepions;

/**
 * An exception class throwed when a programm require an entity,
 * instead the one we encouter is either null, or have a wrong value (state).
 * 
 * @author bwa
 *
 */
public class InvalidEntityValueException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * @param message
	 */
	public InvalidEntityValueException(String message) {
		super(message);
	}

}
