package uiCommands;

import customer.Customer;
import theater.Theater;
import userInterface.UI;

/**
 * The command to remove a credit card.
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 */
public class RemoveCreditCard implements Command<UI>
{
	private static RemoveCreditCard singleton;
	private final String LABEL = "Remvoe a credit card.";
	private final boolean IS_DATA_COMMAND = true;
	private final boolean IS_TERMINATION_COMMAND = false;

	/**
	 * 
	 * Constructs a <code>RemoveCreditCard</code> object used when creating a subtype singleton
	 * 
	 * @throws Exception
	 *             if used to try to create a base type
	 */
	protected RemoveCreditCard() throws Exception
	{
		if (getClass().getName().equals("RemoveCreditCard"))
		{
			throw new Exception();
		}
	}

	/**
	 * Constructs the <code>RemoveCreditCard</code> object used to create the singleton.
	 * 
	 * @param i
	 *            an integer with no significance other than giving it a
	 *            different signature than the protected constructor.
	 */
	private RemoveCreditCard(int i)
	{
	}

	/**
	 * Gets or creates an instance of the singleton
	 * @return an instance of the singleton
	 */
	public static RemoveCreditCard instance()
	{
		if (singleton == null)
		{
			singleton = new RemoveCreditCard(1);
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

	/* (non-Javadoc)
	 * @see uiCommands.Command#isTerminateionCommand()
	 */
	@Override
	public boolean isTerminationCommand()
	{
		return IS_TERMINATION_COMMAND;
	}


	/* (non-Javadoc)
	 * @see uiCommands.Command#call(java.lang.Object)
	 */
	@Override
	public void call(UI ui)
	{
		Theater theater = ui.getTheater();
		boolean done = false;
		while(!done){
			try{
				// get Customer ID for customer for which to add a CreditCard object
				Long customerID = Long.parseLong(UI.getInput("Enter Customer ID: "));
				// get other necessary information to add a CreditCard object to the list
				String cardNumber = UI.getInput("Enter Credit Card Number: ");
				
				// find customer using target customerID
				Customer customer = theater.getCustomerList().findMatched(customerID);
				
				// remove card from Customer's list
				theater.removeCreditCard(customer, cardNumber);
				
				// output to user that CreditCard was removed from the list
				UI.outputSuccessMessage(cardNumber + " was removed from Customer " + customerID
										+ "'s list of credit cards.");
				
				// ask the user if they would like to remove another CreditCard
				done = !UI.yesCheck("Remove another credit card?");
			}
			catch(Exception e){
				// output error message to user
				UI.outputError(e, "Unable to remove Credit Card.");
				
				// ask user if they would like to try again
				done = !UI.tryAgainCheck();
			}
		}
		
	}

}
