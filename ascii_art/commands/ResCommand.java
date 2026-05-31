package ascii_art.commands;

import ascii_art.AsciiArtState;

public class ResCommand implements ICommand {
    private final AsciiArtState state;

    public ResCommand(AsciiArtState state) {
        this.state = state;
    }

    @Override
    public void execute(String[] parts) {
        if (parts.length == 1) {
            state.printResolution();
            return;
        }

        try {
            if (!state.changeResolution(parts[1])) {
                System.out.println("Did not change resolution due to incorrect format.");
                return;
            }

            state.printResolution();
        } catch (IllegalArgumentException e) {
            System.out.println("Did not change resolution due to exceeding boundaries.");
        }
    }
}