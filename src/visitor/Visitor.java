package visitor;

import client.Client;
import customer.Customer;
import customer.Customer.CreditCard;
import play.Play;
import ticket.Ticket;

public interface Visitor
{
	public void visit(Client client);
	public void visit(Customer customer);
	public void visit(Play play);
	public void visit(CreditCard card);
	public void visit(Ticket ticket);
}
