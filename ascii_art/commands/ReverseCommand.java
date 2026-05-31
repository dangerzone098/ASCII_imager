package ascii_art.commands;

import ascii_art.AsciiArtState;

public class ReverseCommand implements ICommand {
    private final AsciiArtState state;

    public ReverseCommand(AsciiArtState state) {
        this.state = state;
    }

    @Override
    public void execute(String[] parts) {
        state.toggleReverse();
    }
}