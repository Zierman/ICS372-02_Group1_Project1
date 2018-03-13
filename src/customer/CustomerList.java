package customer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import client.Client;
import customer.Customer;
import customer.CustomerList;
import exceptions.NoKeyTokenFoundException;
import keyToken.KeyedList;
import singleton.ReadResolveable;
import storage.FileIO;
import storage.Loadable;
import storage.Savable;

/**
 * A list that holds customers.
 * @author Troy Novak [wh1617wd@metrostate.edu]
 *
 */
public class CustomerList implements ReadResolveable<CustomerList>, KeyedList<Customer, Long>, Savable, Loadable
{
	/**
	 * the singleton <code>CustomerList</code>.
	 */
	private static CustomerList singleton;
	
	/**
	 * The filename <code>String</code> that is used for the save and load functionality.
	 */
	protected static final String FILENAME = "customers.dat";
	
	/**
	 * the <code>LinkedList</code> that holds all the customers
	 */
	private static LinkedList<Customer> customers = new LinkedList<Customer>();

	public static CustomerList instance()
	{
		if (singleton == null)
		{
			singleton = new CustomerList(1);
		}
		return singleton;
	}

	/**
	 * 
	 * Constructs a <code>CustomerList</code> used when creating a subtype singleton
	 * 
	 * @throws Exception
	 *             if used to try to create a base type
	 */
	protected CustomerList() throws Exception
	{
		if (getClass().getName().equals("CustomerList"))
		{
			throw new Exception();
		}
	}

	/**
	 * Constructs the <code>CustomerList</code> used to create the singleton.
	 * 
	 * @param i
	 *            an integer with no significance other than giving it a
	 *            different signature than the protected constructor.
	 */
	private CustomerList(int i)
	{
	}

	/* (non-Javadoc)
	 * @see java.util.List#add(java.lang.Object)
	 */
	@Override
	public boolean add(Customer customer)
	{
		return instance().customers.add(customer);
	}

	/* (non-Javadoc)
	 * @see java.util.List#add(int, java.lang.Object)
	 */
	@Override
	public void add(int index, Customer customer)
	{
		instance().customers.add(index, customer);
	}

	/* (non-Javadoc)
	 * @see java.util.List#addAll(java.util.Collection)
	 */
	@Override
	public boolean addAll(Collection<? extends Customer> collection)
	{
		return instance().customers.addAll(collection);
	}

	/* (non-Javadoc)
	 * @see java.util.List#addAll(int, java.util.Collection)
	 */
	@Override
	public boolean addAll(int index, Collection<? extends Customer> collection)
	{
		return instance().customers.addAll(index, collection);
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
			FileIO customerFile = FileIO.startRead(FILENAME);
			LinkedList<Customer> tmp =  (LinkedList<Customer>) customerFile.read();
			customerFile.close();
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
		instance().customers.clear();
	}

	/* (non-Javadoc)
	 * @see java.util.List#contains(java.lang.Object)
	 */
	@Override
	public boolean contains(Object object)
	{
		return instance().customers.contains(object);
	}

	/* (non-Javadoc)
	 * @see java.util.List#containsAll(java.util.Collection)
	 */
	@Override
	public boolean containsAll(Collection<?> collection)
	{
		return instance().customers.containsAll(collection);
	}

	/* (non-Javadoc)
	 * @see keyToken.KeyedList#findMatched(java.lang.Object)
	 */
	@Override
	public Customer findMatched(Long key) throws NoKeyTokenFoundException
	{
		Customer customer = null;
		for(Customer c :  instance())
		{
			if(c.matches(key))
			{
				customer = c;
			}
		}
		if(customer == null)
		{
			throw new NoKeyTokenFoundException();
		}
		return customer;
	}

	/* (non-Javadoc)
	 * @see java.util.List#get(int)
	 */
	@Override
	public Customer get(int index)
	{
		return instance().customers.get(index);
	}

	/* (non-Javadoc)
	 * @see keyToken.KeyedList#getLastKey()
	 */
	@Override
	public Long getLastKey()
	{
		return Customer.lastID;
	}

	/* (non-Javadoc)
	 * @see java.util.List#indexOf(java.lang.Object)
	 */
	@Override
	public int indexOf(Object object)
	{
		return instance().customers.indexOf(object);
	}

