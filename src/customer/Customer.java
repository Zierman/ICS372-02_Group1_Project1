package customer;

import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;

import com.sun.org.apache.xml.internal.serializer.SerializationHandler;

import address.Address;
import client.ClientList;
import exceptions.NoCardFoundException;
import keyToken.KeyToken;
import keyToken.Keyed;
import phoneNumber.PhoneNumber;
import storage.FileIO;
import storage.Savable;
import ownership.Owned;

/**
 * Represents a Customer who attends plays at the theater.
 * @author Troy Novak [wh1617wd@metrostate.edu]
 *
 */
public class Customer implements Serializable, Keyed<Long>
{
	/**
	 * Serialization version.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The last used ID key.
	 */
	private static Long lastID = Long.MIN_VALUE;
	
	/**
	 * The customer id.
	 */
	private ID id;
	
	/**
	 * The customer's name.
	 */
	private Name name = new Name();
	
	/**
	 * The customer's street address.
	 */
	private Address address = new Address();
	
	/**
	 * The customer's phone number.
	 */
	private PhoneNumber phoneNumber = new PhoneNumber();
	
	/**
	 * List to hold customer's credit card information
	 */
	private LinkedList<CreditCard> cardList = new LinkedList<CreditCard>();
	
	/**
	 * Customer class constructor that initializes instances attributes,
	 * generates an ID unique to the instance, and initializes a list of
	 * CreditCard objects unique to the instance.
	 * @author Troy Novak [wh1617wd@metrostate.edu]
	 * @param custName
	 * @param custAddress
	 * @param custPhone
	 * @param cardNum
	 * @param cardExpiry
	 */
	public Customer(String name, String address,
					String phoneNumber, String cardNumber, String cardExpiration){
		// initialize customer's information
		this.name.setName(name);
		this.address.setAddress(address);
		this.phoneNumber.setNumber(phoneNumber);
		// give customer a unique customer ID
		id = new ID();
		// initialize the customer's list of credit cards
		cardList.add(new CreditCard(cardNumber, cardExpiration, this));
		
	}
	
	/**
	 * subclass used to create a name for each Customer
	 * @author Joshua Zierman [py1422xs@metrostate.edu]
	 *
	 */
	public class Name implements Serializable
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
		 * Gets the customer's name.
		 * @return the string representation of the name.
		 */
		public String getName()
		{
			return name;
		}

