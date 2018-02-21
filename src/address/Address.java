package address;

public class Address
{
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
