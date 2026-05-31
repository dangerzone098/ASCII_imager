package ascii_art.commands;

import ascii_art.AsciiArtState;

public class RemoveCommand implements ICommand {
    private final AsciiArtState state;

    public RemoveCommand(AsciiArtState state) {
        this.state = state;
    }

    @Override
    public void execute(String[] parts) {
        if (parts.length < 2 || !state.removeChars(parts[1])) {
            System.out.println("Did not remove due to incorrect format.");
        }
    }
}