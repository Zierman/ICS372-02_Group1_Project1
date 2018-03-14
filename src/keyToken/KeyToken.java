package keyToken;

import java.io.Serializable;

/**
 * A Key value token used to store a key such as an ID number
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 * @param <Type> They type of object this key is used for
 */
public abstract class KeyToken <Type, Key extends Comparable<Key> > implements Serializable, Matchable<KeyToken<Type,Key>>
{
	/**
	 * Serialization version
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The actual key value 
	 */
	private Key keyValue;
	
	/**
	 * Constructs a new Key Value
	 */
	public KeyToken()
	{
		keyValue = getNextValue();
		setLastValue(keyValue);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		return obj instanceof KeyToken && toTestString().equals(((KeyToken<Type, Key>)obj).toTestString());
	}

	public Key getKeyValue()
	{
		return keyValue;
	}
	
	/**
	 * Gets the last key generated
	 * @return a key value
	 */
	protected abstract Key getLastValue();
	
	/**
	 * Gets the next key to be generated.
	 * @return a key value
	 */
	protected abstract Key getNextValue();
	
	/**
	 * Checks if a key value matches this key value
	 * @param value a key value
	 * @return true if they match, else false
	 */
	public boolean matches(Key value)
	{
		return this.keyValue.equals(value);
	}
	
	/**
	 * Checks if a key value matches this key value
	 * @param key a key value
	 * @return true if they match, else false
	 */
	public boolean matches(KeyToken<Type, Key> key)
	{
		return this.keyValue.equals(key);
	}
	
	/**
	 * Sets the key 
	 * @param keyToken a key value
	 */
	public void setKey(KeyToken<Type, Key> keyToken)
	{
		setValue(keyToken.keyValue);
	}
	
	public void setKeyValue(Key keyValue)
	{
		this.keyValue = keyValue;

		
		if(this.keyValue.compareTo(getLastValue()) > 0)
		{
			setLastValue(this.keyValue);
		}
	}
	
	/**
	 * Sets the last key generated
	 * @param key a key value
	 */
	protected abstract void setLastValue(Key key);
	
	/**
	 * Sets the key and
	 * @param key a key value
	 */
	public void setValue(Key key)
	{
		this.keyValue = key;
		
		if(this.keyValue.compareTo(getLastValue()) > 0)
		{
			setLastValue(this.keyValue);
		}
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "" + keyValue;
	}
	
	public String toTestString()
	{
		return getClass().getName() + keyValue;
	}
}
