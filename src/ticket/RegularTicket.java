package ticket;

import java.util.Date;
import customer.Customer;
import customer.Customer.CreditCard;
import play.Play;

/**
 * A normal ticket to a play for a customer who buys it that on the day of the show.
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 */
public class RegularTicket extends Ticket
{

	/**
	 * Serial Version
	 */
	private static final long serialVersionUID = 1L;

	public RegularTicket(Date dateOfShow, Play play, Customer owner, CreditCard creditCard) throws Exception
	{
		super(dateOfShow, play, owner, 1.0, "regular ticket", creditCard);	
	}
}