		/**
		 * Sets the customer's name.
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
	 * subclass used to create an ID for each Customer
	 * @author Joshua Zierman [py1422xs@metrostate.edu]
	 *
	 */
	public class ID extends KeyToken<Customer, Long>
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
			return Customer.lastID + 1;
		}

		/* (non-Javadoc)
		 * @see keyToken.KeyToken#getLastKey()
		 */
		@Override
		protected Long getLastKey()
		{
			// TODO Auto-generated method stub
			return Customer.lastID;
		}

		/* (non-Javadoc)
		 * @see keyToken.KeyToken#setLastKey(java.lang.Object)
		 */
		@Override
		protected void setLastKey(Long key)
		{
			Customer.lastID = key;
			
		}
	}
	
	/**
	 * subclass used to create individual objects for customer's credit cards
	 * @author Troy Novak [wh1617wd@metrostate.edu]
	 * 
	 */
	public class CreditCard implements Owned<Customer>,Serializable{
		
		/**
		 * Serialization version.
		 */
		private static final long serialVersionUID = 1L;
		
		/**
		 * the String representation of the credit card number
		 */
		private String cardNumber;
		
		/**
		 * the String representation of the card expiration date
		 */
		private String cardExpiration;
		
		/**
		 * the instance of Cutomer that owns the credit card
		 */
		private Customer owner;
		
		/**
		 * creates a CreditCard object
		 * @param cardNum
		 * @param cardExpiry
		 * @param owner
		 */
		public CreditCard(String cardNum, String cardExpiry, Customer owner){
			this.cardNumber = cardNum;
			this.cardExpiration = cardExpiry;
			this.owner = owner;
		}
		
		/**
		 * returns this istance's cardNumber
		 * @return cardNumber
		 */
		public String getCardNumber(){
			return this.cardNumber;
		}
		
		/**
		 * sets this instance's cardNumber
		 * @param newCardNumber
		 */
		public void setCardNumber(String newCardNumber){
			this.cardNumber = newCardNumber;
		}
		
		/**
		 * returns this instance's cardExpiration
		 * @return
		 */
		public String getCardExpiration(){
			return this.cardExpiration;
		}
		
		/**
		 * sets this instance's cardExpiration
		 * @param newCardExpiration
		 */
		public void setCardExpiration(String newCardExpiration){
			this.cardExpiration = newCardExpiration;
		}

		/* (non-Javadoc)
		 * @see ownership.owned#getOwner()
		 */
		@Override
		public Customer getOwner()
		{
			return owner;
		}
	}
	
	/**
	 * adds a new card to cardList as long as that card doesn't already exist
	 * within cardList
	 * @param cardNum
	 * @param cardExpiry
	 * @throws NoCardFoundException 
	 */
	public boolean addCreditCard(String cardNum, String cardExpiry) throws NoCardFoundException{
		// if card doesn't already exist within cardList...
		if(!exists(cardNum))
			// ...add new card to cardList
			return cardList.add(new CreditCard(cardNum,cardExpiry, this));
		
		// if card does already exist within cardList...
		else{
			throw new NoCardFoundException();
		}
	}
	
	/**
	 * removes a card from cardList as long as that card exists within cardList
	 * @param cardNum
	 * @throws NoCardFoundException 
	 */
	public void removeCreditCard(String cardNum) throws NoCardFoundException{
		// if card exists within cardList...
		if(exists(cardNum)){
			// ...remove it from cardList
			for(int i = 0; i < cardList.size(); i++){
				if(cardList.get(i).getCardNumber().equals(cardNum)){
					cardList.remove(i);
				}
			}
		}
		// if card doesn't exist within cardList...
		else{
			throw new NoCardFoundException();
		}
	}
	
	/**
	 * searches cardList for specified cardNumber
	 * @param cardNum
	 * @return true if found
	 * @return false if not found
	 */
	public boolean exists(String cardNum){
		// list traversal loop
		for(int i = 0; i < cardList.size(); i++){
			// if target is found...
			if(cardList.get(i).getCardNumber().equals(cardNum))
				// ...returns true...
				return true;
		}
		// ...otherwise returns false
		return false;
	}
	
	/**
	 * returns this instance's id
	 * @return id
	 */
	public ID getID(){
		return this.id;
	}
	
	/**
	 * sets this instance's id
	 * @param custID
	 */
	public void setID(ID custID){
		this.id = custID;
	}
	
	/**
	 * returns this instance's name
	 * @return name
	 */
	public Name getName(){
		return this.name;
	}
	
	/**
	 * sets this instance's name
	 * @param custName
	 */
	public void setName(String custName){
		this.name.setName(custName);
	}
	
	/**
	 * returns this instance's address
	 * @return address
	 */
	public Address getAddress(){
		return this.address;
	}
	
	/**
	 * sets this instance's address
	 * @param custAddress
	 */
	public void setAddress(String custAddress){
		this.address.setAddress(custAddress);
	}
	
	/**
	 * returns this instance's phoneNumber
	 * @return phoneNumber
	 */
	public PhoneNumber getPhoneNumber(){
		return this.phoneNumber;
	}
	
	/**
	 * sets this instance's phoneNumber
	 * @param phoneNum
	 */
	public void setPhoneNum(String phoneNum){
		this.phoneNumber.setNumber(phoneNum);
	}

	public LinkedList<CreditCard> getCardList(){
		return this.cardList;
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
