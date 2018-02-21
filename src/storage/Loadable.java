/**
 * 
 */
package storage;

import java.io.IOException;

/**
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 */
public interface Loadable
{
	public void load() throws ClassNotFoundException, IOException;
}
