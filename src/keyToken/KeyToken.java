package keyToken;

import java.io.Serializable;

/**
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 * @param <Type> They type of object this key is used for
 */
public abstract class KeyToken <Type, Key> implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Key key;
	
	public KeyToken()
	{
		key = getNextKey();
		setLastKey(key);
	}
	
	protected abstract Key getNextKey();
	
	protected abstract Key getLastKey();
	
	protected abstract void setLastKey(Key key);
	
	protected void setKey(Key key)
	{
		this.key = key;
	}
	
	public boolean matches(Key key)
	{
		return this.key.equals(key);
	}
	
	@Override
	public boolean equals(Object obj)
	{
		return obj instanceof KeyToken && toString().equals(obj.toString());
	}
	
	@Override
	public String toString()
	{
		return getClass().getName() + key;
	}
}
