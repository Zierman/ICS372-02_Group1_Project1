package ticket;

import java.io.Serializable;
import java.util.Date;

import currency.Dollar;
import customer.Customer;
import keyToken.KeyToken;
import keyToken.Keyed;
import ownership.Owned;
import play.Play;

/**
 * Tickets are objects grant entry to a play.
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 */
public abstract class Ticket implements Keyed<Long>, Owned<Customer>, Serializable
{
	public Ticket(Date dateOfShow, Play play, Customer owner)
	{
		super();
		this.dateOfShow = dateOfShow;
		this.play = play;
		this.owner = owner;
		this.serialNumber = new SerialNumber();
	}



	/**
	 * The serial number of the ticket.
	 */
	private SerialNumber serialNumber;
	
	private Date dateOfShow;
	
	private String TypeOfTicket;
	
	private double priceMultiplier;
	
	private Dollar priceOfTicket;
	
	private String extraMessage;
	
	private Play play;

	private Customer owner;
	
	
	
	/**
	 * gets the date of show
	 * @return the dateOfShow
	 */
	public Date getDateOfShow()
	{
		return dateOfShow;
	}



	/**
	 * sets the date of the show 
	 * @param dateOfShow the dateOfShow to set
	 */
	public void setDateOfShow(Date dateOfShow)
	{
		this.dateOfShow = dateOfShow;
	}



	/**
	 * gets the type of ticket
	 * @return the typeOfTicket
	 */
	public String getTypeOfTicket()
	{
		return TypeOfTicket;
	}



	/**
	 * sets the type of ticket
	 * @param typeOfTicket the typeOfTicket to set
	 */
	public void setTypeOfTicket(String typeOfTicket)
	{
		TypeOfTicket = typeOfTicket;
	}



	/**
	 * gets the price multiplier
	 * @return the priceMultiplier
	 */
	public double getPriceMultiplier()
	{
		return priceMultiplier;
	}



	/**
	 * Sets the price multiplier
	 * @param priceMultiplier the priceMultiplier to set
	 */
	public void setPriceMultiplier(double priceMultiplier)
	{
		this.priceMultiplier = priceMultiplier;
	}



	/**
	 * Gets the price of the ticket
	 * @return the priceOfTicket
	 */
	public Dollar getPriceOfTicket()
	{
		return priceOfTicket;
	}



	/**
	 * Sets the price of the ticket
	 * @param priceOfTicket the priceOfTicket to set
	 */
	public void setPriceOfTicket(Dollar priceOfTicket)
	{
		this.priceOfTicket = priceOfTicket;
	}



	/**
	 * Gets the extra message
	 * @return the extraMessage
	 */
	public String getExtraMessage()
	{
		return extraMessage;
	}



	/**
	 * Sets the extra message
	 * @param extraMessage the extraMessage to set
	 */
	public void setExtraMessage(String extraMessage)
	{
		this.extraMessage = extraMessage;
	}



	/**
	 * Gets the play
	 * @return the play
	 */
	public Play getPlay()
	{
		return play;
	}



	/**
	 * Sets the play.
	 * @param play the play to set
	 */
	public void setPlay(Play play)
	{
		this.play = play;
	}



	/**
	 * Holds a serial number assosiated with the ticket.
	 * @author Joshua Zierman [py1422xs@metrostate.edu]
	 *
	 */
	public static class SerialNumber extends KeyToken<Ticket, Long>
	{
		/**
		 * Serial Version Number
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * The last serial number used
		 */
		private static Long lastValue = 0L;

		/* (non-Javadoc)
		 * @see keyToken.KeyToken#getNextValue()
		 */
		@Override
		protected Long getNextValue()
		{

			return lastValue + 1;
		}

		/* (non-Javadoc)
		 * @see keyToken.KeyToken#getLastValue()
		 */
		@Override
		protected Long getLastValue()
		{

			return lastValue;
		}

		/* (non-Javadoc)
		 * @see keyToken.KeyToken#setLastValue(java.lang.Comparable)
		 */
		@Override
		protected void setLastValue(Long serialNumberValue)
		{
			lastValue = serialNumberValue;

		}

	}

	/* (non-Javadoc)
	 * @see ownership.Owned#setOwner(java.lang.Object)
	 */
	@Override
	public void setOwner(Customer owner)
	{
		this.owner = owner;
	}

	/* (non-Javadoc)
	 * @see ownership.Owned#getOwner()
	 */
	@Override
	public Customer getOwner()
	{
		
		return owner;
	}



	/**
	 * Sets the key to a provided key. used in loading from storage. 
	 * @param keyToken
	 * @see keyToken.KeyToken#setKey(keyToken.KeyToken)
	 */
	public void setKey(KeyToken<Ticket, Long> keyToken)
	{
		serialNumber.setKey(keyToken);
	}



	/**
	 * Checks to see if the key value in the parameter is a match for this item's key
	 * @param key the key value to be checked against
	 * @return true if the two match or false if they don't.
	 * @see keyToken.KeyToken#matches(keyToken.KeyToken)
	 */
	public boolean matches(KeyToken<Ticket, Long> key)
	{
		return serialNumber.matches(key);
	}



	/* (non-Javadoc)
	 * @see keyToken.Keyed#matches(java.lang.Object)
	 */
	@Override
	public boolean matches(Long keyValue)
	{
		return serialNumber.matches(keyValue);
	}



	/* (non-Javadoc)
	 * @see keyToken.Keyed#setKey(java.lang.Object)
	 */
	@Override
	public void setKey(Long keyValue)
	{
		serialNumber.setValue(keyValue);
		
	}


	@Override
	public Long getKey()
	{
		
		return serialNumber.getKeyValue();
	}
	

}
