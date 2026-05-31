package ascii_art;

import ascii_art.commands.CommandFactory;
import ascii_art.commands.ICommand;
import ascii_art.errors.AddCommandException;
import image.Image;

import java.io.IOException;

/**
 * Interactive command-line interface for the ASCII art application.
 */
public class Shell {
	private static final String PROMPT = ">>> ";

    /**
     * default contructor
     */
    public Shell() {}

	/**
	 * Executes run.
	 * @param imageName the imageName
	 */
	public void run(String imageName) {
		Image image;

		try {
			image = new Image(imageName);
		/**
		 * Executes catch.
		 * @param e the e
		 * @return the result
		 */
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return;
		}

		AsciiArtState state = new AsciiArtState(image);
		CommandFactory commandFactory = new CommandFactory(state);

		while (true) {
			System.out.print(PROMPT);
			String input = KeyboardInput.readLine();

			if (input.isEmpty()) {
				System.out.println("Did not execute due to incorrect command.");
				continue;
			}

			String[] parts = input.split("\\s+");

			if (parts[0].equals("exit")) {
				return;
			}

			ICommand command = commandFactory.getCommand(parts[0]);

			if (command == null) {
				System.out.println("Did not execute due to incorrect command.");
				continue;
			}
			try{
				command.execute(parts);
			/**
			 * Executes catch.
			 * @param e the e
			 * @return the result
			 */
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}
	}

	/**
	 * Executes main.
	 * @param args the args
	 */
	public static void main(String[] args) {
		Shell shell = new Shell();
		shell.run(args[0]);
	}
}