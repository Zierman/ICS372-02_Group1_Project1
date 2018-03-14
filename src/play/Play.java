package play;

import java.io.Serializable;
import java.time.DateTimeException;
import java.util.Date;

import client.Client;
import currency.Dollar;
import ownership.Owned;

/**
 * A play that is performed in a theater by a client for customers.
 * 
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 */
public class Play implements Serializable, Owned<Client>
{
	/**
	 * Play name
	 * 
	 * @author Joshua Zierman [py1422xs@metrostate.edu]
	 *
	 */
	public class Name implements Serializable
	{
		/**
		 * Serialization version.
		 */
		private static final long serialVersionUID = 2L;

		/**
		 * shortest allowed length of name
		 */
		public static final int MIN_LENGTH = 0;

		/**
		 * the string that holds the name.
		 */
		private String name;

		/**
		 * Gets the name
		 * 
		 * @return a <code>String</code> representing the name
		 */
		public String getName()
		{
			return name;
		}

		/**
		 * Sets the name
		 * 
		 * @param name
		 *            a <code>String</code> representing a name.
		 */
		public void setName(String name)
		{
			if (name.length() < MIN_LENGTH)
			{
				throw new IllegalArgumentException(
						"name must be length at least " + MIN_LENGTH
								+ " characters long.");
			}
			this.name = name;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString()
		{
			return getName();
		}
	}

	/**
	 * Serialization version.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * the name of the play.
	 */
	private Name name = new Name();

	/**
	 * The client that performs the play.
	 */
	private Client owner;

	/**
	 * the date that the play starts performances.
	 */
	private Date startDate;

	/**
	 * the date that the play ends performances.
	 */
	private Date endDate;

	private Dollar regularTicketPrice;

	/**
	 * How many customers can attend a play.
	 * 
	 * Currently there is no command to change the seating capacity but it is flexible.
	 */
	private Integer seatingCapacity = 100;

	/**
	 * Creates a <code>Play</code> object
	 * 
	 * @param name
	 *            the <code>String</code> that holds the name of the play
	 * @param client
	 *            the <code>Client</code> object of the client that performs the
	 *            play.
	 * @param startDate
	 *            the <code>Date</code> object that holds the starting date.
	 * @param endDate
	 *            the <code>Date</code> object that holds the ending date.
	 * @param regularTicketPrice the price of a non-discounted ticket.
	 */
	public Play(String name, Client client, Date startDate, Date endDate, Dollar regularTicketPrice)
	{

		if (startDate.after(endDate))
		{
			throw new DateTimeException(null);
		}
		this.name.setName(name);
		this.owner = client;
		this.startDate = startDate;
		this.endDate = endDate;
		this.regularTicketPrice = regularTicketPrice;
	}

	/**
	 * Gets the end date
	 * 
	 * @return the date that the play stops showing.
	 */
	public Date getEndDate()
	{
		return endDate;
	}

	/**
	 * Gets the name
	 * 
	 * @return a <code>Name</code> object.
	 */
	public Name getName()
	{
		return name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ownership.Owned#getOwner()
	 */
	@Override
	public Client getOwner()
	{
		return owner;
	}

	/**
	 * Gets the regular ticket price for the play
	 * @return the regular ticket price for the play
	 */
	public Dollar getRegularTicketPrice()
	{

		return regularTicketPrice;
	}

	/**
	 * Gets the start date.
	 * 
	 * @return the date that the play begins showing.
	 */
	public Date getStartDate()
	{
		return startDate;
	}

	/**
	 * Sets the end date
	 * 
	 * @param endDate
	 *            the date that the play stops showing.
	 */
	public void setEndDate(Date endDate)
	{
		this.endDate = endDate;
	}

	/**
	 * Sets the name
	 * 
	 * @param name
	 *            the <code>String</code> representation of a name that
	 *            <code>this</code> name will be set to.
	 */
	public void setName(String name)
	{
		this.name.setName(name);
	}

	@Override
	public void setOwner(Client owner)
	{
		this.owner = owner;

	}

	/**
	 * Sets Regular ticket price for this play
	 * @param regularTicketPrice the regularTicketPrice to set
	 */
	public void setRegularTicketPrice(Dollar regularTicketPrice)
	{
		this.regularTicketPrice = regularTicketPrice;
	}

	/**
	 * Sets the start date
	 * 
	 * @param startDate
	 *            the date that the play begins showing.
	 */
	public void setStartDate(Date startDate)
	{
		this.startDate = startDate;
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
