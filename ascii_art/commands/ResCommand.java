package ascii_art.commands;

import ascii_art.AsciiArtState;
import ascii_art.errors.ResolutionCommandException;
import ascii_art.errors.ExceededBoundariesException;

public class ResCommand implements ICommand {
    private final AsciiArtState state;

    public ResCommand(AsciiArtState state) {
        this.state = state;
    }

    @Override
    public void execute(String[] parts) throws ResolutionCommandException, ExceededBoundariesException {
        if (parts.length == 1) {
            state.printResolution();
            return;
        }

        if (!state.changeResolution(parts[1])) {
            throw new ResolutionCommandException("Did not change resolution due to incorrect format.");
        }
        state.printResolution();
    }
}