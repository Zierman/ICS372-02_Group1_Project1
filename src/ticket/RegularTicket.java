package ticket;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;

import currency.Dollar;
import customer.Customer;
import play.Play;
import storage.FileIO;
import ticket.Ticket.SerialNumber;

public class RegularTicket extends Ticket
{

	public RegularTicket(Date dateOfShow, Play play, Customer owner)
	{
		super(dateOfShow, play, owner);

		
		typeOfTicket = "regular ticket";
		
		priceMultiplier = 1.0;
		
		priceOfTicket = getPriceOfTicket();
		
		
	}
}