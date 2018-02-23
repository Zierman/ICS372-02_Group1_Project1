package phoneNumber;

import java.io.Serializable;

/**
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 */
public class PhoneNumber implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String number;
	
	@Override
	public String toString()
	{
		return getNumber();
	}

	public String getNumber()
	{
		return number;
	}

	public void setNumber(String number)
	{
		this.number = number;
	}

	public void setNumber(PhoneNumber phoneNumber)
	{
		this.number = phoneNumber.getNumber();
		
	}
}
