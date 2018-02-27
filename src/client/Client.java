package client;

import java.io.Serializable;

import address.Address;
import currency.Dollar;
import keyToken.KeyToken;
import keyToken.Keyed;
import phoneNumber.PhoneNumber;

/**
 * Represents a Client who performs plays at the theater.
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 */
public class Client implements Serializable, Keyed<Long>
{
	/**
	 * Serialization version.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The last used ID key.
	 */
	private static long lastId = 0L;
	
	/**
	 * The client id.
	 */
	private ID id;
	
	/**
	 * The amount of money owed to the client.
	 */
	private Dollar balanceDue;
	
	/**
	 * The client's name.
	 */
	private Name name = new Name();
	
	/**
	 * The client's street address.
	 */
	private Address address = new Address();
	
	/**
	 * The client's phone number.
	 */
	private PhoneNumber phoneNumber = new PhoneNumber();

	/**
	 * Represents the client id
	 * @author Joshua Zierman [py1422xs@metrostate.edu]
	 *
	 */
	public static class ID extends KeyToken<Client, Long>
	{

		/**
		 * Serialization version.
		 */
		private static final long serialVersionUID = 1L;

		/* (non-Javadoc)
		 * @see keyToken.KeyToken#getNextKey()
		 */
		@Override
		protected Long getNextKey()
		{
			return Client.lastId + 1;
		}

		/* (non-Javadoc)
		 * @see keyToken.KeyToken#getLastKey()
		 */
		@Override
		protected Long getLastKey()
		{
			return Client.lastId;
		}

		/* (non-Javadoc)
		 * @see keyToken.KeyToken#setLastKey(java.lang.Object)
		 */
		@Override
		protected void setLastKey(Long key)
		{
			Client.lastId = key;
		}

	}

	/**
	 * Represents the client name
	 * @author Joshua Zierman [py1422xs@metrostate.edu]
	 *
	 */
	public static class Name implements Serializable
	{
		/**
		 * Serialization version.
		 */
		private static final long serialVersionUID = 1L;
		
		/**
		 * The string representation of the name
		 */
		private String name;

		/**
		 * Gets the client's name.
		 * @return the string representation of the name.
		 */
		public String getName()
		{
			return name;
		}

		/**
		 * Sets the client's name.
		 * @param name the string representation of the name.
		 */
		public void setName(String name)
		{
			this.name = name;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString()
		{
			return getName();
		}
	}

	/**
	 * Constructs a new <code>Client</code> object.
	 * 
	 * @param name
	 *            the string representation of the client's name.
	 * @param address
	 *            the string representation of the client's address.
	 * @param phoneNumber
	 *            the string representation of the client's phone number.
	 */
	public Client(String name, String address, String phoneNumber)
	{
		this.name.setName(name);
		this.address.setAddress(address);
		this.phoneNumber.setNumber(phoneNumber);
		id = new ID();
		balanceDue = new Dollar(0.0);
	}

	/**
	 * Constructs a new <code>Client</code> object.
	 * @param name the string representation of the client's name.
	 * @param address a <code>Address</code> object that is a representation of the client's address.
	 * @param phoneNumber a <code>PhoneNumber</code> object that is a representation of the client's phone number.
	 */
	public Client(String name, Address address, PhoneNumber phoneNumber)
	{
		this.name.setName(name);
		this.address.setAddress(address);
		this.phoneNumber.setNumber(phoneNumber);
		id = new ID();
		balanceDue = new Dollar(0.0);
	}

	/**
	 * Gets the client ID.
	 * @return the <code>ID</code> object that holds the client's ID.
	 */
	public ID getID()
	{
		return id;
	}

	/**
	 * Gets the amount of money that is owed to the client.
	 * @return a <code>Dollar</code> object that represents how much money is owed to the client.
	 */
	public Dollar getBalanceDue()
	{
		return balanceDue;
	}

	/**
	 * Sets the amount of mony that is owed to the client.
	 * @param balanceDue a <code>Dollar</code> object that represents how much money is owed to the client.
	 */
	public void setBalanceDue(Dollar balanceDue)
	{
		this.balanceDue = balanceDue;
	}

	/**
	 * Gets the client's name.
	 * @return the <code>Name</code> object that holds the client's name.
	 */
	public Name getName()
	{
		return name;
	}

	/**
	 * Sets the client's name.
	 * @param name a <code>String</code> representation of the client's name.
	 */
	public void setName(String name)
	{
		this.name.setName(name);
	}

	/**
	 * Gets the client's address.
	 * @return the <code>Address</code> object that contains the client's address.
	 */
	public Address getAddress()
	{
		return address;
	}

	/**
	 * Sets the client's address.
	 * @param address the <code>Address</code> object that contains the client's address.
	 */
	public void setAddress(String address)
	{
		this.address.setAddress(address);
	}

	/**
	 * Gets the client's phone number.
	 * @return the <code>PhoneNumber</code> object that contains the client's phone number.
	 */
	public PhoneNumber getPhoneNumber()
	{
		return phoneNumber;
	}

	/**
	 * Sets the client's phone number.
	 * @param phoneNumber the <code>PhoneNumber</code> object that contains the client's phone number.
	 */
	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber.setNumber(phoneNumber);
	}

	/* (non-Javadoc)
	 * @see keyToken.Keyed#matches(java.lang.Object)
	 */
	@Override
	public boolean matches(Long key)
	{
		return getID().matches(key);
	}

}
