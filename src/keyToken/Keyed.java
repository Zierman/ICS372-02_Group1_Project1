package keyToken;

/**
 * An interface that allows an object to be checked to see if a key value
 * matches the object's key value
 * 
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 * @param <Key>
 *            The data type of the key value
 */
public interface Keyed<Key>
{
	public Key getKey();
	

	/**
	 * Checks if the object has a key that matches
	 * 
	 * @param key
	 *            a key value
	 * @return true if they match or false if they do not.
	 */
	public boolean matches(Key key);
	
	public void setKey(Key key);
}
