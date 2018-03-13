package ticket;

import java.io.Serializable;
import java.util.Date;

import currency.Dollar;
import customer.Customer;
import customer.Customer.CreditCard;
import exceptions.DateIsValidAssertionException;
import exceptions.DateOutOfBoundsException;
import keyToken.KeyToken;
import keyToken.Keyed;
import ownership.Owned;
import play.Play;

/**
 * Tickets are objects grant entry to a play.
 * 
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 */
public abstract class Ticket
		implements Keyed<Long>, Owned<Customer>, Serializable
{
	/**
	 * Holds a serial number assosiated with the ticket.
	 * 
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

		/*
		 * (non-Javadoc)
		 * 
		 * @see keyToken.KeyToken#getLastValue()
		 */
		@Override
		protected Long getLastValue()
		{

			return lastValue;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see keyToken.KeyToken#getNextValue()
		 */
		@Override
		protected Long getNextValue()
		{

			return lastValue + 1;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see keyToken.KeyToken#setLastValue(java.lang.Comparable)
		 */
		@Override
		protected void setLastValue(Long serialNumberValue)
		{
			lastValue = serialNumberValue;

		}

	}

	/**
	 * Serial Version
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The serial number of the ticket.
	 */
	protected SerialNumber serialNumber;

	/**
	 * The date that this ticket is valid to see the show
	 */
	protected Date dateOfShow;

	/**
	 * A string that tells what type of ticket this is
	 */
	protected final String typeOfTicket;

	/**
	 * The multiplier that is applied to the regular ticket price of the play to determine the starting price of this type of ticket.
	 */
	protected double priceMultiplier;

	/**
	 * The current price of this ticket
	 */
	protected Dollar priceOfTicket;

	/**
	 * A optional message to be printed on a ticket.
	 */
	protected String extraMessage;

	/**
	 * The play that this ticket is for.
	 */
	protected Play play;

	/**
	 * The owner of this ticket
	 */
	protected Customer owner;
	
	protected boolean hasExtraMessage;
	
	protected CreditCard creditCard;

	public Ticket(Date dateOfShow, Play play, Customer owner, Double priceMultiplier, String typeOfTicket, String extraMessage, CreditCard creditCard) throws Exception
	{
		super();
		setDateOfShow(dateOfShow);
		setPlay(play);
		setOwner(owner);
		setPriceMultiplier(priceMultiplier);
		this.serialNumber = new SerialNumber();
		this.typeOfTicket = typeOfTicket;
		setExtraMessage(extraMessage);
		TicketIsValidAssertions();
		this.creditCard = creditCard;
		
	}

	public Ticket(Date dateOfShow, Play play, Customer owner, Double priceMultiplier, String typeOfTicket, CreditCard creditCard) throws Exception
	{
		super();
		setDateOfShow(dateOfShow);
		setPlay(play);
		setOwner(owner);
		setPriceMultiplier(priceMultiplier);
		this.serialNumber = new SerialNumber();
		this.typeOfTicket = typeOfTicket;
		setExtraMessage(null);
		TicketIsValidAssertions();
		this.creditCard = creditCard;
		
	}
	

	/**
	 * gets the date of show
	 * 
	 * @return the dateOfShow
	 */
	public Date getDateOfShow()
	{
		return dateOfShow;
	}

	/**
	 * Gets the extra message
	 * 
	 * @return the extraMessage
	 */
	public String getExtraMessage()
	{
		return extraMessage;
	}

	@Override
	public Long getKey()
	{

		return serialNumber.getKeyValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ownership.Owned#getOwner()
	 */
	@Override
	public Customer getOwner()
	{

		return owner;
	}

	/**
	 * Gets the play
	 * 
	 * @return the play
	 */
	public Play getPlay()
	{
		return play;
	}

	/**
	 * gets the price multiplier
	 * 
	 * @return the priceMultiplier
	 */
	public double getPriceMultiplier()
	{
		return priceMultiplier;
	}

	/**
	 * Gets the price of the ticket
	 * 
	 * @return the priceOfTicket
	 */
	public Dollar getPriceOfTicket() 
	{
		// I set it up so that if the price is blank, it will calculate and set
		// the current price to the initial price of that type of ticket, but if
		// the price is already set it returns that amount. I did this to allow
		// for the possibility of additional price adjustments

		if (priceOfTicket == null)
		{
			setPriceOfTicket(new Dollar(
					play.getRegularTicketPrice().getAmount() * priceMultiplier));
		}
		return priceOfTicket;
	}

	/**
	 * gets the type of ticket
	 * 
	 * @return the typeOfTicket
	 */
	public String getTypeOfTicket()
	{
		return typeOfTicket;
	}

	/**
	 * Checks to see if the key value in the parameter is a match for this
	 * item's key
	 * 
	 * @param key
	 *            the key value to be checked against
	 * @return true if the two match or false if they don't.
	 * @see keyToken.KeyToken#matches(keyToken.KeyToken)
	 */
	public boolean matches(KeyToken<Ticket, Long> key)
	{
		return serialNumber.matches(key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see keyToken.Keyed#matches(java.lang.Object)
	 */
	@Override
	public boolean matches(Long keyValue)
	{
		return serialNumber.matches(keyValue);
	}

	/**
	 * sets the date of the show
	 * 
	 * @param dateOfShow
	 *            the dateOfShow to set
	 * @throws DateOutOfBoundsException if the date of the show is out of bounds of the play's showing period.
	 */
	public void setDateOfShow(Date dateOfShow) throws DateOutOfBoundsException
	{
		if(play == null)
		{
			// the play has not been set yet.
			this.dateOfShow = dateOfShow;
		}
		else if(dateIsValid(dateOfShow))
		{
			this.dateOfShow = dateOfShow;
		}
		else
		{
			throw new DateOutOfBoundsException(dateOfShow);
		}
		
	}

	/**
	 * Sets the extra message
	 * 
	 * @param extraMessage
	 *            the extraMessage to set
	 */
	public void setExtraMessage(String extraMessage)
	{
		if(extraMessage != null && !extraMessage.isEmpty() )
		{
			hasExtraMessage = true;
		}
		else
		{
			hasExtraMessage = false;
		}
		this.extraMessage = extraMessage;
	}

	/**
	 * Sets the key to a provided key. used in loading from storage.
	 * 
	 * @param keyToken the keyToken that should be used as this key token
	 * @see keyToken.KeyToken#setKey(keyToken.KeyToken)
	 */
	public void setKey(KeyToken<Ticket, Long> keyToken)
	{
		serialNumber.setKey(keyToken);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see keyToken.Keyed#setKey(java.lang.Object)
	 */
	@Override
	public void setKey(Long keyValue)
	{
		serialNumber.setValue(keyValue);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ownership.Owned#setOwner(java.lang.Object)
	 */
	@Override
	public void setOwner(Customer owner)
	{
		this.owner = owner;
	}

	/**
	 * Sets the play.
	 * 
	 * @param play
	 *            the play to set
	 * @throws DateOutOfBoundsException if the ticket is for a day outside the bounds of the play's showing period.
	 */
	public void setPlay(Play play) throws DateOutOfBoundsException
	{
	
		if(dateOfShow == null)
		{
			// the dateOfShow has not been set yet.
			this.play = play;
		}
		else if(dateIsValid(play))
		{
			this.play = play;
		}
		else
		{
			throw new DateOutOfBoundsException(play);
		}
	}
	
	/**
	 * Checks to see if the date of the show is valid for the play. 
	 * @return true if date of show is in the range (playStart, playEnd], otherwise returns false
	 */
	private boolean dateIsValid(Date dateOfShow)
	{
		boolean result;
		Date playStart = play.getStartDate();
		Date playEnd = play.getEndDate();
		if(dateOfShow.after(playStart) && (dateOfShow.before(playEnd)) || dateOfShow.equals(playEnd)) 
		{
			result = true;
		}
		else
		{
			result = false;
		}
		return result;
	}
	

	/**
	 * Checks to see if the date of the show is valid for the play. 
	 * @return true if date of show is in the range (playStart, playEnd], otherwise returns false
	 */
	private boolean dateIsValid()
	{
		boolean result;
		Date playStart = play.getStartDate();
		Date playEnd = play.getEndDate();
		if(dateOfShow.after(playStart) && (dateOfShow.before(playEnd)) || dateOfShow.equals(playEnd)) 
		{
			result = true;
		}
		else
		{
			result = false;
		}
		return result;
	}
	
	
	private boolean dateIsValid(Play play)
	{
		boolean result;
		Date playStart = play.getStartDate();
		Date playEnd = play.getEndDate();
		if(dateOfShow.after(playStart) && (dateOfShow.before(playEnd)) || dateOfShow.equals(playEnd)) 
		{
			result = true;
		}
		else
		{
			result = false;
		}
		return result;
	}
	
	private void dateIsValidAssertion() throws DateIsValidAssertionException
	{
		try
		{
			if(!dateIsValid())
			{
				throw new DateIsValidAssertionException();
			}
		}
		catch (Exception e)
		{
			throw new DateIsValidAssertionException();
		}
	}
	
	private void TicketIsValidAssertions() throws Exception
	{
		dateIsValidAssertion();
	}

	/**
	 * Sets the price multiplier
	 * 
	 * @param priceMultiplier
	 *            the priceMultiplier to set
	 */
	public void setPriceMultiplier(double priceMultiplier)
	{
		this.priceMultiplier = priceMultiplier;
	}

	/**
	 * Sets the price of the ticket
	 * 
	 * @param priceOfTicket
	 *            the priceOfTicket to set
	 */
	public void setPriceOfTicket(Dollar priceOfTicket)
	{
		this.priceOfTicket = priceOfTicket;
	}

	/**
	 * @return the serialNumber
	 */
	public SerialNumber getSerialNumber()
	{
		return serialNumber;
	}

	/**
	 * @param serialNumber the serialNumber to set
	 */
	public void setSerialNumber(SerialNumber serialNumber)
	{
		this.serialNumber = serialNumber;
	}

	/**
	 * @return the hasExtraMessage
	 */
	public boolean isHasExtraMessage()
	{
		return hasExtraMessage;
	}

	/**
	 * @param hasExtraMessage the hasExtraMessage to set
	 */
	public void setHasExtraMessage(boolean hasExtraMessage)
	{
		this.hasExtraMessage = hasExtraMessage;
	}

	/**
	 * @return the creditCard
	 */
	public CreditCard getCreditCard()
	{
		return creditCard;
	}

	/**
	 * @param creditCard the creditCard to set
	 */
	public void setCreditCard(CreditCard creditCard)
	{
		this.creditCard = creditCard;
	}

	

}
