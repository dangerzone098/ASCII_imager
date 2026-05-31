package ascii_art.commands;

import ascii_art.AsciiArtState;

/**
 * Represents the CharsCommand.
 * @author username1, username2
 */
public class CharsCommand implements ICommand {
	private final AsciiArtState state;

	/**
	 * Constructs CharsCommand.
	 * @param state the state
	 */
	public CharsCommand(AsciiArtState state) {
		this.state = state;
	}

	@Override
	/**
	 * Executes execute.
	 * @param parts the parts
	 */
	public void execute(String[] parts) {
		state.printChars();
	}
}