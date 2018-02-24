/**
 * 
 */
package storage;

import java.io.IOException;

//TODO document all of this
/**
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 */
public interface Loadable
{
	public void load() throws ClassNotFoundException, IOException;
}
