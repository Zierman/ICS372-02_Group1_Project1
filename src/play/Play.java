package play;

import java.io.IOException;
import java.io.Serializable;
import java.time.DateTimeException;
import java.util.Date;

import address.Address;
import client.Client;
import currency.Dollar;
import keyToken.KeyToken;
import ownership.Owned;
import phoneNumber.PhoneNumber;
import storage.FileIO;
import storage.Savable;

//TODO document all of this
/**
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 */
public class Play implements Serializable, Owned<Client>
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Name name = new Name();
	private Client owner;
	private Date startDate;
	private Date endDate;

	/**
	 * @author Joshua Zierman [py1422xs@metrostate.edu]
	 *
	 */
	public class Name implements Serializable
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public static final int MIN_LENGTH = 2;
		private String name;

		public String getName()
		{
			return name;
		}

		public void setName(String name)
		{
			if(name.length() < MIN_LENGTH)
			{
				throw new IllegalArgumentException("name must be length at least " + MIN_LENGTH + " characters long.");
			}
			this.name = name;
		}

		@Override
		public String toString()
		{
			return getName();
		}
	}

	public Play(String name, Client client, Date startDate, Date endDate)
	{

		if (startDate.after(endDate))
		{
			throw new DateTimeException(null);
		}
		this.name.setName(name);
		this.owner = client;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Name getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name.setName(name);
	}

	public Date getStartDate()
	{
		return startDate;
	}

	public void setStartDate(Date startDate)
	{
		this.startDate = startDate;
	}

	public Date getEndDate()
	{
		return endDate;
	}

	public void setEndDate(Date endDate)
	{
		this.endDate = endDate;
	}

	/* (non-Javadoc)
	 * @see ownership.Owned#getOwner()
	 */
	@Override
	public Client getOwner()
	{
		return owner;
	}

}
