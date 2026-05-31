package ascii_art.errors;

public class UndersizedCharsetException extends Exception{

	public UndersizedCharsetException(){super();}

	/**
	 * Constructs UndersizedCharsetException.
	 * @param message the message
	 */
	public UndersizedCharsetException(String message){
		super(message);
	}
}
