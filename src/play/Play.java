package play;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

import address.Address;
import client.Client;
import curancy.Dollar;
import keyToken.KeyToken;
import phoneNumber.PhoneNumber;
import storage.FileIO;
import storage.Savable;

/**
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 */
public class Play implements Savable, Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Dollar balanceDue;
	private Name name;
	private Client client;
	private Date startDate;
	private Date endDate;
	
	
	
	/**
	 * @author Joshua Zierman [py1422xs@metrostate.edu]
	 *
	 */
	public class Name
	{
		private String name;

		public String getName()
		{
			return name;
		}

		public void setName(String name)
		{
			this.name = name;
		}
		
		@Override
		public String toString()
		{
			return getName();
		}
	}

	@Override
	public void save() throws IOException
	{
		FileIO.write(this, PlayList.FILENAME);

	}

	public Dollar getBalanceDue()
	{
		return balanceDue;
	}

	public void setBalanceDue(Dollar balanceDue)
	{
		this.balanceDue = balanceDue;
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

	public Client getClient()
	{
		return client;
	}

	public void setClient(Client client)
	{
		this.client = client;
	}

}
