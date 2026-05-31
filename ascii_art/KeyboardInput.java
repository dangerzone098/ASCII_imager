package ascii_art;

import java.util.Scanner;

/**
 * Represents the KeyboardInput.
 * @author username1, username2
 */
class KeyboardInput {
	private static ascii_art.KeyboardInput keyboardInputObject = null;
	private Scanner scanner;

	/* 
	 * Constructs KeyboardInput.
	 */
	private KeyboardInput() {
		this.scanner = new Scanner(System.in);
	}

	/**
	 * Gets object.
	 * @return the result
	 */
	public static ascii_art.KeyboardInput getObject() {
		if(ascii_art.KeyboardInput.keyboardInputObject == null)
		{
			ascii_art.KeyboardInput.keyboardInputObject = new ascii_art.KeyboardInput();
		}
		return ascii_art.KeyboardInput.keyboardInputObject;
	}

	public static String readLine()
	{
		return ascii_art.KeyboardInput.getObject().scanner.nextLine().trim();
	}
}