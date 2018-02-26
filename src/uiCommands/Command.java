/**
 * 
 */
package uiCommands;

import singleton.Singleton;


/**
 * Interface for Commands
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 * @param <Type> the type of User Interface that the commands are designed for.
 */
public interface Command<Type> extends Singleton<Command<Type>>
{
	/**
	 * Gets a label to show to user describing the command.
	 * @return the label.
	 */
	public String getLabel();

	/**Executes the action associated with the command.
	 * @param ui a user interface
	 * @throws Exception 
	 */
	public void call(Type ui) throws Exception;

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
