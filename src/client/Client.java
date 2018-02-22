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
public class Client implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static long lastId = Long.MIN_VALUE; 
	private ID id;
	private Dollar balanceDue;
	private Name name = new Name();
	private Address address = new Address(); 
	private PhoneNumber phoneNumber = new PhoneNumber(); 
	
	/**
	 * @author Joshua Zierman [py1422xs@metrostate.edu]
	 *
	 */
	public static class ID extends KeyToken<Client, Long>
	{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		protected Long getNextKey()
		{
			return Client.lastId + 1;
		}

		@Override
		protected Long getLastKey()
		{
			return Client.lastId;
		}

		@Override
		protected void setLastKey(Long key)
		{
			Client.lastId = key;		
		}
		
	}
	
	/**
	 * @author Joshua Zierman [py1422xs@metrostate.edu]
	 *
	 */
	public static class Name
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
		id = new ID();
	}

	public Client(String name, Address address, PhoneNumber phoneNumber)
	{
		this.name.setName(name);
		this.address.setAddress(address);
		this.phoneNumber.setNumber(phoneNumber);
		id = new ID();
	}

	public ID getId()
	{
		return id;
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
