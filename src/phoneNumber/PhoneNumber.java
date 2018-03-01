package phoneNumber;

import java.io.Serializable;

/**
 * Represents a phone number
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 */
public class PhoneNumber implements Serializable
{
	/**
	 * Serialization version
	 */
	private static final long serialVersionUID = 1L;
	private String number;
	
	/**
	 * Gets the phone number.
	 * @return a <code>String</code> representation of the phone number.
	 */
	public String getNumber()
	{
		return number;
	}

	/**
	 * Sets the phone number
	 * @param phoneNumber a <code>PhoneNumber</code> object.
	 */
	public void setNumber(PhoneNumber phoneNumber)
	{
		this.number = phoneNumber.getNumber();
		
	}

	/**
	 * Sets the phone number.
	 * @param number the <code>StringS</code> representation of the phone number.
	 */
	public void setNumber(String number)
	{
		this.number = number;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return getNumber();
	}
}
