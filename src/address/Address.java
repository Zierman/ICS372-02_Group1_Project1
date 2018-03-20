package address;

import java.io.Serializable;

/**
 * Represents a street address.
 * 
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 */
public class Address implements Serializable
{
	/**
	 * the version of the serialization
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * a string that represents the address.
	 */
	private String address;

	/**
	 * Gets the address
	 * 
	 * @return the string representation of the address.
	 */
	public String getAddress()
	{
		return address;
	}

	/**
	 * Sets the address
	 * 
	 * @param address
	 *            an <code>Address</code> object.
	 */
	public void setAddress(Address address)
	{
		this.address = address.getAddress();
	}

	/**
	 * Sets the address
	 * 
	 * @param address
	 *            a string representation of the address.
	 */
	public void setAddress(String address)
	{
		this.address = address;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return getAddress();
	}
}
