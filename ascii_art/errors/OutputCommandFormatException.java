package ascii_art.errors;

/**
 * Represents the OutputCommandFormatException.
 * @author username1, username2
 */
public class OutputCommandFormatException extends Exception{
	/**
	 * defulat contrcutor
	 */
	public OutputCommandFormatException(){super();}

	/**
	 * Constructs OutputCommandFormatException.
	 * @param message the message
	 */
	public OutputCommandFormatException(String message){
		super(message);
	}
}
