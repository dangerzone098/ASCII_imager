package ascii_art.commands;

/**
 * Represents the ICommand.
 * @author username1, username2
 */
public interface ICommand {
	/**
	 * .
	 * @param parts
	 * @throws Exception
	 */
	void execute(String[] parts) throws Exception;
}