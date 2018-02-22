/**
 * 
 */
package uiCommands;

import singleton.Singleton;

/**
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 */
public interface Command<Type> extends Singleton<Command<Type>>
{
	public String getLabel();

	public void call(Type arg);

	public boolean isDataCommand();

	public boolean isTerminationCommand();
}
