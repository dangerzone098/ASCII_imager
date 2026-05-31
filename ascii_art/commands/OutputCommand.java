package ascii_art.commands;

import ascii_art.AsciiArtState;
import ascii_art.errors.OutputCommandFormatException;

public class OutputCommand implements ICommand {
    private final AsciiArtState state;

    public OutputCommand(AsciiArtState state) {
        this.state = state;
    }

    @Override
    public void execute(String[] parts) throws OutputCommandFormatException {
        if (parts.length < 2) {
            throw new OutputCommandFormatException
                    ("Did not change output method due to incorrect format.");
        }

        if (parts[1].equals("console")) {
            state.setConsoleOutput();
        } else if (parts[1].equals("html")) {
            state.setHtmlOutput();
        } else {
            throw new OutputCommandFormatException
                    ("Did not change output method due to incorrect format.");
        }
    }
}