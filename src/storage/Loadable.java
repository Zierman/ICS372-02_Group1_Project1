/**
 * 
 */
package storage;

import java.io.IOException;

/** Interface for objects that can be read from storage with a load method call.
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 */
public interface Loadable
{
	/** load the object from storage
	 * @throws ClassNotFoundException if there is a problem with a class not being found
	 * @throws IOException If there is an issue with the FileIO.
	 */
	public void load() throws ClassNotFoundException, IOException;
	
	/**
	 * Checks to see if the object can be loaded.
	 * @return true if the object can be loaded, else false.
	 */
	public boolean canLoad();
}
