package ascii_art.commands;

import java.util.HashMap;
import java.util.Map;

import ascii_art.AsciiArtState;

public class CommandFactory {
    private final Map<String, ICommand> commands;

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

    public ICommand getCommand(String commandName) {
        return commands.get(commandName);
    }
}