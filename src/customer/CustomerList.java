package customer;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import client.Client;
import customer.Customer;
import customer.CustomerList;
import singleton.Singleton;
import storage.FileIO;
import storage.Loadable;
import storage.Savable;

public class CustomerList implements Singleton<CustomerList>, List<Customer>, Savable, Loadable
{
	private static CustomerList singleton;
	protected static final String FILENAME = "customers.dat";
	private LinkedList<Customer> customers = new LinkedList<Customer>();

	protected CustomerList() throws Exception
	{
		if (getClass().getName().equals("CustomerList"))
		{
			throw new Exception();
		}
	}

	private CustomerList(int i)
	{
	}

	public static CustomerList instance()
	{
		if (singleton == null)
		{
			singleton = new CustomerList(1);
		}
		return singleton;
	}

	@Override
	public CustomerList readResolve()
	{
		return instance();
	}

	@Override
	public boolean add(Customer customer)
	{
		return instance().customers.add(customer);
	}

	@Override
	public void add(int index, Customer customer)
	{
		instance().customers.add(index, customer);
	}

	@Override
	public boolean addAll(Collection<? extends Customer> collection)
	{
		return instance().customers.addAll(collection);
	}

	@Override
	public boolean addAll(int index, Collection<? extends Customer> collection)
	{
		return instance().customers.addAll(index, collection);
	}

	@Override
	public void clear()
	{
		instance().customers.clear();
	}

	@Override
	public boolean contains(Object object)
	{
		return instance().customers.contains(object);
	}

	@Override
	public boolean containsAll(Collection<?> collection)
	{
		return instance().customers.containsAll(collection);
	}

	@Override
	public Customer get(int index)
	{
		return instance().customers.get(index);
	}

	@Override
	public int indexOf(Object object)
	{
		return instance().customers.indexOf(object);
	}

	@Override
	public boolean isEmpty()
	{
		return instance().customers.isEmpty();
	}

	@Override
	public Iterator<Customer> iterator()
	{
		return instance().customers.iterator();
	}

	@Override
	public int lastIndexOf(Object object)
	{
		return instance().customers.lastIndexOf(object);
	}

	@Override
	public ListIterator<Customer> listIterator()
	{
		return instance().customers.listIterator();
	}

	@Override
	public ListIterator<Customer> listIterator(int index)
	{
		return instance().customers.listIterator(index);
	}

	@Override
	public boolean remove(Object object)
	{
		return instance().customers.remove(object);
	}

	@Override
	public Customer remove(int index)
	{
		return instance().customers.remove(index);
	}

	@Override
	public boolean removeAll(Collection<?> collection)
	{
		return instance().customers.removeAll(collection);
	}

	@Override
	public boolean retainAll(Collection<?> collection)
	{
		return instance().customers.retainAll(collection);
	}

	@Override
	public Customer set(int index, Customer customer)
	{
		return instance().customers.set(index, customer);
	}

	@Override
	public int size()
	{
		return instance().customers.size();
	}

	@Override
	public List<Customer> subList(int startIndex, int endIndex)
	{
		return instance().customers.subList(startIndex, endIndex);
	}

	@Override
	public Object[] toArray()
	{
		return instance().customers.toArray();
	}

	@Override
	public <T> T[] toArray(T[] arg0)
	{
		return instance().customers.toArray(arg0);
	}


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

	@Override
	public void save() throws IOException
	{
	
		
		FileIO customerFile = FileIO.startWrite(FILENAME);
		customerFile.write(new LinkedList<Customer>(instance()));
		customerFile.close();
		
	}

}
