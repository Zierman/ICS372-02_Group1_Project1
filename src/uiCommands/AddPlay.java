/**
 * 
 */
package uiCommands;

import client.Client;
import theater.Theater;
import userInterface.UI;

/**
 * @author Joshua Zierman [py1422xs@metrostate.edu]
 *
 */
public class AddPlay implements Command<UI>
{
	private static AddPlay singleton;
	private final String LABEL = "Add a new play";
	private final boolean IS_DATA_COMMAND = true;
	private final boolean IS_TERMINATION_COMMAND = false;

	protected AddPlay() throws Exception
	{
		if (getClass().getName().equals("AddPlay"))
		{
			throw new Exception();
		}
	}

	private AddPlay(int i)
	{
	}

	public static AddPlay instance()
	{
		if (singleton == null)
		{
			singleton = new AddPlay(1);
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

	@Override
	public void call(UI ui)
	{
		Theater theater = ui.getTheater();
		boolean done = false;
		while (!done)
		{
			try
			{
				String clientName = UI.getInput("Enter Client's Name: ");
				String clientAddress = UI.getInput("Enter Client's Address: ");
				String clientPhoneNumber = UI.getInput("Enter Client's Phone Number: ");
				theater.getClientList().add(new Client(clientName,clientAddress,clientPhoneNumber));
				UI.outputSuccessMessage(clientName + " added to client list.");
				
				// the loop is done if the user answers no
				done = UI.getInput("Add another client? (Y/N)").toLowerCase().startsWith("n");
			}
			catch (Exception e)
			{
				UI.outputError(e, "Unable to add client");
				
				// the loop is done if the user answers no
				done = UI.getInput("Try again? (Y/N)").toLowerCase().startsWith("n");
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
