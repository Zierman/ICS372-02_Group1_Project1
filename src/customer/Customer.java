package customer;

import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;

import com.sun.org.apache.xml.internal.serializer.SerializationHandler;

import address.Address;
import client.ClientList;
import exceptions.CardAlreadyInListException;
import exceptions.NoCardFoundException;
import keyToken.KeyToken;
import keyToken.Keyed;
import phoneNumber.PhoneNumber;
import storage.FileIO;
import storage.Savable;
import ticket.Ticket;
import ownership.Owned;

/**
 * Represents a Customer who attends plays at the theater.
 * @author Troy Novak [wh1617wd@metrostate.edu]
 *
 */
public class Customer implements Serializable, Keyed<Long>
{
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
		 * @param cardNum The card number
		 * @param cardExpiry the card's experation
		 * @param owner the card's owner
		 */
		public CreditCard(String cardNum, String cardExpiry, Customer owner){
			this.cardNumber = cardNum;
			this.cardExpiration = cardExpiry;
			this.owner = owner;
		}
		
		/**
		 * returns this instance's cardExpiration
		 * @return cardExpiration
		 */
		public String getCardExpiration(){
			return this.cardExpiration;
		}
		
		/**
		 * returns this istance's cardNumber
		 * @return cardNumber
		 */
		public String getCardNumber(){
			return this.cardNumber;
		}
		
		/* (non-Javadoc)
		 * @see ownership.owned#getOwner()
		 */
		@Override
		public Customer getOwner()
		{
			return owner;
		}
		
		/**
		 * sets this instance's cardExpiration
		 * @param newCardExpiration the new card expiration date
		 */
		public void setCardExpiration(String newCardExpiration){
			this.cardExpiration = newCardExpiration;
		}

		/**
		 * sets this instance's cardNumber
		 * @param newCardNumber the new credit card number.
		 */
		public void setCardNumber(String newCardNumber){
			this.cardNumber = newCardNumber;
		}

		@Override
		public void setOwner(Customer owner)
		{
			this.owner = owner;
			
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
		 * @see keyToken.ValueToken#getLastValue()
		 */
		@Override
		protected Long getLastValue()
		{
			return Customer.lastID;
		}

		/* (non-Javadoc)
		 * @see keyToken.ValueToken#getNextValue()
		 */
		@Override
		protected Long getNextValue()
		{
			return Customer.lastID + 1;
		}

		/* (non-Javadoc)
		 * @see keyToken.ValueToken#setLastValue(java.lang.Object)
		 */
		@Override
		protected void setLastValue(Long key)
		{
			Customer.lastID = key;
			
		}
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
	 * Serialization version.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The last used ID key.
	 */
	protected static Long lastID = Long.MIN_VALUE;
	
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
	 * List to hold a customers purchased tickets
	 */
	private LinkedList<Ticket> ticketsList = new LinkedList<Ticket>();
	
	/**
	 * Customer class constructor that initializes instances attributes,
	 * generates an ID unique to the instance, and initializes a list of
	 * CreditCard objects unique to the instance.
	 * @author Troy Novak [wh1617wd@metrostate.edu]
	 * @param name the customer's full name
	 * @param address the customer's street address
	 * @param phoneNumber the customer's phone number
	 * @param cardNumber The customer's credit card number
	 * @param cardExpiration The credit card's expiration date
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
	 * Adds a ticket to this customer's ticket list
	 * @param ticket The ticket to be added to the ticket list
	 * @return true if the ticket was added
	 * @see java.util.LinkedList#add(java.lang.Object)
	 */
	public boolean add(Ticket ticket)
	{
		return ticketsList.add(ticket);
	}
	
	/**
	 * adds a new card to cardList as long as that card doesn't already exist
	 * within cardList 
	 * @param cardNum The credit card number
	 * @param cardExpiry the expiration date
	 * @throws CardAlreadyInListException  if the card is already in list
	 * @return true if the card is found or false if not. 
	 */
	public boolean addCreditCard(String cardNum, String cardExpiry) throws CardAlreadyInListException{
		// if card doesn't already exist within cardList...
		if(!exists(cardNum))
			// ...add new card to cardList
			return cardList.add(new CreditCard(cardNum,cardExpiry, this));
		
		// if card does already exist within cardList...
		else{
			throw new CardAlreadyInListException();
		}
	}
	
	/**
	 * searches cardList for specified cardNumber
	 * @param cardNum The credit card number
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
	 * returns this instance's address
	 * @return address
	 */
	public Address getAddress(){
		return this.address;
	}
	
	/**
	 * returns this instance's cardList
	 * @return cardList
	 */
	public LinkedList<CreditCard> getCardList(){
		return this.cardList;
	}
	
	/**
	 * returns this instance's id
	 * @return id
	 */
	public ID getID(){
		return this.id;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see keyToken.Keyed#getKey(java.lang.Object)
	 */
	@Override
	public Long getKey()
	{
		return this.id.getKeyValue();
	}
	
	/**
	 * returns this instance's name
	 * @return name
	 */
	public Name getName(){
		return this.name;
	}
	
	/**
	 * returns this instance's phoneNumber
	 * @return phoneNumber
	 */
	public PhoneNumber getPhoneNumber(){
		return this.phoneNumber;
	}
	
	/**
	 * gets the list of tickets owned
	 * @return the ticketsList the list of tickets
	 */
	public LinkedList<Ticket> getTicketsList()
	{
		return ticketsList;
	}
	
	/* (non-Javadoc)
	 * @see keyToken.Keyed#matches(java.lang.Object)
	 */
	@Override
	public boolean matches(Long key)
	{
		return getID().matches(key);
	}

	/**
	 * removes a card from cardList as long as that card exists within cardList
	 * @param cardNum The credit card number
	 * @throws NoCardFoundException if the credit card number doesn't match any in list.
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
	 * sets this instance's address
	 * @param custAddress customer's street address
	 */
	public void setAddress(String custAddress){
		this.address.setAddress(custAddress);
	}
	
	/**
	 * sets this instance's id
	 * @param custID the identification of the customer
	 */
	public void setID(ID custID){
		this.id = custID;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see keyToken.Keyed#setKey(java.lang.Object)
	 */
	@Override
	public void setKey(Long key)
	{

		this.id.setValue(key);

	}

	/**
	 * sets this instance's name
	 * @param custName the full name of the customer
	 */
	public void setName(String custName){
		this.name.setName(custName);
	}

	/**
	 * sets this instance's phoneNumber
	 * @param phoneNum The phone number of the customer
	 */
	public void setPhoneNum(String phoneNum){
		this.phoneNumber.setNumber(phoneNum);
	}
}
