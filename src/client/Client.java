package client;

import java.io.IOException;
import java.io.Serializable;

import address.Address;
import curancy.Dollar;
import keyToken.KeyToken;
import phoneNumber.PhoneNumber;
import storage.FileIO;
import storage.Savable;

/**
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 */
public class Client implements Savable, Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;
	private static long lastId = Long.MIN_VALUE; // I'm assuming we will never
													// have enough clients to
													// roll over
	private Dollar balanceDue;
	private Name name;
	private Address address; 
	private PhoneNumber phoneNumber; 
	
	/**
	 * @author Joshua Zierman [py1422xs@metrostate.edu]
	 *
	 */
	public class ID extends KeyToken<Client>
	{
		
	}
	
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

	public Client(String name, String address, String phoneNumber)
	{
		this.name.setName(name);
		this.address.setAddress(address);
		this.phoneNumber.setNumber(phoneNumber);
		id = ++lastId;
	}

	public Client(String name, Address address, PhoneNumber phoneNumber)
	{
		this.name.setName(name);
		this.address.setAddress(address);
		this.phoneNumber.setNumber(phoneNumber);
		id = ++lastId;
	}

	@Override
	public void save() throws IOException
	{
		FileIO.write(this, ClientList.FILENAME);

	}

	public long getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
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

	public Address getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address.setAddress(address);
	}

	public PhoneNumber getPhoneNumber()
	{
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber.setNumber(phoneNumber);
	}

}
