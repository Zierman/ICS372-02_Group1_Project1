package ticket;

import java.util.Date;

import customer.Customer;
import customer.Customer.CreditCard;
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

	/** Creates a Student Advance Ticket
	 * 
	 * @param dateOfShow the Date that the showing is on
	 * @param play the play the ticket is to see
	 * @param owner the customer who the ticket is being sold to
	 * @param creditCard the credit card used to purchase the ticket
	 * @throws Exception If the ticket could not be created
	 */
	public StudentAdvanceTicket(Date dateOfShow, Play play, Customer owner, CreditCard creditCard) throws Exception
	{
		super(dateOfShow, play, owner, 0.5, "student advance ticket", "Must show valid student id.", creditCard);
	}
}
