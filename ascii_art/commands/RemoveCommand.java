package ascii_art.commands;

import ascii_art.AsciiArtState;
import ascii_art.errors.RemoveCommandException;

public class RemoveCommand implements ICommand {
	private static final String INCORRECT_FORMAT_MSG = "Did not remove due to incorrect format.";
	private final AsciiArtState state;

	/**
	 * Constructs RemoveCommand.
	 * @param state the state
	 */
	public RemoveCommand(AsciiArtState state) {
		this.state = state;
	}

	@Override
	/**
	 * Executes execute.
	 * @param parts the parts
	 * @throws RemoveCommandException if an error occurs
	 */
	public void execute(String[] parts) throws RemoveCommandException {
		if (parts.length < 2 || !state.removeChars(parts[1])) {
			throw new RemoveCommandException(INCORRECT_FORMAT_MSG);
		}
	}
}