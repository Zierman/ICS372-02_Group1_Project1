package customer;

import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;

import com.sun.org.apache.xml.internal.serializer.SerializationHandler;

import address.Address;
import client.ClientList;
import keyToken.KeyToken;
import keyToken.Keyed;
import phoneNumber.PhoneNumber;
import storage.FileIO;
import storage.Savable;

//TODO document all of this

public class Customer implements Serializable, Keyed<Long>
{
	private static Long lastID = Long.MIN_VALUE;
	private ID id;
	private Name name;
	private Address address;
	private PhoneNumber phoneNumber;
	private LinkedList<CreditCard> cardList;
	
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
	public Customer(String custName, String custAddress,
					String custPhone, String cardNum, String cardExpiry){
		// initialize customer's information
		this.name.setName(custName);
		this.address.setAddress(custAddress);
		this.phoneNumber.setNumber(custPhone);
		// give customer a unique customer ID
		id = new ID();
		// initialize the customer's list of credit cards
		cardList.add(new CreditCard(cardNum, cardExpiry));
		
	}
	
	/**
	 * subclass used to create a name for each Customer
	 * @author Joshua Zierman [py1422xs@metrostate.edu]
	 *
	 */
	public class Name implements Serializable
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
	 * subclass used to create an ID for each Customer
	 * @author Joshua Zierman [py1422xs@metrostate.edu]
	 *
	 */
	public class ID extends KeyToken<Customer, Long>
	{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

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
	 * subclass used to create individual objects for customer's credit cards
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
	}
	
	
	/*
	 * !! JOSH !!
	 *
	 * let me know if you think it would be a good idea to create custom
	 * Exceptions to be thrown when a user attempts to add a duplicate of a
	 * card already in the list, or if the card that they are attempting to
	 * delete from the list does not exist. I couldn't really find solid
	 * options from the list of java's pre-defined Exceptions...
	 * NoSuchFieldException or ClassNotFoundException could work, but they
	 * don't seem fully appropriate.
	 * 
	 * >>FROM JOSH<<
	 * 
	 * NoSuchField and ClassNotFound don't work for this situation.
	 * IllegalArgumentException might work because the card number 
	 * is illegal. You could also create a new class named 
	 * DuplicateCardException that extends Exception.  
	 */
	
	/**
	 * adds a new card to cardList as long as that card doesn't already exist
	 * within cardList
	 * @param cardNum
	 * @param cardExpiry
	 */
	public void addCreditCard(String cardNum, String cardExpiry){
		// if card doesn't already exist within cardList...
		if(!exists(cardNum))
			// ...add new card to cardList
			cardList.add(new CreditCard(cardNum,cardExpiry));
		
		// if card does already exist within cardList...
		else{
			// ...throw custom exception?
		}
	}
	
	/**
	 * removes a card from cardList as long as that card exists within cardList
	 * @param cardNum
	 */
	public void removeCreditCard(String cardNum){
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
			// ...throw custom exception?
		}
	}
	
	/**
	 * searches cardList for specified cardNumber
	 * @param target
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

	@Override
	public boolean matches(Long key)
	{
		return getID().matches(key);
	}
	
	// TODO Finish

}
