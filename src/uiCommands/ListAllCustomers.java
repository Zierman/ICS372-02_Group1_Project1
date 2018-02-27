package uiCommands;

import java.util.LinkedList;

import customer.Customer;
import customer.Customer.CreditCard;
import customer.CustomerList;
import theater.Theater;
import userInterface.UI;

/**
 * The command to list all customers.
 * 
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 */
public class ListAllCustomers implements Command<UI>
{
	private static ListAllCustomers singleton;
	private final String LABEL = "Show a list of all customers.";
	private final boolean IS_DATA_COMMAND = true;
	private final boolean IS_TERMINATION_COMMAND = false;

	/**
	 * 
	 * Constructs a <code>ListAllCustomers</code> object used when creating a
	 * subtype singleton
	 * 
	 * @throws Exception
	 *             if used to try to create a base type
	 */
	protected ListAllCustomers() throws Exception
	{
		if (getClass().getName().equals("ListAllCustomers"))
		{
			throw new Exception();
		}
	}

	/**
	 * Constructs the <code>ListAllCustomers</code> object used to create the
	 * singleton.
	 * 
	 * @param i
	 *            an integer with no significance other than giving it a
	 *            different signature than the protected constructor.
	 */
	private ListAllCustomers(int i)
	{
	}

	/**
	 * Gets or creates an instance of the singleton
	 * 
	 * @return an instance of the singleton
	 */
	public static ListAllCustomers instance()
	{
		if (singleton == null)
		{
			singleton = new ListAllCustomers(1);
		}
		return singleton;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see userInterface.Command#getLabel()
	 */
	@Override
	public String getLabel()
	{
		return instance().LABEL;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see userInterface.Command#isDataCommand()
	 */
	@Override
	public boolean isDataCommand()
	{
		return instance().IS_DATA_COMMAND;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see singleton.Singleton#readResolve()
	 */
	@Override
	public Command<UI> readResolve()
	{
		return instance();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see uiCommands.Command#isTerminationCommand()
	 */
	@Override
	public boolean isTerminationCommand()
	{
		return IS_TERMINATION_COMMAND;
	}

	/* (non-Javadoc)
	 * @see uiCommands.Command#call(java.lang.Object)
	 * @author Troy Novak [wh1617wd@metrostate.edu]
	 */
	@Override
	public void call(UI ui)
	{
		boolean done = false;
		while(!done){
			try{
				boolean found = false;
				Theater theater = ui.getTheater();
				CustomerList customerList = theater.getCustomerList();
				String output = "";
				for(Customer customer : customerList){
					found = true;
					output += "ID: " + customer.getID() + ",\nName: "
							+ customer.getName() + ",\nAddress: " 
							+ customer.getAddress() + ",\nPhone Number: "
							+ customer.getPhoneNumber() + "\n\n";
					LinkedList<CreditCard> cardList = customer.getCardList();
					for(CreditCard creditCard : cardList){
						output += "Card Number: " + creditCard.getCardNumber()
								+ ",\nCard Expiration: " + creditCard.getCardExpiration()
								+ "\n\n";
					}
				}
				if(!found){
					UI.println("No customers found.");
				}
				else{
					UI.println(output);
				}
				done = true;
			}
			catch(Exception e){
				// output error message to user
				UI.outputError(e, "Unable to list all customers.");
				
				// ask user if they would like to try again
				done = !UI.tryAgainCheck();
			}
		}

	}

}