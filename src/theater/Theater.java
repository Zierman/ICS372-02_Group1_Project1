/**
 * 
 */
package theater;

import java.io.IOException;
import client.Client;
import client.ClientList;
import currency.Dollar;
import customer.Customer;
import customer.CustomerList;
import exceptions.CardAlreadyInListException;
import exceptions.NoCardFoundException;
import exceptions.NoKeyTokenFoundException;
import exceptions.OverpayingClientException;
import play.Play;
import play.PlayList;
import singleton.ReadResolveable;
import storage.FileIO;
import storage.Loadable;
import storage.Savable;
import ticket.Ticket;
import ticket.TicketList;

/**
 * Represents a theater that shows plays performed by clients to customers.
 * 
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 */
public class Theater implements ReadResolveable<Theater>, Loadable, Savable
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
	 * How many customers can attend a play.
	 */
	private Integer seatingCapacity;

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
	 * @throws Exception if used to try to create a base type Theater
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
	 * @param i an integer with no significance other than giving it a
	 *            different signature than the protected constructor.
	 */
	private Theater(int i)
	{
	}

	/**
	 * Adds a new client
	 * 
	 * @param client {@link client.Client} to be added.
	 * @return true if added, false if not.
	 */
	public boolean add(Client client)
	{
		return clientList.add(client);
	}

	/**
	 * Adds a new customer
	 * 
	 * @param customer a {@link customer.Customer} to be added.
	 * @return true if added, false if not.
	 */
	public boolean add(Customer customer)
	{
		return customerList.add(customer);
	}

	/**
	 * adds a new credit card
	 * 
	 * @param customer The customer who is adding the credit card.
	 * @param cardNumber the credit card number for the new credit card.
	 * @param cardExpiration the expiration date of the new card.
	 * @return true if added, false if not
	 * @throws CardAlreadyInListException if the card that is being added was already in list.
	 */
	public boolean add(Customer customer, String cardNumber, String cardExpiration) throws CardAlreadyInListException{
		return customer.addCreditCard(cardNumber, cardExpiration);
	}

	/**
	 * Adds a new play
	 * 
	 * @param play a {@link play.Play} tobe added.
	 * @return true if added, false if not.
	 */
	public boolean add(Play play)
	{
		return playList.add(play);
	}

	/* (non-Javadoc)
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
		catch(Exception e)
		{
			
		}
		return true;
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
	 * Gets the seating capacity.
	 * 
	 * @return an <code>Integer</code> of the current seating capacity.
	 */
	public Integer getSeatingCapacity()
	{
		return seatingCapacity;
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
		FileIO theaterFile = FileIO.startRead(FILENAME);
		instance().setName((String) theaterFile.read());
		instance().setSeatingCapacity((Integer) theaterFile.read());
		theaterFile.close();
		clientList.load();
		customerList.load();
		playList.load();
	}

	public void pay(Client client, Dollar dollars) throws OverpayingClientException
	{
		if(client.getBalanceDue().compareTo(dollars) < 0)
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
	 * @param customer the customer who owns the credit card to be removed
	 * @param cardNumber the credit card number of the card to remove.
	 * @throws NoCardFoundException if the credit card number doesn't match any card in list.
	 */
	public void removeCreditCard(Customer customer, String cardNumber) throws NoCardFoundException{
		customer.removeCreditCard(cardNumber);
	}

	/**
	 * Removes a client with matching id
	 * 
	 * @param id a <code>Long</code> that represents the Id
	 * @throws NoKeyTokenFoundException if no match is found
	 */
	public void removeMatchedClient(Long id) throws NoKeyTokenFoundException
	{
		clientList.removeMatched(id);
	}
	
	/**
	 * Removes a customer with a matching id
	 * 
	 * @param id a <code>Long</code> that represents the Id
	 * @throws NoKeyTokenFoundException if no match is found
	 */
	public void removeMatchedCustomer(Long id) throws NoKeyTokenFoundException
	{
		customerList.removeMatched(id);
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
		theaterFile.write(seatingCapacity);
		theaterFile.close();
		clientList.save();
		customerList.save();
		playList.save();
	}
	
	/**
	 * Sells a ticket
	 * @param ticket The ticket to be sold
	 */
	public void Sell(Ticket ticket)
	{
		ticketList.add(ticket);
		increaseDebt(ticket.getPlay().getOwner(), ticket.getPriceOfTicket().half());
	}

	/**
	 * Sets the name of the theater.
	 * 
	 * @param name a <code>String</code> representing the name of the theater
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	
	/**
	 * Sets the seating capacity.
	 * 
	 * @param seatingCapacity an <code>Integer</code> of the current seating capacity.
	 */
	public void setSeatingCapacity(Integer seatingCapacity)
	{
		this.seatingCapacity = seatingCapacity;
	}
}
