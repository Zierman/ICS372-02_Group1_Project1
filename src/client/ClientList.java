package client;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import exceptions.NoKeyTokenFoundException;
import keyToken.KeyedList;
import storage.FileIO;
import storage.Loadable;
import storage.ReadResolveable;
import storage.Resetable;
import storage.Savable;

/**
 * A list that holds clients.
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 */
public class ClientList implements ReadResolveable<ClientList>, KeyedList<Client, Long>, Savable, Loadable, Resetable
{
	/**
	 * the singleton <code>ClientList</code>.
	 */
	private static ClientList singleton;
	
	/**
	 * The filename <code>String</code> that is used for the save and load functionality.
	 */
	protected static final String FILENAME = "clients.dat";
	
	/**
	 * the <code>LinkedList</code> that holds all the clients
	 */
	private static LinkedList<Client> clients = new LinkedList<Client>();

	/**
	 * Gets or Creates the instance of the singleton <code>ClientList</code>
	 * @return the instance of the singleton <code>ClientList</code>
	 */
	public static ClientList instance()
	{
		if (singleton == null)
		{
			singleton = new ClientList(1);
		}
		return singleton;
	}

	/**
	 * 
	 * Constructs a <code>ClientList</code> used when creating a subtype singleton
	 * 
	 * @throws Exception
	 *             if used to try to create a base type
	 */
	protected ClientList() throws Exception
	{
		if (getClass().getName().equals("ClientList"))
		{
			throw new Exception();
		}
	}

	/**
	 * Constructs the <code>ClientList</code> used to create the singleton.
	 * 
	 * @param i
	 *            an integer with no significance other than giving it a
	 *            different signature than the protected constructor.
	 */
	private ClientList(int i)
	{
	}

	/* (non-Javadoc)
	 * @see java.util.List#add(java.lang.Object)
	 */
	@Override
	public boolean add(Client client)
	{
		return clients.add(client);
	}

	/* (non-Javadoc)
	 * @see java.util.List#add(int, java.lang.Object)
	 */
	@Override
	public void add(int index, Client client)
	{
		clients.add(index, client);
	}

	/* (non-Javadoc)
	 * @see java.util.List#addAll(java.util.Collection)
	 */
	@Override
	public boolean addAll(Collection<? extends Client> collection)
	{
		return clients.addAll(collection);
	}

	/* (non-Javadoc)
	 * @see java.util.List#addAll(int, java.util.Collection)
	 */
	@Override
	public boolean addAll(int index, Collection<? extends Client> collection)
	{
		return clients.addAll(index, collection);
	}

	/* (non-Javadoc)
	 * @see storage.Loadable#canLoad()
	 */
	@Override
	public boolean canLoad()
	{
		Long id = getLastKey();
		try
		{
			
			FileIO clientFile = FileIO.startRead(FILENAME);
			LinkedList<Client> tmp =  (LinkedList<Client>) clientFile.read();
			clientFile.close();
			
		}
		catch (Exception e)
		{
			return false;
		}
		setLastKey(id);
		return true;
	}

	/* (non-Javadoc)
	 * @see java.util.List#clear()
	 */
	@Override
	public void clear()
	{
		clients.clear();
	}

	/* (non-Javadoc)
	 * @see java.util.List#contains(java.lang.Object)
	 */
	@Override
	public boolean contains(Object object)
	{
		return clients.contains(object);
	}

	/* (non-Javadoc)
	 * @see java.util.List#containsAll(java.util.Collection)
	 */
	@Override
	public boolean containsAll(Collection<?> collection)
	{
		return clients.containsAll(collection);
	}

	/* (non-Javadoc)
	 * @see keyToken.KeyedList#findMatched(java.lang.Object)
	 */
	@Override
	public Client findMatched(Long key) throws NoKeyTokenFoundException
	{
		Client client = null;
		for(Client c :  instance())
		{
			if(c.matches(key))
			{
				client = c;
			}
		}
		if(client == null)
		{
			throw new NoKeyTokenFoundException();
		}
		return client;
	}

	/* (non-Javadoc)
	 * @see java.util.List#get(int)
	 */
	@Override
	public Client get(int index)
	{
		return clients.get(index);
	}

	/* (non-Javadoc)
	 * @see keyToken.KeyedList#getLastKey()
	 */
	@Override
	public Long getLastKey()
	{
		return Client.lastId;
	}

	/* (non-Javadoc)
	 * @see java.util.List#indexOf(java.lang.Object)
	 */
	@Override
	public int indexOf(Object object)
	{
		return clients.indexOf(object);
	}

