package ascii_art.commands;

import ascii_art.AsciiArtState;
import ascii_art.errors.UndersizedCharsetException;

public class AsciiArtCommand implements ICommand {
	private final AsciiArtState state;

	/**
	 * Constructs AsciiArtCommand.
	 * @param state the state
	 */
	public AsciiArtCommand(AsciiArtState state) {
		this.state = state;
	}

	@Override
	/**
	 * Executes execute.
	 * @param parts the parts
	 * @throws UndersizedCharsetException if an error occurs
	 */
	public void execute(String[] parts) throws UndersizedCharsetException {
		state.runAsciiArt();
	}
}