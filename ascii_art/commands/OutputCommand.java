package ascii_art.commands;

import ascii_art.AsciiArtState;
import ascii_art.errors.OutputCommandFormatException;

public class OutputCommand implements ICommand {
	private static final String INCORRECT_FORMAT_MSG = "Did not change output method due to incorrect format.";
	private static final String CONSOLE_ARG = "console";
	private static final String HTML_ARG = "html";
	private final AsciiArtState state;

	/**
	 * Constructs OutputCommand.
	 * @param state the state
	 */
	public OutputCommand(AsciiArtState state) {
		this.state = state;
	}

	@Override
	/**
	 * Executes execute.
	 * @param parts the parts
	 * @throws OutputCommandFormatException if an error occurs
	 */
	public void execute(String[] parts) throws OutputCommandFormatException {
		if (parts.length < 2) {
			throw new OutputCommandFormatException(INCORRECT_FORMAT_MSG);
		}

		if (parts[1].equals(CONSOLE_ARG)) {
			state.setConsoleOutput();
		} else if (parts[1].equals(HTML_ARG)) {
			state.setHtmlOutput();
		} else {
			throw new OutputCommandFormatException(INCORRECT_FORMAT_MSG);
		}
	}
}