package keyToken;

/**
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 * @param <Type> They type of object this key is used for
 */
public class KeyToken <Type>
{
	private long id;
	private static long lastID = Long.MIN_VALUE;
	
	public KeyToken()
	{
		id = ++ lastID;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		return obj instanceof KeyToken && toString().equals(obj.toString());
	}
	
	@Override
	public String toString()
	{
		return getClass().getName() + id;
	}
}
