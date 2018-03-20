package keyToken;

/**
 * An interface that allows an object to be checked to see if a key value
 * matches the object's key value
 * 
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 * @param <KeyType>
 *            The data type of the key value
 */
public interface Keyed<KeyType> extends Matchable<KeyType>
{
	/**
	 * Gets the keyValue of the Keyed object
	 * 
	 * @return the keyValue
	 */
	public KeyType getKey();

	/**
	 * Checks if the object has a key that matches
	 * 
	 * @param key
	 *            a key value
	 * @return true if they match or false if they do not.
	 */
	public boolean matches(KeyType key);

	/**
	 * Sets the key of the object
	 * 
	 * @param key
	 *            to set the key
	 */
	public void setKey(KeyType key);
}
