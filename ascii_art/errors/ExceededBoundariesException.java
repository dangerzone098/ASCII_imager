package ascii_art.errors;

public class ExceededBoundariesException extends Exception {

	/**
	 * Constructs ExceededBoundariesException.
	 * @param message the message
	 */
	public ExceededBoundariesException(String message){
		super(message);
	}
}
