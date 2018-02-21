package theater;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import singleton.Singleton;

public class ClientList implements Singleton<ClientList>, List<Client>
{
	private static ClientList singleton;
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean contains(Object object)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> collection)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Client get(int index)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int indexOf(Object object)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty()
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator<Client> iterator()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int lastIndexOf(Object object)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ListIterator<Client> listIterator()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator<Client> listIterator(int index)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Object object)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Client remove(int index)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeAll(Collection<?> collection)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> collection)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Client set(int index, Client client)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Client> subList(int startIndex, int endIndex)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] arg0)
	{
		// TODO Auto-generated method stub
		return null;
	}

	// TODO Auto-generated method stub

}