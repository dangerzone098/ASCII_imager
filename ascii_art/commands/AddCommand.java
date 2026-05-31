package ascii_art.commands;

import ascii_art.AsciiArtState;
import ascii_art.errors.AddCommandException;

public class AddCommand implements ICommand {
	private static final String INCORRECT_FORMAT_MSG = "Did not add due to incorrect format.";
	private final AsciiArtState state;

	/**
	 * Constructs AddCommand.
	 * @param state the state
	 */
	public AddCommand(AsciiArtState state) {
		this.state = state;
	}

	@Override
	/**
	 * Executes execute.
	 * @param parts the parts
	 * @throws AddCommandException if an error occurs
	 */
	public void execute(String[] parts) throws AddCommandException {
		if (parts.length < 2 || !state.addChars(parts[1])) {
			throw new AddCommandException(INCORRECT_FORMAT_MSG);
		}
	}
}