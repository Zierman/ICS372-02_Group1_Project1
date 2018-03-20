package keyToken;

import java.util.List;

import exceptions.NoKeyTokenFoundException;

/**
 * A list of {@link keyToken.Keyed} objects.
 * 
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 * @param <KeyedType>
 *            The type of the object in the list.
 * @param <KeyType>
 *            The data type of the key values.
 */
public interface KeyedList<KeyedType extends Keyed<KeyType>, KeyType>
		extends List<KeyedType>
{
	/**
	 * Looks for an object in the list with a key that matches.
	 * 
	 * @param key
	 *            the key value that is to be matched by the returned object's
	 *            key.
	 * @return the matched object.
	 * @throws NoKeyTokenFoundException
	 *             if no key is found during the search.
	 */
	public KeyedType findMatched(KeyType key) throws NoKeyTokenFoundException;

	/**
	 * Gets the last key
	 * 
	 * @return the key value of the last key
	 */
	public KeyType getLastKey();

	/**
	 * Looks for and removes an object in the list with a key that matches.
	 * 
	 * @param key
	 *            the key value that is to be matched by the returned object's
	 *            key.
	 * @throws NoKeyTokenFoundException
	 *             if no key is found during the search.
	 */
	public void removeMatched(KeyType key) throws NoKeyTokenFoundException;

	/**
	 * Sets last key
	 * 
	 * @param keyValue
	 *            the key to set the last key's keyValue to.
	 */
	public void setLastKey(KeyType keyValue);
}
