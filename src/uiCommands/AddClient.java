/**
 * 
 */
package uiCommands;

import client.Client;
import client.ClientList;
import theater.Theater;
import userInterface.UI;

/**
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 */
public class AddClient implements Command<Theater>
{
	private static AddClient singleton;
	private final String LABEL = "Add a new client";
	private final boolean IS_DATA_COMMAND = true;

	protected AddClient() throws Exception
	{
		if (getClass().getName().equals("AddClient"))
		{
			throw new Exception();
		}
	}

	private AddClient(int i)
	{
	}

	public AddClient instance()
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
	public Command<Theater> readResolve()
	{
		return instance();
	}

	@Override
	public void call(Theater theater)
	{
		boolean done = false;
		while (!done)
		{
			try
			{
				String clientName = UI.getInput("Enter Client's Name: ");
				String clientAddress = UI.getInput("Enter Client's Address: ");
				String clientPhoneNumber = UI.getInput("Enter Client's Phone Number: ");
				theater.getClientList().add(new Client(clientName,clientAddress,clientPhoneNumber));
				
				// the loop is done if the user answers no
				done = UI.getInput("Add another client? (Y/N)").toLowerCase().startsWith("n");
			}
			catch (Exception e)
			{
				UI.OutputError(e, "Unable to add client");
				
				// the loop is done if the user answers no
				done = UI.getInput("Try again? (Y/N)").toLowerCase().startsWith("n");
			}
		}
	}

}
