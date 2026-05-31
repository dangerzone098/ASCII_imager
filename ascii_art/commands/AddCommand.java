package ascii_art.commands;

import ascii_art.AsciiArtState;

public class AddCommand implements ICommand {
    private final AsciiArtState state;

    public AddCommand(AsciiArtState state) {
        this.state = state;
    }

    @Override
    public void execute(String[] parts) {
        if (parts.length < 2 || !state.addChars(parts[1])) {
            System.out.println("Did not add due to incorrect format.");
        }
    }
}