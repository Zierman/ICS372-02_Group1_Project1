/**
 * 
 */
package userInterface;

import singleton.Singleton;

/**
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 */
public interface Command extends Singleton
{
	public String getLabel();
	public void setLabel(String label);
	public void call();
}
