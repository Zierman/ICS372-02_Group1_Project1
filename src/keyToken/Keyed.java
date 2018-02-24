package keyToken;

public interface Keyed<Key>
{
	/**
	 * Checks if the object has a key that matches
	 * @param key a key value
	 * @return true if they match or false if they do not. 
	 */
	public boolean matches(Key key);
}
