/**
 * 
 */
package storage;

import java.io.IOException;

/**
 * An interface for objects that can be written to storage with a save method call.
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 */
public interface Savable
{
	/** Saves an object to a file.
	 * @throws IOException if there is a problem with FileIO.
	 */
	public void save() throws IOException;
}
