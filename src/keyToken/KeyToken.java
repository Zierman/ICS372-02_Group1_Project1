package keyToken;

import java.io.Serializable;

/**
 * A Key value token used to store a key such as an ID number
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 * @param <Type> They type of object this key is used for
 */
public abstract class KeyToken <Type, Key> implements Serializable
{
	/**
	 * Serilization version
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The actual key value 
	 */
	private Key key;
	
	/**
	 * Constructs a new Key Value
	 */
	public KeyToken()
	{
		key = getNextKey();
		setLastKey(key);
	}
	
	/**
	 * Gets the next key to be generated.
	 * @return a key value
	 */
	protected abstract Key getNextKey();
	
	/**
	 * Gets the last key generated
	 * @return a key value
	 */
	protected abstract Key getLastKey();
	
	/**
	 * Sets the last key generated
	 * @param key a key value
	 */
	protected abstract void setLastKey(Key key);
	
	/**
	 * Sets the key 
	 * @param key a key value
	 */
	protected void setKey(Key key)
	{
		this.key = key;
	}
	
	/**
	 * Checks if a key value matches this key value
	 * @param key a key value
	 * @return true if they match, else false
	 */
	public boolean matches(Key key)
	{
		return this.key.equals(key);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		return obj instanceof KeyToken && toString().equals(obj.toString());
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return getClass().getName() + key;
	}
}
