package ascii_art.errors;

public class OutputCommandFormatException extends Exception{

	public OutputCommandFormatException(){super();}

	/**
	 * Constructs OutputCommandFormatException.
	 * @param message the message
	 */
	public OutputCommandFormatException(String message){
		super(message);
	}
}
