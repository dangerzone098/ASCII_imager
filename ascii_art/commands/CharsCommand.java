package ascii_art.commands;

import ascii_art.AsciiArtState;

public class CharsCommand implements ICommand {
    private final AsciiArtState state;

    public CharsCommand(AsciiArtState state) {
        this.state = state;
    }

    @Override
    public void execute(String[] parts) {
        state.printChars();
    }
}