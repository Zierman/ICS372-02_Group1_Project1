package uiCommands;

import client.Client;
import currency.Dollar;
import theater.Theater;
import userInterface.UI;

public class PayClient implements Command<UI>
{
	private static PayClient singleton;

	/**
	 * Gets or creates an instance of the singleton
	 * 
	 * @return an instance of the singleton
	 */
	public static PayClient instance()
	{
		if (singleton == null)
		{
			singleton = new PayClient(1);
		}
		return singleton;
	}

	private final String LABEL = "Pay Client.";

	private final boolean IS_DATA_COMMAND = true;

	private final boolean IS_TERMINATION_COMMAND = false;

	/**
	 * 
	 * Constructs a <code>SellRegularTicket</code> object used when creating a
	 * subtype singleton
	 * 
	 * @throws Exception
	 *             if used to try to create a base type
	 */
	protected PayClient() throws Exception
	{
		if (getClass().getName().equals("SellRegularTicket"))
		{
			throw new Exception();
		}
	}

	/**
	 * Constructs the <code>SellRegularTicket</code> object used to create the
	 * singleton.
	 * 
	 * @param i
	 *            an integer with no significance other than giving it a
	 *            different signature than the protected constructor.
	 */
	private PayClient(int i)
	{
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see uiCommands.Command#call(java.lang.Object)
	 */
	@Override
	public void call(UI ui)
	{
		Theater theater = ui.getTheater();
		boolean done = false;
		while (!done)
		{
			try
			{
				// find appropriate client to pay
				Client client = UI.getClientFromInputID();
				// output the Client's current balance
				UI.println("Client's current balance: "
						+ theater.getClientBalanceDue(client));
				// get amount to pay client and verify its validity
				Dollar dollars = UI
						.getDollarFromInput("Enter amount to pay client");
				// pay the client the appropriate amount
				theater.pay(client, dollars);

				// output to user that the Customer was added
				UI.outputSuccessMessage("Client has been paid\n");

				// ask the user if they would like to add another Customer

				/*
				 * From Joshua:
				 * 
				 * I changed from "done =
				 * UI.getInput("Pay another client? (Y/N)").toLowerCase().
				 * startsWith("n");" because the noCheck and yesCheck methods
				 * will force the user to input a string starting with a 'N',
				 * 'n', 'Y', or 'y'.
				 * 
				 */
				done = UI.noCheck("Pay another client?");
			}
			catch (Exception e)
			{
				// output error message to user
				UI.outputError(e, "Unable to pay client.");

				// ask user if they would like to try again
				done = !UI.tryAgainCheck();
			}
		}
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
	 * @see uiCommands.Command#isTerminateionCommand()
	 */
	@Override
	public boolean isTerminationCommand()
	{
		return IS_TERMINATION_COMMAND;
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

}
