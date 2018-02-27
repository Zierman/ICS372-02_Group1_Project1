package keyToken;

import java.util.List;

import exceptions.NoKeyTokenFoundException;

/**
 * A list of {@link keyToken.Keyed} objects.
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 * @param <Type> The type of the object in the list.
 * @param <Key> The data type of the key values.
 */
public interface KeyedList<Type extends Keyed<Key>, Key> extends List<Type>
{
	/**
	 * Looks for an object in the list with a key that matches.
	 * @param key the key value that is to be matched by the returned object's key.
	 * @return the matched object.
	 * @throws NoKeyTokenFoundException if no key is found during the search.
	 */
	public Type findMatched(Key key) throws NoKeyTokenFoundException;

	/**
	 * Looks for and removes an object in the list with a key that matches.
	 * @param key the key value that is to be matched by the returned object's key.
	 * @throws NoKeyTokenFoundException if no key is found during the search.
	 */
	public void removeMatched(Key key) throws NoKeyTokenFoundException;
	
	public Key getLastKey();
	
	public void setLastKey(Key key);
}
