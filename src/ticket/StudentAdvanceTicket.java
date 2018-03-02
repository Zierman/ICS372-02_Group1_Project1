package ticket;

import java.util.Date;

import customer.Customer;
import play.Play;

/**
 * A discounted ticket to a play for a customer who buys it in advance and can show a student id when claiming the ticket.
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 */
public class StudentAdvanceTicket extends Ticket
{
	/**
	 * Serial Version
	 */
	private static final long serialVersionUID = 1L;

	public StudentAdvanceTicket(Date dateOfShow, Play play, Customer owner) throws Exception
	{
		super(dateOfShow, play, owner, 0.5, "student advance ticket", "Must show valid student id.");
	}
}
