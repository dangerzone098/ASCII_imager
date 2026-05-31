package ascii_art.commands;

import ascii_art.AsciiArtState;
import ascii_art.errors.AddCommandException;

public class AddCommand implements ICommand {
    private final AsciiArtState state;

    public AddCommand(AsciiArtState state) {
        this.state = state;
    }

    @Override
    public void execute(String[] parts) throws AddCommandException {
        if (parts.length < 2 || !state.addChars(parts[1])) {
            throw new AddCommandException("Did not add due to incorrect format.");
        }
    }
}