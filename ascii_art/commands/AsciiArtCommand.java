package ascii_art.commands;

import ascii_art.AsciiArtState;
import ascii_art.errors.UndersizedCharsetException;

public class AsciiArtCommand implements ICommand {
    private final AsciiArtState state;

    public AsciiArtCommand(AsciiArtState state) {
        this.state = state;
    }

    @Override
    public void execute(String[] parts) throws UndersizedCharsetException {
        state.runAsciiArt();
    }
}