package customer;

import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;

import address.Address;
import keyToken.KeyToken;
import phoneNumber.PhoneNumber;
import storage.Savable;


public class Customer implements Savable, Serializable
{
	private static Long lastID = Long.MIN_VALUE;
	private ID id;
	private Name name;
	private Address address;
	private PhoneNumber phoneNumber;
	private LinkedList<CreditCard> cardList;
	
	/**
	 * @author Troy Novak [wh1617wd@metrostate.edu]
	 * @param custName
	 * @param custAddress
	 * @param custPhone
	 * @param cardNum
	 * @param cardExpiry
	 */
	public Customer(String custName, String custAddress, String custPhone, String cardNum, String cardExpiry){
		this.name.setName(custName);
		this.address.setAddress(custAddress);
		this.phoneNumber.setNumber(custPhone);
		id = new ID();
		cardList.add(new CreditCard(cardNum, cardExpiry));
		
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
	
	/**
	 * @author Joshua Zierman [py1422xs@metrostate.edu]
	 *
	 */
	public class ID extends KeyToken<Customer, Long>
	{

		@Override
		protected Long getNextKey()
		{
			return Customer.lastID + 1;
		}

		@Override
		protected Long getLastKey()
		{
			// TODO Auto-generated method stub
			return Customer.lastID;
		}

		@Override
		protected void setLastKey(Long key)
		{
			Customer.lastID = key;
			
		}
	}
	
	/**
	 * @author Troy Novak [wh1617wd@metrostate.edu]
	 * 
	 */
	public class CreditCard {
		private String cardNumber;
		private String cardExpiration;
		
		public CreditCard(String cardNum, String cardExpiry){
			cardNumber = cardNum;
			cardExpiration = cardExpiry;
		}
	}
	
	@Override
	public void save() throws IOException
	{
		// TODO Auto-generated method stub

	}
	
	public void setPhoneNum(String phoneNum){
		this.phoneNumber.setNumber(phoneNum);
	}
	
	// TODO Finish

}
