package keyToken;

public interface Keyed<Key>
{
	public boolean matches(Key key);
}
