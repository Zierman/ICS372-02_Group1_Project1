package visitor;

import java.awt.Button;
import java.util.LinkedList;

import client.Client;
import customer.Customer;
import customer.Customer.CreditCard;
import play.Play;
import theater.Theater;
import ticket.Ticket;
import userInterface.UI;

/**
 * A Visitor used for Standard Formatted output of various objects that need to
 * be displayed in the "ListAll___" commands
 * 
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 */
public class StandardFormat implements Visitor
{
	private String str = "";

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return str;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(client.Client)
	 */
	@Override
	public void visit(Client client)
	{
		str = "Client id: " + client.getID() + ",\n" + "name: "
				+ client.getName() + ",\n" + "  balance due: "
				+ client.getBalanceDue() + ",\n" + "  address: "
				+ client.getAddress() + ",\n" + "  phone number: "
				+ client.getPhoneNumber() + "\n" + "\n";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(customer.Customer.CreditCard)
	 */
	@Override
	public void visit(CreditCard creditCard)
	{
		str = "Card Number: " + creditCard.getCardNumber()
				+ "\n  Card Expiration: " + creditCard.getCardExpiration()
				+ "\n\n";

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(customer.Customer)
	 */
	@Override
	public void visit(Customer customer)
	{
		str = "ID: " + customer.getID() + "\n  Name: " + customer.getName()
				+ "\n  Address: " + customer.getAddress() + "\n  Phone Number: "
				+ customer.getPhoneNumber() + "\n\n";
		LinkedList<CreditCard> cardList = customer.getCardList();
		for (CreditCard creditCard : cardList)
		{
			str += "  Card Number: " + creditCard.getCardNumber()
					+ "\n    Card Expiration: " + creditCard.getCardExpiration()
					+ "\n\n";
		}
		for (Ticket ticket : Theater.instance().getTicketList())
		{
			if (ticket.getOwner().matches(customer.getKey()))
			{
				str += "  Ticket Serial Number: " + ticket.getSerialNumber();
				str += "\n    Ticket Type: " + ticket.getTypeOfTicket();
				str += "\n    Play the ticket is for: "
						+ ticket.getPlay().getName();
				str += "\n    Date of showing: "
						+ UI.format(ticket.getDateOfShow());
				str += "\n    Credit card used to buy: "
						+ ticket.getCreditCard();
				str += "\n\n";
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(play.Play)
	 */
	@Override
	public void visit(Play play)
	{
		str = "Play name: " + play.getName() + ",\n";
		str += "  start date: " + UI.format(play.getStartDate()) + ",\n";
		str += "  end date: " + UI.format(play.getEndDate()) + ",\n";
		str += "  client : " + play.getOwner().getName() + " ("
				+ play.getOwner().getID() + ")\n";
		str += "  regular ticket price: " + play.getRegularTicketPrice() + "\n";
		str += "  seating capacity: " + play.getSeatingCapacity() + "\n";
		str += "\n";

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see visitor.Visitor#visit(ticket.Ticket)
	 */
	@Override
	public void visit(Ticket ticket)
	{
		
		str = "Ticket Serial Number: " + ticket.getSerialNumber();
		str += "\n  Ticket Type: " + ticket.getTypeOfTicket();
		str += "\n  Play the ticket is for: " + ticket.getPlay().getName();
		str += "\n  Date of showing: " + UI.format(ticket.getDateOfShow());
		str += "\n  Credit card used to buy: " + ticket.getCreditCard();
		if(ticket.hasExtraMessage())
		{
			str += "\n  Extra Message: " + ticket.getExtraMessage();
		}
		str += "\n\n";

	}

}
