package ticket;

import java.util.Date;

import customer.Customer;
import customer.Customer.CreditCard;
import play.Play;

public abstract class TicketFactory
{
	public static final int REGULAR_TICKET = 0;
	public static final int ADVANCE_TICKET = 1;
	public static final int STUDNET_ADVANCE_TICKET = 2;
	
	public static Ticket make(int objectType, Date dateOfShow, Play play, Customer owner, CreditCard creditCard) throws Exception
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
			ticket = new StudentAdvanceTicket(dateOfShow, play, owner, creditCard);
			break;
		}
		return ticket;
	}
}
