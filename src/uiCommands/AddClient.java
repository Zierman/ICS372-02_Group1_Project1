/**
 * 
 */
package uiCommands;

import client.Client;
import theater.Theater;
import userInterface.UI;

/**
 * the command to add a new client
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 */
public class AddClient implements Command<UI>
{
	private static AddClient singleton;
	private final String LABEL = "Add a new client";
	private final boolean IS_DATA_COMMAND = true;
	private final boolean IS_TERMINATION_COMMAND = false;

	/**
	 * 
	 * Constructs a <code>AddClient</code> object used when creating a subtype singleton
	 * 
	 * @throws Exception
	 *             if used to try to create a base type
	 */
	protected AddClient() throws Exception
	{
		if (getClass().getName().equals("AddClient"))
		{
			throw new Exception();
		}
	}

	/**
	 * Constructs the <code>AddClient</code> object used to create the singleton.
	 * 
	 * @param i
	 *            an integer with no significance other than giving it a
	 *            different signature than the protected constructor.
	 */
	private AddClient(int i)
	{
	}

	/**
	 * Gets or creates an instance of the singleton
	 * @return an instance of the singleton
	 */
	public static AddClient instance()
	{
		if (singleton == null)
		{
			singleton = new AddClient(1);
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
				// get input needed to create a new client object
				String clientName = UI.getInput("Enter Client's Name: ");
				String clientAddress = UI.getInput("Enter Client's Address: ");
				String clientPhoneNumber = UI.getInput("Enter Client's Phone Number: ");
				
				// create new client object
				Client client = new Client(clientName,clientAddress,clientPhoneNumber);
				
				// add to list
				theater.add(client);
				
				// show user that it was added
				UI.outputSuccessMessage(clientName + " added to client list.");
				
				// ask if user wants to continue and end if the user answers no
				done = UI.getInput("Add another client? (Y/N)").toLowerCase().startsWith("n");
			}
			catch (Exception e)
			{
				// show error message
				UI.outputError(e, "Unable to add client");
				
				// ask if user wants to continue and end if the user answers no
				done = !UI.tryAgainCheck();
			}
		}
	}

	/* (non-Javadoc)
	 * @see uiCommands.Command#isTerminationCommand()
	 */
	@Override
	public boolean isTerminationCommand()
	{
		return IS_TERMINATION_COMMAND;
	}

}