	/* (non-Javadoc)
	 * @see java.util.List#isEmpty()
	 */
	@Override
	public boolean isEmpty()
	{
		return instance().customers.isEmpty();
	}

	/* (non-Javadoc)
	 * @see java.util.List#iterator()
	 */
	@Override
	public Iterator<Customer> iterator()
	{
		return instance().customers.iterator();
	}

	/* (non-Javadoc)
	 * @see java.util.List#lastIndexOf(java.lang.Object)
	 */
	@Override
	public int lastIndexOf(Object object)
	{
		return instance().customers.lastIndexOf(object);
	}

	/* (non-Javadoc)
	 * @see java.util.List#listIterator()
	 */
	@Override
	public ListIterator<Customer> listIterator()
	{
		return instance().customers.listIterator();
	}

	/* (non-Javadoc)
	 * @see java.util.List#listIterator(int)
	 */
	@Override
	public ListIterator<Customer> listIterator(int index)
	{
		return instance().customers.listIterator(index);
	}

	/* (non-Javadoc)
	 * @see storage.Loadable#load()
	 */
	@Override
	public void load() throws ClassNotFoundException, IOException
	{
		instance().clear(); // clears the list in case anything was in it
		FileIO customerFile = FileIO.startRead(FILENAME);
		LinkedList<Customer> tmp =  (LinkedList<Customer>) customerFile.read();
		customerFile.close();
		
		for(Customer c : tmp)
		{
			instance().add(c);
		}
	}

	/* (non-Javadoc)
	 * @see singleton.Singleton#readResolve()
	 */
	@Override
	public CustomerList readResolve()
	{
		return instance();
	}

	/* (non-Javadoc)
	 * @see java.util.List#remove(int)
	 */
	@Override
	public Customer remove(int index)
	{
		return instance().customers.remove(index);
	}

	/* (non-Javadoc)
	 * @see java.util.List#remove(java.lang.Object)
	 */
	@Override
	public boolean remove(Object object)
	{
		return instance().customers.remove(object);
	}

	/* (non-Javadoc)
	 * @see java.util.List#removeAll(java.util.Collection)
	 */
	@Override
	public boolean removeAll(Collection<?> collection)
	{
		return instance().customers.removeAll(collection);
	}

	/* (non-Javadoc)
	 * @see keyToken.KeyedList#removeMatched(java.lang.Object)
	 */
	@Override
	public void removeMatched(Long key) throws NoKeyTokenFoundException
	{

		boolean found = false;
		int i = 0;
		for(Customer c :  instance())
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
	 * @see java.util.List#retainAll(java.util.Collection)
	 */
	@Override
	public boolean retainAll(Collection<?> collection)
	{
		return instance().customers.retainAll(collection);
	}


	/* (non-Javadoc)
	 * @see storage.Savable#save()
	 */
	@Override
	public void save() throws IOException
	{
	
		
		FileIO customerFile = FileIO.startWrite(FILENAME);
		customerFile.write(new LinkedList<Customer>(instance()));
		customerFile.close();
		
	}

	/* (non-Javadoc)
	 * @see java.util.List#set(int, java.lang.Object)
	 */
	@Override
	public Customer set(int index, Customer customer)
	{
		return instance().customers.set(index, customer);
	}

	/* (non-Javadoc)
	 * @see keyToken.KeyedList#setLastKey(java.lang.Object)
	 */
	@Override
	public void setLastKey(Long key)
	{
		Customer.lastID = key;
		
	}

	/* (non-Javadoc)
	 * @see java.util.List#size()
	 */
	@Override
	public int size()
	{
		return instance().customers.size();
	}

	/* (non-Javadoc)
	 * @see java.util.List#subList(int, int)
	 */
	@Override
	public List<Customer> subList(int startIndex, int endIndex)
	{
		return instance().customers.subList(startIndex, endIndex);
	}

	/* (non-Javadoc)
	 * @see java.util.List#toArray()
	 */
	@Override
	public Object[] toArray()
	{
		return instance().customers.toArray();
	}

	/* (non-Javadoc)
	 * @see java.util.List#toArray(java.lang.Object[])
	 */
	@Override
	public <T> T[] toArray(T[] arg0)
	{
		return instance().customers.toArray(arg0);
	}

	public void reset()
	{
		clear();
		singleton = null;
		instance();
		
	}

}
