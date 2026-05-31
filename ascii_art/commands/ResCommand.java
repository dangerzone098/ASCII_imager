package ascii_art.commands;

import ascii_art.AsciiArtState;
import ascii_art.errors.ResolutionCommandException;
import ascii_art.errors.ExceededBoundariesException;

public class ResCommand implements ICommand {
	private static final String INCORRECT_FORMAT_MSG = "Did not change resolution due to incorrect format.";
	private static final String UP_ARG = "up";
	private static final String DOWN_ARG = "down";
	private final AsciiArtState state;

	/**
	 * Constructs ResCommand.
	 * @param state the state
	 */
	public ResCommand(AsciiArtState state) {
		this.state = state;
	}

	@Override
	/**
	 * Executes execute.
	 * @param parts the parts
	 * @throws ResolutionCommandException if an error occurs
	 * @throws ExceededBoundariesException if an error occurs
	 */
	public void execute(String[] parts) throws ResolutionCommandException, ExceededBoundariesException {
		if (parts.length == 1) {
			state.printResolution();
			return;
		}

		if (!parts[1].equals(UP_ARG) && !parts[1].equals(DOWN_ARG)) {
			throw new ResolutionCommandException(INCORRECT_FORMAT_MSG);
		}

		if (!state.changeResolution(parts[1])) {
			throw new ResolutionCommandException(INCORRECT_FORMAT_MSG);
		}
		state.printResolution();
	}
}