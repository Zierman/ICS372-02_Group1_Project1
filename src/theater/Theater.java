/**
 * 
 */
package theater;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;

import client.Client;
import client.ClientList;
import currency.Dollar;
import customer.Customer;
import customer.CustomerList;
import exceptions.CardAlreadyInListException;
import exceptions.NoCardFoundException;
import exceptions.NoKeyTokenFoundException;
import exceptions.NoPlayFoundException;
import exceptions.OverpayingClientException;
import play.Play;
import play.PlayList;
import storage.FileIO;
import storage.Loadable;
import storage.ReadResolveable;
import storage.Resetable;
import storage.Savable;
import ticket.Ticket;
import ticket.TicketList;

/**
 * Represents a theater that shows plays performed by clients to customers.
 * 
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 */
public class Theater
		implements ReadResolveable<Theater>, Loadable, Savable, Resetable
{
	/**
	 * a <code>Theater</code> object to enforce singleton behavior.
	 */
	private static Theater singleton;

	/**
	 * a <code>String</code> representing a filename for use with the save and
	 * load functionality.
	 */
	protected static final String FILENAME = "theater.dat";

	/**
	 * Gets or Creates the instance of the singleton Theater
	 * 
	 * @return the instance of the singleton Theater
	 */
	public static Theater instance()
	{
		if (singleton == null)
		{
			singleton = new Theater(1);
		}
		return singleton;
	}

	/**
	 * the name of the theater.
	 */
	private String name;

	/**
	 * a list of all clients.
	 */
	private ClientList clientList = ClientList.instance();

	/**
	 * a list of all plays.
	 */
	private PlayList playList = PlayList.instance();

	private TicketList ticketList = TicketList.instance();

	/**
	 * a list of all customers.
	 */
	private CustomerList customerList = CustomerList.instance();

	/**
	 * Constructs a Theater used when creating a subtype singleton
	 * 
	 * @throws Exception
	 *             if used to try to create a base type Theater
	 */
	protected Theater() throws Exception
	{
		if (getClass().getName().equals("Theater"))
		{
			throw new Exception();
		}
	}

	/**
	 * Constructs the Theater used to create the singleton.
	 * 
	 * @param i
	 *            an integer with no significance other than giving it a
	 *            different signature than the protected constructor.
	 */
	private Theater(int i)
	{
	}

	/**
	 * Adds a new client
	 * 
	 * @param client
	 *            {@link client.Client} to be added.
	 * @return true if added, false if not.
	 */
	public boolean add(Client client)
	{
		return clientList.add(client);
	}

	/**
	 * Adds a new customer
	 * 
	 * @param customer
	 *            a {@link customer.Customer} to be added.
	 * @return true if added, false if not.
	 */
	public boolean add(Customer customer)
	{
		return customerList.add(customer);
	}

	/**
	 * adds a new credit card
	 * 
	 * @param customer
	 *            The customer who is adding the credit card.
	 * @param cardNumber
	 *            the credit card number for the new credit card.
	 * @param cardExpiration
	 *            the expiration date of the new card.
	 * @return true if added, false if not
	 * @throws CardAlreadyInListException
	 *             if the card that is being added was already in list.
	 */
	public boolean add(Customer customer, String cardNumber,
			String cardExpiration) throws CardAlreadyInListException
	{
		return customer.addCreditCard(cardNumber, cardExpiration);
	}

	/**
	 * Adds a new play
	 * 
	 * @param play
	 *            a {@link play.Play} tobe added.
	 * @return true if added, false if not.
	 */
	public boolean add(Play play)
	{
		return playList.add(play);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see storage.Loadable#canLoad()
	 */
	@Override
	public boolean canLoad()
	{
		try
		{
			FileIO theaterFile = FileIO.startRead(FILENAME);
			String tmpString = (String) theaterFile.read();
			Integer tmpInteger = (Integer) theaterFile.read();
			theaterFile.close();
			clientList.canLoad();
			customerList.canLoad();
			playList.canLoad();
		}
		catch (Exception e)
		{

		}
		return true;
	}

	/**
	 * Finds the client given a string representation of the client's id
	 * 
	 * @param clientID
	 *            a string representation of the client's id
	 * @return a client object
	 * @throws NoKeyTokenFoundException
	 *             if the clientID string cannot be matched
	 */
	public Client findClient(String clientID) throws NoKeyTokenFoundException
	{
		Client client = null;
		Theater theater = this;
		for (Client c : theater.getClientList())
		{
			try
			{
				if (c.getID().matches(Long.parseLong(clientID)))
				{
					client = c;
					break;
				}
			}
			catch (NumberFormatException e)
			{
				throw new NoKeyTokenFoundException();
			}
		}
		if (client == null)
		{
			throw new NoKeyTokenFoundException();
		}
		return client;
	}

	/**
	 * Finds the customer given a string representation of the customer's id
	 * 
	 * @param customerID
	 *            a string representation of the customer's id
	 * @return a Customer object
	 * @throws NoKeyTokenFoundException
	 *             if the Customer's id was not found
	 */
	public Customer findCustomer(String customerID)
			throws NoKeyTokenFoundException
	{
		Customer customer = null;
		Theater theater = this;
		for (Customer c : theater.getCustomerList())
		{
			try
			{
				if (c.getID().matches(Long.parseLong(customerID)))
				{
					customer = c;
					break;
				}
			}
			catch (NumberFormatException e)
			{
				throw new NoKeyTokenFoundException();
			}
		}
		if (customer == null)
		{
			throw new NoKeyTokenFoundException();
		}
		return customer;
	}

	/**
	 * Gets a list of all clients.
	 * 
	 * @return a {@link client.ClientList} that holds all clients
	 */
	public ClientList getClientList()
	{
		return clientList;
	}

	/**
	 * gets the client's current balance due
	 * 
	 * @param client
	 * @return a {@link client.balanceDue} that contains the client's balance
	 */
	public Dollar getClientBalanceDue(Client client)
	{
		return client.getBalanceDue();
	}

	/**
	 * Gets a list of all customers.
	 * 
	 * @return a {@link customer.CustomerList} that holds all customers
	 */
	public CustomerList getCustomerList()
	{
		return customerList;
	}

	/**
	 * Gets the name of the theater.
	 * 
	 * @return a <code>String</code> representing the name of the theater
	 */
	public String getName()
	{
		return name;
	}

	public Play getPlay(Date dateOfShow) throws NoPlayFoundException
	{
		Play play = null;

		for (Play p : playList)
		{
			// if dateOfShow is in range [start, end) of the play
			if ((p.getStartDate().before(dateOfShow)
					&& p.getEndDate().after(dateOfShow))
					|| p.getStartDate().equals(dateOfShow))
			{
				play = p;
			}
		}
		if (play == null)
		{
			throw new NoPlayFoundException();
		}

		return play;
	}

	/**
	 * Gets a list of all plays.
	 * 
	 * @return a {@link play.PlayList} that holds all plays
	 */
	public PlayList getPlayList()
	{
		return playList;
	}

	/**
	 * @return the ticketList
	 */
	public TicketList getTicketList()
	{
		return ticketList;
	}

	public boolean hasEnoughFreeSeats(int qty, Date dateOfShow, Play play)
	{
		try
		{
			int alreadySold = ticketList.countFor(dateOfShow);
			return qty + alreadySold <= play.getSeatingCapacity();
		}
		catch (Exception e)
		{
			return false;
		}

	}

	public void increaseDebt(Client client, Dollar dollars)
	{
		client.setBalanceDue(client.getBalanceDue().addTogether((dollars)));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see storage.Loadable#load()
	 */
	@Override
	public void load() throws ClassNotFoundException, IOException
	{
		try
		{
			FileIO theaterFile = FileIO.startRead(FILENAME);
			instance().setName((String) theaterFile.read());
			theaterFile.close();
			clientList.load();
			customerList.load();
			playList.load();
			ticketList.load();
		}
		catch (Exception e)
		{
			reset();
			throw e;
		}
	}

	public void pay(Client client, Dollar dollars)
			throws OverpayingClientException
	{
		if (client.getBalanceDue().compareTo(dollars) < 0)
		{
			throw new OverpayingClientException();
		}
		client.setBalanceDue(client.getBalanceDue().subtract(dollars));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see singleton.Singleton#readResolve()
	 */
	@Override
	public Theater readResolve()
	{
		return instance();
	}

	/**
	 * removes a credit card
	 * 
	 * @param customer
	 *            the customer who owns the credit card to be removed
	 * @param cardNumber
	 *            the credit card number of the card to remove.
	 * @throws NoCardFoundException
	 *             if the credit card number doesn't match any card in list.
	 */
	public void removeCreditCard(Customer customer, String cardNumber)
			throws NoCardFoundException
	{
		customer.removeCreditCard(cardNumber);
	}

	/**
	 * Removes a client with matching id
	 * 
	 * @param id
	 *            a <code>Long</code> that represents the Id
	 * @throws NoKeyTokenFoundException
	 *             if no match is found
	 */
	public void removeMatchedClient(Long id) throws NoKeyTokenFoundException
	{
		clientList.removeMatched(id);
	}

	/**
	 * Removes a customer with a matching id
	 * 
	 * @param id
	 *            a <code>Long</code> that represents the Id
	 * @throws NoKeyTokenFoundException
	 *             if no match is found
	 */
	public void removeMatchedCustomer(Long id) throws NoKeyTokenFoundException
	{
		customerList.removeMatched(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see storage.Resetable#reset()
	 */
	@Override
	public void reset()
	{
		clientList.reset();
		customerList.reset();
		playList.reset();
		ticketList.reset();
		instance();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see storage.Savable#save()
	 */
	@Override
	public void save() throws IOException
	{
		FileIO theaterFile = FileIO.startWrite(FILENAME);
		theaterFile.write(name);
		theaterFile.close();
		clientList.save();
		customerList.save();
		playList.save();
		ticketList.save();
	}

	public void sell(Collection<Ticket> tickets)
	{
		for (Ticket ticket : tickets)
		{
			sell(ticket);
		}

	}

	public void sell(Ticket ticket)
	{
		ticketList.add(ticket);

		// client that performs the play gets half the price of the ticket
		Dollar newTotal = ticket.getPlay().getOwner().getBalanceDue()
				.addTogether(ticket.getPriceOfTicket().half());
		ticket.getPlay().getOwner().setBalanceDue(newTotal);
	}

	/**
	 * Sells a ticket
	 * 
	 * @param ticket
	 *            The ticket to be sold
	 */
	public void Sell(Ticket ticket)
	{
		ticketList.add(ticket);
		increaseDebt(ticket.getPlay().getOwner(),
				ticket.getPriceOfTicket().half());
	}

	/**
	 * Sets the name of the theater.
	 * 
	 * @param name
	 *            a <code>String</code> representing the name of the theater
	 */
	public void setName(String name)
	{
		this.name = name;
	}
}
