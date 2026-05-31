package ascii_art.commands;

import ascii_art.AsciiArtState;
import ascii_art.errors.RemoveCommandException;

public class RemoveCommand implements ICommand {
    private final AsciiArtState state;

    public RemoveCommand(AsciiArtState state) {
        this.state = state;
    }

    @Override
    public void execute(String[] parts) throws RemoveCommandException {
        if (parts.length < 2 || !state.removeChars(parts[1])) {
            throw new RemoveCommandException("Did not remove due to incorrect format.");
        }
    }
}