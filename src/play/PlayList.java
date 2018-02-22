package play;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import singleton.Singleton;
import storage.FileIO;
import storage.Loadable;
import storage.Savable;

/**
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 */
public class PlayList implements Singleton<PlayList>, List<Play>, Savable, Loadable
{
	private static PlayList singleton;
	protected static final String FILENAME = "plays.dat";
	private LinkedList<Play> plays = new LinkedList<Play>();

	protected PlayList() throws Exception
	{
		if (getClass().getName().equals("PlayList"))
		{
			throw new Exception();
		}
	}

	private PlayList(int i)
	{
	}

	public static PlayList instance()
	{
		if (singleton == null)
		{
			singleton = new PlayList(1);
		}
		return singleton;
	}

	@Override
	public PlayList readResolve()
	{
		return instance();
	}

	@Override
	public boolean add(Play play)
	{
		return instance().plays.add(play);
	}

	@Override
	public void add(int index, Play play)
	{
		instance().plays.add(index, play);
	}

	@Override
	public boolean addAll(Collection<? extends Play> collection)
	{
		return instance().plays.addAll(collection);
	}

	@Override
	public boolean addAll(int index, Collection<? extends Play> collection)
	{
		return instance().plays.addAll(index, collection);
	}

	@Override
	public void clear()
	{
		instance().plays.clear();
	}

	@Override
	public boolean contains(Object object)
	{
		return instance().plays.contains(object);
	}

	@Override
	public boolean containsAll(Collection<?> collection)
	{
		return instance().plays.containsAll(collection);
	}

	@Override
	public Play get(int index)
	{
		return instance().plays.get(index);
	}

	@Override
	public int indexOf(Object object)
	{
		return instance().plays.indexOf(object);
	}

	@Override
	public boolean isEmpty()
	{
		return instance().plays.isEmpty();
	}

	@Override
	public Iterator<Play> iterator()
	{
		return instance().plays.iterator();
	}

	@Override
	public int lastIndexOf(Object object)
	{
		return instance().plays.lastIndexOf(object);
	}

	@Override
	public ListIterator<Play> listIterator()
	{
		return instance().plays.listIterator();
	}

	@Override
	public ListIterator<Play> listIterator(int index)
	{
		return instance().plays.listIterator(index);
	}

	@Override
	public boolean remove(Object object)
	{
		return instance().plays.remove(object);
	}

	@Override
	public Play remove(int index)
	{
		return instance().plays.remove(index);
	}

	@Override
	public boolean removeAll(Collection<?> collection)
	{
		return instance().plays.removeAll(collection);
	}

	@Override
	public boolean retainAll(Collection<?> collection)
	{
		return instance().plays.retainAll(collection);
	}

	@Override
	public Play set(int index, Play play)
	{
		return instance().plays.set(index, play);
	}

	@Override
	public int size()
	{
		return instance().plays.size();
	}

	@Override
	public List<Play> subList(int startIndex, int endIndex)
	{
		return instance().plays.subList(startIndex, endIndex);
	}

	@Override
	public Object[] toArray()
	{
		return instance().plays.toArray();
	}

	@Override
	public <T> T[] toArray(T[] arg0)
	{
		return instance().plays.toArray(arg0);
	}

	@Override
	public void load() throws ClassNotFoundException, IOException
	{
		instance().clear(); // clears the list in case anything was in it
		FileIO playFile = FileIO.startRead(FILENAME);
		while(playFile.hasMoreToRead())
		{
			instance().plays.add((Play) playFile.read());
		}
		playFile.close();
	}

	@Override
	public void save() throws IOException
	{
		FileIO playFile = FileIO.startWrite(FILENAME);
		for(Play play : instance().plays)
		{
			playFile.write(play);
		}
		playFile.close();
	}

}
