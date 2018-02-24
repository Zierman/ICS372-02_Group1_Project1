package client;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import client.Client.ID;
import keyToken.KeyedList;
import keyToken.NoKeyTokenFoundException;
import play.Play;
import singleton.Singleton;
import storage.FileIO;
import storage.Loadable;
import storage.Savable;

//TODO document all of this
/**
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 */
public class ClientList implements Singleton<ClientList>, KeyedList<Client, Long>, Savable, Loadable
{
	private static ClientList singleton;
	protected static final String FILENAME = "clients.dat";
	private LinkedList<Client> clients = new LinkedList<Client>();

	protected ClientList() throws Exception
	{
		if (getClass().getName().equals("ClientList"))
		{
			throw new Exception();
		}
	}

	private ClientList(int i)
	{
	}

	public static ClientList instance()
	{
		if (singleton == null)
		{
			singleton = new ClientList(1);
		}
		return singleton;
	}

	@Override
	public ClientList readResolve()
	{
		return instance();
	}

	/* (non-Javadoc)
	 * @see java.util.List#add(java.lang.Object)
	 */
	@Override
	public boolean add(Client client)
	{
		return instance().clients.add(client);
	}

	@Override
	public void add(int index, Client client)
	{
		instance().clients.add(index, client);
	}

	@Override
	public boolean addAll(Collection<? extends Client> collection)
	{
		return instance().clients.addAll(collection);
	}

	@Override
	public boolean addAll(int index, Collection<? extends Client> collection)
	{
		return instance().clients.addAll(index, collection);
	}

	@Override
	public void clear()
	{
		instance().clients.clear();
	}

	@Override
	public boolean contains(Object object)
	{
		return instance().clients.contains(object);
	}

	@Override
	public boolean containsAll(Collection<?> collection)
	{
		return instance().clients.containsAll(collection);
	}

	@Override
	public Client get(int index)
	{
		return instance().clients.get(index);
	}

	@Override
	public int indexOf(Object object)
	{
		return instance().clients.indexOf(object);
	}

	@Override
	public boolean isEmpty()
	{
		return instance().clients.isEmpty();
	}

	@Override
	public Iterator<Client> iterator()
	{
		return instance().clients.iterator();
	}

	@Override
	public int lastIndexOf(Object object)
	{
		return instance().clients.lastIndexOf(object);
	}

	@Override
	public ListIterator<Client> listIterator()
	{
		return instance().clients.listIterator();
	}

	@Override
	public ListIterator<Client> listIterator(int index)
	{
		return instance().clients.listIterator(index);
	}

	@Override
	public boolean remove(Object object)
	{
		return instance().clients.remove(object);
	}

	@Override
	public Client remove(int index)
	{
		return instance().clients.remove(index);
	}

	@Override
	public boolean removeAll(Collection<?> collection)
	{
		return instance().clients.removeAll(collection);
	}

	@Override
	public boolean retainAll(Collection<?> collection)
	{
		return instance().clients.retainAll(collection);
	}

	@Override
	public Client set(int index, Client client)
	{
		return instance().clients.set(index, client);
	}

	@Override
	public int size()
	{
		return instance().clients.size();
	}

	@Override
	public List<Client> subList(int startIndex, int endIndex)
	{
		return instance().clients.subList(startIndex, endIndex);
	}

	@Override
	public Object[] toArray()
	{
		return instance().clients.toArray();
	}

	@Override
	public <T> T[] toArray(T[] arg0)
	{
		return instance().clients.toArray(arg0);
	}

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
		}
	}

	@Override
	public void save() throws IOException
	{
	
		
		FileIO clientFile = FileIO.startWrite(FILENAME);
		clientFile.write(new LinkedList<Client>(instance()));
		clientFile.close();
		
	}

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
}