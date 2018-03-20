package ticket;

import java.util.Date;

import customer.Customer;
import customer.Customer.CreditCard;
import play.Play;

/**
 * A Factory for creating ticket objects
 * 
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 */
public abstract class TicketFactory
{
	public static final int REGULAR_TICKET = 0;
	public static final int ADVANCE_TICKET = 1;
	public static final int STUDNET_ADVANCE_TICKET = 2;

	/**
	 * Creates the appropriate type of ticket
	 * 
	 * @param objectType
	 *            an integer value linked to a type of ticket. Can be the
	 *            Factory's public static final integers REGULAR_TICKET,
	 *            ADVANCE_TICKET, STUDNET_ADVANCE_TICKET
	 * 
	 * @param dateOfShow
	 *            the Date that the showing is on
	 * @param play
	 *            the play the ticket is to see
	 * @param owner
	 *            the customer who the ticket is being sold to
	 * @param creditCard
	 *            the credit card used to purchase the ticket
	 * @return The created Ticket object.
	 * @throws Exception
	 *             If the ticket could not be created
	 */
	public static Ticket make(int objectType, Date dateOfShow, Play play,
			Customer owner, CreditCard creditCard) throws Exception
	{
		Ticket ticket = null;

		switch (objectType)
		{
		case REGULAR_TICKET:
			ticket = new RegularTicket(dateOfShow, play, owner, creditCard);
			break;
		case ADVANCE_TICKET:
			ticket = new AdvanceTicket(dateOfShow, play, owner, creditCard);
			break;
		case STUDNET_ADVANCE_TICKET:
			ticket = new StudentAdvanceTicket(dateOfShow, play, owner,
					creditCard);
			break;
		}
		return ticket;
	}
}
