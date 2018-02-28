package ticket;

import java.util.Date;

import customer.Customer;
import play.Play;

public class AdvanceTicket extends Ticket
{
	public AdvanceTicket(Date dateOfShow, Play play, Customer owner)
	{
		super(dateOfShow, play, owner);

		
		typeOfTicket = "regular ticket";
		
		priceMultiplier = 0.7;
		
		priceOfTicket = getPriceOfTicket();
		
		
	}
}
