package ticket;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;

import customer.Customer;
import play.Play;
import storage.FileIO;

public class RagularTicket extends Ticket
{

	public RagularTicket(Date dateOfShow, Play play, Customer owner)
	{
		super(dateOfShow, play, owner);
		
	}
}