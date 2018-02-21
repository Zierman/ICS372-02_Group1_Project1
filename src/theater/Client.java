package theater;

import java.io.IOException;
import java.io.Serializable;

import curancy.Dollar;
import storage.FileIO;

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
	private String name;
	private String address; // change to Address type if/when we create Address
							// class
	private String phoneNumber; // change to PhoneNumber type if/when we create
								// PhoneNumber class

	Client(String name, String address, String phoneNumber)
	{
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
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

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getPhoneNumber()
	{
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}

}
