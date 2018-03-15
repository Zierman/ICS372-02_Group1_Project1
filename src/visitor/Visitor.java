package visitor;

import client.Client;
import customer.Customer;
import customer.Customer.CreditCard;
import play.Play;
import ticket.Ticket;

/**
 * an interface to create a visitor object
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 */
public interface Visitor
{
	
	/**
	 * Visits a Client
	 * @param client the client to be visited
	 */
	public void visit(Client client);
	
	/**
	 * Visits a Customer
	 * @param customer the customer to be visited
	 */
	public void visit(Customer customer);
	
	/**
	 * Visits a Play
	 * @param play the play to be visited
	 */
	public void visit(Play play);
	
	/**
	 * Visits a Credit Card
	 * @param creditCard the credit card to be visited
	 */
	public void visit(CreditCard creditCard);
	
	/**
	 * Visits a Ticket
	 * @param ticket the ticket to be visited
	 */
	public void visit(Ticket ticket);
}
