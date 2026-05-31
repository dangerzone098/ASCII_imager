package ascii_art.errors;

/**
 * Represents the UndersizedCharsetException.
 * @author username1, username2
 */
public class UndersizedCharsetException extends Exception{

	/**
	 * default Contrcutor
	 */
	public UndersizedCharsetException(){super();}

	/**
	 * Constructs UndersizedCharsetException.
	 * @param message the message
	 */
	public UndersizedCharsetException(String message){
		super(message);
	}
}
