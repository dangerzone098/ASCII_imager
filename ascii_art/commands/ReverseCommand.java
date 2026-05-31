package ascii_art.commands;

import ascii_art.AsciiArtState;

public class ReverseCommand implements ICommand {
	private final AsciiArtState state;

	/**
	 * Constructs ReverseCommand.
	 * @param state the state
	 */
	public ReverseCommand(AsciiArtState state) {
		this.state = state;
	}

	@Override
	/**
	 * Executes execute.
	 * @param parts the parts
	 */
	public void execute(String[] parts) {
		state.toggleReverse();
	}
}