package ticket;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import client.Client;
import client.ClientList;
import customer.Customer;
import customer.CustomerList;
import exceptions.DateOutOfBoundsException;
import exceptions.NotEnoughSeatsAvailibleException;
import play.Play;
import play.PlayList;
import singleton.ReadResolveable;
import storage.FileIO;
import storage.Loadable;
import storage.Savable;
import theater.Theater;
import userInterface.UI;
//TODO Document this 
public class TicketList
		implements ReadResolveable<TicketList>, List<Ticket>, Savable, Loadable
{
	private static TicketList singleton;
	protected static final String FILENAME = "tickets.dat";
	private static LinkedList<Ticket> tickets = new LinkedList<Ticket>();

	/**
	 * Gets or creates an instance of <code>TicketList</code>.
	 * 
	 * @return the instance of <code>TicketList</code>
	 */
	public static TicketList instance()
	{
		if (singleton == null)
		{
			singleton = new TicketList(1);
		}
		return singleton;
	}

	/**
	 * Constructs a <code>TicketList</code> used when creating a subtype
	 * singleton
	 * 
	 * @throws Exception
	 *             if used to try to create a base type
	 */
	protected TicketList() throws Exception
	{
		if (getClass().getName().equals("TicketList"))
		{
			throw new Exception();
		}
	}

	/**
	 * Constructs the <code>TicketList</code> used to create the singleton.
	 * 
	 * @param i
	 *            an integer with no significance other than giving it a
	 *            different signature than the protected constructor.
	 */
	private TicketList(int i)
	{
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#add(int, java.lang.Object)
	 */
	@Override
	public void add(int index, Ticket ticket)
	{
		checkCanAdd(ticket);
		tickets.add(index, ticket);
	}

	/**
	 * Checks to see if a ticket can be added to the list
	 * 
	 * @param ticket
	 *            to be added to list
	 * @throws IllegalArgumentException
	 *             if the ticket cannot be added to list
	 */
	private void checkCanAdd(Ticket ticket) throws IllegalArgumentException
	{
		int count = 0;
		for (Ticket t : instance())
		{
			if (t.dateOfShow.equals(ticket.dateOfShow))
			{
				count++;
			}
		}
		if (count >= ticket.getPlay().getSeatingCapacity())
		{
			throw new NotEnoughSeatsAvailibleException();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#add(java.lang.Object)
	 */
	@Override
	public boolean add(Ticket ticket)
	{
		checkCanAdd(ticket); // will throw an exception if it can't
		return tickets.add(ticket);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#addAll(java.util.Collection)
	 */
	@Override
	public boolean addAll(Collection<? extends Ticket> collection)
	{
		int size = tickets.size();
		for (Ticket p : collection)
		{
			checkCanAdd(p); // will throw an exception if it can't
		}

		for (Ticket p : collection)
		{
			tickets.add(p);
		}
		return size != tickets.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#addAll(int, java.util.Collection)
	 */
	@Override
	public boolean addAll(int index, Collection<? extends Ticket> collection)
	{
		int size = tickets.size();

		for (Ticket p : collection)
		{
			checkCanAdd(p); // will throw an exception if it can't
		}

		for (Ticket p : collection)
		{
			tickets.add(index++, p);

		}
		return size != tickets.size();
	}

	@Override
	public boolean canLoad()
	{
		try
		{
			clear(); // clears the list in case anything was in it
			FileIO ticketFile = FileIO.startRead(FILENAME);
			LinkedList<Ticket> tmp = (LinkedList<Ticket>) ticketFile.read();
			ticketFile.close();

			for (Ticket c : tmp)
			{
				instance().add(c);
			}
		}
		catch (Exception e)
		{
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#clear()
	 */
	@Override
	public void clear()
	{
		tickets.clear();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#contains(java.lang.Object)
	 */
	@Override
	public boolean contains(Object object)
	{
		return tickets.contains(object);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#containsAll(java.util.Collection)
	 */
	@Override
	public boolean containsAll(Collection<?> collection)
	{
		return tickets.containsAll(collection);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#get(int)
	 */
	@Override
	public Ticket get(int index)
	{
		return tickets.get(index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#indexOf(java.lang.Object)
	 */
	@Override
	public int indexOf(Object object)
	{
		return tickets.indexOf(object);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#isEmpty()
	 */
	@Override
	public boolean isEmpty()
	{
		return tickets.isEmpty();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#iterator()
	 */
	@Override
	public Iterator<Ticket> iterator()
	{
		return tickets.iterator();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#lastIndexOf(java.lang.Object)
	 */
	@Override
	public int lastIndexOf(Object object)
	{
		return tickets.lastIndexOf(object);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#listIterator()
	 */
	@Override
	public ListIterator<Ticket> listIterator()
	{
		return tickets.listIterator();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#listIterator(int)
	 */
	@Override
	public ListIterator<Ticket> listIterator(int index)
	{
		return tickets.listIterator(index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see storage.Loadable#load()
	 */
	@Override
	public void load() throws ClassNotFoundException, IOException
	{
		clear(); // clears the list in case anything was in it
		FileIO ticketFile = FileIO.startRead(FILENAME);
		LinkedList<Ticket> tmp = (LinkedList<Ticket>) ticketFile.read();
		ticketFile.close();

		for (Ticket t : tmp)
		{

			for (Customer customer : CustomerList.instance())
			{
				if (customer.matches(t.getOwner().getKey()))
				{
					t.setOwner(customer);
				}
			}

			for (Play play : PlayList.instance())
			{
				if (play.equals(t.getPlay()))
					;
				{
					try
					{
						t.setPlay(play);
					}
					catch (Exception e)
					{
						UI.outputError(e,
								"couldn't load ticket beause play could not be set");
					}
				}
			}

			instance().add(t);

			t.getSerialNumber().setKey(t.getSerialNumber());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see singleton.Singleton#readResolve()
	 */
	@Override
	public TicketList readResolve()
	{
		return instance();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#remove(int)
	 */
	@Override
	public Ticket remove(int index)
	{
		return tickets.remove(index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#remove(java.lang.Object)
	 */
	@Override
	public boolean remove(Object object)
	{
		return tickets.remove(object);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#removeAll(java.util.Collection)
	 */
	@Override
	public boolean removeAll(Collection<?> collection)
	{
		return tickets.removeAll(collection);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#retainAll(java.util.Collection)
	 */
	@Override
	public boolean retainAll(Collection<?> collection)
	{
		return tickets.retainAll(collection);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see storage.Savable#save()
	 */
	@Override
	public void save() throws IOException
	{

		FileIO ticketFile = FileIO.startWrite(FILENAME);
		ticketFile.write(new LinkedList<Ticket>(instance()));
		ticketFile.close();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#set(int, java.lang.Object)
	 */
	@Override
	public Ticket set(int index, Ticket ticket)
	{
		return tickets.set(index, ticket);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#size()
	 */
	@Override
	public int size()
	{
		return tickets.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#subList(int, int)
	 */
	@Override
	public List<Ticket> subList(int startIndex, int endIndex)
	{
		return tickets.subList(startIndex, endIndex);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#toArray()
	 */
	@Override
	public Object[] toArray()
	{
		return tickets.toArray();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#toArray(java.lang.Object[])
	 */
	@Override
	public <T> T[] toArray(T[] arg0)
	{
		return tickets.toArray(arg0);
	}

	public int countFor(Date dateOfShow)
	{
		int count = 0;
		for (Ticket t : this)
		{
			if (t.getDateOfShow().equals(dateOfShow))
			{
				count++;
			}
		}
		return count;
	}

	public void reset()
	{

		clear();
		singleton = null;
		instance();
	}

}
