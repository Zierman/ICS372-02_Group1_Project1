package ticket;

import java.util.Date;
import customer.Customer;
import customer.Customer.CreditCard;
import play.Play;

/**
 * A normal ticket to a play for a customer who buys it that on the day of the
 * show.
 * 
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 */
public class RegularTicket extends Ticket
{

	/**
	 * Serial Version
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Creates a Regular Ticket
	 * 
	 * @param dateOfShow
	 *            the Date that the showing is on
	 * @param play
	 *            the play the ticket is to see
	 * @param owner
	 *            the customer who the ticket is being sold to
	 * @param creditCard
	 *            the credit card used to purchase the ticket
	 * @throws Exception
	 *             If the ticket could not be created
	 */
	public RegularTicket(Date dateOfShow, Play play, Customer owner,
			CreditCard creditCard) throws Exception
	{
		super(dateOfShow, play, owner, 1.0, "regular ticket", creditCard);
	}
}