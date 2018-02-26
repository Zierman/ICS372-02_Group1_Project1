package play;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import singleton.ReadResolveable;
import storage.FileIO;
import storage.Loadable;
import storage.Savable;

/**
 * A list of plays
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 */
public class PlayList implements ReadResolveable<PlayList>, List<Play>, Savable, Loadable
{
	private static PlayList singleton;
	protected static final String FILENAME = "plays.dat";
	private static LinkedList<Play> plays = new LinkedList<Play>();

	/**
	 * Constructs a <code>PlayList</code> used when creating a subtype singleton
	 * 
	 * @throws Exception
	 *             if used to try to create a base type
	 */
	protected PlayList() throws Exception
	{
		if (getClass().getName().equals("PlayList"))
		{
			throw new Exception();
		}
	}

	/**
	 * Constructs the <code>PlayList</code> used to create the singleton.
	 * 
	 * @param i
	 *            an integer with no significance other than giving it a
	 *            different signature than the protected constructor.
	 */
	private PlayList(int i)
	{
	}

	/**
	 * Gets or creates an instance of <code>PlayList</code>.
	 * @return the instance of <code>PlayList</code>
	 */
	public static PlayList instance()
	{
		if (singleton == null)
		{
			singleton = new PlayList(1);
		}
		return singleton;
	}

	/* (non-Javadoc)
	 * @see singleton.Singleton#readResolve()
	 */
	@Override
	public PlayList readResolve()
	{
		return instance();
	}

	/* (non-Javadoc)
	 * @see java.util.List#add(java.lang.Object)
	 */
	@Override
	public boolean add(Play play)
	{
		return plays.add(play);
	}

	/* (non-Javadoc)
	 * @see java.util.List#add(int, java.lang.Object)
	 */
	@Override
	public void add(int index, Play play)
	{
		plays.add(index, play);
	}

	/* (non-Javadoc)
	 * @see java.util.List#addAll(java.util.Collection)
	 */
	@Override
	public boolean addAll(Collection<? extends Play> collection)
	{
		return plays.addAll(collection);
	}

	/* (non-Javadoc)
	 * @see java.util.List#addAll(int, java.util.Collection)
	 */
	@Override
	public boolean addAll(int index, Collection<? extends Play> collection)
	{
		return plays.addAll(index, collection);
	}

	/* (non-Javadoc)
	 * @see java.util.List#clear()
	 */
	@Override
	public void clear()
	{
		plays.clear();
	}

	/* (non-Javadoc)
	 * @see java.util.List#contains(java.lang.Object)
	 */
	@Override
	public boolean contains(Object object)
	{
		return plays.contains(object);
	}

	/* (non-Javadoc)
	 * @see java.util.List#containsAll(java.util.Collection)
	 */
	@Override
	public boolean containsAll(Collection<?> collection)
	{
		return plays.containsAll(collection);
	}

	/* (non-Javadoc)
	 * @see java.util.List#get(int)
	 */
	@Override
	public Play get(int index)
	{
		return plays.get(index);
	}

	/* (non-Javadoc)
	 * @see java.util.List#indexOf(java.lang.Object)
	 */
	@Override
	public int indexOf(Object object)
	{
		return plays.indexOf(object);
	}

	/* (non-Javadoc)
	 * @see java.util.List#isEmpty()
	 */
	@Override
	public boolean isEmpty()
	{
		return plays.isEmpty();
	}

	/* (non-Javadoc)
	 * @see java.util.List#iterator()
	 */
	@Override
	public Iterator<Play> iterator()
	{
		return plays.iterator();
	}

	/* (non-Javadoc)
	 * @see java.util.List#lastIndexOf(java.lang.Object)
	 */
	@Override
	public int lastIndexOf(Object object)
	{
		return plays.lastIndexOf(object);
	}

	/* (non-Javadoc)
	 * @see java.util.List#listIterator()
	 */
	@Override
	public ListIterator<Play> listIterator()
	{
		return plays.listIterator();
	}

	/* (non-Javadoc)
	 * @see java.util.List#listIterator(int)
	 */
	@Override
	public ListIterator<Play> listIterator(int index)
	{
		return plays.listIterator(index);
	}

	/* (non-Javadoc)
	 * @see java.util.List#remove(java.lang.Object)
	 */
	@Override
	public boolean remove(Object object)
	{
		return plays.remove(object);
	}

	/* (non-Javadoc)
	 * @see java.util.List#remove(int)
	 */
	@Override
	public Play remove(int index)
	{
		return plays.remove(index);
	}

	/* (non-Javadoc)
	 * @see java.util.List#removeAll(java.util.Collection)
	 */
	@Override
	public boolean removeAll(Collection<?> collection)
	{
		return plays.removeAll(collection);
	}

	/* (non-Javadoc)
	 * @see java.util.List#retainAll(java.util.Collection)
	 */
	@Override
	public boolean retainAll(Collection<?> collection)
	{
		return plays.retainAll(collection);
	}

	/* (non-Javadoc)
	 * @see java.util.List#set(int, java.lang.Object)
	 */
	@Override
	public Play set(int index, Play play)
	{
		return plays.set(index, play);
	}

	/* (non-Javadoc)
	 * @see java.util.List#size()
	 */
	@Override
	public int size()
	{
		return plays.size();
	}

	/* (non-Javadoc)
	 * @see java.util.List#subList(int, int)
	 */
	@Override
	public List<Play> subList(int startIndex, int endIndex)
	{
		return plays.subList(startIndex, endIndex);
	}

	/* (non-Javadoc)
	 * @see java.util.List#toArray()
	 */
	@Override
	public Object[] toArray()
	{
		return plays.toArray();
	}

	/* (non-Javadoc)
	 * @see java.util.List#toArray(java.lang.Object[])
	 */
	@Override
	public <T> T[] toArray(T[] arg0)
	{
		return plays.toArray(arg0);
	}


	/* (non-Javadoc)
	 * @see storage.Loadable#load()
	 */
	@Override
	public void load() throws ClassNotFoundException, IOException
	{
		clear(); // clears the list in case anything was in it
		FileIO playFile = FileIO.startRead(FILENAME);
		LinkedList<Play> tmp =  (LinkedList<Play>) playFile.read();
		playFile.close();
		
		for(Play c : tmp)
		{
			instance().add(c);
		}
	}

	/* (non-Javadoc)
	 * @see storage.Savable#save()
	 */
	@Override
	public void save() throws IOException
	{
	
		
		FileIO playFile = FileIO.startWrite(FILENAME);
		playFile.write(new LinkedList<Play>(instance()));
		playFile.close();
		
	}

}
