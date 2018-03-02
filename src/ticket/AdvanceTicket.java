package ticket;

import java.util.Date;

import customer.Customer;
import play.Play;

/**
 * A discounted ticket to a play for a customer who buys it in advance.
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 */
public class AdvanceTicket extends Ticket
{
	/**
	 * Serial Version
	 */
	private static final long serialVersionUID = 1L;

	public AdvanceTicket(Date dateOfShow, Play play, Customer owner) throws Exception
	{
		super(dateOfShow, play, owner, 0.7, "advance ticket");
	}
}
