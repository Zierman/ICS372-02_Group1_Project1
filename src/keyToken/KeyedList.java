package keyToken;

import java.util.List;

public interface KeyedList<Type extends Keyed<Key>, Key> extends List<Type>
{
	public Type findMatched(Key key) throws NoKeyTokenFoundException;

	public void removeMatched(Key key) throws NoKeyTokenFoundException;
}
