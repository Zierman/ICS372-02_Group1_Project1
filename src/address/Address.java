package address;

import java.io.Serializable;

/**
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 */
public class Address implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String address;

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}
	
	@Override
	public String toString()
	{
		return getAddress();
	}

	public void setAddress(Address address)
	{
		this.address = address.getAddress();
	}
}
