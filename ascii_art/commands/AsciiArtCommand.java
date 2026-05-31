package ascii_art.commands;

import ascii_art.AsciiArtState;

public class AsciiArtCommand implements ICommand {
    private final AsciiArtState state;

    public AsciiArtCommand(AsciiArtState state) {
        this.state = state;
    }

    @Override
    public void execute(String[] parts) {
        state.runAsciiArt();
    }
}