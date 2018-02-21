package phoneNumber;

public class PhoneNumber
{
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
}