	/* (non-Javadoc)
	 * @see java.util.List#isEmpty()
	 */
	@Override
	public boolean isEmpty()
	{
		return clients.isEmpty();
	}

	/* (non-Javadoc)
	 * @see java.util.List#iterator()
	 */
	@Override
	public Iterator<Client> iterator()
	{
		return clients.iterator();
	}

	/* (non-Javadoc)
	 * @see java.util.List#lastIndexOf(java.lang.Object)
	 */
	@Override
	public int lastIndexOf(Object object)
	{
		return clients.lastIndexOf(object);
	}

	/* (non-Javadoc)
	 * @see java.util.List#listIterator()
	 */
	@Override
	public ListIterator<Client> listIterator()
	{
		return clients.listIterator();
	}

	/* (non-Javadoc)
	 * @see java.util.List#listIterator(int)
	 */
	@Override
	public ListIterator<Client> listIterator(int index)
	{
		return clients.listIterator(index);
	}

	/* (non-Javadoc)
	 * @see storage.Loadable#load()
	 */
	@Override
	public void load() throws ClassNotFoundException, IOException
	{
		instance().clear(); // clears the list in case anything was in it
		FileIO clientFile = FileIO.startRead(FILENAME);
		LinkedList<Client> tmp =  (LinkedList<Client>) clientFile.read();
		clientFile.close();
		
		for(Client c : tmp)
		{
			instance().add(c);
			
			c.getID().setKey(c.getID());
		}
	}

	/* (non-Javadoc)
	 * @see singleton.Singleton#readResolve()
	 */
	@Override
	public ClientList readResolve()
	{
		return instance();
	}

	/* (non-Javadoc)
	 * @see java.util.List#remove(int)
	 */
	@Override
	public Client remove(int index)
	{
		return clients.remove(index);
	}

	/* (non-Javadoc)
	 * @see java.util.List#remove(java.lang.Object)
	 */
	@Override
	public boolean remove(Object object)
	{
		return clients.remove(object);
	}

	/* (non-Javadoc)
	 * @see java.util.List#removeAll(java.util.Collection)
	 */
	@Override
	public boolean removeAll(Collection<?> collection)
	{
		return clients.removeAll(collection);
	}

	/* (non-Javadoc)
	 * @see keyToken.KeyedList#removeMatched(java.lang.Object)
	 */
	@Override
	public void removeMatched(Long key) throws NoKeyTokenFoundException
	{
		boolean found = false;
		int i = 0;
		for(Client c :  instance())
		{
			if(c.matches(key))
			{
				instance().remove(i);
				found = true;
				break;
			}
			i++;
		}
		if(!found)
		{
			throw new NoKeyTokenFoundException();
		}
		
	}

	/* (non-Javadoc)
	 * @see storage.Resetable#reset()
	 */
	@Override
	public void reset()
	{
		clear();
		singleton = null;
		instance();
	}

	/* (non-Javadoc)
	 * @see java.util.List#retainAll(java.util.Collection)
	 */
	@Override
	public boolean retainAll(Collection<?> collection)
	{
		return clients.retainAll(collection);
	}

	/* (non-Javadoc)
	 * @see storage.Savable#save()
	 */
	@Override
	public void save() throws IOException
	{
	
		
		FileIO clientFile = FileIO.startWrite(FILENAME);
		clientFile.write(new LinkedList<Client>(instance()));
		clientFile.close();
		
	}

	/* (non-Javadoc)
	 * @see java.util.List#set(int, java.lang.Object)
	 */
	@Override
	public Client set(int index, Client client)
	{
		return clients.set(index, client);
	}

	/* (non-Javadoc)
	 * @see keyToken.KeyedList#setLastKey(java.lang.Object)
	 */
	@Override
	public void setLastKey(Long key)
	{
		Client.lastId = key;
		
	}

	/* (non-Javadoc)
	 * @see java.util.List#size()
	 */
	@Override
	public int size()
	{
		return clients.size();
	}

	/* (non-Javadoc)
	 * @see java.util.List#subList(int, int)
	 */
	@Override
	public List<Client> subList(int startIndex, int endIndex)
	{
		return clients.subList(startIndex, endIndex);
	}

	/* (non-Javadoc)
	 * @see java.util.List#toArray()
	 */
	@Override
	public Object[] toArray()
	{
		return clients.toArray();
	}

	/* (non-Javadoc)
	 * @see java.util.List#toArray(java.lang.Object[])
	 */
	@Override
	public <T> T[] toArray(T[] arg0)
	{
		return clients.toArray(arg0);
	}
}