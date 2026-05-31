package ascii_art.commands;

import ascii_art.AsciiArtState;

public class OutputCommand implements ICommand {
    private final AsciiArtState state;

    public OutputCommand(AsciiArtState state) {
        this.state = state;
    }

    @Override
    public void execute(String[] parts) {
        if (parts.length < 2) {
            System.out.println("Did not change output method due to incorrect format.");
            return;
        }

        if (parts[1].equals("console")) {
            state.setConsoleOutput();
        } else if (parts[1].equals("html")) {
            state.setHtmlOutput();
        } else {
            System.out.println("Did not change output method due to incorrect format.");
        }
    }
}