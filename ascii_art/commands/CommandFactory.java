package ascii_art.commands;

import java.util.HashMap;
import java.util.Map;

import ascii_art.AsciiArtState;

/**
 * Represents the CommandFactory.
 * @author username1, username2
 */
public class CommandFactory {
	private final Map<String, ICommand> commands;

	/**
	 * Constructs CommandFactory.
	 * @param state the state
	 */
	public CommandFactory(AsciiArtState state) {
		this.commands = new HashMap<>();

		commands.put("chars", new CharsCommand(state));
		commands.put("add", new AddCommand(state));
		commands.put("remove", new RemoveCommand(state));
		commands.put("res", new ResCommand(state));
		commands.put("output", new OutputCommand(state));
		commands.put("reverse", new ReverseCommand(state));
		commands.put("asciiArt", new AsciiArtCommand(state));
	}

	/**
	 * Gets command.
	 * @param commandName the commandName
	 * @return the result
	 */
	public ICommand getCommand(String commandName) {
		return commands.get(commandName);
	}
}