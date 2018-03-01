/**
 * 
 */
package uiCommands;

import singleton.ReadResolveable;


/**
 * Interface for Commands
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 * @param <Type> the type of User Interface that the commands are designed for.
 */
public interface Command<Type> extends ReadResolveable<Command<Type>>
{
	/**Executes the action associated with the command.
	 * @param ui a user interface
	 */
	public void call(Type ui);

	/**
	 * Gets a label to show to user describing the command.
	 * @return the label.
	 */
	public String getLabel();

	/**
	 * Checks to see if the command accesses data.
	 * @return true if the command accesses data, else false.
	 */
	public boolean isDataCommand();

	/**
	 * Checks to see if the command terminates the application.
	 * @return true if the command is supposed to close the application.
	 */
	public boolean isTerminationCommand();
	
}
