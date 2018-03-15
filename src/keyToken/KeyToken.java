package keyToken;

import java.io.Serializable;

/**
 * A Key value token used to store a key such as an ID number
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 * @param <KeyedType> They type of object this key is used for
 */
public abstract class KeyToken <KeyedType, KeyType extends Comparable<KeyType> > implements Serializable, Matchable<KeyToken<KeyedType,KeyType>>
{
	/**
	 * Serialization version
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The actual key value 
	 */
	private KeyType keyValue;
	
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
		return obj instanceof KeyToken && toTestString().equals(((KeyToken<KeyedType, KeyType>)obj).toTestString());
	}

	public KeyType getKeyValue()
	{
		return keyValue;
	}
	
	/**
	 * Gets the last key generated
	 * @return a key value
	 */
	protected abstract KeyType getLastValue();
	
	/**
	 * Gets the next key to be generated.
	 * @return a key value
	 */
	protected abstract KeyType getNextValue();
	
	/**
	 * Checks if a key value matches this key value
	 * @param key a key value
	 * @return true if they match, else false
	 */
	public boolean matches(KeyToken<KeyedType, KeyType> key)
	{
		return this.keyValue.equals(key.getKeyValue());
	}
	
	/**
	 * Checks if a key value matches this key value
	 * @param value a key value
	 * @return true if they match, else false
	 */
	public boolean matches(KeyType value)
	{
		return this.keyValue.equals(value);
	}
	
	/**
	 * Sets the key 
	 * @param keyToken a key value
	 */
	public void setKey(KeyToken<KeyedType, KeyType> keyToken)
	{
		setValue(keyToken.keyValue);
	}
	
	/**
	 * Sets the key value of this object
	 * @param keyValue the new key value to be set
	 */
	public void setKeyValue(KeyType keyValue)
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
	protected abstract void setLastValue(KeyType key);
	
	/**
	 * Sets the key and
	 * @param key a key value
	 */
	public void setValue(KeyType key)
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
	
	/**
	 * Converts to a special string that allows for comparison
	 * @return a specially formatted string
	 */
	public String toTestString()
	{
		return getClass().getName() + keyValue;
	}
}
