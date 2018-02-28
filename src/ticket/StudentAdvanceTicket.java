package ticket;

import java.util.Date;

import customer.Customer;
import play.Play;

public class StudentAdvanceTicket extends Ticket
{
	/**
	 * Serial Version
	 */
	private static final long serialVersionUID = 1L;

	public StudentAdvanceTicket(Date dateOfShow, Play play, Customer owner)
	{
		super(dateOfShow, play, owner);

		
		typeOfTicket = "regular ticket";
		
		priceMultiplier = 1.0;
		
		priceOfTicket = getPriceOfTicket();
		

		extraMessage = "Must show valid student id.";
		
	}
}
